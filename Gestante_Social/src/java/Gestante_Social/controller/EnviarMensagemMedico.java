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
@WebServlet(name = "EnviarMensagemMedico", urlPatterns = {"/EnviarMensagemMedico"})
public class EnviarMensagemMedico extends HttpServlet {

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
            
            MensagensMedicasDAO dao;
            String loginMedico = null;
            String mensagem = null, arquivo = null;
            
            if (ServletFileUpload.isMultipartContent(request)) {

                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {

                    if (item.getFieldName().equals("mensagem")) {
                        mensagem = item.getString();
                    }
                    
                    if (item.getFieldName().equals("loginMedico")) {
                        loginMedico = Decripto(item.getString());
                    }


                    if (!item.isFormField()) {

                        String nomeImagem = item.getName();

                        if (!nomeImagem.equals("")) {
                            dao = new MensagensMedicasDAO();
                            String imagem = Cripto(dao.pegaNomeArquivo());
                            item.write(new File(request.getServletContext().getRealPath("/img/arquivosmedicos").replace("\\build", "")
                                    + File.separator + imagem + ".JPG"));
                            arquivo = imagem + ".JPG";
                            Thread.sleep(7000);
                        }
                    }

                }
            }
            
            Medico med = new Medico();
            med.setUsuario(loginMedico);
            
            MensagemMedico msg= new MensagemMedico();
            msg.setArquivo(arquivo);
            msg.setMedico(med);
            msg.setMensagem(mensagem);
            msg.setUsuario(user);
            
            dao = new MensagensMedicasDAO();
            if(dao.enviarMensagemParaMedico(msg)){
                request.setAttribute("mensagem", "Mensagem enviada com sucesso!");
            }else{
                request.setAttribute("mensagem", "Ocorreu algum erro e não foi possível enviar a mensagem!");
            }
            
            request.getRequestDispatcher("BuscarMedicosGestante").forward(request, response);
            
            
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
