/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author JONATAS
 */
public class Conexao {
    private Connection Conexao;
    
    public Conexao()throws Exception{
        String url = "jdbc:postgresql://localhost:5432/Gestante_Social";
        String driver = "org.postgresql.Driver";
        Class.forName(driver);
        Conexao = DriverManager.getConnection(url, "postgres", "postgres");
    }
    
    public Connection getConexao(){
        return Conexao;
    }
    
    public void fechaConexao(){
       try{
           Conexao.close();
       }catch(Exception e){
           e.printStackTrace();
       }
    }
    
}
