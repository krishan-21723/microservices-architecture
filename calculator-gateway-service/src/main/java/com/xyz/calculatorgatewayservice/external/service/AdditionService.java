package com.xyz.calculatorgatewayservice.external.service;

import java.math.BigDecimal;

import com.xyz.calculatorgatewayservice.exception.GenericException;

public interface AdditionService {

	public BigDecimal add(BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
