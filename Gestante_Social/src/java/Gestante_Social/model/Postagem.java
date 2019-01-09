/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.model;

import java.util.Date;

/**
 *
 * @author JONATAS
 */
public class Postagem {
    private int id_postagem;
    private String mensagem;
    private String url_imagem;
    private Usuario usuario;
    private Date data;

    public Postagem(int id_postagem, String mensagem, String url_imagem, Usuario usuario, Date data) {
        this.id_postagem = id_postagem;
        this.mensagem = mensagem;
        this.url_imagem = url_imagem;
        this.usuario = usuario;
        this.data = data;
    }

    public Postagem() {
    }

    /**
     * @return the id_postagem
     */
    public int getId_postagem() {
        return id_postagem;
    }

    /**
     * @param id_postagem the id_postagem to set
     */
    public void setId_postagem(int id_postagem) {
        this.id_postagem = id_postagem;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the url_imagem
     */
    public String getUrl_imagem() {
        return url_imagem;
    }

    /**
     * @param url_imagem the url_imagem to set
     */
    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
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
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }
    
}
