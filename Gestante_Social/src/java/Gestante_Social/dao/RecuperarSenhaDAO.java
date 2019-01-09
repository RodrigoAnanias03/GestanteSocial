/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

import Gestante_Social.model.Medico;
import Gestante_Social.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author JONATAS
 */
public class RecuperarSenhaDAO extends Conexao {

    public RecuperarSenhaDAO() throws Exception {
    }

    public boolean verificaEmail(String email) throws Exception {
        int x = 0;
        try {
            String sql = "SELECT COUNT(*) FROM usuario WHERE email_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Erro no método verificaEmail()! Erro " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public boolean verificaEmailFamiliar(String email) throws Exception {
        int x = 0;
        try {
            String sql = "SELECT COUNT(*) FROM familiar WHERE email_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Erro no método verificaEmail()! Erro " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public ResultSet pegaDados(String email) throws Exception {
        ResultSet rs = null;
        try {
            String sql = "SELECT senha_usuario, login_usuario FROM usuario WHERE email_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            System.out.println("Erro no método pegaDados()! Erro " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return rs;
        }
    }

    public ResultSet pegaDadosFamiliar(String email) throws Exception {
        ResultSet rs = null;
        try {
            String sql = "SELECT senha_familiar, usuario_familiar FROM familiar WHERE email_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
        } catch (Exception ex) {
            System.out.println("Erro no método pegaDados()! Erro " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return rs;
        }
    }

    //Médico
    public boolean verificaEmailMedico(String email) {
        int x = 0;
        try {
            String sql = "select count(*) from medico where email_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                x = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.RecuperarSenhaDAO.verificaEmailMedico()" + ex.getMessage());
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public boolean verificaAtivoMedico(String email) {
        boolean x = false;
        try {
            String sql = "select status_medico from medico where email_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                x = rs.getBoolean("status_medico");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.RecuperarSenhaDAO.verificaAtivoMedico()" + ex.getMessage());
        } finally {
            fechaConexao();
            return x;
        }
    }
    
    public Medico buscarDadosMedico(String email){
        Medico obj = new Medico();
        try {
            String sql = "select usuario_medico, senha_medico from medico where email_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                obj.setUsuario(rs.getString("usuario_medico"));
                obj.setSenha(Decripto(rs.getString("senha_medico")));
                
            }
            
            
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.RecuperarSenhaDAO.buscarDadosMedico()"+ex.getMessage());
        }finally{
            fechaConexao();
            return obj;
        }
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
}
