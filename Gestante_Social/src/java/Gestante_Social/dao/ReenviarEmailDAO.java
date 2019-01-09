/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

import Gestante_Social.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author JONATAS
 */
public class ReenviarEmailDAO extends Conexao{
    public ReenviarEmailDAO()throws Exception{}
    
    public int pegarId(String email){
        int id=0;
        try{
            String sql = "select id_usuario from usuario where email_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id_usuario");
            }
        }catch(Exception ex){
            System.out.println("Erro! pegarId():"+ex.getMessage());
        }finally{
            fechaConexao();
            return id;
        }
    }
    
    public int pegarIdFamiliar(String email){
        int id=0;
        try{
            String sql = "select id_familiar from familiar where email_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id_familiar");
            }
        }catch(Exception ex){
            System.out.println("Erro! pegarId():"+ex.getMessage());
        }finally{
            fechaConexao();
            return id;
        }
    }
    
    public boolean verEmail(String email){
        int x=0;
        try{
            String sql = "select count(*) from usuario where email_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                x = rs.getInt("count");
            }
        }catch(Exception ex){
            System.out.println("Erro! pegarEmail():"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    public boolean verEmailFamiliar(String email){
        int x=0;
        try{
            String sql = "select count(*) from familiar where email_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                x = rs.getInt("count");
            }
        }catch(Exception ex){
            System.out.println("Erro! pegarEmail():"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
}
