package com.xyz.additionservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.additionservice.exception.GenericException;
import com.xyz.additionservice.response.APIResponse;
import com.xyz.additionservice.service.AdditionService;
import com.xyz.additionservice.util.ResponseUtil;

@RestController
public class AdditionController {

	@Autowired
	private AdditionService additionService;

	@GetMapping("/add/{firstNum}/{secondNum}")
	public ResponseEntity<APIResponse<BigDecimal>> sum(@PathVariable BigDecimal firstNum, @PathVariable BigDecimal secondNum) throws GenericException {
		return ResponseUtil.getApiResponse(additionService.add(firstNum, secondNum));
	}

}
