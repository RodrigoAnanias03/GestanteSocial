/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.PerfilDAO;
import Gestante_Social.model.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EditarCadastro", urlPatterns = {"/EditarCadastro"})
public class EditarCadastro extends HttpServlet {

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
            Usuario user = (Usuario) sessao.getAttribute("usuAutenticado");

            PerfilDAO dao;

            Usuario obj = new Usuario();
            obj.setIdUsuario(user.getIdUsuario());

            if (ServletFileUpload.isMultipartContent(request)) {

                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {

                    if (item.getFieldName().equals("numeroUsuario")) {
                        obj.setNumero(item.getString());
                    }

                    if (item.getFieldName().equals("telefoneUsuario")) {
                        obj.setTelefone(item.getString());
                    }

                    if (item.getFieldName().equals("bairroUsuario")) {
                        obj.setBairro(item.getString());
                    }

                    if (item.getFieldName().equals("cepUsuario")) {
                        obj.setCep(item.getString());
                    }

                    if (item.getFieldName().equals("ruaUsuario")) {
                        obj.setRua(item.getString());
                    }

                    if (item.getFieldName().equals("estadoUsuario")) {
                        obj.setEstado(item.getString());
                    }

                    if (item.getFieldName().equals("cidadeUsuario")) {
                        obj.setCidade(item.getString());
                    }

                    if (item.getFieldName().equals("nomeUsuario")) {
                        obj.setNome(item.getString());
                        user.setNome(obj.getNome());
                    }

                    if (item.getFieldName().equals("sobrenomeUsuario")) {
                        obj.setSobrenome(item.getString());
                    }

                    if (!item.isFormField()) {

                        String nomeImagem = item.getName();

                        if (!nomeImagem.equals("")) {

                            dao = new PerfilDAO();
                            String nomeImg = dao.buscarNomeImg(user.getIdUsuario());

                            File ft = new File(request.getServletContext().getRealPath("/img/imgperfilusuario").replace("\\build", "")
                                    + File.separator + nomeImg);

                            ft.delete();

                            dao = new PerfilDAO();
                            String imagem = Cripto(dao.pegaNomeImagem());

                            item.write(new File(request.getServletContext().getRealPath("/img/imgperfilusuario").replace("\\build", "")
                                    + File.separator + imagem + ".JPG"));
                            obj.setFoto(imagem + ".JPG");
                            user.setFoto(imagem + ".JPG");

                            Thread.sleep(7000);
                        } else {
                            dao = new PerfilDAO();
                            obj.setFoto(dao.pegaNomeImagemUsuario(user.getIdUsuario()));
                        }
                    }

                }
            }

            sessao.setAttribute("usuAutenticado", user);

            dao = new PerfilDAO();
            boolean x = dao.EditarPerfil(obj);

            request.getRequestDispatcher("BuscarPerfilPessoal").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("erro", String.valueOf(ex));
            request.getRequestDispatcher("tela_erro.jsp").forward(request, response);
            System.out.println("Erro em: EditarCadastro.processRequest()!Erro: " + ex.getMessage());
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
