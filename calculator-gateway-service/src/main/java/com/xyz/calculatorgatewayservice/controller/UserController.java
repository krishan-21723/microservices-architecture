package com.xyz.calculatorgatewayservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.calculatorgatewayservice.dto.UserDTO;
import com.xyz.calculatorgatewayservice.exception.GenericException;
import com.xyz.calculatorgatewayservice.external.request.AddUserRequest;
import com.xyz.calculatorgatewayservice.external.service.UserService;
import com.xyz.calculatorgatewayservice.response.APIResponse;
import com.xyz.calculatorgatewayservice.util.CommonUtils;
import com.xyz.calculatorgatewayservice.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<APIResponse<Integer>> createUser(@RequestBody AddUserRequest request) throws GenericException {
		log.info("Got request to create user with request", CommonUtils.toJson(request));
		return ResponseUtil.getApiResponse(userService.createUser(request));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<APIResponse<UserDTO>> getUser(@PathVariable Integer userId) throws GenericException {
		log.info("Got request to fetch user Detail with userId ", userId);
		return ResponseUtil.getApiResponse(userService.getUserByUserId(userId));
	}

}
