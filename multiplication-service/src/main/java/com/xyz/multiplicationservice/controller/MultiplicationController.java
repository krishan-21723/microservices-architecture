package com.xyz.multiplicationservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.multiplicationservice.exception.GenericException;
import com.xyz.multiplicationservice.response.APIResponse;
import com.xyz.multiplicationservice.service.MultiplicationService;
import com.xyz.multiplicationservice.util.ResponseUtil;

@RestController
public class MultiplicationController {

	@Autowired
	private MultiplicationService multiplicationService;
	
	@GetMapping("/multiply/{firstNum}/{secondNum}")
	public ResponseEntity<APIResponse<BigDecimal>> multiply(@PathVariable BigDecimal firstNum, @PathVariable BigDecimal secondNum) throws GenericException {
		return ResponseUtil.getApiResponse(multiplicationService.multiply(firstNum, secondNum));
	}

}
