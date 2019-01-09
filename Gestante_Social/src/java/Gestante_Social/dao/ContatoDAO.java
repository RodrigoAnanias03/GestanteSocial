/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

import Gestante_Social.model.Contato;
import Gestante_Social.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author JONATAS
 */
public class ContatoDAO extends Conexao{
    public ContatoDAO()throws Exception{}
    
    public boolean ignorarContato(int id){
        int x=0;
        try {
            String sql="update contato set status=true where id_contato=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.ContatoDAO.ignorarContato()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    public boolean salvarContato(Contato obj){
        int x=0;
        try {
            String sql="insert into contato(nome, sobrenome, cpf, assunto, mensagem, email, status) values(?, ?, ?, ?, ?, ?, false)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getSobrenome());
            ps.setString(3, obj.getCpf());
            ps.setString(4, obj.getAssunto());
            ps.setString(5, obj.getMensagem());
            ps.setString(6, obj.getEmail());
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.ContatoDAO.salvarContato()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    public ArrayList<Contato> buscarContato(int assunto){
        ArrayList<Contato> contatos = new ArrayList<>();
        Contato obj;
        try {
            String x = "";
            if(assunto==1){
                x = "Ativação de conta médica";
            }else if(assunto==2){
                x = "Erro do sistema";
            }else if(assunto==3){
                x = "Dúvidas";
            }else if(assunto==4){
                x = "Outro";
            }
            
            String sql = "select * from contato where assunto=? and status=false order by id_contato desc";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, x);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                obj = new Contato();
                obj.setAssunto(rs.getString("assunto"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setId_contato(rs.getInt("id_contato"));
                obj.setMensagem(rs.getString("mensagem"));
                obj.setNome(rs.getString("nome"));
                obj.setSobrenome(rs.getString("sobrenome"));
                contatos.add(obj);
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.ContatoDAO.buscarMsgAtivacao()"+ex.getMessage());
        }finally{
            fechaConexao();
            return contatos;
        }
    }
    
    public Contato buscarMsgContato(int id){
        Contato obj = new Contato();
        try {
            String sql = "select * from contato where id_contato=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                obj.setAssunto(rs.getString("assunto"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setId_contato(rs.getInt("id_contato"));
                obj.setMensagem(rs.getString("mensagem"));
                obj.setNome(rs.getString("nome"));
                obj.setSobrenome(rs.getString("sobrenome"));
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.ContatoDAO.buscarContato()"+ex.getMessage());
        }finally{
            fechaConexao();
            return obj;
        }
    }
    
    public void validarMensagem(int id){
        try {
            String sql = "update contato set status=true where id_contato=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeQuery();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.ContatoDAO.validarMensagem()"+ex.getMessage());
        }finally{
            fechaConexao();
        }
    }
}
