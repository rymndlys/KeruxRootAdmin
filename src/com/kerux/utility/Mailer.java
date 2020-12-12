package com.kerux.utility;

import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;    
public class Mailer{  
    public static String send(String from,String password,String to,String sub,String msg){  
          //Get properties object    
          Properties props = new Properties();    
         String messageA="";

         props.put("mail.smtp.host", "smtp.googlemail.com");    
         props.put("mail.smtp.socketFactory.port", "465");    
         props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
         props.put("mail.smtp.auth", "true");    
         props.put("mail.smtp.port", "465");
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);    
           //send message  
           Transport.send(message);   
           messageA="message sent successfully";
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {
        	  messageA=e.getMessage();
        	  throw new RuntimeException(e);}    
          finally{
        	  return messageA;
          }
             
 
    }  
}  