package com.xyz.calculatorgatewayservice.external.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCreditRequest {
	private Integer userId;
	private Double amountToAdd;
	private String purpose;
}
