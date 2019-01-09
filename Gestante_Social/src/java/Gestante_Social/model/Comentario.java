/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.model;

/**
 *
 * @author JONATAS
 */
public class Comentario {
    private int idComentario;
    private Usuario usuario;
    private Familiar familiar;
    private Medico medico;
    private Postagem postagem;
    private String comentario;

    public Comentario(int idComentario, Usuario usuario, Familiar familiar, Medico medico, Postagem postagem, String comentario) {
        this.idComentario = idComentario;
        this.usuario = usuario;
        this.familiar = familiar;
        this.medico = medico;
        this.postagem = postagem;
        this.comentario = comentario;
    }

    public Comentario() {
    }

    /**
     * @return the idComentario
     */
    public int getIdComentario() {
        return idComentario;
    }

    /**
     * @param idComentario the idComentario to set
     */
    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the familiar
     */
    public Familiar getFamiliar() {
        return familiar;
    }

    /**
     * @param familiar the familiar to set
     */
    public void setFamiliar(Familiar familiar) {
        this.familiar = familiar;
    }

    /**
     * @return the medico
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * @return the postagem
     */
    public Postagem getPostagem() {
        return postagem;
    }

    /**
     * @param postagem the postagem to set
     */
    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    
    

    
    
}
