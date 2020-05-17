package com.xyz.calculatorgatewayservice.validator.impl;

import org.springframework.stereotype.Service;

import com.xyz.calculatorgatewayservice.constants.AppConstants;
import com.xyz.calculatorgatewayservice.constants.ErrorCodes;
import com.xyz.calculatorgatewayservice.dto.UserDTO;
import com.xyz.calculatorgatewayservice.enums.OperationType;
import com.xyz.calculatorgatewayservice.exception.GenericException;
import com.xyz.calculatorgatewayservice.validator.Validator;

@Service
public class CalculationValidator implements Validator {

	@Override
	public void validateUserAndAction(UserDTO user, OperationType type) throws GenericException {
		
		if (user.getAvailableCredits() == null || user.getAvailableCredits() - AppConstants.getOpercationCost(type) < 0) {
			throw new GenericException(ErrorCodes.CA003, ErrorCodes.getErrorMsg(ErrorCodes.CA003));
		}
	}
	
	public void validateUser(UserDTO user) throws GenericException {
		if (user == null) {
			throw new GenericException(ErrorCodes.CA002, ErrorCodes.getErrorMsg(ErrorCodes.CA002));
		}
	}

}
