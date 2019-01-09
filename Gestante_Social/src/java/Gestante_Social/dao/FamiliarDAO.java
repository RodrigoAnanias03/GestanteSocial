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
public class FamiliarDAO extends Conexao {

    public FamiliarDAO() throws Exception {
    }
    
    public String buscarNomeImg(int id_familiar){
        String nomeImg="";
        try {
            String sql = "select foto_familiar from familiar where id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_familiar);

            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                nomeImg = rs.getString("foto_familiar");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.FamiliarDAO.desativarConta()"+ex.getMessage());
        }finally{
            fechaConexao();
            return nomeImg;
        }
    }
    
    
    public boolean desativarConta(int id){
        int x = 0;
        try {
            String sql = "update familiar set situacao_familiar=false where id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.FamiliarDAO.desativarConta()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    public boolean editarPerfil(Familiar obj){
        int x=0;
        try {
            String sql = "update familiar set nome_familiar=?, sobrenome_familiar=?, cidade_familiar=?, estado_familiar=?, foto_familiar=? where id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getSobreNome());
            ps.setString(3, obj.getCidade());
            ps.setString(4, obj.getEstado());
            ps.setString(5, obj.getFoto());
            ps.setInt(6, obj.getId());
            
            x = ps.executeUpdate();
            
            cadastrarFoto(obj.getFoto(), obj.getId());
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.FamiliarDAO.editarPerfil()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }

    public boolean cadastrarFamiliar(Familiar obj) {
        int resultado = 0;
        try {
            String sql = "insert into familiar values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getSobreNome());
            ps.setString(4, obj.getCpf());
            ps.setString(5, obj.getEmail());
            ps.setDate(6, new java.sql.Date(obj.getDataNascimento().getTime()));
            ps.setString(7, obj.getCidade());
            ps.setString(8, obj.getEstado());
            ps.setString(9, obj.getUsuario());
            ps.setString(10, Cripto(obj.getSenha()));
            ps.setString(11, obj.getFoto());

            resultado = ps.executeUpdate();
            
            cadastrarFoto(obj.getFoto(), obj.getId());

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.FamiliarDAO.cadastrarFamiliar()" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return resultado > 0;
        }
    }
    
    public void cadastrarFoto(String foto, int id_familiar)throws Exception{
        String sql = "insert into fotos_familiar(id_familiar, foto_familiar) values(?, ?)";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setInt(1, id_familiar);
        ps.setString(2, foto);
        ps.executeUpdate();
    }
    
    public String pegaNomeImagemFamiliar(int id) {
        String x = "";
        try {
            String sql = "select foto_familiar from familiar where id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getString("foto_familiar");
            }

        } catch (Exception ex) {
            System.out.println("Erro no método pegaNomeImagem()! Erro: " + ex.getMessage());
            ex.printStackTrace();

        } finally {
            fechaConexao();
            return x;
        }
    }
    
    

    public String pegaNomeImagem() {
        int x = 0;
        try {
            String sql = "SELECT nextval('seq_img_perfil_familiar')";
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

    public String pegaId() throws Exception {
        int id = 0;
        try {
            String sql = "SELECT nextval('familiar_id_familiar_seq')";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("nextval");
            }
        } catch (Exception ex) {
            System.out.println("Erro no método PegarId! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return String.valueOf(id);
        }
    }

    public boolean verificaEmail(Familiar obj) {
        int resultado = 0;
        try {
            String sql = "select count(*) from familiar where email_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, obj.getEmail());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt("count");

            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.FamiliarDAO.verificaEmail()");
        } finally {
            fechaConexao();
            return resultado > 0;
        }
    }

    public boolean verificaLogin(Familiar obj) {
        int resultado = 0;
        try {
            String sql = "select count(*) from familiar where usuario_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, obj.getUsuario());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                resultado = rs.getInt("count");

            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.FamiliarDAO.verificaLogin()");
        } finally {
            fechaConexao();
            return resultado > 0;
        }
    }

    public boolean ativaFamiliar(int id) throws Exception {
        int x = 0;
        try {
            String sql = "UPDATE familiar set situacao_familiar = true WHERE id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro no método PegarId! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public String pegaNome(int id) throws Exception {
        String nome = null;
        try {
            String sql = "SELECT nome_familiar FROM familiar WHERE id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nome = rs.getString("nome_familiar");
            }
        } catch (Exception ex) {
            System.out.println("Erro no método PegarId! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return nome;
        }
    }

    public boolean verificaAtivo(int id) throws Exception {
        boolean result = true;

        try {

            String sql = "SELECT situacao_familiar FROM familiar WHERE id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getBoolean("situacao_familiar");
            }

        } catch (Exception ex) {
            System.out.println("Erro no mérodo verificaAtivo()! Erro " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return !result;

        }
    }

    public boolean verificaUsuario(String login) {
        boolean resultado = false;
        try {
            String sql = "select count(*) from familiar where usuario_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getInt("count") > 0) {
                    resultado = true;
                }
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.FamiliarDAO.verificaLogin()" + ex.getMessage());
        } finally {
            fechaConexao();
            return resultado;
        }
    }

    public String verificaSenha(String login) {
        String key = "";
        try {
            String sql = "select senha_familiar from familiar where usuario_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                key = Decripto(rs.getString("senha_familiar"));
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.FamiliarDAO.verificaSenha()" + ex.getMessage());
        } finally {
            fechaConexao();
            return key;
        }
    }

    public Familiar buscarInfo(String login) {
        Familiar obj = new Familiar();
        try {
            String sql = "select id_familiar, nome_familiar, sobrenome_familiar, foto_familiar from familiar where usuario_familiar = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                obj.setId(rs.getInt("id_familiar"));
                obj.setFoto(rs.getString("foto_familiar"));
                obj.setNome(rs.getString("nome_familiar"));
                obj.setSobreNome(rs.getString("sobrenome_familiar"));   
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.FamiliarDAO.buscarInfo()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }
    
    public boolean verificaSituacao(String login){
        boolean resultado = false;
        try{
            String sql = "select situacao_familiar from familiar where usuario_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                resultado = rs.getBoolean("situacao_familiar");
                
            }
            
        }catch(Exception ex){
            
        }finally{
            fechaConexao();
            return resultado;
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
