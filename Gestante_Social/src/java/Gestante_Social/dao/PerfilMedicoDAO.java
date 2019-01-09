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
public class PerfilMedicoDAO extends Conexao {

    public PerfilMedicoDAO() throws Exception {
    }

    public String buscarNomeImg(int id_medico) {
        String nomeImg = "";
        try {
            String sql = "select foto_medico from medico where id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_medico);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                nomeImg = rs.getString("foto_medico");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.PerfilMedicoDAO.buscarDadosMedico()" + ex.getMessage());
        } finally {
            fechaConexao();
            return nomeImg;
        }
    }

    public Medico buscarDadosMedico(int id) {
        Medico obj = new Medico();
        try {
            String sql = "select * from medico where id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                obj.setBairro(rs.getString("bairro_medico"));
                obj.setCep(rs.getString("cep_medico"));
                obj.setCidade(rs.getString("cidade_medico"));
                obj.setCpf(rs.getString("cpf_medico"));
                obj.setDataNascimento(rs.getDate("datanascimento_medico"));
                obj.setDocumento(rs.getString("documento_medico"));
                obj.setEmail(rs.getString("email_medico"));
                obj.setEstado(rs.getString("estado_medico"));
                obj.setFoto(rs.getString("foto_medico"));
                obj.setNome(rs.getString("nome_medico"));
                obj.setNumero(rs.getString("numero_medico"));
                obj.setProfissao(rs.getString("profissao_medico"));
                obj.setRua(rs.getString("rua_medico"));
                obj.setSobreNome(rs.getString("sobrenome_medico"));
                obj.setTelefone(rs.getString("telefone_medico"));
                obj.setUsuario(rs.getString("usuario_medico"));
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.PerfilMedicoDAO.buscarDadosMedico()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
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

    public String pegaNomeImagemMedico(int id) {
        String x = "";
        try {
            String sql = "select foto_medico from medico where id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x = rs.getString("foto_medico");
            }

        } catch (Exception ex) {
            System.out.println("Erro no método pegaNomeImagem()! Erro: " + ex.getMessage());
            ex.printStackTrace();

        } finally {
            fechaConexao();
            return x;
        }
    }

    public boolean editarPerfil(Medico obj) {
        int x = 0;
        try {
            String sql = "update medico set nome_medico=?, sobrenome_medico=?, telefone_medico=?, cidade_medico=?, "
                    + "estado_medico=?, rua_medico=?, numero_medico=?, bairro_medico=?, cep_medico=?, foto_medico=? "
                    + "where id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getSobreNome());
            ps.setString(3, obj.getTelefone());
            ps.setString(4, obj.getCidade());
            ps.setString(5, obj.getEstado());
            ps.setString(6, obj.getRua());
            ps.setString(7, obj.getNumero());
            ps.setString(8, obj.getBairro());
            ps.setString(9, obj.getCep());
            ps.setString(10, obj.getFoto());
            ps.setInt(11, obj.getId());

            x = ps.executeUpdate();

            cadastrarFoto(obj.getFoto(), obj.getId());
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.PerfilMedicoDAO.editarPerfil()" + ex.getMessage());
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public void cadastrarFoto(String foto, int id_medico) throws Exception {
        String sql = "insert into fotos_medico(id_medico, foto) values(?, ?)";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setInt(1, id_medico);
        ps.setString(2, foto);
        ps.executeUpdate();
    }

    public String buscarSenhaMedico(int id) {
        String senha = "";
        try {
            String sql = "select senha_medico from medico where id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                senha = rs.getString("senha_medico");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.PerfilFamiliarDAO.buscarSenhaFamiliar()" + ex.getMessage());
        } finally {
            fechaConexao();
            return senha;
        }
    }

    public boolean alterarSenhaMedico(int id, String senha) {
        int x = 0;
        try {
            String sql = "update medico set senha_medico=? where id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, senha);
            ps.setInt(2, id);

            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.PerfilFamiliarDAO.alterarSenhaFamiliar()" + ex.getMessage());
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

}
