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
public class EditarAmizadeMedicoDAO extends Conexao {

    public EditarAmizadeMedicoDAO() throws Exception {
    }

    public int bucarIdAmigo(String login) {
        int id = 0;
        try {
            String sql = "select id_usuario from usuario where login_usuario = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id_usuario");
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.EditarAmizadeFamiliarDAO.bucarIdAmigo()" + ex.getMessage());
        } finally {
            fechaConexao();
            return id;
        }

    }

    public boolean adicionarAmigo(int id, int idGestante) {
        int x = 0;
        try {
            String sql = "insert into amigos_medico(status_amizade, autor_amizade, id_gestante, id_medico) "
                    + "values(false, 'medico', ?, ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idGestante);
            ps.setInt(2, id);

            x = ps.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.EditarAmizadeFamiliarDAO.aceitarAmizade()" + ex.getMessage());
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public void aceitarSolicitacao(int id, int idGestante) {
        try {
            String sql = "update amigos_medico set status_amizade = true where id_medico=? and id_gestante=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, idGestante);

            int x = ps.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.EditarAmizadeFamiliarDAO.aceitarSolicitacao()" + ex.getMessage());
        } finally {
            fechaConexao();
        }
    }
    
    
    public void excluirSolicitacao (int id, int idGestante){
        try {
            String sql="delete from amigos_medico where id_medico=? and id_gestante=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, idGestante);
            
            int x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.EditarAmizadeFamiliarDAO.excluirSolicitacao()"+ex.getMessage());
        }finally{
            fechaConexao();
        }
    }
    
    

}
