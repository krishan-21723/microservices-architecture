package com.xyz.calculatorgatewayservice.external.service;

import java.math.BigDecimal;

import com.xyz.calculatorgatewayservice.exception.GenericException;

public interface DivisionService {

	public BigDecimal divide(BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
