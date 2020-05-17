package com.xyz.calculatorgatewayservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.calculatorgatewayservice.exception.GenericException;
import com.xyz.calculatorgatewayservice.response.APIResponse;
import com.xyz.calculatorgatewayservice.service.CalculatorService;
import com.xyz.calculatorgatewayservice.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/calculate")
public class CalculationController {

	@Autowired
	private CalculatorService calculatorService;
	
	@GetMapping("/add/{userId}/{firstNum}/{secondNum}")
	public ResponseEntity<APIResponse<BigDecimal>> sum(@PathVariable Integer userId, @PathVariable BigDecimal firstNum, @PathVariable BigDecimal secondNum) throws GenericException {
		log.info("Request to add numbers ", firstNum, secondNum);
		return ResponseUtil.getApiResponse(calculatorService.add(userId, firstNum, secondNum));
	}

	@GetMapping("/subtract/{userId}/{firstNum}/{secondNum}")
	public ResponseEntity<APIResponse<BigDecimal>> subtract(@PathVariable Integer userId, @PathVariable BigDecimal firstNum, @PathVariable BigDecimal secondNum) throws GenericException {
		log.info("Request to subtract numbers ", firstNum, secondNum);
		return ResponseUtil.getApiResponse(calculatorService.subtract(userId, firstNum, secondNum));
	}

	@GetMapping("/multiply/{userId}/{firstNum}/{secondNum}")
	public ResponseEntity<APIResponse<BigDecimal>> multiply(@PathVariable Integer userId, @PathVariable BigDecimal firstNum, @PathVariable BigDecimal secondNum) throws GenericException {
		log.info("Request to multiply numbers ", firstNum, secondNum);
		return ResponseUtil.getApiResponse(calculatorService.multiply(userId, firstNum, secondNum));
	}

	@GetMapping("/divide/{userId}/{firstNum}/{secondNum}")
	public ResponseEntity<APIResponse<BigDecimal>> divide(@PathVariable Integer userId, @PathVariable BigDecimal firstNum, @PathVariable BigDecimal secondNum) throws GenericException {
		log.info("Request to divide numbers ", firstNum, secondNum);
		return ResponseUtil.getApiResponse(calculatorService.divide(userId, firstNum, secondNum));
	}
}
