package com.xyz.multiplicationservice.service;

import java.math.BigDecimal;

import com.xyz.multiplicationservice.exception.GenericException;

public interface MultiplicationService {

	public BigDecimal multiply(BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
