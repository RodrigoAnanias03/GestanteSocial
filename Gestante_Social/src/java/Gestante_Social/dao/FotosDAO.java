/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

import Gestante_Social.model.Postagem;
import Gestante_Social.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author JONATAS
 */
public class FotosDAO extends Conexao {

    public FotosDAO() throws Exception {
    }

    public ResultSet BuscarFotos(int id){
        ResultSet rs = null;
        try {
            String sql = "select url_imagem from postagem where id_usuario=? and url_imagem!=''";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
           
            
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.FotosDAO.BuscarFotos()"+ex.getMessage());
        }finally{
            fechaConexao();
            return rs;
        }
    }
}
