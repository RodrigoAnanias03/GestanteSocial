/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.PostagemDAO;
import Gestante_Social.model.Postagem;
import Gestante_Social.model.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author JONATAS
 */
@WebServlet(name = "PostagemGestante", urlPatterns = {"/PostagemGestante"})
public class PostagemGestante extends HttpServlet {

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
            String mensagem = "", url_imagem = "";
            HttpSession sessao = request.getSession();
            Usuario usu = (Usuario) sessao.getAttribute("usuAutenticado");

            PostagemDAO dao;

            if (ServletFileUpload.isMultipartContent(request)) {

                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {

                    if (item.getFieldName().equals("texto")) {
                        mensagem = item.getString();
                    }

                    if (!item.isFormField()) {
                        //imagem = item.getName();

                        if (!item.getName().equals("")) {
                            dao = new PostagemDAO();
                            String url = Cripto(String.valueOf(dao.pegaIdImagem()));
                            url_imagem = url + ".JPG";
                            item.write(new File(request.getServletContext().getRealPath("/img/imgpostagem").replace("\\build", "")
                                    + File.separator + url + ".JPG"));
                            Thread.sleep(7000);
                        }
                    }

                }

            }

            Usuario user = new Usuario();
            user.setIdUsuario(usu.getIdUsuario());

            Postagem obj = new Postagem(0, mensagem, url_imagem, user, null);

            dao = new PostagemDAO();

            if (dao.cadastrarPostagem(obj)) {
                request.setAttribute("id_usuario", usu.getIdUsuario());
                request.setAttribute("permissao", 1);
                request.getRequestDispatcher("VoltarIndex").forward(request, response);
            } else {
                request.setAttribute("id_usuario", usu.getIdUsuario());
                request.setAttribute("permissao", 2);
                request.getRequestDispatcher("IniciarPostagens").forward(request, response);
            }

        } catch (Exception ex) {
            request.setAttribute("erro", String.valueOf(ex));
            request.getRequestDispatcher("tela_erro.jsp").forward(request, response);
            System.out.println("Erro PostagemGestante.processRequest()! Erro: " + ex.getMessage());
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
