/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.BuscarInfoAmigosMedicoDAO;
import Gestante_Social.model.Medico;
import Gestante_Social.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "BuscarInfoAmigosMedico", urlPatterns = {"/BuscarInfoAmigosMedico"})
public class BuscarInfoAmigosMedico extends HttpServlet {

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
            
            String loginAmigo = String.valueOf(request.getParameter("loginAmigo"));
            
            if(loginAmigo.equals("null")){
                loginAmigo = String.valueOf(request.getAttribute("loginAmigo"));
            }
            
            if(loginAmigo.equals("null")){
                loginAmigo = Cripto(obj.getUsuario());
            }
            
            
            BuscarInfoAmigosMedicoDAO dao;
            loginAmigo = Decripto(loginAmigo);
            
            dao = new BuscarInfoAmigosMedicoDAO();
            
            Usuario amigo = new Usuario();
            amigo = dao.buscarDados(loginAmigo);
            
            if(amigo.getInicioGestacao()!=null){
            
            request.setAttribute("semanas_amigo", semanas(amigo.getInicioGestacao()));
            }
            request.setAttribute("id_amigo", Cripto(String.valueOf(amigo.getIdUsuario())));
            request.setAttribute("login_amigo", Cripto(amigo.getLogin()));
            request.setAttribute("imagem_amigo", amigo.getFoto());
            request.setAttribute("nome_amigo", amigo.getNome());
            request.setAttribute("sobrenome_amigo", amigo.getSobrenome());
            
            dao = new BuscarInfoAmigosMedicoDAO();
                int x = dao.buscarStatusAmizade(obj.getId(), amigo.getIdUsuario());
                if (x == 0) {
                    request.setAttribute("botao", "Adicionar");
                } else if (x == 1) {
                    request.setAttribute("botao", "Amigos");
                }else if (x == 2) {
                    request.setAttribute("botao", "Solicitação enviada");
                }else if (x == 3) {
                    request.setAttribute("botao", "Aceitar Solicitação");
                }
                
                dao = new BuscarInfoAmigosMedicoDAO();
            request.setAttribute("maximo", dao.pegaMaximoPostPessoal(amigo.getIdUsuario()));
            
            request.getRequestDispatcher("perfil_amigo_medico.jsp").forward(request, response);
            
        } catch (Exception ex) {
            request.setAttribute("erro", String.valueOf(ex));
            request.getRequestDispatcher("tela_erro.jsp").forward(request, response);
        }
    }
    public long semanas(Date data) {
        Date hoje = new Date();
        long semanas = hoje.getTime() - data.getTime();
        semanas = ((semanas / (24 * 60 * 60 * 1000)) / 7) + 1;
        return semanas;
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
