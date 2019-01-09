/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

import Gestante_Social.model.Medico;
import Gestante_Social.model.Mensagem;
import Gestante_Social.model.MensagemMedico;
import Gestante_Social.model.Usuario;
import Gestante_Social.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author JONATAS
 */
public class MensagensMedicasDAO extends Conexao{
    public MensagensMedicasDAO()throws Exception{}
    
    public boolean enviarMensagemMedico(MensagemMedico obj){
        int x=0;
        try {
            
            String sql = "insert into mensagens_medicas(mensagem, id_medico, id_gestante, status_mensagem, autor, data) "
                    + "values(?, ?, (select id_usuario from usuario where login_usuario=?), false, 'medico', NOW())";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, obj.getMensagem());
            ps.setInt(2, obj.getMedico().getId());
            ps.setString(3, obj.getUsuario().getLogin());
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.MensagensMedicasDAO.enviarMensagemMedico()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
        
    }

    public ArrayList<MensagemMedico> buscarMensagens(int id) {
        ArrayList<MensagemMedico> obj = new ArrayList<>();
        MensagemMedico msg;
        Usuario usuario;
        Medico medico;
        try {
            String sql = "select * from mensagens_medicas msg, usuario usu where usu.id_usuario=msg.id_gestante and msg.id_medico=? and autor='gestante' order by msg.id_mensagem desc";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                msg = new MensagemMedico();

                usuario = new Usuario();
                usuario.setNome(rs.getString("nome_usuario"));
                usuario.setSobrenome(rs.getString("sobrenome_usuario"));
                usuario.setFoto(rs.getString("url_img"));
                msg.setUsuario(usuario);
                msg.setDataMensagem(rs.getDate("data"));
                msg.setStatusMensagem(rs.getBoolean("status_mensagem"));
                msg.setMensagem(rs.getString("mensagem"));
                msg.setIdMensagem(rs.getInt("id_mensagem"));

              

                obj.add(msg);
            }

        } catch (Exception ex) {
            System.out.println("Erro em: MensagensDAO.buscarMensagens()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return obj;
        }
    }
    
    public boolean excluirMensagemMedico(int idMensagem){
        int x = 0;
        try {
            String sql = "delete from mensagens_medicas where id_mensagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idMensagem);
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.MensagensMedicasDAO.excluirMensagemMedico()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    public String buscarNomeImagem(int idMensagem){
        String nomeImagem = "";
        try {
            String sql = "select arquivo_medico from mensagens_medicas where id_mensagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idMensagem);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                nomeImagem = rs.getString("arquivo_medico");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.MensagensMedicasDAO.excluirMensagemMedico()"+ex.getMessage());
        }finally{
            fechaConexao();
            return nomeImagem;
        }
    }

    public void alterarStatusMensagem(int idMensagem) {
        try {
            String sql = "update mensagens_medicas set status_mensagem=true where id_mensagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idMensagem);
            int x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.MensagensMedicasDAO.alterarStatusMensagem()"+ex.getMessage());
        }finally{
            fechaConexao();
        }
    }

    public MensagemMedico buscarMensagem(int idMensagem) {
        MensagemMedico msg = new MensagemMedico();
        Usuario usu = new Usuario();
        try {
            String sql = "select * from mensagens_medicas msg, usuario usu where msg.id_gestante=usu.id_usuario and id_mensagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idMensagem);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                usu.setIdUsuario(rs.getInt("id_gestante"));
                usu.setNome(rs.getString("nome_usuario"));
                usu.setSobrenome(rs.getString("sobrenome_usuario"));
                usu.setFoto(rs.getString("url_img"));
                usu.setLogin(Cripto(rs.getString("login_usuario")));
                msg.setUsuario(usu);
                
                msg.setDataMensagem(rs.getDate("data"));
                msg.setIdMensagem(rs.getInt("id_mensagem"));
                msg.setMensagem(rs.getString("mensagem"));               
                msg.setStatusMensagem(rs.getBoolean("status_mensagem"));
                msg.setArquivo(rs.getString("arquivo_medico"));
            }
            
        } catch (Exception ex) {
            System.out.println("Erro em: MensagensDAO.buscarMensagem()!Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return msg;
        }
    }
    
    
    
    //Gestante
    
    
    
    public String pegaNomeArquivo() {
        int x = 0;
        try {
            String sql = "SELECT nextval('seq_arquivos_medicos')";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt("nextval");
            }

        } catch (Exception ex) {
            System.out.println("Erro no método pegaNomeArquivo()! Erro: " + ex.getMessage());
            ex.printStackTrace();

        } finally {
            fechaConexao();
            return String.valueOf(x);
        }
    }
    
    public boolean enviarMensagemParaMedico(MensagemMedico obj){
        int x=0;
        try {
            String sql = "insert into mensagens_medicas(mensagem, status_mensagem, autor, data, id_medico, id_gestante, arquivo_medico) "
                    + "values (?, false, 'gestante', NOW(), (select id_medico from medico where usuario_medico=?), ?, ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, obj.getMensagem());
            ps.setString(2, obj.getMedico().getUsuario());
            ps.setInt(3, obj.getUsuario().getIdUsuario());
            ps.setString(4, obj.getArquivo());
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.MensagensMedicasDAO.enviarMensagemParaMedico()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    public int quantidadeMedico(int id){
        int qnt=0;
        try {
            String sql = "select count(*) from mensagens_medicas where id_medico=? and status_mensagem=false and autor='gestante'";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                qnt = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.MensagensMedicasDAO.quantidadeMedico()"+ex.getMessage());
        }finally{
            fechaConexao();
            return qnt;
        }
    }
    
    public boolean excluirMsg(int id_mensagem){
        int x=0;
        try {
            String sql = "delete from mensagens_medicas where id_mensagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_mensagem);
            
            x = ps.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.MensagensMedicasDAO.excluirMsg()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
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
