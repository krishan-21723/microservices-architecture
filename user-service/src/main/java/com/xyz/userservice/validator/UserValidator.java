package com.xyz.userservice.validator;

import com.xyz.userservice.exception.GenericException;
import com.xyz.userservice.model.User;
import com.xyz.userservice.request.AddCreditRequest;
import com.xyz.userservice.request.AddUserRequest;
import com.xyz.userservice.request.RedeemCreditRequest;

public interface UserValidator {

	public void validateAddUserRequest(AddUserRequest request) throws GenericException;
	
	public void validateUser(User user) throws GenericException;
	
	public void validateNonExistingUserByUserId(Integer userId) throws GenericException;

	public void validateAddCreditRequest(AddCreditRequest request) throws GenericException;
	
	public void validateRedeemCreditRequest(RedeemCreditRequest request) throws GenericException;
}
