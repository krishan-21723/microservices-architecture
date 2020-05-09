package com.xyz.notificationservice.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.xyz.notificationservice.enums.EmailServices;
import com.xyz.notificationservice.service.BaseEMailService;

@Component
public class EmailServiceFactory {

	@Autowired
	@Qualifier("smtpEmailService")
	private BaseEMailService smtpeMailServiceImpl;

	@Autowired
	@Qualifier("otherEmailService")
	private BaseEMailService otherEMailServiceImpl;

	public BaseEMailService getEmailService(EmailServices type) {

		BaseEMailService emailService = null;

		if (type != null) {

			switch (type) {
			case SMTP:
				emailService = smtpeMailServiceImpl;
				break;
			case OTHER:
				emailService = otherEMailServiceImpl;
				break;

			default:
				break;
			}
		}
		if (emailService == null) {
			emailService = smtpeMailServiceImpl; // default
		}
		return emailService;
	}
}
