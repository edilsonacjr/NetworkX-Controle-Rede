/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Aluno;
import entidades.Curso;
import entidades.Materia;
import entidades.MateriaNotaFalta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class AlunoDao {

    private Connection conexao;

    public AlunoDao() throws ClassNotFoundException, SQLException {
        conexao = Conexao.getConexao();
    }

    public void insere(Aluno a) throws SQLException {
        String sql = "insert into aluno values (null, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, a.getCurso().getId());
        stmt.setDate(2, new Date(a.getDataAdmissao().getTime()));
        stmt.setString(3, a.getNome());
        stmt.setString(4, a.getCpf());
        stmt.setDate(5, new Date(a.getDataNascimento().getTime()));
        stmt.setString(6, a.getLogin());
        stmt.setString(7, a.getSenha());
        stmt.setString(8, a.getEmail());
        stmt.execute();
        stmt.close();
    }

    public void atualiza(Aluno a) throws SQLException {
        String sql = "update aluno set idcurso = ?, dataadmissao = ?"
                + ", nome = ?, cpf = ?, datanascimento = ?, login = ?, "
                + "senha = ?, email = ? where idaluno = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, a.getCurso().getId());
        stmt.setDate(2, new Date(a.getDataAdmissao().getTime()));
        stmt.setString(3, a.getNome());
        stmt.setString(4, a.getCpf());
        stmt.setDate(5, new Date(a.getDataNascimento().getTime()));
        stmt.setString(6, a.getLogin());
        stmt.setString(7, a.getSenha());
        stmt.setString(8, a.getEmail());
        stmt.setInt(9, a.getId());
        stmt.execute();
        stmt.close();
    }

    public void exclui(Aluno a) throws SQLException {
        String sql = "delete from aluno where idaluno = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, a.getId());
        stmt.execute();
        stmt.close();
    }

    public List<Aluno> listar() throws SQLException {
        List<Aluno> list = new ArrayList<>();
        Aluno a = null;
        String sql = "select * from aluno a "
                + "inner join curso c on "
                + "(a.idcurso = c.idcurso)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            a = new Aluno();
            a.setId(rs.getInt("a.idaluno"));
            a.getCurso().setId(rs.getInt("c.idcurso"));
            a.getCurso().setNome(rs.getString("c.nome"));
            a.getCurso().setCategoria(rs.getString("c.categoria"));
            a.getCurso().getCordenador().setId(rs.getInt("c.idprofessor"));
            a.setDataAdmissao(rs.getDate("a.dataadmissao"));
            a.setNome(rs.getString("a.nome"));
            a.setCpf(rs.getString("a.cpf"));
            a.setDataNascimento(rs.getDate("a.datanascimento"));
            a.setLogin(rs.getString("a.login"));
            a.setSenha(rs.getString("a.senha"));
            a.setEmail(rs.getString("a.email"));
            list.add(a);
        }
        stmt.close();
        rs.close();
        return list;
    }

    public Aluno getAluno(Aluno aluno) throws SQLException {
        String sql = "select * from aluno a where idaluno = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, aluno.getId());
        Aluno a = null;
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            a = new Aluno();
            a.setId(rs.getInt("a.idaluno"));
            a.getCurso().setId(rs.getInt("a.idcurso"));
            a.setDataAdmissao(rs.getDate("a.dataadmissao"));
            a.setNome(rs.getString("a.nome"));
            a.setCpf(rs.getString("a.cpf"));
            a.setDataNascimento(rs.getDate("a.datanascimento"));
            a.setLogin(rs.getString("a.login"));
            a.setSenha(rs.getString("a.senha"));
            a.setEmail(rs.getString("a.email"));
        }
        stmt.close();
        rs.close();
        return a;
    }

    public Aluno validaAluno(Aluno aluno) throws SQLException {
        String sql = "select * from aluno a "
                + "left join curso c on (a.idcurso = c.idcurso) "
                + "where login = ? and senha = ? ";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, aluno.getLogin());
        stmt.setString(2, aluno.getSenha());
        Aluno a = null;
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            a = new Aluno();
            a.setId(rs.getInt("a.idaluno"));
            a.getCurso().setId(rs.getInt("a.idcurso"));
            a.setDataAdmissao(rs.getDate("a.dataadmissao"));
            a.setNome(rs.getString("a.nome"));
            a.setCpf(rs.getString("a.cpf"));
            a.setDataNascimento(rs.getDate("a.datanascimento"));
            a.setLogin(rs.getString("a.login"));
            a.setSenha(rs.getString("a.senha"));
            a.setEmail(rs.getString("a.email"));
        }
        return a;
    }

    public List<MateriaNotaFalta> getMateriaNotaFalta(Aluno a) throws SQLException {
        String sql = "select *, (select count(idfalta) from falta f where "
                + "     am.idalunomateria = f.idalunomateria and "
                + "     am.idmateria = m.idmateria) as 'falta' \n"
                + "from alunomateria am \n"
                + "     inner join materia m on (am.idmateria = m.idmateria)\n"
                + "     inner join nota n on (am.idalunomateria = n.idalunomateria)\n"
                + "     inner join aluno a on (am.idaluno = a.idaluno)\n"
                + " where a.idaluno = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, a.getId());
        MateriaNotaFalta mnf = null;
        List<MateriaNotaFalta> list = new ArrayList();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            mnf = new MateriaNotaFalta();
            mnf.getAluno().setId(rs.getInt("a.idaluno"));
            mnf.getAluno().getCurso().setId(rs.getInt("a.idcurso"));
            mnf.getAluno().setDataAdmissao(rs.getDate("a.dataadmissao"));
            mnf.getAluno().setNome(rs.getString("a.nome"));
            mnf.getAluno().setCpf(rs.getString("a.cpf"));
            mnf.getAluno().setDataNascimento(rs.getDate("a.datanascimento"));
            mnf.getAluno().setLogin(rs.getString("a.login"));
            mnf.getAluno().setSenha(rs.getString("a.senha"));
            mnf.getAluno().setEmail(rs.getString("a.email"));
            mnf.getMateria().setId(rs.getInt("m.idmateria"));
            mnf.getMateria().setId(rs.getInt("m.idperiodo"));
            mnf.getMateria().setNome(rs.getString("m.nome"));
            mnf.getMateria().getProfessor().setId(rs.getInt("m.idprofessor"));
            mnf.getNota().setId(rs.getInt("n.idnota"));
            mnf.getNota().setN1(rs.getDouble("n.n1"));
            mnf.getNota().setN2(rs.getDouble("n.n2"));
            mnf.getNota().setN3(rs.getDouble("n.n3"));
            mnf.getNota().getAlunoMateria().setId(rs.getInt("n.idalunomateria"));
            mnf.setFalta(rs.getInt("falta"));
            list.add(mnf);
        }
        stmt.close();
        rs.close();
        return list;
    }

    public List<Aluno> getConsulta(Aluno aluno) throws SQLException {
        String sql = "select * from aluno a \n"
                + "    inner join curso c on (a.idcurso = c.idcurso) \n"
                + "where a.nome like ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, aluno.getNome());
        Aluno a = null;
        List<Aluno> list = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            a = new Aluno();
            a.setId(rs.getInt("a.idaluno"));
            a.getCurso().setId(rs.getInt("c.idcurso"));
            a.getCurso().setNome(rs.getString("c.nome"));
            a.getCurso().setCategoria(rs.getString("c.categoria"));
            a.getCurso().getCordenador().setId(rs.getInt("c.idprofessor"));
            a.setDataAdmissao(rs.getDate("a.dataadmissao"));
            a.setNome(rs.getString("a.nome"));
            a.setCpf(rs.getString("a.cpf"));
            a.setDataNascimento(rs.getDate("a.datanascimento"));
            a.setLogin(rs.getString("a.login"));
            a.setSenha(rs.getString("a.senha"));
            a.setEmail(rs.getString("a.email"));
            list.add(a);
        }
        //System.out.println("TAMANHO 2: " + stmt);
        stmt.close();
        rs.close();
        return list;
    }
    
    public List<Aluno> getAlunosCursoMateria(Curso c, Materia m) throws SQLException{
        String sql = "select * from aluno a \n"
                + "inner join alunomateria am on (a.idaluno = am.idaluno) \n"
                + "where a.idcurso = ? and am.idmateria = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, c.getId());
        stmt.setInt(2, m.getId());
        ResultSet rs = stmt.executeQuery();
        Aluno a;
        List<Aluno> list = new ArrayList<>();
        while (rs.next()) {
            a = new Aluno();
            a.setId(rs.getInt("idaluno"));
            a.getCurso().setId(rs.getInt("idcurso"));
            a.setDataAdmissao(rs.getDate("dataadmissao"));
            a.setNome(rs.getString("nome"));
            a.setCpf(rs.getString("cpf"));
            a.setDataNascimento(rs.getDate("datanascimento"));
            a.setLogin(rs.getString("login"));
            a.setSenha(rs.getString("senha"));
            a.setEmail(rs.getString("email"));
            list.add(a);
        }
        stmt.close();
        rs.close();
        return list;
    }
    
}

