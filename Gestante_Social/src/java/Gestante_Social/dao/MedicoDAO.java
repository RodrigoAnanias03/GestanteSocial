/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

import Gestante_Social.model.Medico;
import Gestante_Social.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author JONATAS
 */
public class MedicoDAO extends Conexao {

    public MedicoDAO() throws Exception {
    }

    

    public String pegaNomeImagem() {
        int x = 0;
        try {
            String sql = "SELECT nextval('seq_img_perfil_medico')";
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
            String sql = "SELECT nextval('medico_id_medico_seq')";
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
    
    public boolean verificaEmail(Medico obj) {
        int resultado = 0;
        try {
            String sql = "select count(*) from medico where email_medico=?";
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
    
    public boolean verificaLogin(Medico obj) {
        int resultado = 0;
        try {
            String sql = "select count(*) from medico where usuario_medico=?";
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

    public boolean cadastrarMedico(Medico obj) {
        int x=0;
        try {
            String sql = "insert into medico (id_medico, nome_medico, sobrenome_medico, cpf_medico, telefone_medico, profissao_medico, documento_medico, "
                    + "email_medico, datanascimento_medico, cidade_medico, estado_medico, rua_medico, numero_medico, bairro_medico, cep_medico, "
                    + "usuario_medico, senha_medico, foto_medico, status_medico) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false)";
            
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, obj.getId());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getSobreNome());
            ps.setString(4, obj.getCpf());
            ps.setString(5, obj.getTelefone());
            ps.setString(6, obj.getProfissao());
            ps.setString(7, obj.getDocumento());
            ps.setString(8, obj.getEmail());
            ps.setDate(9, new java.sql.Date(obj.getDataNascimento().getTime()));
            ps.setString(10, obj.getCidade());
            ps.setString(11, obj.getEstado());
            ps.setString(12, obj.getRua());
            ps.setString(13, obj.getNumero());
            ps.setString(14, obj.getBairro());
            ps.setString(15, obj.getCep());
            ps.setString(16, obj.getUsuario());
            ps.setString(17, Cripto(obj.getSenha()));
            ps.setString(18, obj.getFoto());
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.MedicoDAO.cadastrarMedico()"+ex.getMessage());
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
