/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kiddo.Utils;

import java.time.LocalDate;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author TR3x
 */
public class Email {
    
   public static void sendMail(String recepient,String contenu) throws Exception{
    System.out.println("preparing to send emaill!!!!!!!!!!!");
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.host", "smtp.googlemail.com");
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.starttls.enable", "true");
    
    String myAccountEmail = "hafdhiatef@gmail.com";
    String password = "atoufatoisthebest";
    Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
            });
        
    Message message = prepareMessage(session,myAccountEmail,recepient,contenu);
        Transport.send(message);
        System.out.println("message sent successfully!!!!!!!!!!!");
    }
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient,String contenu ) throws AddressException, MessagingException
    {        
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Concernant votre reclamation");
            message.setText(contenu);
            return message;       
    }
    
}
