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
import java.util.ArrayList;

/**
 *
 * @author JONATAS
 */
public class BuscarMedicoDAO extends Conexao {

    public BuscarMedicoDAO() throws Exception {
    }

    public ArrayList<Medico> buscarMedicos(int id) {
        ArrayList<Medico> obj = new ArrayList<>();
        Medico med;

        try {

            String sql = "select * from medico med, amigos_medico ami where med.id_medico=ami.id_medico and id_gestante=? and status_amizade=true and status_medico=true;";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                med = new Medico();
                med.setFoto(rs.getString("foto_medico"));
                med.setNome(rs.getString("nome_medico"));
                med.setSobreNome(rs.getString("sobrenome_medico"));
                med.setTelefone(rs.getString("telefone_medico"));
                med.setUsuario(Cripto(rs.getString("usuario_medico")));
                med.setProfissao(rs.getString("profissao_medico"));
                obj.add(med);
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarMedicoDAO.buscarFamiliares()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }

    public boolean exclurAmizade(int id, String loginMedico) {
        int x = 0;
        try {
            String sql = "delete from amigos_medico where id_gestante=? and id_medico=(select id_medico from medico where usuario_medico=?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, Decripto(loginMedico));
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarMedicoDAO.exclurAmizade()" + ex.getMessage());
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public ArrayList<Medico> pesquisarMedico(String nome, int id_gestante) {
        ArrayList<Medico> obj = new ArrayList<>();
        try {
            String sql = "select * from medico where upper(retira_acentuacao(nome_medico ||' '|| sobrenome_medico)) like ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Medico med = new Medico();
                med.setNome(rs.getString("nome_medico"));
                med.setSobreNome(rs.getString("sobrenome_medico"));
                med.setFoto(rs.getString("foto_medico"));
                med.setUsuario(Cripto(rs.getString("usuario_medico")));
                med.setId(0);
                if (verificarAmizade(id_gestante, rs.getInt("id_medico"))) {
                    //aceitar solicitacao
                    med.setId(1);
                }
                if (verificarStatusAmizade(id_gestante, rs.getInt("id_medico"))) {
                    //aguardar aprovação
                    med.setId(2);
                }
                if (verificarSeSaoAmigos(id_gestante, rs.getInt("id_medico"))) {
                    //aguardar aprovação
                    med.setId(3);
                }

                obj.add(med);

            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarFamiliarDAO.ArrayList()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }

    }

    public boolean verificarSeSaoAmigos(int id_gestante, int id_medico) {
        int x = 0;
        try {
            String sql = "select count(*) from amigos_medico where id_gestante=? and id_medico=? and status_amizade=true";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);
            ps.setInt(2, id_medico);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                x = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarFamiliarDAO.verificarStatusAmizade()" + ex.getMessage());
        } finally {
            return x > 0;
        }
    }

    public boolean verificarStatusAmizade(int id_gestante, int id_medico) {
        int x = 0;
        try {
            String sql = "select count(*) from amigos_medico where id_gestante=? and id_medico=? and status_amizade=false and autor_amizade='gestante'";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);
            ps.setInt(2, id_medico);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                x = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarFamiliarDAO.verificarStatusAmizade()" + ex.getMessage());
        } finally {
            return x > 0;
        }
    }

    public boolean verificarAmizade(int id_gestante, int id_medico) {
        int x = 0;
        try {
            String sql = "select count(*) from amigos_medico where id_gestante=? and id_medico=? and status_amizade=false and autor_amizade='medico'";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);
            ps.setInt(2, id_medico);
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
    
    public boolean AdicionarMedico(int id_gestante, String login_medico) {
        int x = 0;
        String erro="";
        try {
            String sql = "insert into amigos_medico (id_gestante, id_medico, autor_amizade, status_amizade) values (?, (select id_medico from medico where usuario_medico=?), 'gestante', false)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);
            ps.setString(2, Decripto(login_medico));
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarMeicoDAO.AdicionarMedico()" + ex.getMessage());
            erro = String.valueOf(ex);
        } finally {
            fechaConexao();
            return x > 0;
        }
    }
    
    public void aceitarSolicitacao(int id_gestante, String login_medico){
        try {
            String sql = "update amigos_medico set status_amizade = true where id_gestante=? and id_medico = (select id_medico from medico where usuario_medico=?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);
            ps.setString(2, Decripto(login_medico));
            
            int x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.BuscarMedicoDAO.aceitarSolicitacao()"+ex.getMessage());
        }finally{
            fechaConexao();
        }
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
}
