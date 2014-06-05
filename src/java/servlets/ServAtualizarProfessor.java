/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import dao.AlunoDao;
import dao.ProfessorDao;
import entidades.Professor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josepedro
 */
@WebServlet(name = "ServAtualizarProfessor", urlPatterns = {"/ServAtualizarProfessor"})
public class ServAtualizarProfessor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         Professor p = new Professor();
        p.setId(Integer.parseInt(request.getParameter("button1id")));
        p.setNome(request.getParameter("nome"));
        p.setCpf(request.getParameter("cpf"));
        String date = request.getParameter("data");
        String dateAdm = request.getParameter("dataadmissao");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate;
        try {
            parsedDate = dateFormat.parse(date);
            date = dateFormat.format(parsedDate);
        } catch (ParseException ex) {
            Logger.getLogger(ServAtualizarProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }        
        Date dataadmissao = new Date(System.currentTimeMillis());
        dateFormat.format(dataadmissao);
        p.setDataNascimento(Date.valueOf(date));
        p.setDataAdmissao(dataadmissao);
        
        p.setLogin(request.getParameter("login"));
        p.setSenha(request.getParameter("senha"));
        p.setEmail(request.getParameter("email"));
         try {
            ProfessorDao professordao = new ProfessorDao();
            professordao.atualizar(p);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServAtualizarProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }
         RequestDispatcher view = request.getRequestDispatcher("Professores.jsp");
        view.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
