package com.xyz.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.userservice.exception.GenericException;
import com.xyz.userservice.request.AddCreditRequest;
import com.xyz.userservice.request.RedeemCreditRequest;
import com.xyz.userservice.response.APIResponse;
import com.xyz.userservice.service.CreditService;
import com.xyz.userservice.util.CommonUtils;
import com.xyz.userservice.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("credit")
public class CreditController {

	@Autowired
	private CreditService creditService;

	@PostMapping("/add")
	public ResponseEntity<APIResponse<Integer>> addCredit(@RequestBody AddCreditRequest request) throws GenericException {
		log.info("Got request to add credits with request", CommonUtils.toJson(request));
		return ResponseUtil.getApiResponse(creditService.validateUserAndAddCredit(request));
	}

	@PostMapping("/redeem")
	public ResponseEntity<APIResponse<Integer>> redeemCredits(@RequestBody RedeemCreditRequest request) throws GenericException {
		log.info("Got request to redeem credits with request", CommonUtils.toJson(request));
		return ResponseUtil.getApiResponse(creditService.validateUserAndRedeemCredit(request));
	}
}
