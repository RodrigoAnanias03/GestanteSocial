/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.controller;

import Gestante_Social.dao.FotosDAO;
import Gestante_Social.model.Usuario;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletContext;
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
@WebServlet(name = "BaixarFotos", urlPatterns = {"/BaixarFotos"})
public class BaixarFotos extends HttpServlet {

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
            int x = 0, y = 0;

            HttpSession sessao = request.getSession();
            Usuario usu = (Usuario) sessao.getAttribute("usuAutenticado");

            String path = request.getServletContext().getRealPath("/img/imgpostagem/").replace("\\build", "");

            FotosDAO dao;
            dao = new FotosDAO();
            ResultSet rs = dao.BuscarFotos(usu.getIdUsuario());

            ArrayList<String> fotos = new ArrayList<>();

            while (rs.next()) {
                if (rs.getString("url_imagem").equals("Nascimento.jpg")) {
                    if (x == 0) {
                        x = 1;
                        fotos.add(path + rs.getString("url_imagem"));
                    }
                } else if (rs.getString("url_imagem").equals("NovaGravidez.jpg")) {
                    if (y == 0) {
                        y = 1;
                        fotos.add(path + rs.getString("url_imagem"));
                    }
                } else {
                    fotos.add(path + rs.getString("url_imagem"));
                }

            }
            if (fotos.size() != 0) {

                String caminhoZip = request.getServletContext().getRealPath("/img/fotoscompactadas").replace("\\build", "")
                        + File.separator + usu.getIdUsuario() + ".zip";

                compactarParaZip(caminhoZip, fotos);

                boolean chave = true;

                while (chave) {
                    response.setContentType("application/zip");
                    ServletContext ctx = getServletContext();
                    InputStream is = ctx.getResourceAsStream("/img/fotoscompactadas/" + usu.getIdUsuario() + ".zip");
                    int read = 0;
                    byte[] bytes = new byte[1024];
                    OutputStream os = response.getOutputStream();
                    try {
                        while ((read = is.read(bytes)) != -1) {

                            os.write(bytes, 0, read);
                        }
                        os.flush();
                        os.close();
                        chave = false;
                    } catch (Exception ex) {

                    }
                }
                
                Thread.sleep(10000);
                
                File ft = new File(request.getServletContext().getRealPath("/img/fotoscompactadas").replace("\\build", "")
                       + File.separator + usu.getIdUsuario() + ".zip");

               ft.delete();

            } else {
                request.setAttribute("retorno", "Não há arquivos disponíveis!");
                request.getRequestDispatcher("IniciarPostagens").forward(request, response);
            }

        } catch (Exception ex) {
            request.setAttribute("erro", String.valueOf(ex));
            request.getRequestDispatcher("tela_erro.jsp").forward(request, response);
        }
    }

    static final int TAMANHO_BUFFER = 4096; // 4kb

    //método para compactar arquivo
    public static void compactarParaZip(String arqSaida, ArrayList<String> arqEntrada)
            throws IOException {
        int cont;
        byte[] dados = new byte[TAMANHO_BUFFER];

        BufferedInputStream origem = null;
        FileInputStream streamDeEntrada = null;
        FileOutputStream destino = null;
        ZipOutputStream saida = null;
        ZipEntry entry = null;

        try {
            destino = new FileOutputStream(new File(arqSaida));
            saida = new ZipOutputStream(new BufferedOutputStream(destino));

            File file;

            for (int i = 0; i < arqEntrada.size(); i++) {
                file = new File(arqEntrada.get(i));
                streamDeEntrada = new FileInputStream(file);
                origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
                entry = new ZipEntry(file.getName());
                saida.putNextEntry(entry);

                while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
                    saida.write(dados, 0, cont);
                }
            }

            origem.close();
            saida.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
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
