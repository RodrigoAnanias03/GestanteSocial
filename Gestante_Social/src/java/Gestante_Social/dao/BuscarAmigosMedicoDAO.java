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
import java.util.ArrayList;

/**
 *
 * @author JONATAS
 */
public class BuscarAmigosMedicoDAO extends Conexao{
    public BuscarAmigosMedicoDAO()throws Exception{}
    
    public ArrayList<Usuario> listaAmigos(int id){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "select * from amigos_medico ami, usuario usu where ami.id_gestante = usu.id_usuario and ami.id_medico=? and usu.situacao=true and ami.status_amizade=true";
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
    
    
    public Usuario buscarDadosUsuario(String login){
        Usuario obj = new Usuario();
        try {
            String sql = "select * from usuario where login_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                obj.setBairro(rs.getString("bairro_usuario"));
                obj.setCep(rs.getString("cep_usuario"));
                obj.setCidade(rs.getString("cidade_usuario"));
                obj.setCpf(rs.getString("cpf_usuario"));
                obj.setDataNascimento(rs.getDate("datanascimento_usuario"));
                obj.setEmail(rs.getString("email_usuario"));
                obj.setEstado(rs.getString("estado_usuario"));
                obj.setFoto(rs.getString("url_img"));
                obj.setInicioGestacao(rs.getDate("inicio_gestacao"));
                obj.setNome(rs.getString("nome_usuario"));
                obj.setNumero(rs.getString("numero_usuario"));
                obj.setRua(rs.getString("rua_usuario"));
                obj.setSobrenome(rs.getString("sobrenome_usuario"));
                obj.setTelefone(rs.getString("telefone_usuario"));
            }
            
            
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarAmigosMedicoDAO.buscarDadosUsuario()"+ex.getMessage());
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
