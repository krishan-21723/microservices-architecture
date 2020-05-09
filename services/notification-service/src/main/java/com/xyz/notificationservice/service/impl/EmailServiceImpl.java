package com.xyz.notificationservice.service.impl;

import java.text.MessageFormat;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xyz.notificationservice.constants.AppConstants;
import com.xyz.notificationservice.enums.CreditTransactionType;
import com.xyz.notificationservice.enums.EmailServices;
import com.xyz.notificationservice.factory.EmailServiceFactory;
import com.xyz.notificationservice.models.CreditTransactionKafkaPayload;
import com.xyz.notificationservice.service.BaseEMailService;
import com.xyz.notificationservice.service.EmailService;
import com.xyz.notificationservice.util.CommonUtils;

@Service
public class EmailServiceImpl implements EmailService {

	@Value("${credit.transaction.sender.email}")
	private String senderEmail;
	
	@Value("${credit.transaction.sender.password}")
	private String senderPassword;

	@Autowired
	private EmailServiceFactory emailServiceFactory;

	@Override
	public boolean sendCreditTransctionEmail(CreditTransactionKafkaPayload payload, CreditTransactionType type) {
		if (type != null && payload != null && StringUtils.isNotEmpty(payload.getUserEmail())) {
			String subject = null;
			String body = null;
			
			BaseEMailService eMailService = emailServiceFactory.getEmailService(EmailServices.SMTP);
			
			switch (type) {
			case ADDED:
				if (payload.getTransactionAmount() != null && payload.getTransactionAmount() > 1) {
					subject = MessageFormat.format(AppConstants.CREDIT_ADD_SUBJECT_MUTLI, payload.getTransactionAmount());
					body = MessageFormat.format(AppConstants.CREDIT_ADD_BODY_MUTLI, payload.getTransactionAmount(),
							CommonUtils.getStringDate(payload.getTransactionAt()), payload.getTransactionId(), payload.getAvailableCredits());
				} else {
					subject = MessageFormat.format(AppConstants.CREDIT_ADD_SUBJECT_SINGLE, payload.getTransactionAmount());
					body = MessageFormat.format(AppConstants.CREDIT_ADD_BODY_SINGLE, payload.getTransactionAmount(),
							CommonUtils.getStringDate(payload.getTransactionAt()), payload.getTransactionId(), payload.getAvailableCredits());
				}
				eMailService.sendCreditTransctionEmail(Arrays.asList(payload.getUserEmail()), senderEmail, senderPassword, subject, body);
				break;
				
			case REDDEMED:
				if (payload.getTransactionAmount() != null && payload.getTransactionAmount() > 1) {
					subject = MessageFormat.format(AppConstants.CREDIT_REDEEM_SUBJECT_MUTLI, payload.getTransactionAmount());
					body = MessageFormat.format(AppConstants.CREDIT_REDEEM_BODY_MUTLI, payload.getTransactionAmount(), payload.getPurpose(),
							CommonUtils.getStringDate(payload.getTransactionAt()), payload.getTransactionId(), payload.getAvailableCredits());
				} else {
					subject = MessageFormat.format(AppConstants.CREDIT_REDEEM_SUBJECT_SINGLE, payload.getTransactionAmount());
					body = MessageFormat.format(AppConstants.CREDIT_REDEEM_BODY_SINGLE, payload.getTransactionAmount(),
							payload.getPurpose(), CommonUtils.getStringDate(payload.getTransactionAt()), payload.getTransactionId(), payload.getAvailableCredits());
				}
				eMailService.sendCreditTransctionEmail(Arrays.asList(payload.getUserEmail()), senderEmail, senderPassword, subject, body);
				
				if (payload.getAvailableCredits() <= 0) {
					subject = AppConstants.CREDIT_EXAUST_SUBJECT;
					body = MessageFormat.format(AppConstants.CREDIT_EXAUST_BODY, payload.getPurpose(), CommonUtils.getStringDate(payload.getTransactionAt()), payload.getTransactionId());
					eMailService.sendCreditTransctionEmail(Arrays.asList(payload.getUserEmail()), senderEmail, senderPassword, subject, body);
				}
				break;

			default:
				break;
			}
		}
		return true;
	}

}
