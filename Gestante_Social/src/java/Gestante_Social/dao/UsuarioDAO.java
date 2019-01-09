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
public class UsuarioDAO extends Conexao {

    public UsuarioDAO() throws Exception {

    }

    public String pegaNomeImagem() {
        int x=0;
        try {
            String sql = "SELECT nextval('seq_img_perfil_usuario')";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt("nextval");
            }
            
        } catch (Exception ex) {
            System.out.println("Erro no método pegaNomeImagem()! Erro: "+ ex.getMessage());
            ex.printStackTrace();
            
        }finally{
            fechaConexao();
            return String.valueOf(x);
        }
    }

    public boolean verificarEmail(String email) throws Exception {
        boolean verifica = false;
        try {
            String sql = "SELECT COUNT(*) FROM usuario WHERE email_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);
            int x = 0;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt("count");
            }
            verifica = x > 0;
        } catch (Exception ex) {
            System.out.println("Erro no método verificarEmail! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return verifica;
        }
    }

    public boolean verificarLogin(String login) throws Exception {
        boolean verifica = false;
        try {
            String sql = "SELECT COUNT(*) FROM usuario WHERE login_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);
            int x = 0;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt("count");
            }
            verifica = x > 0;
        } catch (Exception ex) {
            System.out.println("Erro no método verificarLogin! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return verifica;
        }
    }

    public String pegaId() throws Exception {
        int id = 0;
        try {
            String sql = "SELECT nextval('usuario_id_usuario_seq')";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("nextval");
            }
        } catch (Exception ex) {
            System.out.println("Erro no método PegarId! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return String.valueOf(id);
        }
    }

    public String pegaNome(int id) throws Exception {
        String nome = null;
        try {
            String sql = "SELECT nome_usuario FROM usuario WHERE id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nome = rs.getString("nome_usuario");
            }
        } catch (Exception ex) {
            System.out.println("Erro no método PegarId! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return nome;
        }
    }

    public boolean ativaUsuario(int id) throws Exception {
        int x = 0;
        try {
            String sql = "UPDATE usuario set situacao=true WHERE id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro no método PegarId! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public boolean inserirUsuario(Usuario obj) throws Exception {
        String sql = "INSERT INTO usuario(nome_usuario, sobrenome_usuario, email_usuario, senha_usuario, login_usuario, datanascimento_usuario, situacao, url_img, "
                + "cidade_usuario, estado_usuario, rua_usuario, numero_usuario, cep_usuario, bairro_usuario, telefone_usuario, inicio_gestacao, cpf_usuario, id_usuario)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int x = 0;
        String foto = obj.getFoto();

        try {
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getSobrenome());
            ps.setString(3, obj.getEmail());
            ps.setString(4, Cripto(obj.getSenha()));
            ps.setString(5, obj.getLogin());
            ps.setDate(6, new java.sql.Date(obj.getDataNascimento().getTime()));
            ps.setBoolean(7, false);
            ps.setString(8, obj.getFoto());
            ps.setString(9, obj.getCidade());
            ps.setString(10, obj.getEstado());
            ps.setString(11, obj.getRua());
            ps.setString(12, obj.getNumero());
            ps.setString(13, obj.getCep());
            ps.setString(14, obj.getBairro());
            ps.setString(15, obj.getTelefone());
            ps.setDate(16, new java.sql.Date(obj.getInicioGestacao().getTime()));
            ps.setString(17, obj.getCpf());
            ps.setInt(18, obj.getIdUsuario());

            x = ps.executeUpdate();

        } catch (Exception ex) {

            System.out.println("Erro no método inserirUsuario! Erro: " + ex.getMessage());
            ex.printStackTrace();

        } finally {
            fechaConexao();
            return x > 0;
        }

    }

    public boolean verificaAtivo(int id) throws Exception {
        boolean result = true;

        try {

            String sql = "SELECT situacao FROM usuario WHERE id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getBoolean("situacao");
            }

        } catch (Exception ex) {
            System.out.println("Erro no mérodo verificaAtivo()! Erro " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return !result;

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
