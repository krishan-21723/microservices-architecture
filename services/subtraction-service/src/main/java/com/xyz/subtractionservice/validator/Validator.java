package com.xyz.subtractionservice.validator;

import java.math.BigDecimal;

import com.xyz.subtractionservice.exception.GenericException;

public interface Validator {

	public void validate(BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
