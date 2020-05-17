package com.xyz.divisionservice.validator.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.xyz.divisionservice.constants.ErrorCodes;
import com.xyz.divisionservice.exception.GenericException;
import com.xyz.divisionservice.validator.Validator;

@Service
public class DivisionValidator implements Validator {

	@Override
	public void validate(BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		if (firstNum == null || secondNum == null) {
			throw new GenericException(ErrorCodes.DV001, ErrorCodes.getErrorMsg(ErrorCodes.DV001));
		}
		if (secondNum.equals(new BigDecimal(0))) {
			throw new GenericException(ErrorCodes.DV002, ErrorCodes.getErrorMsg(ErrorCodes.DV002));
		}
	}

}
