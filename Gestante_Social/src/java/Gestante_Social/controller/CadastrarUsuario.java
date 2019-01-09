/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.UsuarioDAO;
import Gestante_Social.model.Usuario;
import Gestante_Social.util.Enviar_Email;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CadastrarUsuario", urlPatterns = {"/CadastrarUsuario", "/cadastrarusuario"})
public class CadastrarUsuario extends HttpServlet {

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
            throws ServletException, IOException, ParseException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try {
            UsuarioDAO dao;

            String url = String.valueOf(request.getRequestURL());
            dao = new UsuarioDAO();
            String id = dao.pegaId();
            url = url.replaceAll("cadastrarusuario", "confirmarcadastro?idUsuario=" + Cripto(id));

            String nome = "", sobrenome = "", email = "", login = "", senha = "", imagem = "", dataNascimento = "", cidade = "", estado = "", rua = "", cep = "", bairro = "", telefone = "", cpf = "", inicioGestacao = "", numero = "";
            
            dao = new UsuarioDAO();
            String nomeImagem = Cripto(dao.pegaNomeImagem());
            imagem = nomeImagem + ".JPG";
            if (ServletFileUpload.isMultipartContent(request)) {

                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {

                    if (item.getFieldName().equals("numeroUsuario")) {
                        numero = item.getString();
                    }

                    if (item.getFieldName().equals("gestacaoUsuario")) {
                        inicioGestacao = item.getString();
                    }

                    if (item.getFieldName().equals("cpfUsuario")) {
                        cpf = item.getString();
                    }

                    if (item.getFieldName().equals("telefoneUsuario")) {
                        telefone = item.getString();
                    }

                    if (item.getFieldName().equals("bairroUsuario")) {
                        bairro = item.getString();
                    }

                    if (item.getFieldName().equals("cepUsuario")) {
                        cep = item.getString();
                    }

                    if (item.getFieldName().equals("ruaUsuario")) {
                        rua = item.getString();
                    }

                    if (item.getFieldName().equals("estadoUsuario")) {
                        estado = item.getString();
                    }

                    if (item.getFieldName().equals("cidadeUsuario")) {
                        cidade = item.getString();
                    }

                    if (item.getFieldName().equals("nomeUsuario")) {
                        nome = item.getString();
                    }

                    if (item.getFieldName().equals("sobrenomeUsuario")) {
                        sobrenome = item.getString();
                    }

                    if (item.getFieldName().equals("emailUsuario")) {
                        email = item.getString();
                    }
                    if (item.getFieldName().equals("loginUsuario")) {
                        login = item.getString();
                    }
                    if (item.getFieldName().equals("senha")) {
                        senha = item.getString();
                    }
                    if (item.getFieldName().equals("dataNascimento")) {
                        dataNascimento = item.getString();
                    }

                    if (!item.isFormField()) {
                        //imagem = item.getName();

                        if (!imagem.equals("")) {
                            

                            item.write(new File(request.getServletContext().getRealPath("/img/imgperfilusuario").replace("\\build", "")
                                    + File.separator + nomeImagem + ".JPG"));
                        }
                    }

                }
            }

            dao = new UsuarioDAO();
            if (dao.verificarEmail(email)) {
                request.setAttribute("nome", nome);
                request.setAttribute("sobrenome", sobrenome);
                request.setAttribute("email", email);
                request.setAttribute("login", login);
                request.setAttribute("nascimento", dataNascimento);
                request.setAttribute("cidade", cidade);
                request.setAttribute("estado", estado);
                request.setAttribute("rua", rua);
                request.setAttribute("cep", cep);
                request.setAttribute("bairro", bairro);
                request.setAttribute("telefone", telefone);
                request.setAttribute("cpf", cpf);
                request.setAttribute("gestacao", inicioGestacao);
                request.setAttribute("numero", numero);
                request.setAttribute("erroEmail", "Este email já está sendo utilizado!");
                request.getRequestDispatcher("novo_cad.jsp").forward(request, response);
            } else {
                dao = new UsuarioDAO();
                if (dao.verificarLogin(login)) {
                    request.setAttribute("nome", nome);
                    request.setAttribute("sobrenome", sobrenome);
                    request.setAttribute("email", email);
                    request.setAttribute("login", login);
                    request.setAttribute("nascimento", dataNascimento);
                    request.setAttribute("cidade", cidade);
                    request.setAttribute("estado", estado);
                    request.setAttribute("rua", rua);
                    request.setAttribute("cep", cep);
                    request.setAttribute("bairro", bairro);
                    request.setAttribute("telefone", telefone);
                    request.setAttribute("cpf", cpf);
                    request.setAttribute("gestacao", inicioGestacao);
                    request.setAttribute("numero", numero);
                    request.setAttribute("erroLogin", "Este login já está sendo utilizado!");
                    request.getRequestDispatcher("novo_cad.jsp").forward(request, response);
                } else {

                    Usuario obj = new Usuario(Integer.parseInt(id), nome, sobrenome, email, converterData(dataNascimento), login, senha, cidade, estado, rua, numero, cep, bairro, telefone, converterData(inicioGestacao), imagem, cpf);

                    dao = new UsuarioDAO();
                    if (dao.inserirUsuario(obj)) {
                        Enviar_Email env_email = new Enviar_Email();
                        env_email.enviar(email, url);
                        request.setAttribute("email", email);
                        request.getRequestDispatcher("cadastro_finalizar.jsp").forward(request, response);
                    } else {

                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("erro", String.valueOf(ex));
            request.getRequestDispatcher("tela_erro.jsp").forward(request, response);
            System.out.println("Erro na classe cadastrarusuario! Erro: " + ex.getMessage());
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
