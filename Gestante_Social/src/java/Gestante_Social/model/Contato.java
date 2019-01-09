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
public class Contato {
    private int id_contato;
    private String nome;
    private String Sobrenome;
    private String cpf;
    private String email;
    private String assunto;
    private String mensagem;
    private boolean status;

    public Contato(int id_contato, String nome, String Sobrenome, String cpf, String email, String assunto, String mensagem, boolean status) {
        this.id_contato = id_contato;
        this.nome = nome;
        this.Sobrenome = Sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.status = status;
    }

    public Contato() {
    }

    /**
     * @return the id_contato
     */
    public int getId_contato() {
        return id_contato;
    }

    /**
     * @param id_contato the id_contato to set
     */
    public void setId_contato(int id_contato) {
        this.id_contato = id_contato;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the Sobrenome
     */
    public String getSobrenome() {
        return Sobrenome;
    }

    /**
     * @param Sobrenome the Sobrenome to set
     */
    public void setSobrenome(String Sobrenome) {
        this.Sobrenome = Sobrenome;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the assunto
     */
    public String getAssunto() {
        return assunto;
    }

    /**
     * @param assunto the assunto to set
     */
    public void setAssunto(String assunto) {
        this.assunto = assunto;
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
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    

    
    
    
    
}
