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
public class LoginMedicoDAO extends Conexao {

    public LoginMedicoDAO() throws Exception {
    }

    public boolean verificarUsuario(String usuario) {
        int x = 0;
        try {
            String sql = "select count(*) from medico where usuario_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.LoginMedicoDAO.verificarUsuario()" + ex.getMessage());
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public boolean verificarSenha(String senha, String usuario) {
        boolean result = false;
        try {
            String sql = "select senha_medico from medico where usuario_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (Decripto(rs.getString("senha_medico")).equals(senha)) {
                    result = true;
                }
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.LoginMedicoDAO.verificarSenha()" + ex.getMessage());
        } finally {
            fechaConexao();
            return result;
        }
    }

    public boolean verificarStatus(String login) {
        boolean result = false;
        try {
            String sql = "select status_medico from medico where usuario_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getBoolean("status_medico");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.LoginMedicoDAO.verificarStatus()" + ex.getMessage());
        } finally {
            fechaConexao();
            return result;
        }

    }
    
    public Medico buscarInformacoes(String usuario){
        Medico obj = new Medico();
        try {
            String sql = "select id_medico, foto_medico, nome_medico, sobrenome_medico from medico where usuario_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                obj.setId(rs.getInt("id_medico"));
                obj.setFoto(rs.getString("foto_medico"));
                obj.setNome(rs.getString("nome_medico"));
                obj.setSobreNome(rs.getString("sobrenome_medico"));
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.LoginMedicoDAO.buscarInformacoes()"+ex.getMessage());
        }finally{
            fechaConexao();
            return obj;
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

}
