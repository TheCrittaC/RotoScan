//package com.mkyong.common;
 
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendEmail{
 
    public static void send(String from, String to, String password, String text) {
	
	final String username = from;
	final String pass = password;
 
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
 
	Session session = Session.getInstance(props,
					      new javax.mail.Authenticator() {
						  protected PasswordAuthentication getPasswordAuthentication() {
						      return new PasswordAuthentication(username, pass);
						  }
					      });
 
	try {
 
	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(username));
	    message.setRecipients(Message.RecipientType.TO,
				  InternetAddress.parse(to));
	    message.setSubject("");
	    message.setText(text);
 
	    Transport.send(message);
 	    
	} catch (MessagingException e) {
	    throw new RuntimeException(e);
	}
    }
}
