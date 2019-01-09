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
import java.sql.SQLException;

/**
 *
 * @author JONATAS
 */
public class BuscarInfoAmigosMedicoDAO extends Conexao{
    public BuscarInfoAmigosMedicoDAO()throws Exception{}
    
    public Usuario buscarDados(String login){
        Usuario obj = new Usuario();
        try{
            String sql = "select login_usuario, id_usuario, nome_usuario,sobrenome_usuario, url_img, inicio_gestacao from usuario where id_usuario in (select id_usuario from usuario where login_usuario = ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                obj.setIdUsuario(rs.getInt("id_usuario"));
                obj.setNome(rs.getString("nome_usuario"));
                obj.setFoto(rs.getString("url_img"));
                obj.setInicioGestacao(rs.getDate("inicio_gestacao"));
                obj.setSobrenome(rs.getString("sobrenome_usuario"));
                obj.setLogin(rs.getString("login_usuario"));
            }
        }catch(Exception ex){
            System.out.println("Erro na classe EfetuarLoginDao BuscarDados! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
            return obj;
        }
    }
    
    public int buscarStatusAmizade(int id, int idAmigo){
        int controle=0;
        try{
            String sql = "select * from amigos_medico where (id_medico = ? and id_gestante = ?);";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, idAmigo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getBoolean("status_amizade")==true){
                    controle=1;
                }else if(rs.getBoolean("status_amizade")==false && rs.getString("autor_amizade").equals("medico")){
                    controle=2;
                }else if(rs.getBoolean("status_amizade")==false && rs.getString("autor_amizade").equals("gestante")){
                    controle=3;
                }
            }
        }catch(Exception ex){
            System.out.println("Erro em BuscarInfoAmigosDAO.buscarStatusAmizade()!Erro:"+ ex.getMessage());
            ex.printStackTrace();
        }finally{
            fechaConexao();
            return controle;
        }
        
        
    }
    
    public int pegaMaximoPostPessoal(int id) throws SQLException {
        int x = 0;
        try {

            String sql = "select max(id_postagem) from postagem where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getInt("max");
            }
        } catch (Exception ex) {
            System.out.println("Erro em : dao.PostagemDAO.pegaMaximoPostPessoal(): Erro:"+ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return x;
        }
    }
    
}
