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
import java.util.Date;

/**
 *
 * @author JONATAS
 */
public class PerfilDAO extends Conexao {

    public PerfilDAO() throws Exception {
    }
    
    
    public String buscarNomeImg(int id_usuario){
        String nomeImg = "";
        try {
            String sql="select url_img from usuario where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_usuario);

            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                nomeImg = rs.getString("url_img");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.PerfilDAO.publicarNascimento()"+ex.getMessage());
        }finally{
            fechaConexao();
            return nomeImg;
        }
    }
    
    public boolean publicarNascimento(int id, String url, String mensagem){
        int x=0;
        try {
            String sql="insert into postagem(mensagem, url_imagem, id_usuario, data) values(?, ?, ?, NOW())";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, mensagem);
            ps.setString(2, url);
            ps.setInt(3, id);
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.PerfilDAO.publicarNascimento()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    public boolean declararNascimento(int id_gestante){
        int x=0;
        try {
            String sql = "update usuario set inicio_gestacao=null where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);
            
            x = ps.executeUpdate();
            
            
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.PerfilDAO.declararNascimento()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }

    public boolean novaGestacao(int id_gestante, Date data){
        int x=0;
        try {
            String sql = "update usuario set inicio_gestacao=? where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(data.getTime()));
            ps.setInt(2, id_gestante);
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.PerfilDAO.novaGestacao()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    public String pegaNomeImagem() {
        int x = 0;
        try {
            String sql = "SELECT nextval('seq_img_perfil_usuario')";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt("nextval");
            }

        } catch (Exception ex) {
            System.out.println("Erro no método pegaNomeImagem()! Erro: " + ex.getMessage());
            ex.printStackTrace();

        } finally {
            fechaConexao();
            return String.valueOf(x);
        }
    }
    
    public String pegaNomeImagemUsuario(int id) {
        String x = "";
        try {
            String sql = "select url_img from usuario where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getString("url_img");
            }

        } catch (Exception ex) {
            System.out.println("Erro no método pegaNomeImagem()! Erro: " + ex.getMessage());
            ex.printStackTrace();

        } finally {
            fechaConexao();
            return x;
        }
    }

    public Usuario buscarPerfil(int id) {
        Usuario obj = new Usuario();
        try {
            String sql = "select * from usuario where id_usuario = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj.setBairro(rs.getString("bairro_usuario"));
                obj.setCep(rs.getString("cep_usuario"));
                obj.setCidade(rs.getString("cidade_usuario"));
                obj.setCpf(rs.getString("cpf_usuario"));
                obj.setDataNascimento(rs.getDate("datanascimento_usuario"));
                obj.setEmail(rs.getString("email_usuario"));
                obj.setEstado(rs.getString("estado_usuario"));
                obj.setFoto(rs.getString("url_img"));
                obj.setIdUsuario(rs.getInt("id_usuario"));
                obj.setInicioGestacao(rs.getDate("inicio_gestacao"));
                obj.setLogin(rs.getString("login_usuario"));
                obj.setNome(rs.getString("nome_usuario"));
                obj.setNumero(rs.getString("numero_usuario"));
                obj.setRua(rs.getString("rua_usuario"));
                obj.setSenha(rs.getString("senha_usuario"));
                obj.setSobrenome(rs.getString("sobrenome_usuario"));
                obj.setTelefone(rs.getString("telefone_usuario"));
            }
        } catch (Exception ex) {
            System.out.println("Erro em: Gestante_Social.dao.PerfilDAO.buscarPerfil()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return obj;
        }
    }

    public boolean EditarPerfil(Usuario obj) {
        int resultado = 0;
        try {
            String sql = "update usuario set nome_usuario=?, sobrenome_usuario=?, numero_usuario=?, "
                    + "telefone_usuario=?, bairro_usuario=?, cep_usuario=?, rua_usuario=?, "
                    + "estado_usuario=?, cidade_usuario=?, url_img=? where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getSobrenome());
            ps.setString(3, obj.getNumero());
            ps.setString(4, obj.getTelefone());
            ps.setString(5, obj.getBairro());
            ps.setString(6, obj.getCep());
            ps.setString(7, obj.getRua());
            ps.setString(8, obj.getEstado());
            ps.setString(9, obj.getCidade());
            ps.setString(10, obj.getFoto());
            ps.setInt(11, obj.getIdUsuario());

            resultado = ps.executeUpdate();
            
            cadastrarImagem(obj.getIdUsuario(), obj.getFoto());

        } catch (Exception ex) {
            System.out.println("Erro em: PerfilDAO.EditarPerfil()! Erro " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return resultado > 0;
        }
    }
    
    public void cadastrarImagem(int id, String imagem){
        try{
            String sql = "insert into fotos_usuarios(id_usuario, url_img) values (?, ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, imagem);
            
            int resultado = ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("Erro em : PerfilDAO.cadastrarImagem()!Erro: "+ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
        }
    }
    
    public String senhaUsuario(int id){
        String senha="";
        try{
            String sql = "select senha_usuario from usuario where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                senha = rs.getString("senha_usuario");
            }
                    
        }catch(Exception ex){
            System.out.println("Erro em: PerfilDAO.senhaUsuario()! Erro "+ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
            return senha;
        }
    }
    
    public boolean alterarSenha(int id, String senha){
        int resultado=0;
        try{
            String sql = "update usuario set senha_usuario=? where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, senha);
            ps.setInt(2, id);
            resultado = ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("Erro em: PerfilDAO.alterarSenha()!Erro: "+ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
            return resultado>0;
        }
    }
    
    public boolean desativarConta(int idUsuario){
        int resultado=0;
        try{
            String sql = "update usuario set situacao=false where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idUsuario);
            resultado = ps.executeUpdate();
        }catch(Exception ex){
            System.out.println("Erro em: PerfilDAO.desativarConta()!Erro: "+ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
            return resultado>0;
        }
    }
}
