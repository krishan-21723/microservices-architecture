package com.xyz.additionservice.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.additionservice.exception.GenericException;
import com.xyz.additionservice.service.AdditionService;
import com.xyz.additionservice.validator.impl.AdditionValidator;

@Service
public class AdditionServiceImpl implements AdditionService {

	@Autowired
	private AdditionValidator additionValidator;

	@Override
	public BigDecimal add(BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		additionValidator.validate(firstNum, secondNum);
		return firstNum.add(secondNum, MathContext.DECIMAL128);
	}
}
