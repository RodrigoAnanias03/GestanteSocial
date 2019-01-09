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
public class AdministradorDAO extends Conexao {

    public AdministradorDAO() throws Exception {
    }
    
     public boolean editarPerfilFamiliar(Familiar obj) {
         int x=0;
         try {
             String sql = "update familiar set nome_familiar=?, sobrenome_familiar=?, cpf_familiar=?, "
                     + "nascimento_familiar=?, cidade_familiar=?, estado_familiar=? where id_familiar=?";
             PreparedStatement ps = getConexao().prepareStatement(sql);
             ps.setString(1, obj.getNome());
             ps.setString(2, obj.getSobreNome());
             ps.setString(3, obj.getCpf());
             ps.setDate(4, new java.sql.Date(obj.getDataNascimento().getTime()));
             ps.setString(5, obj.getCidade());
             ps.setString(6, obj.getEstado());
             ps.setInt(7, obj.getId());
             
             x = ps.executeUpdate();
         } catch (Exception ex) {
             System.out.println("Gestante_Social.dao.AdministradorDAO.editarPerfilFamiliar()"+ex.getMessage());
         }finally{
             fechaConexao();
             return x>0;
         }
         
     }
    
    public boolean ativarGestante(int id){
        int x=0;
        try {
            String sql = "update usuario set situacao=true where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.ativarGestante()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    
    
    public boolean ativarFamiliar(int id){
        int x=0;
        try {
            String sql = "update familiar set situacao_familiar=true where id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.ativarFamiliar()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    public boolean desativarGestante(int id){
        int x=0;
        try {
            String sql = "update usuario set situacao=false where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.desativarGestante()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    public boolean desativarMedico(int id){
        int x=0;
        try {
            String sql = "update medico set status_medico=false where id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.desativarMedico()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }
    
    public boolean desativarFamiliar(int id){
        int x=0;
        try {
            String sql = "update familiar set situacao_familiar=false where id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.desativarFamiliar()"+ex.getMessage());
        }finally{
            fechaConexao();
            return x>0;
        }
    }

    public boolean editarPerfilGestante(Usuario obj) {
        int x = 0;
        try {
            String sql = "update usuario set nome_usuario=?, sobrenome_usuario=?, email_usuario=?,login_usuario=?,"
                    + "datanascimento_usuario=?, inicio_gestacao=?,cpf_usuario=?, numero_usuario=?, "
                    + "telefone_usuario=?, bairro_usuario=?, cep_usuario=?, rua_usuario=?, "
                    + "estado_usuario=?, cidade_usuario=? where id_usuario=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getSobrenome());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getLogin());
            ps.setDate(5, new java.sql.Date(obj.getDataNascimento().getTime()));
            if(obj.getInicioGestacao()!=null){
                ps.setDate(6, new java.sql.Date(obj.getInicioGestacao().getTime()));
            }else{
                ps.setDate(6, null);
            }
            
            ps.setString(7, obj.getCpf());
            ps.setString(8, obj.getNumero());
            ps.setString(9, obj.getTelefone());
            ps.setString(10, obj.getBairro());
            ps.setString(11, obj.getCep());
            ps.setString(12, obj.getRua());
            ps.setString(13, obj.getEstado());
            ps.setString(14, obj.getCidade());
            ps.setInt(15, obj.getIdUsuario());

            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.editarPerfilGestante()" + ex.getMessage());
        } finally {
            fechaConexao();
            return x > 0;
        }
    }
    
    public boolean editarPerfilMedico(Medico obj) {
        int x = 0;
        try {
            String sql = "update medico set nome_medico=?, cpf_medico=?, sobrenome_medico=?, telefone_medico=?, "
                    + "documento_medico=?, datanascimento_medico=?, cidade_medico=?, estado_medico=?,"
                    + "rua_medico=?, numero_medico=?, bairro_medico=?, cep_medico=? where id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getCpf());
            ps.setString(3, obj.getSobreNome());
            ps.setString(4, obj.getTelefone());
            ps.setString(5, obj.getDocumento());
            ps.setDate(6, new java.sql.Date(obj.getDataNascimento().getTime()));
            ps.setString(7, obj.getCidade());
            ps.setString(8, obj.getEstado());
            ps.setString(9, obj.getRua());
            ps.setString(10, obj.getNumero());
            ps.setString(11, obj.getBairro());
            ps.setString(12, obj.getCep());
            ps.setInt(13, obj.getId());

            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.editarPerfilMedico()" + ex.getMessage());
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public Usuario buscarPerfilGestante(int id) {
        Usuario obj = new Usuario();
        try {
            String sql = "select * from usuario where id_usuario = ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj.setBairro(rs.getString("bairro_usuario"));
                obj.setCep(rs.getString("cep_usuario"));
                obj.setCidade(rs.getString("cidade_usuario"));
                obj.setCpf(rs.getString("cpf_usuario"));
                obj.setDataNascimento(rs.getDate("datanascimento_usuario"));
                obj.setEmail(rs.getString("email_usuario"));
                obj.setEstado(rs.getString("estado_usuario"));
                obj.setFoto(rs.getString("url_img"));
                obj.setIdUsuario(rs.getInt("id_usuario"));
                obj.setInicioGestacao(rs.getDate("inicio_gestacao"));
                obj.setLogin(rs.getString("login_usuario"));
                obj.setNome(rs.getString("nome_usuario"));
                obj.setNumero(rs.getString("numero_usuario"));
                obj.setRua(rs.getString("rua_usuario"));
                obj.setSenha(rs.getString("senha_usuario"));
                obj.setSobrenome(rs.getString("sobrenome_usuario"));
                obj.setTelefone(rs.getString("telefone_usuario"));
            }
        } catch (Exception ex) {
            System.out.println("Erro em: Gestante_Social.dao.PerfilDAO.buscarPerfil()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return obj;
        }
    }
    
    public Medico buscarPerfilMedico(int id) {
        Medico obj = new Medico();
        try {
            String sql = "select * from medico where id_medico = ?";
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
                obj.setId(rs.getInt("id_medico"));
            }
        } catch (Exception ex) {
            System.out.println("Erro em: Gestante_Social.dao.PerfilDAO.buscarPerfilMedico()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return obj;
        }
    }
    
    
    
    public Familiar buscarPerfilFamiliar(int id) {
        Familiar obj = new Familiar();
        try {
            String sql = "select * from familiar where id_familiar=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                obj.setCidade(rs.getString("cidade_familiar"));
                obj.setCpf(rs.getString("cpf_familiar"));
                obj.setDataNascimento(rs.getDate("nascimento_familiar"));
                obj.setEmail(rs.getString("email_familiar"));
                obj.setEstado(rs.getString("estado_familiar"));
                obj.setFoto(rs.getString("foto_familiar"));
                obj.setNome(rs.getString("nome_familiar"));
                obj.setSobreNome(rs.getString("sobrenome_familiar"));
                obj.setUsuario(rs.getString("usuario_familiar"));
                obj.setId(rs.getInt("id_familiar"));
            }
        } catch (Exception ex) {
            System.out.println("Erro em: Gestante_Social.dao.PerfilDAO.buscarPerfilFamiliar()! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            fechaConexao();
            return obj;
        }
    }
    

    public ArrayList<Usuario> buscarGestantes(String nome, String email, String cpf) {
        ArrayList<Usuario> obj = new ArrayList<>();
        Usuario usu;
        try {
            String sql = "select * from usuario where upper(retira_acentuacao(nome_usuario ||' '|| sobrenome_usuario)) like ? and cpf_usuario like ? and email_usuario like ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ps.setString(2, "%" + cpf + "%");
            ps.setString(3, "%" + email + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usu = new Usuario();
                usu.setNome(rs.getString("nome_usuario"));
                usu.setSobrenome(rs.getString("sobrenome_usuario"));
                usu.setFoto(rs.getString("url_img"));
                usu.setCpf(rs.getString("cpf_usuario"));
                usu.setEmail(rs.getString("email_usuario"));
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setEstado(String.valueOf(rs.getBoolean("situacao")));
                obj.add(usu);
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.buscarGestantes()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }
    
    public ArrayList<Medico> buscarMedicos(String nome, String email, String cpf) {
        ArrayList<Medico> obj = new ArrayList<>();
        Medico med;
        try {
            String sql = "select * from medico where upper(retira_acentuacao(nome_medico ||' '|| sobrenome_medico)) like ? and cpf_medico like ? and email_medico like ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ps.setString(2, "%" + cpf + "%");
            ps.setString(3, "%" + email + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                med = new Medico();
                med.setNome(rs.getString("nome_medico"));
                med.setSobreNome(rs.getString("sobrenome_medico"));
                med.setFoto(rs.getString("foto_medico"));
                med.setCpf(rs.getString("cpf_medico"));
                med.setEmail(rs.getString("email_medico"));
                med.setId(rs.getInt("id_medico"));
                med.setEstado(String.valueOf(rs.getBoolean("status_medico")));
                med.setProfissao(rs.getString("profissao_medico"));
                obj.add(med);
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.buscarMedicos()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }
    
    
    public ArrayList<Familiar> buscarFamiliares(String nome, String email, String cpf) {
        ArrayList<Familiar> obj = new ArrayList<>();
        Familiar fam;
        try {
            String sql = "select * from familiar where upper(retira_acentuacao(nome_familiar ||' '|| sobrenome_familiar)) like ? and cpf_familiar like ? and email_familiar like ?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ps.setString(2, "%" + cpf + "%");
            ps.setString(3, "%" + email + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                fam = new Familiar();
                fam.setNome(rs.getString("nome_familiar"));
                fam.setSobreNome(rs.getString("sobrenome_familiar"));
                fam.setFoto(rs.getString("foto_familiar"));
                fam.setCpf(rs.getString("cpf_familiar"));
                fam.setEmail(rs.getString("email_familiar"));
                fam.setId(rs.getInt("id_familiar"));
                fam.setEstado(String.valueOf(rs.getBoolean("situacao_familiar")));
                obj.add(fam);
            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.buscarFamiliares()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }

    public boolean loginAdm(String senha) {
        boolean result = false;
        try {
            String sql = "select senha from administrador";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("senha").equals(Cripto(senha))) {
                    result = true;
                }
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.loginAdm()" + ex.getMessage());
        } finally {
            fechaConexao();
            return result;
        }
    }

    public int buscarQntSolProfissionais() {
        int qnt = 0;
        try {
            String sql = "select count(*) from medico where status_medico=false";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                qnt = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.buscarQntSolProfissionais()" + ex.getMessage());
        } finally {
            fechaConexao();
            return qnt;
        }
    }

    public ArrayList<Medico> buscarSolicitacoesMedicas() {
        ArrayList<Medico> medicos = new ArrayList<>();
        Medico obj;
        try {
            String sql = "select * from medico where status_medico=false";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                obj = new Medico();
                obj.setNome(rs.getString("nome_medico"));
                obj.setSobreNome(rs.getString("sobrenome_medico"));
                obj.setFoto(rs.getString("foto_medico"));
                obj.setId(rs.getInt("id_medico"));
                medicos.add(obj);

            }

        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.buscarSolicitacoesMedicas()" + ex.getMessage());
        } finally {
            fechaConexao();
            return medicos;
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

                obj.setId(rs.getInt("id_medico"));
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
            System.out.println("Gestante_Social.dao.AdministradorDAO.buscarDadosMedico()" + ex.getMessage());
        } finally {
            fechaConexao();
            return obj;
        }
    }

    public boolean ativarMedico(int id) {
        int x = 0;
        try {
            String sql = "update medico set status_medico=true where id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);

            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.ativarMedico()" + ex.getMessage());
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public boolean excluirMedico(int id) {
        int x = 0;
        try {
            String sql = "delete from medico where id_medico=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setInt(1, id);

            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.excluirMedico()" + ex.getMessage());

        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public int quantidadeGestante() {
        int qnt = 0;
        try {
            String sql = "select count(*) from usuario where situacao=true";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                qnt = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.quantidadeGestante()" + ex.getMessage());
        } finally {
            fechaConexao();
            return qnt;
        }
    }

    public int quantidadeProfissionais() {
        int qnt = 0;
        try {
            String sql = "select count(*) from medico where status_medico=true";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                qnt = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.quantidadeGestante()" + ex.getMessage());
        } finally {
            fechaConexao();
            return qnt;
        }
    }

    public int quantidadeFamiliares() {
        int qnt = 0;
        try {
            String sql = "select count(*) from familiar where situacao_familiar=true";
            PreparedStatement ps = getConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                qnt = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.quantidadeGestante()" + ex.getMessage());
        } finally {
            fechaConexao();
            return qnt;
        }
    }

    public boolean verificarSenhaAdm(String senha) {
        boolean result = false;
        try {
            String sql = "select senha from administrador";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (Decripto(rs.getString("senha")).equals(senha)) {
                    result = true;
                }
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.verificarSenhaAdm()" + ex.getMessage());
        } finally {
            fechaConexao();
            return result;
        }
    }

    public boolean alterarSenhaAdm(String senha) {
        int x = 0;
        try {
            String sql = "update administrador set senha=?";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ps.setString(1, Cripto(senha));

            x = ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.alterarSenhaAdm()" + ex.getMessage());
        } finally {
            fechaConexao();
            return x > 0;
        }
    }

    public int msgAtivacao() {
        int qnt = 0;
        try {
            String sql = "select count(*) from contato where assunto='Ativação de conta médica' and status=false";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                qnt = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.msgAtivacao()" + ex.getMessage());
        } finally {
            fechaConexao();
            return qnt;
        }
    }

    public int msgErro() {
        int qnt = 0;
        try {
            String sql = "select count(*) from contato where assunto='Erro do sistema' and status=false";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                qnt = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.msgAtivacao()" + ex.getMessage());
        } finally {
            fechaConexao();
            return qnt;
        }
    }

    public int msgDuvida() {
        int qnt = 0;
        try {
            String sql = "select count(*) from contato where assunto='Dúvidas' and status=false";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                qnt = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.msgAtivacao()" + ex.getMessage());
        } finally {
            fechaConexao();
            return qnt;
        }
    }

    public int msgOutro() {
        int qnt = 0;
        try {
            String sql = "select count(*) from contato where assunto='Outro' and status=false";
            PreparedStatement ps = getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                qnt = rs.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println("Gestante_Social.dao.AdministradorDAO.msgAtivacao()" + ex.getMessage());
        } finally {
            fechaConexao();
            return qnt;
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
