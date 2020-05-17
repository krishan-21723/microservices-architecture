package com.xyz.notificationservice.service;

import com.xyz.notificationservice.enums.CreditTransactionType;
import com.xyz.notificationservice.models.CreditTransactionKafkaPayload;

public interface EmailService {
	public boolean sendCreditTransctionEmail(CreditTransactionKafkaPayload payload, CreditTransactionType type);
}
