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
public class MensagensDAO extends Conexao {

    public MensagensDAO() throws Exception {
    }
    
    public boolean enviarMensagem(Mensagem msg){
        int x=0;
        try{
            String sql = "insert into mensagem_usuarios(mensagem, id_remetente, id_destinatario, data_mensagem, status_mensagem) values(?, ?, (select id_usuario from usuario where login_usuario=?), NOW(), false )";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, msg.getMensagem());
            ps.setInt(2, msg.getRemetente().getIdUsuario());
            ps.setString(3, msg.getDestinatario().getLogin());
            
            x = ps.executeUpdate();
            
        }catch(Exception ex){
            System.out.println("Erro em: dao.MensagensDAO.enviarMensagem()! Erro: "+ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    public ArrayList<MensagemMedico> buscarMensagensMedico(int id_gestante){
        ArrayList<MensagemMedico> obj = new ArrayList<>();
        Medico med;
        MensagemMedico msg;
        try {
            
            String sql = "select * from mensagens_medicas msg, medico med where msg.id_medico=med.id_medico and msg.id_gestante=? and autor='medico' order by msg.id_mensagem desc";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
               msg = new MensagemMedico();
               
               med = new Medico();
               med.setNome(rs.getString("nome_medico"));
               med.setSobreNome(rs.getString("sobrenome_medico"));
               med.setFoto(rs.getString("foto_medico"));
               
               msg.setMedico(med);
               
               msg.setStatusMensagem(rs.getBoolean("status_mensagem"));
               msg.setIdMensagem(rs.getInt("id_mensagem"));
               msg.setMensagem(rs.getString("mensagem"));
               msg.setDataMensagem(rs.getDate("data"));
               
               obj.add(msg);
               
               
            }
            
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.MensagensDAO.buscarMensagensMedico()"+ex.getMessage());
        }finally{
            fechaConexao();
            return obj;
        }
    }

    public ArrayList<Mensagem> buscarMensagens(int id) {
        ArrayList<Mensagem> obj = new ArrayList<>();
        Mensagem msg;
        Usuario remetente;
        Usuario destinatario;
        try {
            String sql = "select * from mensagem_usuarios msg, usuario usu where usu.id_usuario = msg.id_remetente and msg.id_destinatario=? order by id_mensagem desc";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                msg = new Mensagem();
                remetente = new Usuario();
                destinatario = new Usuario();

                //remetente
                remetente.setIdUsuario(rs.getInt("id_usuario"));
                remetente.setFoto(rs.getString("url_img"));
                remetente.setLogin(rs.getString("login_usuario"));
                remetente.setNome(rs.getString("nome_usuario"));
                remetente.setSobrenome(rs.getString("sobrenome_usuario"));

                //destinatario
                destinatario.setIdUsuario(rs.getInt("id_destinatario"));

                msg.setIdMensagem(rs.getInt("id_mensagem"));
                msg.setMensagem(rs.getString("mensagem"));
                msg.setDataMensagem(rs.getDate("data_mensagem"));
                msg.setStatusMensagem(rs.getBoolean("status_mensagem"));
                msg.setRemetente(remetente);
                msg.setDestinatario(destinatario);

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

    public boolean excluirMensagem(int idMensagem) {
        int x = 0;
        try {
            String sql = "delete from mensagem_usuarios where id_mensagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idMensagem);

            x = ps.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Erro em: MensagensDAO.excluirMensagem()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public Mensagem buscarMensagem(int idMensagem) {
        Mensagem msg = new Mensagem();
        Usuario remetente = new Usuario();
        try {
            String sql = "select * from mensagem_usuarios msg, usuario usu where msg.id_remetente=usu.id_usuario and id_mensagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idMensagem);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                remetente.setIdUsuario(rs.getInt("id_usuario"));
                remetente.setNome(rs.getString("nome_usuario"));
                remetente.setSobrenome(rs.getString("sobrenome_usuario"));
                remetente.setFoto(rs.getString("url_img"));
                remetente.setLogin(Cripto(rs.getString("login_usuario")));
                msg.setRemetente(remetente);
                
                msg.setDataMensagem(rs.getDate("data_mensagem"));
                msg.setIdMensagem(rs.getInt("id_mensagem"));
                msg.setMensagem(rs.getString("mensagem"));               
                msg.setStatusMensagem(rs.getBoolean("status_mensagem"));
            }
            
        } catch (Exception ex) {
            System.out.println("Erro em: MensagensDAO.buscarMensagem()!Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return msg;
        }
    }
    
    public int mensagensNaoLidas(Usuario user){
        int quantidade = 0;
        try{
            String sql = "select count (*) from mensagem_usuarios where id_destinatario=? and status_mensagem=false";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, user.getIdUsuario());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                quantidade = rs.getInt("count");
            }
            
        }catch(Exception ex){
            System.out.println("Erro em: dao.MensagensDAO.mensagensNaoLidas()! Erro: "+ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
            return quantidade;
        }
    }
    
    public int mensagensMedicasNaoLidas(Usuario user){
        int quantidade = 0;
        try{
            String sql = "select count (*) from mensagens_medicas where id_gestante=? and status_mensagem=false and autor='medico'";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, user.getIdUsuario());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                quantidade = rs.getInt("count");
            }
            
        }catch(Exception ex){
            System.out.println("Erro em: dao.MensagensDAO.mensagensNaoLidas()! Erro: "+ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
            return quantidade;
        }
    }
    
    public void alterarStatusMensagem(int idMensagem){
        try{
            String sql = "update mensagem_usuarios set status_mensagem=true where id_mensagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idMensagem);
            
            int x = ps.executeUpdate();
            
        }catch(Exception ex){
            System.out.println("Erro em: MensagensDAO.alterarStatusMensagem()! Erro: "+ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
        }
    }
    
    public MensagemMedico buscarMensagemMedico(int idMensagem) {
        MensagemMedico msg = new MensagemMedico();
        Medico obj = new Medico();
        try {
            String sql = "select * from mensagens_medicas msg, medico med where msg.id_medico=med.id_medico and id_mensagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idMensagem);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                obj.setNome(rs.getString("nome_medico"));
                obj.setSobreNome(rs.getString("sobrenome_medico"));
                obj.setId(rs.getInt("id_medico"));
                obj.setFoto(rs.getString("foto_medico"));
                obj.setUsuario(Cripto(rs.getString("usuario_medico")));
                
                msg.setDataMensagem(rs.getDate("data"));
                msg.setIdMensagem(rs.getInt("id_mensagem"));
                msg.setMensagem(rs.getString("mensagem"));
                msg.setMedico(obj);
            }
            
        } catch (Exception ex) {
            System.out.println("Erro em: MensagensDAO.buscarMensagem()!Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return msg;
        }
    }
    
    public void alterarStatusMensagemMedico(int idMensagem){
        try{
            String sql = "update mensagens_medicas set status_mensagem=true where id_mensagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idMensagem);
            
            int x = ps.executeUpdate();
            
        }catch(Exception ex){
            System.out.println("Erro em: MensagensDAO.alterarStatusMensagem()! Erro: "+ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
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
