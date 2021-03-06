/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.AdministradorDAO;
import Gestante_Social.model.Medico;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JONATAS
 */
@WebServlet(name = "EditarCadastroMedicoAdm", urlPatterns = {"/EditarCadastroMedicoAdm"})
public class EditarCadastroMedicoAdm extends HttpServlet {
    
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
            
            Medico obj = new Medico();
            
            obj.setNome(request.getParameter("nomeUsuario"));
            obj.setCpf(request.getParameter("cpfUsuario"));
            obj.setSobreNome(request.getParameter("sobrenomeUsuario"));
            obj.setTelefone(request.getParameter("telefoneUsuario"));
            obj.setDocumento(request.getParameter("documento"));
            obj.setDataNascimento(converterData(request.getParameter("dataNascimento")));
            obj.setCidade(request.getParameter("cidadeUsuario"));
            obj.setEstado(request.getParameter("estadoUsuario"));
            obj.setRua(request.getParameter("ruaUsuario"));
            obj.setNumero(request.getParameter("numeroUsuario"));
            obj.setBairro(request.getParameter("bairroUsuario"));
            obj.setCep(request.getParameter("cepUsuario"));
            obj.setId(Integer.parseInt(request.getParameter("idUsuario")));
            
            AdministradorDAO dao = new AdministradorDAO();
            
            if(dao.editarPerfilMedico(obj)){
                request.setAttribute("msg", "Alterado com sucesso!");
            }else{
                request.setAttribute("msg", "Houve algum erro ao alterar!!");
            }
            
            request.getRequestDispatcher("buscar_medico_adm.jsp").forward(request, response);
            
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
