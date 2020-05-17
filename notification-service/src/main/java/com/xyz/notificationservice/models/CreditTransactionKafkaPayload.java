package com.xyz.notificationservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditTransactionKafkaPayload {

	private String topicName;
	private Integer transactionId;
	private Integer userId;
	private String userEmail;
	private Double transactionAmount;
	private String purpose;
	private Long transactionAt;
	private Double availableCredits;
}
