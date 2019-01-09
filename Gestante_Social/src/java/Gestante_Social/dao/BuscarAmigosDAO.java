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
public class BuscarAmigosDAO extends Conexao {

    public BuscarAmigosDAO() throws Exception {
    }

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

    public Usuario buscarDadosIndex(int id) {
        Usuario obj = new Usuario();
        try {
            String sql = "select id_usuario, nome_usuario, url_img from usuario where id_usuario in (select id_usuario from usuario where id_usuario = ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj.setIdUsuario(rs.getInt("id_usuario"));
                obj.setNome(rs.getString("nome_usuario"));
                obj.setFoto(rs.getString("url_img"));
            }
        } catch (Exception ex) {
            System.out.println("Erro na classe EfetuarLoginDao BuscarDadosIndex! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return obj;
        }
    }

    public ArrayList<Usuario> buscarAmigosPessoal(int id) {
        ArrayList<Usuario> amigos = new ArrayList<>();
        Usuario obj;
        try {
            String sql = "select * from usuario usu, amigos ami where usu.id_usuario!=? and ((usu.id_usuario = ami.id_pessoa2) or (usu.id_usuario = ami.id_pessoa1)) and ((ami.id_pessoa1=?) or (ami.id_pessoa2=?)) and ami.status_amizade=true and usu.situacao=true order by usu.nome_usuario";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.setInt(3, id);
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
            System.out.println("Erro em: BuscarAmigosDAO.buscarAmigosPessoal()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return amigos;
        }
    }

    public int quantidadeAmigos(int id) {
        int quantidade = 0;
        try {
            String sql = "select count(*) from usuario usu, amigos ami where usu.id_usuario!=? and ((usu.id_usuario = ami.id_pessoa2) or (usu.id_usuario = ami.id_pessoa1)) and ((ami.id_pessoa1=?) or (ami.id_pessoa2=?)) and ami.status_amizade=true and usu.situacao=true";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.setInt(3, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                quantidade = rs.getInt("count");
            }
        } catch (Exception ex) {

        } finally {
            fechaConexao();
            return quantidade;
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
