package com.xyz.multiplicationservice.validator.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.xyz.multiplicationservice.constants.ErrorCodes;
import com.xyz.multiplicationservice.exception.GenericException;
import com.xyz.multiplicationservice.validator.Validator;

@Service
public class MultiplicationValidator implements Validator {

	@Override
	public void validate(BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		if (firstNum == null || secondNum == null) {
			throw new GenericException(ErrorCodes.MU001, ErrorCodes.getErrorMsg(ErrorCodes.MU001));
		}
	}

}
