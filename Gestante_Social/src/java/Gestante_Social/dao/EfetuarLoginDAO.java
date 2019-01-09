/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

import Gestante_Social.model.Usuario;
import Gestante_Social.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author JONATAS
 */
public class EfetuarLoginDAO extends Conexao {

    public EfetuarLoginDAO() throws Exception {}

    public boolean verificarCadastro(String usuario) {
        int ver = 0;
        try {
            String sql = "select count(*) from usuario where login_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ver = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Erro em verificarCadastro()!" + ex.getMessage());
        } finally {
            fechaConexao();
            return ver > 0;
        }
    }
    
    public boolean verSituacao(String usuario){
        boolean x = true;
        try{
            String sql = "select situacao from usuario where login_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                x = rs.getBoolean("situacao");
            }
        }catch(Exception ex){
            System.out.println("Erro! verSituacao():"+ex.getMessage());
        }finally{
            fechaConexao();
            return x;
        }
    }
    
    public boolean verSenha(String usuario, String senha){
        boolean re = false;
        String senhabd = "";
        try{
            String sql = "select senha_usuario from usuario where login_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                senhabd = Decripto(rs.getString("senha_usuario"));
            }
            if(senha.equals(senhabd)){
                re = true;
            }
        }catch(Exception ex){
            System.out.println("Erro! verSenha():"+ex.getMessage());
        }finally{
            fechaConexao();
            return re;
        }
    }
    
    public Usuario buscarDados(String login){
        Usuario obj = new Usuario();
        try{
            String sql = "select login_usuario, id_usuario, nome_usuario, url_img from usuario where id_usuario in (select id_usuario from usuario where login_usuario = ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                obj.setLogin(rs.getString("login_usuario"));
                obj.setIdUsuario(rs.getInt("id_usuario"));
                obj.setNome(rs.getString("nome_usuario"));
                obj.setFoto(rs.getString("url_img"));
            }
        }catch(Exception ex){
            System.out.println("Erro na classe EfetuarLoginDao BuscarDados! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
            return obj;
        }
    }
    
    public Usuario buscarDadosIndex(int id){
        Usuario obj = new Usuario();
        try{
            String sql = "select id_usuario, nome_usuario, url_img from usuario where id_usuario in (select id_usuario from usuario where id_usuario = ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                obj.setIdUsuario(rs.getInt("id_usuario"));
                obj.setNome(rs.getString("nome_usuario"));
                obj.setFoto(rs.getString("url_img"));
            }
        }catch(Exception ex){
            System.out.println("Erro na classe EfetuarLoginDao BuscarDadosIndex! Erro: " + ex.getMessage());
            ex.printStackTrace();
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

}
