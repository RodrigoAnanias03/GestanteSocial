/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

import Gestante_Social.model.Familiar;
import Gestante_Social.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author JONATAS
 */
public class PerfilFamiliarDAO extends Conexao {

    public PerfilFamiliarDAO() throws Exception {
    }

    public Familiar buscarDados(int id) {
        Familiar obj = new Familiar();
        try {
            String sql = "select * from familiar where id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                obj.setCidade(rs.getString("cidade_familiar"));
                obj.setCpf(rs.getString("cpf_familiar"));
                obj.setDataNascimento(rs.getDate("nascimento_familiar"));
                obj.setEmail(rs.getString("email_familiar"));
                obj.setEstado(rs.getString("estado_familiar"));
                obj.setFoto(rs.getString("foto_familiar"));
                obj.setNome(rs.getString("nome_familiar"));
                obj.setSobreNome(rs.getString("sobrenome_familiar"));
                obj.setUsuario(rs.getString("usuario_familiar"));
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.PerfilFamiliarDAO.buscarDados()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }
    
    public String buscarSenhaFamiliar(int id){
        String senha="";
        try {
            String sql = "select senha_familiar from familiar where id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                senha = rs.getString("senha_familiar");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.PerfilFamiliarDAO.buscarSenhaFamiliar()"+ex.getMessage());
        }finally{
            fechaConexao();
            return senha;
        }
    }
    
    public boolean alterarSenhaFamiliar(int id, String senha){
        int x=0;
        try {
            String sql = "update familiar set senha_familiar=? where id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, senha);
            ps.setInt(2, id);
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.PerfilFamiliarDAO.alterarSenhaFamiliar()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
}
