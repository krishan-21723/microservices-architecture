package com.xyz.multiplicationservice.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.multiplicationservice.exception.GenericException;
import com.xyz.multiplicationservice.service.MultiplicationService;
import com.xyz.multiplicationservice.validator.impl.MultiplicationValidator;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

	@Autowired
	private MultiplicationValidator multiplicationValidator;

	@Override
	public BigDecimal multiply(BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		multiplicationValidator.validate(firstNum, secondNum);
		return firstNum.multiply(secondNum, MathContext.DECIMAL128);
	}
}
