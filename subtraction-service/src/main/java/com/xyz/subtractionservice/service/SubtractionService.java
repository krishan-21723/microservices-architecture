package com.xyz.subtractionservice.service;

import java.math.BigDecimal;

import com.xyz.subtractionservice.exception.GenericException;

public interface SubtractionService {

	public BigDecimal subtract(BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
