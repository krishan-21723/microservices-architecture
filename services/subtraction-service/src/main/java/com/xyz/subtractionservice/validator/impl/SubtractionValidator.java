package com.xyz.subtractionservice.validator.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.xyz.subtractionservice.constants.ErrorCodes;
import com.xyz.subtractionservice.exception.GenericException;
import com.xyz.subtractionservice.validator.Validator;

@Service
public class SubtractionValidator implements Validator {

	@Override
	public void validate(BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		if (firstNum == null || secondNum == null) {
			throw new GenericException(ErrorCodes.SU001, ErrorCodes.getErrorMsg(ErrorCodes.SU001));
		}
	}

}
