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
public class EditarAmizadeDAO extends Conexao {

    public EditarAmizadeDAO() throws Exception {
    }

    public int buscarIdAmigo(String login) {
        int id = 0;
        try {
            String sql = "select id_usuario from usuario where id_usuario in (select id_usuario from usuario where login_usuario = ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id_usuario");
            }
        } catch (Exception ex) {
            System.out.println("Erro na classe EfetuarLoginDao BuscarDados! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return id;
        }
    }

    public boolean adicionarAmigo(int id_usuario, int id_amigo) {
        int result = 0;
        try {
            String sql = "insert into amigos(id_pessoa1, id_pessoa2, status_amizade) values (?, ?, FALSE)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_usuario);
            ps.setInt(2, id_amigo);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro em EditarAmizadeDAO.adicionarAmigo():Erro!" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return result > 0;
        }
    }

    public boolean aceitarSolicitacao(int id_usuario, int id_amigo) {
        int result = 0;
        try {
            String sql = "update amigos set status_amizade=true where id_pessoa1=? and id_pessoa2=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_amigo);
            ps.setInt(2, id_usuario);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro em: EditarAmizadeDAO.aceitarSolicitacao()! Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return result > 0;
        }
    }

    public boolean excluirAmigo(int id_usuario, int id_amigo) {
        int result = 0;
        try {
            String sql = "delete from amigos where (id_pessoa1=? and id_pessoa2=?) or (id_pessoa1=? and id_pessoa2=?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_amigo);
            ps.setInt(2, id_usuario);
            ps.setInt(3, id_usuario);
            ps.setInt(4, id_amigo);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro em : EditarAmizadeDAO.excluirAmigo()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return result > 0;
        }
    }
    
    public String buscarQuantidadeSolicitacoes(Usuario obj){
        String result="";
        try{
            String sql = "select count(*) from amigos where id_pessoa2=? and status_amizade=false";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, obj.getIdUsuario());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                result = String.valueOf(rs.getInt("count"));
            }
        }catch(Exception ex){
            System.out.println("Erro em: EditarAmizadeDAO.buscarQuantidadeSolicitacoes()! Erro:"+ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
            return result;
        }
    }
    
    public ArrayList<Usuario> buscarSolicitacoes(int id_usuario){
        Usuario usu;
        ArrayList<Usuario> obj = new ArrayList<>();
        try{
            String sql = "select usu.* from usuario usu, amigos ami where (usu.id_usuario = ami.id_pessoa1 and ami.id_pessoa2=?) and ami.status_amizade=false";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setFoto(rs.getString("url_img"));
                usu.setNome(rs.getString("nome_usuario"));
                usu.setSobrenome(rs.getString("sobrenome_usuario"));
                usu.setLogin(Cripto(rs.getString("login_usuario")));
                obj.add(usu);
            }
        }catch(Exception ex){
            System.out.println("Erro em: EditarAmizadeDAO.buscarQuantidadeSolicitacoes()! Erro:"+ex.getMessage());
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
