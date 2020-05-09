package com.xyz.calculatorgatewayservice.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.calculatorgatewayservice.constants.AppConstants;
import com.xyz.calculatorgatewayservice.dto.UserDTO;
import com.xyz.calculatorgatewayservice.enums.OperationType;
import com.xyz.calculatorgatewayservice.exception.GenericException;
import com.xyz.calculatorgatewayservice.external.service.AdditionService;
import com.xyz.calculatorgatewayservice.external.service.CreditService;
import com.xyz.calculatorgatewayservice.external.service.DivisionService;
import com.xyz.calculatorgatewayservice.external.service.MultiplicationService;
import com.xyz.calculatorgatewayservice.external.service.SubtractionService;
import com.xyz.calculatorgatewayservice.external.service.UserService;
import com.xyz.calculatorgatewayservice.service.CalculatorService;
import com.xyz.calculatorgatewayservice.validator.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalculatorServiceImpl implements CalculatorService {

	@Autowired
	private AdditionService additionService;

	@Autowired
	private SubtractionService subtractionService;

	@Autowired
	private MultiplicationService multiplicationService;

	@Autowired
	private DivisionService divisionService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CreditService creditService;
	
	@Autowired
	private Validator calculationValidator;;
	
	@Override
	public BigDecimal add(Integer userId, BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		
		UserDTO user = userService.getUserByUserId(userId);
		
		calculationValidator.validateUserAndAction(user, OperationType.ADD);
		
		BigDecimal result = additionService.add(firstNum, secondNum);
		creditService.redeemCreditsForUser(user, userId, AppConstants.getOpercationCost(OperationType.ADD), AppConstants.getOpercationPurpose(OperationType.ADD), user.getAvailableCredits() - AppConstants.getOpercationCost(OperationType.ADD));
		
		return result;
	}

	@Override
	public BigDecimal subtract(Integer userId, BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		
		UserDTO user = userService.getUserByUserId(userId); 
		
		calculationValidator.validateUserAndAction(user, OperationType.SUBTRACT);
		
		BigDecimal result = subtractionService.subtract(firstNum, secondNum);
		creditService.redeemCreditsForUser(user, userId, AppConstants.getOpercationCost(OperationType.SUBTRACT), AppConstants.getOpercationPurpose(OperationType.SUBTRACT), user.getAvailableCredits() - AppConstants.getOpercationCost(OperationType.SUBTRACT));
		
		return result;
	}

	@Override
	public BigDecimal multiply(Integer userId, BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		
		UserDTO user = userService.getUserByUserId(userId); 
		
		calculationValidator.validateUserAndAction(user, OperationType.MULTIPLY);
		
		BigDecimal result = multiplicationService.multiply(firstNum, secondNum);
		creditService.redeemCreditsForUser(user, userId, AppConstants.getOpercationCost(OperationType.MULTIPLY), AppConstants.getOpercationPurpose(OperationType.MULTIPLY), user.getAvailableCredits() - AppConstants.getOpercationCost(OperationType.MULTIPLY));
		
		return result;
	}

	@Override
	public BigDecimal divide(Integer userId, BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		
		UserDTO user = userService.getUserByUserId(userId); 
		
		calculationValidator.validateUserAndAction(user, OperationType.DIVIDE);
		
		BigDecimal result = divisionService.divide(firstNum, secondNum);
		creditService.redeemCreditsForUser(user, userId, AppConstants.getOpercationCost(OperationType.DIVIDE), AppConstants.getOpercationPurpose(OperationType.DIVIDE), user.getAvailableCredits() - AppConstants.getOpercationCost(OperationType.DIVIDE));

		return result;
	}
	
}
