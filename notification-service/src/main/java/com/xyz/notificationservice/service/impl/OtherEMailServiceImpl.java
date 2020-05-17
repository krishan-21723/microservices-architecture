package com.xyz.notificationservice.service.impl;

import java.util.List;
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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xyz.notificationservice.service.BaseEMailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("otherEmailService")
public class OtherEMailServiceImpl implements BaseEMailService {

	@Value("${mail.smtp.host}")
	private String smtpHost;
	
	@Value("${mail.smtp.port}")
	private String smtpPort;
	
	@Value("${mail.smtp.socketFactory.class}")
	private String socketFactoryclass;
	
	@Value("${mail.smtp.socketFactory.port}")
	private String socketFactoryPort;
	
	@Value("${mail.smtp.auth}")
	private String smtpAuth;
	
	@Override
	public boolean sendCreditTransctionEmail(List<String> recipients, String senderEmail, String senderPassword, String subject, String body) {

		// Setting up mail server
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpHost);  
		props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.socketFactory.port", socketFactoryPort);    
        props.put("mail.smtp.socketFactory.class", socketFactoryclass);    
        props.put("mail.smtp.auth", smtpAuth);    

		// creating session object to get properties with authentication
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			// MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			//email from field.
			message.setFrom(new InternetAddress(senderEmail));
			//email to field.
			message.addRecipients(Message.RecipientType.TO, createRecipientAdddresses(recipients));
			//subject of the email
			message.setSubject(subject);
			//body of the email.
			message.setText(body);
			
			// Send email.
			Transport.send(message);
			log.info("Mail sent successfully");
		} catch (MessagingException e) {
			log.error("Exception while sending email with ", e);
		}
		return true;
	}

	private InternetAddress[] createRecipientAdddresses(List<String> recipients) {
		return recipients.stream().map(r -> {
			InternetAddress add = null;
			try {
				add = new InternetAddress(r);
			} catch (AddressException e) {
				log.error("Exception while creating internet address for {} with ", r, e);
			}
			return add;
		}).toArray(InternetAddress[]::new);
	}

}
