package com.xyz.subtractionservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.subtractionservice.exception.GenericException;
import com.xyz.subtractionservice.response.APIResponse;
import com.xyz.subtractionservice.service.SubtractionService;
import com.xyz.subtractionservice.util.ResponseUtil;

@RestController
public class SubtractionController {

	@Autowired
	private SubtractionService subtractionService;
	
	@GetMapping("/subtract/{firstNum}/{secondNum}")
	public ResponseEntity<APIResponse<BigDecimal>> subtract(@PathVariable BigDecimal firstNum, @PathVariable BigDecimal secondNum) throws GenericException {
		return ResponseUtil.getApiResponse(subtractionService.subtract(firstNum, secondNum));
	}

}
