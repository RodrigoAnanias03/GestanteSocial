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
import java.util.ArrayList;

/**
 *
 * @author JONATAS
 */
public class BuscarFamiliarDAO extends Conexao {

    public BuscarFamiliarDAO() throws Exception {
    }

    public ArrayList<Familiar> buscarFamiliares(int id) {
        ArrayList<Familiar> obj = new ArrayList<>();
        Familiar fam;

        try {

            String sql = "select * from amigos_familiar ami, familiar fam where fam.id_familiar = ami.id_familiar and fam.situacao_familiar=true and ami.id_gestante = ? and status_amizade=true";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                fam = new Familiar();
                fam.setFoto(rs.getString("foto_familiar"));
                fam.setNome(rs.getString("nome_familiar"));
                fam.setSobreNome(rs.getString("sobrenome_familiar"));
                fam.setUsuario(Cripto(rs.getString("usuario_familiar")));
                obj.add(fam);
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarFamiliarDAO.buscarFamiliares()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }

    public boolean exclurAmizade(int id, String loginFamiliar) {
        int x = 0;
        try {
            String sql = "delete from amigos_familiar where id_gestante = ? and id_familiar = (select id_familiar from familiar where usuario_familiar=?);";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, Decripto(loginFamiliar));
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarFamiliarDAO.exclurAmizade()" + ex.getMessage());
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public ArrayList<Familiar> pesquisarFamiliar(String nome, int id_gestante) {
        ArrayList<Familiar> obj = new ArrayList<>();
        try {
            String sql = "select * from familiar where upper(retira_acentuacao(nome_familiar ||' '|| sobrenome_familiar)) like ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Familiar fam = new Familiar();
                fam.setNome(rs.getString("nome_familiar"));
                fam.setSobreNome(rs.getString("sobrenome_familiar"));
                fam.setFoto(rs.getString("foto_familiar"));
                fam.setUsuario(Cripto(rs.getString("usuario_familiar")));
                fam.setId(0);
                if (verificarAmizade(id_gestante, rs.getInt("id_familiar"))) {
                    //aceitar solicitacao
                    fam.setId(1);
                }
                if(verificarStatusAmizade(id_gestante, rs.getInt("id_familiar"))){
                    //aguardar aprovação
                    fam.setId(2);
                }
                if(verificarSeSaoAmigos(id_gestante, rs.getInt("id_familiar"))){
                    //aguardar aprovação
                    fam.setId(3);
                }
                
                
                
                obj.add(fam);

            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarFamiliarDAO.ArrayList()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }
    
    public boolean verificarSeSaoAmigos(int id_gestante, int id_familiar){
        int x=0;
        try {
            String sql = "select count(*) from amigos_familiar where id_gestante=? and id_familiar=? and status_amizade=true";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);
            ps.setInt(2, id_familiar);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                x = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarFamiliarDAO.verificarStatusAmizade()"+ex.getMessage());
        }finally{
            return x>0;
        }
    }
    
    public boolean verificarStatusAmizade(int id_gestante, int id_familiar){
        int x=0;
        try {
            String sql = "select count(*) from amigos_familiar where id_gestante=? and id_familiar=? and status_amizade=false and autor_amizade='gestante'";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);
            ps.setInt(2, id_familiar);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                x = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarFamiliarDAO.verificarStatusAmizade()"+ex.getMessage());
        }finally{
            return x>0;
        }
    }

    public boolean AdicionarFamiliar(int id_gestante, String login_familiar) {
        int x = 0;
        String erro="";
        try {
            String sql = "insert into amigos_familiar (id_gestante, id_familiar, autor_amizade, status_amizade) values (?, (select id_familiar from familiar where usuario_familiar=?), 'gestante', false)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);
            ps.setString(2, Decripto(login_familiar));
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarFamiliarDAO.AdicionarFamiliar()" + ex.getMessage());
            erro = String.valueOf(ex);
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public boolean verificarAmizade(int id_gestante, int id_familiar) {
        int x = 0;
        try {
            String sql = "select count(*) from amigos_familiar where id_gestante=? and id_familiar=? and status_amizade=false and autor_amizade='familiar'";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);
            ps.setInt(2, id_familiar);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                x = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarFamiliarDAO.verificarAmizade()" + ex.getMessage());
        } finally {
            return x > 0;
        }
    }
    
    public void aceitarSolicitacao(int id_gestante, String login_familiar){
        try {
            String sql = "update amigos_familiar set status_amizade = true where id_gestante=? and id_familiar = (select id_familiar from familiar where usuario_familiar=?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);
            ps.setString(2, Decripto(login_familiar));
            
            int x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarFamiliarDAO.aceitarSolicitacao()"+ex.getMessage());
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

    public static String Decripto(String senha) {
        //Descriptografa a String passada por parâmetro
        int contador, tamanho, codigoASCII;
        String senhaCriptografada = "";
        tamanho = senha.length();
        //senha = senha.toUpperCase();
        contador = 0;
        while (contador < tamanho) {
            codigoASCII = senha.charAt(contador) - 130;
            senhaCriptografada = senhaCriptografada + (char) codigoASCII;
            contador++;
        }
        return senhaCriptografada;
    }

}
