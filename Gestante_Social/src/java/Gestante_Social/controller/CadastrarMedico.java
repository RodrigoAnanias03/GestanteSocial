/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.MedicoDAO;
import Gestante_Social.model.Medico;
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
@WebServlet(name = "CadastrarMedico", urlPatterns = {"/CadastrarMedico"})
public class CadastrarMedico extends HttpServlet {

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
            
            MedicoDAO dao;

            Medico obj = new Medico();

            dao = new MedicoDAO();
            String nomeImagem = Cripto(dao.pegaNomeImagem());

            dao = new MedicoDAO();
            String id = dao.pegaId();
            obj.setId(Integer.parseInt(id));


            if (ServletFileUpload.isMultipartContent(request)) {

                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {

                    if (item.getFieldName().equals("cpfUsuario")) {
                        obj.setCpf(item.getString());
                    }
                    
                    if (item.getFieldName().equals("ruaUsuario")) {
                        obj.setRua(item.getString());
                    }
                    
                    if (item.getFieldName().equals("numeroUsuario")) {
                        obj.setNumero(item.getString());
                    }
                    
                    if (item.getFieldName().equals("bairroUsuario")) {
                        obj.setBairro(item.getString());
                    }
                    
                    if (item.getFieldName().equals("cepUsuario")) {
                        obj.setCep(item.getString());
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
                    if (item.getFieldName().equals("profissao")) {
                        obj.setProfissao(item.getString());
                    }
                    if (item.getFieldName().equals("telefoneUsuario")) {
                        obj.setTelefone(item.getString());
                    }
                    if (item.getFieldName().equals("documento")) {
                        obj.setDocumento(item.getString());
                    }
                    if (!item.isFormField()) {
                        //imagem = item.getName();
                        String imagem = nomeImagem + ".JPG";
                        obj.setFoto(imagem);
                        if (!imagem.equals("")) {
                            item.write(new File(request.getServletContext().getRealPath("/img/imgperfilmedico").replace("\\build", "")
                                    + File.separator + nomeImagem + ".JPG"));
                        }
                    }

                }
            }

            dao = new MedicoDAO();
            if (!dao.verificaEmail(obj)) {
                dao = new MedicoDAO();
                if (!dao.verificaLogin(obj)) {

                    dao = new MedicoDAO();
                    dao.cadastrarMedico(obj);

                    Enviar_Email env_email = new Enviar_Email();
                    env_email.enviarMedico(obj.getEmail(), "Olá "+obj.getNome()+"! Seu cadastro Gestante Social será processado e avaliado por nossa equipe!"
                            + "\n Assim que avaliado você será avisado e sua conta será ativada!");
                    request.setAttribute("email", obj.getEmail());
                    request.getRequestDispatcher("cadastro_finalizar.jsp").forward(request, response);

                } else {
                    request.setAttribute("obj", obj);
                    request.setAttribute("erro", "Este Login já está sendo utilizado!");
                    request.getRequestDispatcher("cadastro_medico.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("obj", obj);
                request.setAttribute("erro", "Este email já está sendo utilizado!");
                request.getRequestDispatcher("cadastro_medico.jsp").forward(request, response);
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
