package com.xyz.calculatorgatewayservice.external.service;

import java.math.BigDecimal;

import com.xyz.calculatorgatewayservice.exception.GenericException;

public interface SubtractionService {

	public BigDecimal subtract(BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
