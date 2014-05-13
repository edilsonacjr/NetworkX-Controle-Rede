/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entidades.Aluno;
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
public class AlunoDAO {
    
    private Connection conexao;
    
    public AlunoDAO() throws ClassNotFoundException, SQLException{
        conexao = Conexao.getConexao();
    }
    
    public void insere(Aluno a) throws SQLException{
        String sql = "insert into aluno values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, a.getId());
        stmt.setInt(2, a.getCurso().getId());
        stmt.setInt(3, a.getMateria().getId());
        stmt.setDate(4, new Date(a.getDataAdmissao().getTime()));
        stmt.setString(5, a.getNome());
        stmt.setString(6, a.getCpf());
        stmt.setDate(7, new Date(a.getDataNascimento().getTime()));
        stmt.setString(8, a.getLogin());
        stmt.setString(9, a.getSenha());
        stmt.setString(10, a.getEmail());
        stmt.execute();
        stmt.close();
    }
    
    public void atualiza(Aluno a) throws SQLException{
        String sql = "update aluno set idCurso = ?, idMateria = ?, dataAdminssao = ?"
                + ", nome = ?, cpf = ?, dataNascimento = ?, login = ?, senha = ?, email = ?"
                + "where idAluno = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, a.getId());
        stmt.setInt(2, a.getCurso().getId());
        stmt.setInt(3, a.getMateria().getId());
        stmt.setDate(4, new Date(a.getDataAdmissao().getTime()));
        stmt.setString(5, a.getNome());
        stmt.setString(6, a.getCpf());
        stmt.setDate(7, new Date(a.getDataNascimento().getTime()));
        stmt.setString(8, a.getLogin());
        stmt.setString(9, a.getSenha());
        stmt.setString(10, a.getEmail());
        stmt.setInt(11, a.getId());
        stmt.execute();
        stmt.close();
    }
    
    public void exclui(Aluno a) throws SQLException{
        String sql = "delete from aluno where idAluno = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, a.getId());
        stmt.execute();
        stmt.close();
    }
    
    public List<Aluno> listar() throws SQLException{
        List<Aluno> list = new ArrayList<>();
        Aluno a;
        String sql = "select * "
                + "from aluno a"
                + "inner join curso c on (a.idCurso = c.idCurso)"
                + "inner join materia m on (a.idMateria = m.idMateria) ";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            a = new Aluno();
            a.setId(rs.getInt("a.idAluno"));
            a.getCurso().setId(rs.getInt("c.idCurso"));
            a.getMateria().setId(rs.getInt("m.idMateria"));
            a.setDataAdmissao(rs.getDate("a.dataAdmissao"));
            a.setNome(rs.getString("a.nome"));
            a.setCpf(rs.getString("a.cpf"));
            a.setDataNascimento(rs.getDate("a.dataNascimento"));
            a.setLogin(rs.getString("a.login"));
            a.setSenha(rs.getString("a.senha"));
            a.setEmail("a.email");
            list.add(a);
        }
        
        return list;
    }
    
}
