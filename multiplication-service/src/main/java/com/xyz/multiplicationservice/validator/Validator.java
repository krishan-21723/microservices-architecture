package com.xyz.multiplicationservice.validator;

import java.math.BigDecimal;

import com.xyz.multiplicationservice.exception.GenericException;

public interface Validator {

	public void validate(BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
