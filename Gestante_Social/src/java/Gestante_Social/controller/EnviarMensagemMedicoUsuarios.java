/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.MensagensMedicasDAO;
import Gestante_Social.model.Medico;
import Gestante_Social.model.MensagemMedico;
import Gestante_Social.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EnviarMensagemMedicoUsuarios", urlPatterns = {"/EnviarMensagemMedicoUsuarios"})
public class EnviarMensagemMedicoUsuarios extends HttpServlet {

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
            Medico obj = (Medico) sessao.getAttribute("usuAutenticado");
            
            String loginAmigo = Decripto(request.getParameter("loginAmigo"));
            String mensagem = request.getParameter("mensagem");
            
            Usuario destinatario = new Usuario();
            destinatario.setLogin(loginAmigo);
            
            Medico remetente = new Medico();
            remetente.setId(obj.getId());
            
            MensagemMedico msg = new MensagemMedico();
            
            msg.setUsuario(destinatario);
            msg.setMedico(remetente);
            msg.setMensagem(mensagem);
            
            MensagensMedicasDAO dao;
            dao = new MensagensMedicasDAO();
            if(dao.enviarMensagemMedico(msg)){
                request.setAttribute("mensagem", "Mesagem enviada com sucesso!");
                
            }else{
                request.setAttribute("mensagem", "Não foi possível enviar a mensagem! Tente mais tarde!");
            }
            
            request.getRequestDispatcher("IniciarMedico").forward(request, response);
            
            
        } catch (Exception ex) {
            request.setAttribute("erro", String.valueOf(ex));
            request.getRequestDispatcher("tela_erro.jsp").forward(request, response);
        }
    }
    
    public static String Decripto(String senha) {
        //Descriptografa a String passada por parâmetro
        int contador, tamanho, codigoASCII;
        String senhaCriptografada = "";
        tamanho = senha.length();
        //senha = senha.toUpperCase();
        contador = 0;
        while (contador < tamanho) {
            codigoASCII = senha.charAt(contador) - 130;
            senhaCriptografada = senhaCriptografada + (char) codigoASCII;
            contador++;
        }
        return senhaCriptografada;
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
