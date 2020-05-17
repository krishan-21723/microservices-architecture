package com.xyz.calculatorgatewayservice.models;

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
	private Double transactionAmount;
	private Long transactionAt;
	private String userEmail;
	private String purpose;
	private Double availableCredits;
	
}
