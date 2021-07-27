package com.portal.services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.portal.models.user.login.passwordforget.MailRequest;


@Service
public class MailService {
	public void sendemail(MailRequest mailRequest)
	{
		// set variable for g-mail
		String host="smtp.gmail.com";
		//get the System Properties
		Properties propertie= System.getProperties();
		// setting important information to properties object
		 
		// host set
		propertie.put("mail.smtp.host", host);
		propertie.put("mail.smtp.port", "465");
		propertie.put("mail.smtp.ssl.enable", "true");
		propertie.put("mail.smtp.auth", "true");
		
		// step 1: to get the session 
		
		 Session session= Session.getInstance(propertie,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("coder455336@gmail.com","*coder+programmer*");
			}
		
		 });
		 session.setDebug(true);
	
		 // step 2: compose the message [test, multi media]
		 
		 MimeMessage m=new MimeMessage(session);
		 try {
			 // from email 
			 m.setFrom("coder455336@gmail.com");
			 
			 //adding reciepent to message
			 //m.addRecipient(msg.RecipientType.to, null);
			 m.addRecipient(Message.RecipientType.TO, new InternetAddress(mailRequest.getReceiverAddress()));
			// adding subject to message
			 m.setSubject(mailRequest.getSubject());
			 // adding text to message
			 m.setText(mailRequest.getMessage());
			 
			 // send 
			 
			 // step 3 : send the message using Transport class
			 
			 Transport.send(m);
			  System.out.println("Send Successfully");
			
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		
	}
	

}
