/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.dao;

import Gestante_Social.model.Comentario;
import Gestante_Social.model.Familiar;
import Gestante_Social.model.Medico;
import Gestante_Social.model.Postagem;
import Gestante_Social.model.Usuario;
import Gestante_Social.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author JONATAS
 */
public class ComentariosDAO extends Conexao {

    public ComentariosDAO() throws Exception {
    }

    public boolean cadastrarComentario(Comentario com) {
        int x = 0;
        try {
            String sql = "insert into comentario(id_postagem, id_usuario, comentario, quem_comenta) values(?, ?, ?, 'gestante')";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, com.getPostagem().getId_postagem());
            ps.setInt(2, com.getUsuario().getIdUsuario());
            ps.setString(3, com.getComentario());

            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro em : ComentariosDAO.cadastrarComentario()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public boolean cadastrarComentarioFamiliar(Comentario com) {
        int x = 0;
        try {
            String sql = "insert into comentario(id_postagem, id_usuario, comentario, quem_comenta) values(?, ?, ?, 'familiar')";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, com.getPostagem().getId_postagem());
            ps.setInt(2, com.getFamiliar().getId());
            ps.setString(3, com.getComentario());

            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro em : ComentariosDAO.cadastrarComentario()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public boolean cadastrarComentarioMedico(Comentario com) {
        int x = 0;
        try {
            String sql = "insert into comentario(id_postagem, id_usuario, comentario, quem_comenta) values(?, ?, ?, 'medico')";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, com.getPostagem().getId_postagem());
            ps.setInt(2, com.getMedico().getId());
            ps.setString(3, com.getComentario());

            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro em : ComentariosDAO.cadastrarComentario()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public ArrayList<Comentario> BuscarComentarios(int incremento, int maximo, int id) {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        Postagem pos;
        Usuario usu;
        Familiar fam;
        Medico med;
        Comentario obj;
        try {
            //String sql = "select usu.situacao, usu.inicio_gestacao, usu.login_usuario, usu.nome_usuario||' '||usu.sobrenome_usuario as nome, usu.url_img, com.* from comentario com, usuario usu where (usu.id_usuario=com.id_usuario) and id_postagem=? and id_comentario<=? and situacao=true order by id_comentario desc limit ?";
            String sql = "select * from comentario where id_postagem=? and id_comentario<=? order by id_comentario desc limit ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, maximo);
            ps.setInt(3, incremento);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                obj = new Comentario();

                if (rs.getString("quem_comenta").equals("familiar")) {
                    //familiar
                    fam = new Familiar();
                    fam = BuscarDadosFamiliar(rs.getInt("id_usuario"), id);
                    obj.setFamiliar(fam);

                } else if (rs.getString("quem_comenta").equals("gestante")) {

                    //usuario
                    usu = new Usuario();
                    usu = BuscarDadosGestante(rs.getInt("id_usuario"), id);
                    obj.setUsuario(usu);
                } else {

                    //médico
                    med = new Medico();
                    med = BuscarDadosMedico(rs.getInt("id_usuario"), id);
                    obj.setMedico(med);

                }

                //postagem
                pos = new Postagem();
                pos.setId_postagem(rs.getInt("id_postagem"));

                //comentario
                obj.setIdComentario(rs.getInt("id_comentario"));
                obj.setComentario(rs.getString("comentario"));

                obj.setPostagem(pos);

                comentarios.add(obj);

            }
        } catch (Exception ex) {
            System.out.println("Erro em: ComentariosDAO.BuscarComentarios()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return comentarios;
        }
    }

    public String buscarLoginUsuario(int id) {
        String login = "";
        try {
            //String sql = "select usu.login_usuario from postagem pos, usuario usu where pos.id_usuario=usu.id_usuario and id_postagem = ?";
            String sql = "select login_usuario from usuario where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                login = rs.getString("login_usuario");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.ComentariosDAO.buscarLoginUsuario()" + ex.getMessage());
        } finally {
            return Cripto(login);
        }
    }

    public Familiar BuscarDadosFamiliar(int id, int idPostagem) {
        Familiar obj = new Familiar();
        String x;
        try {
            String sql = "select * from familiar where id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj.setNome(rs.getString("nome_familiar"));
                obj.setFoto(rs.getString("foto_familiar"));
                obj.setUsuario(buscarLoginUsuario(idPostagem));
                obj.setSobreNome(rs.getString("sobrenome_familiar"));
                obj.setId(rs.getInt("id_familiar"));

            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.ComentariosDAO.BuscarDadosFamiliar()" + ex.getMessage());
            x = String.valueOf(ex);
        } finally {

            return obj;
        }
    }

    public Medico BuscarDadosMedico(int id, int idPostagem) {
        Medico obj = new Medico();
        String x;
        try {
            String sql = "select * from medico where id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj.setNome(rs.getString("nome_medico"));
                obj.setFoto(rs.getString("foto_medico"));
                obj.setUsuario(buscarLoginUsuario(idPostagem));
                obj.setSobreNome(rs.getString("sobrenome_medico"));
                obj.setId(rs.getInt("id_medico"));

            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.ComentariosDAO.BuscarDadosFamiliar()" + ex.getMessage());
            x = String.valueOf(ex);
        } finally {

            return obj;
        }
    }

    public Usuario BuscarDadosGestante(int id, int idPostagem) {
        Usuario usu = new Usuario();
        try {
            String sql = "select nome_usuario||' '||sobrenome_usuario as nome, * from usuario where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setFoto(rs.getString("url_img"));
                usu.setLogin(buscarLoginUsuario(rs.getInt("id_usuario")));
                usu.setNome(rs.getString("nome"));
                usu.setInicioGestacao(rs.getDate("inicio_gestacao"));
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.ComentariosDAO.BuscarDadosFamiliar()" + ex.getMessage());
        } finally {

            return usu;
        }
    }

    public Usuario buscarDadosIndex(int id) {
        Usuario obj = new Usuario();
        try {
            String sql = "select id_usuario, nome_usuario, url_img from usuario where id_usuario in (select id_usuario from usuario where id_usuario = ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj.setIdUsuario(rs.getInt("id_usuario"));
                obj.setNome(rs.getString("nome_usuario"));
                obj.setFoto(rs.getString("url_img"));
            }
        } catch (Exception ex) {
            System.out.println("Erro na classe EfetuarLoginDao BuscarDadosIndex! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return obj;
        }
    }

    public int pegaMaximo(int id) {
        int result = 0;
        try {
            String sql = "select max(id_comentario) from comentario where id_postagem = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("max");
            }
        } catch (Exception ex) {
            System.out.println("Erro em: ComentariosDAO.pegaMaximo()! Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return result;
        }
    }

    public Usuario buscarDados(String login) {
        Usuario obj = new Usuario();
        try {
            String sql = "select id_usuario, nome_usuario||' '||sobrenome_usuario as nome, url_img from usuario where id_usuario in (select id_usuario from usuario where login_usuario = ?)";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj.setIdUsuario(rs.getInt("id_usuario"));
                obj.setNome(rs.getString("nome"));
                obj.setFoto(rs.getString("url_img"));
            }
        } catch (Exception ex) {
            System.out.println("Erro na classe EfetuarLoginDao BuscarDados! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return obj;
        }
    }

    public Postagem buscarPostagem(int id) {
        Postagem obj = new Postagem();
        try {
            String sql = "select * from postagem where id_postagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj.setMensagem(rs.getString("mensagem"));
                obj.setUrl_imagem(rs.getString("url_imagem"));
            }
        } catch (Exception ex) {
            System.out.println("Erro na classe EfetuarLoginDao BuscarDadosIndex! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return obj;
        }
    }

    public boolean excluirComentario(int idComentario) {
        int resultado = 0;
        try {
            String sql = "delete from comentario where id_comentario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idComentario);
            resultado = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Erro em: ComentariosDAO.excluirComentario()" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return resultado > 0;
        }
    }

    public String BuscarLoginAmigo(int idPostagem) {
        String login="";
        try {
            String sql = "select login_usuario from postagem pos, usuario usu where pos.id_usuario=usu.id_usuario and pos.id_postagem=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, idPostagem);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                login= Cripto(rs.getString("login_usuario"));
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.ComentariosDAO.BuscarLoginAmigo()" + ex.getMessage());
        } finally {
            fechaConexao();
            return login;
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

}
