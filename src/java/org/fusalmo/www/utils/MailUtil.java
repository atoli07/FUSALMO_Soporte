/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.utils;

import com.sun.istack.internal.logging.Logger;
import java.util.Properties;
import java.util.logging.Level;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Brymolina
 */
public class MailUtil {

    public static void sendMail(String recepient, String text) {
        try {
            Logger.getLogger(MailUtil.class).log(Level.INFO, null, "Preparando mensaje...");
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            String email = "itsoporte711@gmail.com";
            String password = "soporteIT@21";
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, password);
                }
            });
            Message message = prepareMessage(session, email, recepient, text);
            Transport.send(message);
            Logger.getLogger(MailUtil.class).log(Level.INFO, null, "Mensaje enviado correctamente");
        } catch (MessagingException e) {
            Logger.getLogger(MailUtil.class).log(Level.SEVERE, null, e);
        }
    }

    private static Message prepareMessage(Session session, String email, String recepeint, String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepeint));
            message.setSubject("NOTIFICACION FUSALMO");
            message.setText(text);
            return message;
        } catch (MessagingException e) {
            Logger.getLogger(MailUtil.class).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
