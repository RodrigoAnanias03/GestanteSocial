/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.FamiliarDAO;
import Gestante_Social.model.Familiar;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JONATAS
 */
@WebServlet(name = "EfetuarLoginFamiliar", urlPatterns = {"/EfetuarLoginFamiliar"})
public class EfetuarLoginFamiliar extends HttpServlet {

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
        try {
            FamiliarDAO dao;

            String login = request.getParameter("usuario");
            String senha = request.getParameter("senha");

            dao = new FamiliarDAO();

            //verificar se existe
            if (dao.verificaUsuario(login)) {

                //verifica se está ativo
                dao = new FamiliarDAO();
                if (dao.verificaSituacao(login)) {

                    //verifica se a senha está correta
                    dao = new FamiliarDAO();
                    if (dao.verificaSenha(login).equals(senha)) {

                        dao = new FamiliarDAO();
                        Familiar obj = dao.buscarInfo(login);

                        request.setAttribute("familiar", obj);
                        request.getRequestDispatcher("IniciarFamiliar").forward(request, response);

                    } else {
                        request.setAttribute("info", "Usuário ou senha incorretos!");
                        request.getRequestDispatcher("acesso_familiar.jsp").forward(request, response);
                    }

                } else {

                    //nao está ativo
                    request.getRequestDispatcher("reenviar_email_familiar.jsp").forward(request, response);
                    
                }

            } else {
                request.setAttribute("info", "Usuário ou senha incorretos!");
                request.getRequestDispatcher("acesso_familiar.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            request.setAttribute("erro", String.valueOf(ex));
            request.getRequestDispatcher("tela_erro.jsp").forward(request, response);
        }
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
