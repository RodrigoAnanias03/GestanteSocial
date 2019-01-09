/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

import Gestante_Social.model.Familiar;
import Gestante_Social.model.Medico;
import Gestante_Social.model.Usuario;
import Gestante_Social.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author JONATAS
 */
public class SolicitacoesDAO extends Conexao {

    public SolicitacoesDAO() throws Exception {
    }

    public int buscarStatusGravidez(int id_gestante) {
        int r = 0;
        try {
            String sql = "select inicio_gestacao from usuario where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getDate("inicio_gestacao") != null) {
                    r = 1;
                }
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.SolicitacoesDAO.buscarStatusGravidez()" + ex.getMessage());
        } finally {
            fechaConexao();
            return r;
        }
    }

    public int bucarSolicitacoesFamiliarGestante(int id_gestante) {
        int qnt = 0;
        try {
            String sql = "select count(*) from amigos_familiar where autor_amizade='familiar' and status_amizade=false and id_gestante=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ps.setInt(1, id_gestante);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                qnt = rs.getInt("count");
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.SolicitacoesDAO.bucarSolicitacoesGestanteFamiliar()" + ex.getMessage());
        } finally {
            fechaConexao();
            return qnt;
        }
    }

    public int bucarSolicitacoesMedicoGestante(int id_gestante) {
        int qnt = 0;
        try {
            String sql = "select count(*) from amigos_medico where autor_amizade='medico' and status_amizade=false and id_gestante=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ps.setInt(1, id_gestante);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                qnt = rs.getInt("count");
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.SolicitacoesDAO.bucarSolicitacoesGestanteFamiliar()" + ex.getMessage());
        } finally {
            fechaConexao();
            return qnt;
        }
    }

    public int bucarSolicitacoesGestanteFamiliar(int id_familiar) {
        int qnt = 0;
        try {
            String sql = "select count(*) from amigos_familiar where autor_amizade='gestante' and status_amizade=false and id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ps.setInt(1, id_familiar);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                qnt = rs.getInt("count");
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.SolicitacoesDAO.bucarSolicitacoesGestanteFamiliar()" + ex.getMessage());
        } finally {
            fechaConexao();
            return qnt;
        }
    }

    public int bucarSolicitacoesGestanteMedico(int id_medico) {
        int qnt = 0;
        try {
            String sql = "select count(*) from amigos_medico med, usuario usu where usu.id_usuario = med.id_gestante and med.autor_amizade='gestante' and med.status_amizade=false and id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ps.setInt(1, id_medico);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                qnt = rs.getInt("count");
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.SolicitacoesDAO.bucarSolicitacoesGestanteFamiliar()" + ex.getMessage());
        } finally {
            fechaConexao();
            return qnt;
        }
    }

    public ArrayList<Usuario> resultadoSolicitacoesGestanteFamiliar(int id_familiar) {
        ArrayList<Usuario> gestantes = new ArrayList<>();
        Usuario obj;
        try {
            String sql = "select * from amigos_familiar fam, usuario usu where usu.id_usuario = fam.id_gestante and fam.autor_amizade='gestante' and fam.status_amizade=false and id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ps.setInt(1, id_familiar);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                obj = new Usuario();
                obj.setNome(rs.getString("nome_usuario"));
                obj.setSobrenome(rs.getString("sobrenome_usuario"));
                obj.setFoto(rs.getString("url_img"));
                obj.setLogin(Cripto(rs.getString("login_usuario")));
                gestantes.add(obj);
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.SolicitacoesDAO.resultadoSolicitacoesGestanteFamiliar()" + ex.getMessage());
        } finally {
            fechaConexao();
            return gestantes;
        }
    }

    public ArrayList<Usuario> resultadoSolicitacoesGestanteMedico(int id_medico) {
        ArrayList<Usuario> gestantes = new ArrayList<>();
        Usuario obj;
        try {
            String sql = "select * from amigos_medico med, usuario usu where usu.id_usuario = med.id_gestante and med.autor_amizade='gestante' and med.status_amizade=false and id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ps.setInt(1, id_medico);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                obj = new Usuario();
                obj.setNome(rs.getString("nome_usuario"));
                obj.setSobrenome(rs.getString("sobrenome_usuario"));
                obj.setFoto(rs.getString("url_img"));
                obj.setLogin(Cripto(rs.getString("login_usuario")));
                gestantes.add(obj);
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.SolicitacoesDAO.resultadoSolicitacoesGestanteFamiliar()" + ex.getMessage());
        } finally {
            fechaConexao();
            return gestantes;
        }
    }

    public ArrayList<Familiar> resultadoSolicitacoesFamiliarGestante(int id_gestante) {
        ArrayList<Familiar> obj = new ArrayList<>();
        try {
            String sql = "select * from amigos_familiar ami, familiar fam where ami.id_familiar = fam.id_familiar and ami.autor_amizade='familiar' and ami.status_amizade=false and id_gestante=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Familiar fam = new Familiar();
                fam.setNome(rs.getString("nome_familiar"));
                fam.setSobreNome(rs.getString("sobrenome_familiar"));
                fam.setFoto(rs.getString("foto_familiar"));
                fam.setUsuario(Cripto(rs.getString("usuario_familiar")));
                obj.add(fam);
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.SolicitacoesDAO.resultadoSolicitacoesFamiliarGestante()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }

    public ArrayList<Medico> resultadoSolicitacoesMedicoGestante(int id_gestante) {
        ArrayList<Medico> obj = new ArrayList<>();
        try {
            String sql = "select * from amigos_medico ami, medico med where ami.id_medico = med.id_medico and ami.autor_amizade='medico' and ami.status_amizade=false and id_gestante=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id_gestante);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Medico med = new Medico();
                med.setNome(rs.getString("nome_medico"));
                med.setSobreNome(rs.getString("sobrenome_medico"));
                med.setFoto(rs.getString("foto_medico"));
                med.setUsuario(Cripto(rs.getString("usuario_medico")));
                obj.add(med);
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.SolicitacoesDAO.resultadoSolicitacoesMedicoGestante()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
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
