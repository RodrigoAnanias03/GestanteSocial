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
public class MensagemMedico {
    private int idMensagem;
    private String mensagem;
    private Medico medico;
    private Usuario usuario;
    private boolean statusMensagem;
    private String autor;
    private Date dataMensagem;
    private String arquivo;

    public MensagemMedico(int idMensagem, String mensagem, Medico medico, Usuario usuario, boolean statusMensagem, String autor, Date dataMensagem, String arquivo) {
        this.idMensagem = idMensagem;
        this.mensagem = mensagem;
        this.medico = medico;
        this.usuario = usuario;
        this.statusMensagem = statusMensagem;
        this.autor = autor;
        this.dataMensagem = dataMensagem;
        this.arquivo = arquivo;
    }

    public MensagemMedico() {
    }

    /**
     * @return the idMensagem
     */
    public int getIdMensagem() {
        return idMensagem;
    }

    /**
     * @param idMensagem the idMensagem to set
     */
    public void setIdMensagem(int idMensagem) {
        this.idMensagem = idMensagem;
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
     * @return the statusMensagem
     */
    public boolean getStatusMensagem() {
        return statusMensagem;
    }

    /**
     * @param statusMensagem the statusMensagem to set
     */
    public void setStatusMensagem(boolean statusMensagem) {
        this.statusMensagem = statusMensagem;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the dataMensagem
     */
    public Date getDataMensagem() {
        return dataMensagem;
    }

    /**
     * @param dataMensagem the dataMensagem to set
     */
    public void setDataMensagem(Date dataMensagem) {
        this.dataMensagem = dataMensagem;
    }

    /**
     * @return the arquivo
     */
    public String getArquivo() {
        return arquivo;
    }

    /**
     * @param arquivo the arquivo to set
     */
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    

    
}
