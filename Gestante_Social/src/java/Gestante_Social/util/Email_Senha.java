/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author JONATAS
 */
public class Email_Senha {

    String emailLogin = "contatogestantesocial@gmail.com", emailSenha = "socialgestante1234";

    public void enviar(String endereco, String login, String senha) {
//        SimpleEmail email = new SimpleEmail();
//        email.setSSLOnConnect(true);
//        email.setHostName("smtp.gmail.com");
//        email.setSslSmtpPort("465");
//        email.setAuthenticator(new DefaultAuthenticator(emailLogin, emailSenha));
//        try {
//            email.setFrom("gestantesocial@naoresponder.com");
//
//            email.setDebug(true);
//
//            email.setSubject("Recuperação de senha!");
//            email.setMsg("Seus dados são:\n\n"
//                    + "Login: "+login+
//                    "\nSenha: "+senha);
//            email.addTo(endereco);//por favor trocar antes de testar!!!!
//
//            email.send();
//
//        } catch (EmailException e) {
//            e.printStackTrace();
//        }

        HtmlEmail email = new HtmlEmail();
        email.setSSLOnConnect(true);
        email.setHostName("smtp.gmail.com");
        email.setSslSmtpPort("465");
        email.setAuthenticator(new DefaultAuthenticator(emailLogin, emailSenha));
        try {
            email.setFrom("contatogestantesocial@gmail.com", "Gestante Social");
            email.setDebug(true);
            email.setSubject("Recuperação de senha");

            StringBuilder builder = new StringBuilder();
            builder.append("<h1>Recuperação de senha!</h1>");
            builder.append("<img src=\"http://170.78.231.26:4000/Gestante_Social/img/logo.png\" height=\"130px\"  width=\"190px\">");
            builder.append("<h2>Seus dados são:</h2>");
            builder.append("<h3 style='color: red'>Login: "+login+"</h3>");
            builder.append("<h3 style='color: red'>Senha: "+senha+"</h3>");


            email.setHtmlMsg(builder.toString());
            email.addTo(endereco);
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
