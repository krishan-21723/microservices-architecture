package com.xyz.divisionservice.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.divisionservice.exception.GenericException;
import com.xyz.divisionservice.service.DivisionService;
import com.xyz.divisionservice.validator.impl.DivisionValidator;

@Service
public class DivisionServiceImpl implements DivisionService {

	@Autowired
	private DivisionValidator divisionValidator;

	@Override
	public BigDecimal divide(BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		divisionValidator.validate(firstNum, secondNum);
		return firstNum.divide(secondNum, MathContext.DECIMAL128);
	}
}
