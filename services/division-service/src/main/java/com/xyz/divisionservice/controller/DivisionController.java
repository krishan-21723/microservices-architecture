package com.xyz.divisionservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.divisionservice.exception.GenericException;
import com.xyz.divisionservice.response.APIResponse;
import com.xyz.divisionservice.service.DivisionService;
import com.xyz.divisionservice.util.ResponseUtil;

@RestController
public class DivisionController {

	@Autowired
	private DivisionService divisionService;
	
	@GetMapping("/divide/{firstNum}/{secondNum}")
	public ResponseEntity<APIResponse<BigDecimal>> divide(@PathVariable BigDecimal firstNum, @PathVariable BigDecimal secondNum) throws GenericException {
		return ResponseUtil.getApiResponse(divisionService.divide(firstNum, secondNum));
	}

}
