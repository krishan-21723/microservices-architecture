package com.xyz.calculatorgatewayservice.external.service;

import com.xyz.calculatorgatewayservice.dto.UserDTO;
import com.xyz.calculatorgatewayservice.exception.GenericException;
import com.xyz.calculatorgatewayservice.external.request.AddUserRequest;

public interface UserService {

	public UserDTO getUserByUserId(Integer userId) throws GenericException;
	
	public Integer createUser(AddUserRequest request) throws GenericException;
}
