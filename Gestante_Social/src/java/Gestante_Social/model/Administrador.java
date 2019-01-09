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
public class Administrador {
    private String senha = "";
    
    public Administrador(String senha) {
        this.senha = senha;
    }

    public Administrador() {
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
