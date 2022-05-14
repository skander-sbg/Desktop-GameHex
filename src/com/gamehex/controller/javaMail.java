/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamehex.controller;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author asus
 */
public class javaMail {
    
    public static void sendMail(String recepient) throws Exception{
        System.out.println("preparing to sent email");
        Properties props;
        props = new Properties();
        props.put("mail.smtp.auth","true"); 
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");

        String myAccountEmail="rambo25bh@gmail.com";
        String password="Torturemon3";
        Session session = null;
        session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
            return new javax.mail.PasswordAuthentication(myAccountEmail, password);
            }
            
        });
        Message message=prepareMessage(session,myAccountEmail,recepient);
        Transport.send(message);
        System.out.println("message sent successfully"); 
 }
    
    
    private static Message prepareMessage(Session session, String myAccountEmail,String recepient) { 
        try {
             Message message =new MimeMessage(session);
             message.setFrom(new InternetAddress(myAccountEmail));
             message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
             message.setSubject("GAMEHEX");
             message.setText(" Email verified. \n You can proceed to changing your password. \n Thank you.");
             return message;
            } catch (MessagingException ex) {
            System.out.println(ex);
            }
         return null;
    }
    

    
}
