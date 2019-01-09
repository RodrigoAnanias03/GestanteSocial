/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.BuscarAmigosDAO;
import Gestante_Social.dao.BuscarFamiliarDAO;
import Gestante_Social.model.Familiar;
import Gestante_Social.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JONATAS
 */
@WebServlet(name = "PesquisarFamiliares", urlPatterns = {"/PesquisarFamiliares"})
public class PesquisarFamiliares extends HttpServlet {

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
            
            HttpSession sessao = request.getSession();
            Usuario obj = (Usuario) sessao.getAttribute("usuAutenticado");
            
            String nomeFamiliar = request.getParameter("nomeFamiliar");
            
            if(nomeFamiliar!=null){
            
            nomeFamiliar = Normalizer.normalize(nomeFamiliar, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            nomeFamiliar = nomeFamiliar.toUpperCase();
            }else{
                nomeFamiliar = (String) request.getAttribute("nomePesquisa");
            }
            
            BuscarFamiliarDAO dao;
            dao = new BuscarFamiliarDAO();
            
            ArrayList<Familiar> familiar = dao.pesquisarFamiliar(nomeFamiliar, obj.getIdUsuario());
            
            request.setAttribute("familiar", familiar);
            request.setAttribute("nomePesquisa", nomeFamiliar);
            request.setAttribute("quantidade", familiar.size());
            request.getRequestDispatcher("resultado_pesquisa_familiar.jsp").forward(request, response);
            
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
