package com.xyz.calculatorgatewayservice.external.service;

import java.math.BigDecimal;

import com.xyz.calculatorgatewayservice.exception.GenericException;

public interface MultiplicationService {

	public BigDecimal multiply(BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
