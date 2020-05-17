package com.xyz.additionservice.service;

import java.math.BigDecimal;

import com.xyz.additionservice.exception.GenericException;

public interface AdditionService {

	public BigDecimal add(BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
