/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestante_Social.util;

import Gestante_Social.model.Contato;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author JONATAS
 */
public class Enviar_Email {

    String emailLogin = "contatogestantesocial@gmail.com", emailSenha = "socialgestante1234";

    public void enviar(String endereco, String url) {
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
//            email.setSubject("Novo cadastro GestanteSocial");
//            email.setMsg("Você fez um cadastro no sistema Gestante Social!\n"
//                    + "Para confirmar seu cadastro acesse: "+ url);
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
            email.setSubject("Ativação de Conta");

            StringBuilder builder = new StringBuilder();
            builder.append("<h1>Bem vindo(a) à Gestante Social</h1>");
            builder.append("<img src=\"http://170.78.231.26:4000/Gestante_Social/img/logo.png\" height=\"130px\"  width=\"190px\">");
            builder.append("<h3>Para sua maior segurança pedimos para que confirme seu email clicando abaixo.</h3>");
            builder.append("<a href=\"" + url + "\">Ativar Conta</a> <br> ");

            email.setHtmlMsg(builder.toString());
            email.addTo(endereco);
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void enviarMedico(String endereco, String url) {
        SimpleEmail email = new SimpleEmail();
        email.setSSLOnConnect(true);
        email.setHostName("smtp.gmail.com");
        email.setSslSmtpPort("465");
        email.setAuthenticator(new DefaultAuthenticator(emailLogin, emailSenha));
        try {
            email.setFrom("gestantesocial@naoresponder.com");

            email.setDebug(true);

            email.setSubject("Novo cadastro GestanteSocial");
            email.setMsg(url);
            email.addTo(endereco);//por favor trocar antes de testar!!!!

            email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public void reEnviar(String endereco, String url) {
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
//            email.setSubject("Reativar conta GestanteSocial");
//            email.setMsg("Para confirmar seu cadastro acesse: " + url);
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
            email.setSubject("Reativação de Conta");

            StringBuilder builder = new StringBuilder();
            builder.append("<h1>Reativação de conta Gestante Social</h1>");
            builder.append("<img src=\"http://170.78.231.26:4000/Gestante_Social/img/logo.png\" height=\"130px\"  width=\"190px\">");
            builder.append("<h3>Para sua maior segurança pedimos para que confirme seu email clicando abaixo.</h3>");
            builder.append("<a href=\"" + url + "\">Ativar Conta</a> <br> ");

            email.setHtmlMsg(builder.toString());
            email.addTo(endereco);
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void automaticoContato(String endereco) {
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
//            email.setSubject("Contato Gestante Social");
//            email.setMsg("Obrigado por entrar em contato com Gestante Social!\n Seu email será respondido"
//                    + " em pouco tempo!\n Você será avisado quando obter uma resposta!");
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
            email.setSubject("Contato Gestante Social");

            StringBuilder builder = new StringBuilder();
            builder.append("<h1>Obrigado por entrar em contato com o Gestante Social</h1>");
            builder.append("<img src=\"http://170.78.231.26:4000/Gestante_Social/img/logo.png\" height=\"130px\"  width=\"190px\">");
            builder.append("<h3>Seu e-mail será respondido em pouco tempo!</h3>");
            builder.append("<h3 style='color: red'>Você será notificado quando respondermos!</h3>");

            email.setHtmlMsg(builder.toString());
            email.addTo(endereco);
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AtivarMedico(String endereco) {
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
//            email.setSubject("Ativação Conta Gestante Social");
//            email.setMsg("Obrigado por aguardar nossa análise!"
//                    + "\n Sua conta foi ativada e você já pode efetuar o login no Gestante Social!");
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
            email.setSubject("Ativação Gestante Social");

            StringBuilder builder = new StringBuilder();
            builder.append("<h1>A análise de seu perfil foi concluída!</h1>");
            builder.append("<img src=\"http://170.78.231.26:4000/Gestante_Social/img/logo.png\" height=\"130px\"  width=\"190px\">");
            builder.append("<h3 style='color: blue'>Obrigado por aguardar nossa análise!</h3>");
            builder.append("<h3>Sua conta foi ativada e já é possível efetuar login no Gestante Social!</h3>");

            email.setHtmlMsg(builder.toString());
            email.addTo(endereco);
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ExcluirMedico(String endereco) {
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
//            email.setSubject("Ativação Conta Gestante Social");
//            email.setMsg("Infelizmente nossos administradores detectaram algo de errado com sua conta \n"
//                    + "e por segurança ela foi excluída!!"
//                    + "\n Tente efetuar o cadastro novamente!");
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
            email.setSubject("Análise Gestante Social");

            StringBuilder builder = new StringBuilder();
            builder.append("<h1>Sua análise foi concluída!</h1>");
            builder.append("<img src=\"http://170.78.231.26:4000/Gestante_Social/img/logo.png\" height=\"130px\"  width=\"190px\">");
            builder.append("<h3>A análise de sua conta foi concluída!</h3>");
            builder.append("<h3>Infelizmente detectamos algo de errado com sua conta e a mesma foi excluída!</h3>");
            builder.append("<h3>Caso queira, será possível efetuar um novo cadastro!</h3>");

            email.setHtmlMsg(builder.toString());
            email.addTo(endereco);
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ResponderContato(Contato obj, String resposta) {
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
//            email.setSubject("Resposta Contato Gestante Social");
//            email.setMsg("Olá " + obj.getNome() + ", obrigado por entrar em contato com o Gestante Social!\n"
//                    + "Sua mensagem foi respondida:\n"
//                    + "Nome: " + obj.getNome() + " " + obj.getSobrenome() + "\n"
//                    + "CPF: " + obj.getCpf() + "\n"
//                    + "Assunto: " + obj.getAssunto() + "\n"
//                    + "Mensagem: " + obj.getMensagem() + "\n"
//                    + "---------------------------------------------------------------------------------\n"
//                    + "Resposta: " + resposta);
//            email.addTo(obj.getEmail());//por favor trocar antes de testar!!!!
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
            email.setSubject("Resposta Gestante Social");

            StringBuilder builder = new StringBuilder();
            builder.append("<h1>Obrigado por entrar em contato con Gestante Social!</h1>");
            builder.append("<img src=\"http://170.78.231.26:4000/Gestante_Social/img/logo.png\" height=\"130px\"  width=\"190px\">");
            builder.append("<h3 style='color: green'>Sua mensagem foi respondida!</h3>");
            builder.append("<h4>Nome: "+obj.getNome()+" "+obj.getSobrenome()+"</h4>");
            builder.append("<h4>CPF: "+obj.getCpf()+"</h4>");
            builder.append("<h4>Assunto: "+obj.getAssunto()+"</h4>");
            builder.append("<h4 style='color: blue'>Mensagem: "+obj.getMensagem()+"</h4>");
            builder.append("<h4 style='color: green'>----------------------------------------------------------------</h4>");
            builder.append("<h4 style='color: red'>Resposta: "+resposta+"</h4>");
            

            email.setHtmlMsg(builder.toString());
            email.addTo(obj.getEmail());
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
