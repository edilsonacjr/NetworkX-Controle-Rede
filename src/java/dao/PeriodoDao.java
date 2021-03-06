/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entidades.Curso;
import entidades.Periodo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author claudemir
 */
public class PeriodoDao {
    
    private Connection conexao;
    
    public PeriodoDao() throws ClassNotFoundException, SQLException{
        conexao = Conexao.getConexao();
    }
    
    public void insere(Periodo p) throws SQLException {
        String sql = "insert into periodo values (null, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, p.getCurso().getId());
        stmt.setString(2, p.getNome());
        stmt.setInt(3, p.getAno());
        stmt.setInt(4, p.getSemestre());
        stmt.execute();
        stmt.close();
    }
    
    public void atualiza(Periodo p) throws SQLException {
        String sql = "update periodo set idcurso = ?, nome = ?, ano = ?, "
                + "semestre = ? where idperiodo = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, p.getCurso().getId());
        stmt.setString(2, p.getNome());
        stmt.setInt(3, p.getAno());
        stmt.setInt(4, p.getSemestre());
        stmt.setInt(5, p.getId());
        stmt.execute();
        stmt.close();
    }
    
    public void exclui(Periodo p) throws SQLException {
        String sql = "delete from periodo where idperiodo = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, p.getId());
        stmt.execute();
        stmt.close();
    }
    
    public List<Periodo> listar() throws SQLException {
        List<Periodo> list = new ArrayList<>();
        Periodo p = null;
        //Levanta difunto
        String sql = "select * from periodo p\n"
                + "    inner join curso c on (p.idcurso = c.idcurso)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            p = new Periodo();
            p.setId(rs.getInt("p.idperiodo"));
            p.getCurso().setId(rs.getInt("c.idcurso"));
            p.getCurso().setNome(rs.getString("c.nome"));
            p.getCurso().setCategoria(rs.getString("c.categoria"));
            p.setNome(rs.getString("p.nome"));
            p.setAno(rs.getInt("p.ano"));
            p.setSemestre(rs.getInt("p.semestre"));
            list.add(p);
        }
        stmt.close();
        rs.close();
        return list;
    }
    
    public Periodo getPeriodo(Periodo periodo) throws SQLException {
        String sql = "select * from periodo where idperiodo = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, periodo.getId());
        Periodo p = null;
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            p = new Periodo();
            p.setId(rs.getInt("idperiodo"));
            p.getCurso().setId(rs.getInt("idcurso"));
            p.setNome(rs.getString("nome"));
            p.setAno(rs.getInt("ano"));
            p.setSemestre(rs.getInt("semestre"));
        }
        stmt.close();
        rs.close();
        return p;
    }
    
    public List<Periodo> getConsulta(Curso c) throws SQLException {
        String sql = "select * from periodo p \n"
                + "inner join curso c on (p.idcurso = c.idcurso) \n"
                + "where c.idcurso = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, c.getId());
        Periodo p = null;
        List<Periodo> list = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            p = new Periodo();
            p.setId(rs.getInt("p.idperiodo"));
            p.getCurso().setId(rs.getInt("c.idcurso"));
            p.getCurso().setNome(rs.getString("c.nome"));
            p.getCurso().setCategoria(rs.getString("c.categoria"));
            p.setNome(rs.getString("p.nome"));
            p.setAno(rs.getInt("p.ano"));
            p.setSemestre(rs.getInt("p.semestre"));
            list.add(p);
        }
        stmt.close();
        rs.close();
        return list;
    }
    
}
