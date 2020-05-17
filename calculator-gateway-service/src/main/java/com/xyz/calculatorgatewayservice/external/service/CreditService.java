package com.xyz.calculatorgatewayservice.external.service;

import com.xyz.calculatorgatewayservice.dto.UserDTO;
import com.xyz.calculatorgatewayservice.exception.GenericException;

public interface CreditService {

	public Integer addCreditsForUser(Integer userId, Double amount, String purpose) throws GenericException;

	public Integer redeemCreditsForUser(UserDTO user, Integer userId, Double amount, String purpose, Double availableCredits) throws GenericException;

}
