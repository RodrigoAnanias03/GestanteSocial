/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

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
public class PostagemDAO extends Conexao {

    public PostagemDAO() throws Exception {
    }
    
    
    public String peganomeImagem(int id_postagem) {
        String nomeImagem = "";
        try {
            String sql = "SELECT url_imagem from postagem where id_postagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_postagem);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nomeImagem = rs.getString("url_imagem");
            }
        } catch (Exception ex) {
            System.out.println("Erro PostagemDAO.pegaIdImagem()!Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return nomeImagem;
        }
    }

    public int pegaIdImagem() {
        int id = 0;
        try {
            String sql = "SELECT nextval('seq_img_post')";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("nextval");
            }
        } catch (Exception ex) {
            System.out.println("Erro PostagemDAO.pegaIdImagem()!Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return id;
        }
    }

    public boolean cadastrarPostagem(Postagem obj) {
        int x = 0;
        try {
            String msgTeste = obj.getMensagem();
            msgTeste = msgTeste.replaceAll(" ", "");
            if (msgTeste.length() > 0) {
                String sql = "insert into postagem (mensagem, url_imagem, id_usuario, data) values(?, ?, ?, NOW())";
                PreparedStatement ps = getConexao().prepareStatement(sql);
                ps.setString(1, obj.getMensagem());
                ps.setString(2, obj.getUrl_imagem());
                ps.setInt(3, obj.getUsuario().getIdUsuario());
                x = ps.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Erro PostagemDAO.cadastrarPostagem()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public int pegaMaximo(int id) {
        int x = 0;
        try {

            String sql = "select max(id_postagem) from postagem where id_usuario in (select id_pessoa1 from amigos where id_pessoa2=? and status_amizade=true) or id_usuario in (select id_pessoa2 from amigos where id_pessoa1=? and status_amizade=true) or id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.setInt(3, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt("max");
            }
        } catch (Exception ex) {
            System.out.println("Erro em : dao.PostagemDAO.pegaMaximo(): Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return x;
        }
    }

    public ArrayList<Postagem> pegaPost(int x, int maximo, int id) {

        ArrayList<Postagem> postagem = new ArrayList<>();
        Postagem obj;
        ResultSet rs;
        try {
            String sql = "select pos.*, usu.* from postagem pos, usuario usu where pos.id_usuario = usu.id_usuario and (pos.id_usuario in (select id_pessoa1 from amigos where id_pessoa2=? and status_amizade=true) or pos.id_usuario in (select id_pessoa2 from amigos where id_pessoa1=? and status_amizade=true) or pos.id_usuario=?)and situacao=true and id_postagem <=? order by id_postagem desc limit ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.setInt(3, id);
            ps.setInt(4, maximo);
            ps.setInt(5, x);
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
            System.out.println("Erro em dao.PostagemDAO.pegaPost()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {

            fechaConexao();
            return postagem;
        }
    }

    public boolean excluirPostagem(int idPostagem) {
        int resultado = 0;
        try {
            String sql = "delete from postagem where id_postagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idPostagem);
            resultado = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro em: PostagemDAO.excluirPostagem()!Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return resultado > 0;
        }
    }

}
