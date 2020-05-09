package com.xyz.calculatorgatewayservice.validator;

import com.xyz.calculatorgatewayservice.dto.UserDTO;
import com.xyz.calculatorgatewayservice.enums.OperationType;
import com.xyz.calculatorgatewayservice.exception.GenericException;

public interface Validator {

	public void validateUser(UserDTO user) throws GenericException;
	
	public void validateUserAndAction(UserDTO user, OperationType type) throws GenericException;
}
