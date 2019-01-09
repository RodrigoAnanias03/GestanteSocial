/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

import Gestante_Social.model.Medico;
import Gestante_Social.model.Postagem;
import Gestante_Social.model.Usuario;
import Gestante_Social.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JONATAS
 */
public class BuscarInfoAmigosDAO extends Conexao{
    public BuscarInfoAmigosDAO()throws Exception{}
    
    public Usuario buscarDados(String login){
        Usuario obj = new Usuario();
        try{
            String sql = "select login_usuario, id_usuario, nome_usuario,sobrenome_usuario, url_img, inicio_gestacao from usuario where id_usuario in (select id_usuario from usuario where login_usuario = ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                obj.setIdUsuario(rs.getInt("id_usuario"));
                obj.setNome(rs.getString("nome_usuario"));
                obj.setFoto(rs.getString("url_img"));
                obj.setInicioGestacao(rs.getDate("inicio_gestacao"));
                obj.setSobrenome(rs.getString("sobrenome_usuario"));
                obj.setLogin(rs.getString("login_usuario"));
            }
        }catch(Exception ex){
            System.out.println("Erro na classe EfetuarLoginDao BuscarDados! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
            return obj;
        }
    }
    
    public Medico buscarDadosMedico(String login){
        Medico obj = new Medico();
        try{
            String sql = "select usuario_medico, id_medico, nome_medico,sobrenome_medico, foto_medico from medico where id_medico in (select id_medico from medico where usuario_medico = ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                obj.setId(rs.getInt("id_medico"));
                obj.setNome(rs.getString("nome_medico"));
                obj.setFoto(rs.getString("foto_medico"));
                obj.setSobreNome(rs.getString("sobrenome_medico"));
                obj.setUsuario(rs.getString("usuario_medico"));
            }
        }catch(Exception ex){
            System.out.println("Erro na classe EfetuarLoginDao BuscarDadosMedico! Erro: " + ex.getMessage());
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
    
    public int buscarStatusAmizade(int id, int idAmigo){
        int controle=0;
        try{
            String sql = "select * from amigos where (id_pessoa1=? and id_pessoa2=?) or (id_pessoa1=? and id_pessoa2=?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, idAmigo);
            ps.setInt(3, idAmigo);
            ps.setInt(4, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getBoolean("status_amizade")==true){
                    controle=1;
                }else if(rs.getBoolean("status_amizade")==false && rs.getInt("id_pessoa1")==id){
                    controle=2;
                }else if(rs.getBoolean("status_amizade")==false && rs.getInt("id_pessoa1")==idAmigo){
                    controle=3;
                }
            }
        }catch(Exception ex){
            System.out.println("Erro em BuscarInfoAmigosDAO.buscarStatusAmizade()!Erro:"+ ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
            return controle;
        }
    }
    
    public int pegaMaximoPostPessoal(int id) throws SQLException {
        int x = 0;
        try {

            String sql = "select max(id_postagem) from postagem where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt("max");
            }
        } catch (Exception ex) {
            System.out.println("Erro em : dao.PostagemDAO.pegaMaximoPostPessoal(): Erro:"+ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return x;
        }
    }
    
    public ArrayList<Postagem> pegaPostPessoal(int x, int maximo, int id) {

        ArrayList<Postagem> postagem = new ArrayList<>();
        Postagem obj;
        ResultSet rs;
        try {
            String sql = "select * from postagem pos, usuario usu where (id_postagem<=? and pos.id_usuario=? and pos.id_usuario = usu.id_usuario) order by id_postagem desc limit ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, maximo);
            ps.setInt(2, id);
            ps.setInt(3, x);
            rs = ps.executeQuery();
            while (rs.next()) {
                obj = new Postagem();
                obj.setId_postagem(rs.getInt("id_postagem"));
                obj.setMensagem(rs.getString("mensagem"));
                obj.setUrl_imagem(rs.getString("url_imagem"));
                obj.setData(rs.getDate("data"));
                Usuario usu = new Usuario();
                usu.setFoto(rs.getString("url_img"));
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setNome(rs.getString("nome_usuario"));
                usu.setSobrenome(rs.getString("sobrenome_usuario"));
                usu.setInicioGestacao(rs.getDate("inicio_gestacao"));
                usu.setLogin(rs.getString("login_usuario"));
                obj.setUsuario(usu);
                postagem.add(obj);
            }
        } catch (Exception ex) {
            System.out.println("Erro em dao.BuscarInfoAmigosDAO.pegaPostPessoal()! Erro: "+ex.getMessage());
            ex.printStackTrace();
        } finally {

            fechaConexao();
            return postagem;
        }
    }
    
    
    
}
