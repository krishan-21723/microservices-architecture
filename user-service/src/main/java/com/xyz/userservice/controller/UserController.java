package com.xyz.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.userservice.exception.GenericException;
import com.xyz.userservice.model.User;
import com.xyz.userservice.request.AddUserRequest;
import com.xyz.userservice.response.APIResponse;
import com.xyz.userservice.service.UserService;
import com.xyz.userservice.util.CommonUtils;
import com.xyz.userservice.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<APIResponse<Integer>> saveUser(@RequestBody AddUserRequest request) throws GenericException {
		log.info("Got request to add user with request", CommonUtils.toJson(request));
		return ResponseUtil.getApiResponse(userService.saveUser(request));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<APIResponse<User>> getUser(@PathVariable Integer userId) throws GenericException {
		log.info("Got request to fetch user Detail with userId ", userId);
		return ResponseUtil.getApiResponse(userService.getUser(userId));
	}
}
