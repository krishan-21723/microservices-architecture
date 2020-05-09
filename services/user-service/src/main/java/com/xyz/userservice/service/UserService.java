package com.xyz.userservice.service;

import com.xyz.userservice.exception.GenericException;
import com.xyz.userservice.model.User;
import com.xyz.userservice.request.AddUserRequest;

public interface UserService {

	public Integer saveUser(AddUserRequest request) throws GenericException;

	public User getUser(Integer userId) throws GenericException;

	public void updateUserCredits(Double availableCredits, Integer userId) throws GenericException;
}
