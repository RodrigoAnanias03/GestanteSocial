/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.ComentariosDAO;
import Gestante_Social.model.Comentario;
import Gestante_Social.model.Postagem;
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
@WebServlet(name = "ComentarPublicacao", urlPatterns = {"/ComentarPublicacao"})
public class ComentarPublicacao extends HttpServlet {

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
        try{
            HttpSession sessao = request.getSession();
            Usuario obj = (Usuario) sessao.getAttribute("usuAutenticado");
            
            String mensagem = request.getParameter("texto");
            int idPost = Integer.parseInt(Decripto(request.getParameter("id_post")));
            String loginAmigo = Decripto(request.getParameter("login_amigo"));
            
            //Quem está fazendo o comentário?
            Usuario usu = new Usuario();
            usu.setIdUsuario(obj.getIdUsuario());
            
            //Qual o post?
            Postagem pos = new Postagem();
            pos.setId_postagem(idPost);
            
            //Preencher comentário
            Comentario com = new Comentario();
            com.setComentario(mensagem);
            com.setPostagem(pos);
            com.setUsuario(usu);
            
            //Mandar para o banco
            ComentariosDAO dao = new ComentariosDAO();
            if(dao.cadastrarComentario(com)){
                request.setAttribute("mensagem", "Comentário Realizado!");
            }else{
                request.setAttribute("mensagem", "Comentário Realizado!");
            }
            
            //Voltar para página de comentarios atualizada
            request.setAttribute("idPostagem", Cripto(String.valueOf(idPost)));
            request.setAttribute("loginAmigo", Cripto(loginAmigo));
            request.getRequestDispatcher("BuscarComentarios").forward(request, response);
            
        }catch(Exception ex){
            request.setAttribute("erro", String.valueOf(ex));
            request.getRequestDispatcher("tela_erro.jsp").forward(request, response);
            System.out.println("Erro em: ComentarPublicacao.processRequest()! Erro: "+ex.getMessage());
            ex.printStackTrace();
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