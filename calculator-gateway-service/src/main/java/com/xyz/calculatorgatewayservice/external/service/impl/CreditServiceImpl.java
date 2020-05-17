package com.xyz.calculatorgatewayservice.external.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xyz.calculatorgatewayservice.constants.AppConstants;
import com.xyz.calculatorgatewayservice.dto.UserDTO;
import com.xyz.calculatorgatewayservice.exception.GenericException;
import com.xyz.calculatorgatewayservice.external.request.AddCreditRequest;
import com.xyz.calculatorgatewayservice.external.request.RedeemCreditRequest;
import com.xyz.calculatorgatewayservice.external.service.CreditService;
import com.xyz.calculatorgatewayservice.external.service.UserService;
import com.xyz.calculatorgatewayservice.models.CreditTransactionKafkaPayload;
import com.xyz.calculatorgatewayservice.response.APIResponse;
import com.xyz.calculatorgatewayservice.service.KafkaProducerService;
import com.xyz.calculatorgatewayservice.util.CommonUtils;
import com.xyz.calculatorgatewayservice.validator.impl.CalculationValidator;

@Service
public class CreditServiceImpl implements CreditService {
	
	@Autowired
	private KafkaProducerService kafkaProducerService;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CalculationValidator calculationValidator;
	
	@Value("${add.credit.url}")
	private String addCreditUrl;

	@Value("${redeem.credit.url}")
	private String redeemCreditUrl;

	@Value("${add.credit.topic}")
	private String addCreditTopic;
	
	@Value("${redeem.credit.topic}")
	private String redeemCreditTopic;

	@Override
	public Integer addCreditsForUser(Integer userId, Double amount, String purpose) throws GenericException {
		Integer txId = null;
		UserDTO user = userService.getUserByUserId(userId);
		
		calculationValidator.validateUser(user);
		
		if (StringUtils.isEmpty(purpose)) {
			purpose = AppConstants.CREDIT_TOP_UP;
		}
		ResponseEntity<String> response = restTemplate.postForEntity(addCreditUrl, createAddCreditRequest(userId, amount, purpose), String.class);
		if (response != null && StringUtils.isNotEmpty(response.getBody())) {
			APIResponse<Integer> apiResponse = CommonUtils.readValue(response.getBody(), new TypeReference<APIResponse<Integer>>() {});
			if (apiResponse.getResult() != null) {
				txId = apiResponse.getResult();
			} else if (apiResponse.getError() != null) {
				throw new GenericException(apiResponse.getError().getCode(), apiResponse.getError().getMsg());
			}
		}
		
		Double availableAmount =user.getAvailableCredits() != null ? user.getAvailableCredits() + amount : amount;
		produceKafkaMessage(addCreditTopic, txId, userId, user.getEmail(), amount, purpose, availableAmount);
		return txId;
	}

	@Override
	public Integer redeemCreditsForUser(UserDTO user, Integer userId, Double amount, String purpose, Double availableCredits) throws GenericException {
		Integer txId = null;
		ResponseEntity<String> response = restTemplate.postForEntity(redeemCreditUrl, createRedeemCreditRequest(userId, amount, purpose), String.class);
		if (response != null && StringUtils.isNotEmpty(response.getBody())) {
			APIResponse<Integer> apiResponse = CommonUtils.readValue(response.getBody(), new TypeReference<APIResponse<Integer>>() {});
			if (apiResponse.getResult() != null) {
				txId = apiResponse.getResult();
			} else if (apiResponse.getError() != null) {
				throw new GenericException(apiResponse.getError().getCode(), apiResponse.getError().getMsg());
			}
		}
		produceKafkaMessage(redeemCreditTopic, txId, userId, user.getEmail(), amount, purpose, availableCredits);
		return txId;
	}
	
	private void produceKafkaMessage(String topicName, Integer transactionId, Integer userId, String userEmail,
			Double transactionAmount, String purpose, Double availableCredits) {
		CreditTransactionKafkaPayload payload = CreditTransactionKafkaPayload.builder()
				.availableCredits(availableCredits)
				.purpose(purpose)
				.topicName(topicName)
				.transactionId(transactionId)
				.transactionAmount(transactionAmount)
				.transactionAt(System.currentTimeMillis())
				.userId(userId)
				.userEmail(userEmail)
				.build(); 
		kafkaProducerService.produceMessage(topicName, CommonUtils.toJson(payload));
	}
	
	private RedeemCreditRequest createRedeemCreditRequest(Integer userId, Double amount, String purpose) {
		return RedeemCreditRequest.builder().amountToRedeem(amount).purpose(purpose).userId(userId).build();
	}

	private AddCreditRequest createAddCreditRequest(Integer userId, Double amount, String purpose) {
		return AddCreditRequest.builder().amountToAdd(amount).purpose(purpose).userId(userId).build();
	}

}
