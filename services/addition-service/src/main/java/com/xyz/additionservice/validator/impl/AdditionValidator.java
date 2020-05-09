package com.xyz.additionservice.validator.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.xyz.additionservice.constants.ErrorCodes;
import com.xyz.additionservice.exception.GenericException;
import com.xyz.additionservice.validator.Validator;

@Service
public class AdditionValidator implements Validator {

	@Override
	public void validate(BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		if (firstNum == null || secondNum == null) {
			throw new GenericException(ErrorCodes.US001, ErrorCodes.getErrorMsg(ErrorCodes.US001));
		}
	}

}
