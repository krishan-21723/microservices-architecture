package com.xyz.subtractionservice.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.subtractionservice.exception.GenericException;
import com.xyz.subtractionservice.service.SubtractionService;
import com.xyz.subtractionservice.validator.impl.SubtractionValidator;

@Service
public class SubtractionServiceImpl implements SubtractionService {

	@Autowired
	private SubtractionValidator subtractionValidator;

	@Override
	public BigDecimal subtract(BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		subtractionValidator.validate(firstNum, secondNum);
		return firstNum.subtract(secondNum, MathContext.DECIMAL128);
	}
}
