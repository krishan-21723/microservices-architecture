package com.xyz.calculatorgatewayservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.calculatorgatewayservice.exception.GenericException;
import com.xyz.calculatorgatewayservice.external.request.AddCreditRequest;
import com.xyz.calculatorgatewayservice.external.service.CreditService;
import com.xyz.calculatorgatewayservice.response.APIResponse;
import com.xyz.calculatorgatewayservice.util.CommonUtils;
import com.xyz.calculatorgatewayservice.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/credit")
public class CreditController {

	@Autowired
	private CreditService creditService;

	@PostMapping("/add")
	public ResponseEntity<APIResponse<Integer>> addCredit(@RequestBody AddCreditRequest request) throws GenericException {
		log.info("Got request to add user with request", CommonUtils.toJson(request));
		return ResponseUtil.getApiResponse(creditService.addCreditsForUser(request.getUserId(), request.getAmountToAdd(), request.getPurpose()));
	}

}
