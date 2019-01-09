/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.ComentariosDAO;
import Gestante_Social.model.Postagem;
import Gestante_Social.model.Usuario;
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
@WebServlet(name = "BuscarComentariosMedico", urlPatterns = {"/BuscarComentariosMedico"})
public class BuscarComentariosMedico extends HttpServlet {

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
            ComentariosDAO dao;
            
            String idPost = request.getParameter("idPostagem");
            String loginAmigo;
            int idPostagem;
            
            if (idPost==null) {
                idPost = String.valueOf(request.getAttribute("idPostagem"));
                loginAmigo = Decripto(String.valueOf(request.getAttribute("loginAmigo")));
                idPostagem = Integer.parseInt(Decripto(idPost));


            } else {
                idPost = request.getParameter("idPostagem");
                loginAmigo = Decripto(request.getParameter("loginAmigo"));
                idPostagem = Integer.parseInt(Decripto(String.valueOf(idPost)));

            }
            
            dao = new ComentariosDAO();
            Usuario obj = dao.buscarDados(loginAmigo);
            
            request.setAttribute("foto", obj.getFoto());
            request.setAttribute("nomeresponsavel", obj.getNome());
            request.setAttribute("id_responsavel", Cripto(String.valueOf(obj.getIdUsuario())));
            request.setAttribute("login_amigo", Cripto(loginAmigo));
            int x = obj.getIdUsuario();
            
            Postagem post = new Postagem();
            dao = new ComentariosDAO();
            post = dao.buscarPostagem(idPostagem);
            request.setAttribute("postagem", post.getMensagem());
            request.setAttribute("img", post.getUrl_imagem());
            request.setAttribute("id_post", idPost);
            
            dao = new ComentariosDAO();
            request.setAttribute("maximo", dao.pegaMaximo(idPostagem));
            
            request.getRequestDispatcher("comentarios_usuarios_medico.jsp").forward(request, response);
            
            
        } catch (Exception ex) {
            request.setAttribute("erro", String.valueOf(ex));
            request.getRequestDispatcher("tela_erro.jsp").forward(request, response);
        }
    }
    
    public static String Cripto(String senha) {
        //Criptografa a String passada por parâmetro
        int contador, tamanho, codigoASCII;
        String senhaCriptografada = "";
        tamanho = senha.length();
        //senha = senha.toUpperCase();
        contador = 0;
        while (contador < tamanho) {
            codigoASCII = senha.charAt(contador) + 130;
            senhaCriptografada = senhaCriptografada + (char) codigoASCII;
            contador++;
        }
        return senhaCriptografada;
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
