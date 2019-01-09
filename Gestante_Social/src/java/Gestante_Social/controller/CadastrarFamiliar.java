/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.FamiliarDAO;
import Gestante_Social.model.Familiar;
import Gestante_Social.util.Enviar_Email;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author JONATAS
 */
@WebServlet(name = "CadastrarFamiliar", urlPatterns = {"/CadastrarFamiliar"})
public class CadastrarFamiliar extends HttpServlet {

    public Date converterData(String data) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        if (data == null || data.trim().equals("")) {
            return null;
        } else {
            Date date = fmt.parse(data);
            return date;
        }
    }

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

            Familiar obj = new Familiar();

            dao = new FamiliarDAO();
            String nomeImagem = Cripto(dao.pegaNomeImagem());

            dao = new FamiliarDAO();
            String id = dao.pegaId();
            obj.setId(Integer.parseInt(id));

            String url = String.valueOf(request.getRequestURL());
            url = url.replaceAll("CadastrarFamiliar", "ConfirmarCadastroFamiliar?idFamiliar=" + Cripto(id));

            if (ServletFileUpload.isMultipartContent(request)) {

                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {

                    if (item.getFieldName().equals("cpfUsuario")) {
                        obj.setCpf(item.getString());
                    }

                    if (item.getFieldName().equals("estadoUsuario")) {
                        obj.setEstado(item.getString());
                    }

                    if (item.getFieldName().equals("cidadeUsuario")) {
                        obj.setCidade(item.getString());
                    }

                    if (item.getFieldName().equals("nomeUsuario")) {
                        obj.setNome(item.getString());
                    }

                    if (item.getFieldName().equals("sobrenomeUsuario")) {
                        obj.setSobreNome(item.getString());
                    }

                    if (item.getFieldName().equals("emailUsuario")) {
                        obj.setEmail(item.getString());
                    }
                    if (item.getFieldName().equals("loginUsuario")) {
                        obj.setUsuario(item.getString());
                    }
                    if (item.getFieldName().equals("senha")) {
                        obj.setSenha(item.getString());
                    }
                    if (item.getFieldName().equals("dataNascimento")) {
                        obj.setDataNascimento(converterData(item.getString()));
                    }

                    if (!item.isFormField()) {
                        //imagem = item.getName();
                        String imagem = nomeImagem + ".JPG";
                        obj.setFoto(imagem);
                        if (!imagem.equals("")) {
                            item.write(new File(request.getServletContext().getRealPath("/img/imgperfilfamiliar").replace("\\build", "")
                                    + File.separator + nomeImagem + ".JPG"));
                        }
                    }

                }
            }

            dao = new FamiliarDAO();
            if (!dao.verificaEmail(obj)) {
                dao = new FamiliarDAO();
                if (!dao.verificaLogin(obj)) {

                    dao = new FamiliarDAO();
                    dao.cadastrarFamiliar(obj);

                    Enviar_Email env_email = new Enviar_Email();
                    env_email.enviar(obj.getEmail(), url);
                    request.setAttribute("email", obj.getEmail());
                    request.getRequestDispatcher("cadastro_finalizar.jsp").forward(request, response);

                } else {
                    request.setAttribute("familiar", obj);
                    request.setAttribute("erroEmail", "Este Login já está sendo utilizado!");
                    request.getRequestDispatcher("cadastro_familiar.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("familiar", obj);
                request.setAttribute("erroEmail", "Este email já está sendo utilizado!");
                request.getRequestDispatcher("cadastro_familiar.jsp").forward(request, response);
            }

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
