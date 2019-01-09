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
public class Mensagem {
    private int idMensagem;
    private String mensagem;
    private Usuario remetente;
    private Usuario destinatario;
    private boolean statusMensagem;
    private Date dataMensagem;

    public Mensagem(int idMensagem, String mensagem, Usuario remetente, Usuario destinatario, boolean statusMensagem, Date dataMensagem) {
        this.idMensagem = idMensagem;
        this.mensagem = mensagem;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.statusMensagem = statusMensagem;
        this.dataMensagem = dataMensagem;
    }

    public Mensagem() {
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
     * @return the remetente
     */
    public Usuario getRemetente() {
        return remetente;
    }

    /**
     * @param remetente the remetente to set
     */
    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    /**
     * @return the destinatario
     */
    public Usuario getDestinatario() {
        return destinatario;
    }

    /**
     * @param destinatario the destinatario to set
     */
    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
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

    
    
}
