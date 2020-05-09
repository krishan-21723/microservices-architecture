package com.xyz.notificationservice;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyz.notificationservice.enums.CreditTransactionType;
import com.xyz.notificationservice.models.CreditTransactionKafkaPayload;
import com.xyz.notificationservice.service.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
class NotificationServiceApplicationTests {

	@MockBean
	private EmailService service;

	@Test
	void sendMailTest() {
		CreditTransactionKafkaPayload payload = CreditTransactionKafkaPayload.builder()
				.availableCredits(12d)
				.purpose("test").transactionAmount(1d)
				.transactionId(123)
				.userEmail("test12345@dummy.com")
				.userId(116)
				.build();
		
		Mockito.when(service.sendCreditTransctionEmail(payload, CreditTransactionType.ADDED)).thenReturn(Boolean.TRUE);
		assertTrue(service.sendCreditTransctionEmail(payload, CreditTransactionType.ADDED));
	}

}
