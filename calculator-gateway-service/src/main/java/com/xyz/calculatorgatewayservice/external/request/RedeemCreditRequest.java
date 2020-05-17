package com.xyz.calculatorgatewayservice.external.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedeemCreditRequest {
	private Integer userId;
	private Double amountToRedeem;
	private String purpose;
}
