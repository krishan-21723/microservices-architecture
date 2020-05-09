package com.xyz.calculatorgatewayservice.external.service.impl;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xyz.calculatorgatewayservice.dto.UserDTO;
import com.xyz.calculatorgatewayservice.exception.GenericException;
import com.xyz.calculatorgatewayservice.external.request.AddUserRequest;
import com.xyz.calculatorgatewayservice.external.service.UserService;
import com.xyz.calculatorgatewayservice.response.APIResponse;
import com.xyz.calculatorgatewayservice.util.CommonUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${get.user.url}")
	private String getUserUrl;
	
	@Value("${create.user.url}")
	private String createUserUrl;

	@Override
	public UserDTO getUserByUserId(Integer userId) throws GenericException {
		UserDTO user = null;
		String endpointUrl = MessageFormat.format(getUserUrl, userId);
		ResponseEntity<String> response = restTemplate.getForEntity(endpointUrl, String.class);
		if (response != null && StringUtils.isNotEmpty(response.getBody())) {
			APIResponse<UserDTO> apiResponse = CommonUtils.readValue(response.getBody(), new TypeReference<APIResponse<UserDTO>>() {});
			if (apiResponse.getResult() != null) {
				user = apiResponse.getResult();
			} else if (apiResponse.getError() != null) {
				throw new GenericException(apiResponse.getError().getCode(), apiResponse.getError().getMsg());
			}
		}
		return user;
	}
	
	@Override
	public Integer createUser(AddUserRequest request) throws GenericException {
		Integer userId = null;
		ResponseEntity<String> response = restTemplate.postForEntity(createUserUrl, request, String.class);
		if (response != null && StringUtils.isNotEmpty(response.getBody())) {
			APIResponse<Integer> apiResponse = CommonUtils.readValue(response.getBody(), new TypeReference<APIResponse<Integer>>() {});
			if (apiResponse.getResult() != null) {
				userId = apiResponse.getResult();
			} else if (apiResponse.getError() != null) {
				throw new GenericException(apiResponse.getError().getCode(), apiResponse.getError().getMsg());
			}
		}
		return userId;
	}
}
