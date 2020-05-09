package com.xyz.additionservice.validator;

import java.math.BigDecimal;

import com.xyz.additionservice.exception.GenericException;

public interface Validator {

	public void validate(BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
