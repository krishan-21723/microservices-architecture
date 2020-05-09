package com.xyz.calculatorgatewayservice.service;

import java.math.BigDecimal;

import com.xyz.calculatorgatewayservice.exception.GenericException;

public interface CalculatorService {

	public BigDecimal add(Integer userId, BigDecimal firstNum, BigDecimal secondNum) throws GenericException;

	public BigDecimal subtract(Integer userId, BigDecimal firstNum, BigDecimal secondNum) throws GenericException;

	public BigDecimal multiply(Integer userId, BigDecimal firstNum, BigDecimal secondNum) throws GenericException;

	public BigDecimal divide(Integer userId, BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
