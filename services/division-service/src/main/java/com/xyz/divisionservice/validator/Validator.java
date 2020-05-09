package com.xyz.divisionservice.validator;

import java.math.BigDecimal;

import com.xyz.divisionservice.exception.GenericException;

public interface Validator {

	public void validate(BigDecimal firstNum, BigDecimal secondNum) throws GenericException;
}
