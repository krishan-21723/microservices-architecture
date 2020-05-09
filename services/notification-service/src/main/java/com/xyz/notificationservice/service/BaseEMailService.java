package com.xyz.notificationservice.service;

import java.util.List;

public interface BaseEMailService {

	public boolean sendCreditTransctionEmail(List<String> recipient, String sender, String senderPassword, String subject, String body);
}
