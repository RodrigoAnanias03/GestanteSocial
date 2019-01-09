/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

import Gestante_Social.model.Familiar;
import Gestante_Social.model.Usuario;
import Gestante_Social.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author JONATAS
 */
public class BuscarAmigosFamiliarDAO extends Conexao{
    public BuscarAmigosFamiliarDAO()throws Exception{}
    
    public ArrayList<Usuario> buscarAmigos(String nomeAmigo) {
        Usuario obj;
        ArrayList<Usuario> amigos = new ArrayList<>();
        try {
            String sql = "select * from usuario where upper(retira_acentuacao(nome_usuario ||' '|| sobrenome_usuario)) like ? and situacao=true";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, "%" + nomeAmigo + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj = new Usuario();
                obj.setNome(rs.getString("nome_usuario"));
                obj.setSobrenome(rs.getString("sobrenome_usuario"));
                obj.setFoto(rs.getString("url_img"));
                obj.setLogin(Cripto(rs.getString("login_usuario")));
                amigos.add(obj);
            }
        } catch (Exception ex) {
            System.out.println("Erro! BuscarAmigosDAO.buscarAmigos(): Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return amigos;
        }
    }
    
    public ArrayList<Usuario> listaAmigos(int id){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "select * from amigos_familiar ami, usuario usu where ami.id_gestante = usu.id_usuario and ami.id_familiar=? and usu.situacao=true and ami.status_amizade=true";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Usuario obj = new Usuario();
                obj.setNome(rs.getString("nome_usuario"));
                obj.setSobrenome(rs.getString("sobrenome_usuario"));
                obj.setLogin(Cripto(rs.getString("login_usuario")));
                obj.setFoto(rs.getString("url_img"));
                usuarios.add(obj);
            }
            
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarAmigosFamiliarDAO.listaAmigos()"+ex.getMessage());
        }finally{
            fechaConexao();
            return usuarios;
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
