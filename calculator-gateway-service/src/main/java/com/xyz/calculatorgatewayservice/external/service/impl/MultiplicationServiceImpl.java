package com.xyz.calculatorgatewayservice.external.service.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xyz.calculatorgatewayservice.constants.AppConstants;
import com.xyz.calculatorgatewayservice.constants.ErrorCodes;
import com.xyz.calculatorgatewayservice.exception.GenericException;
import com.xyz.calculatorgatewayservice.external.service.MultiplicationService;
import com.xyz.calculatorgatewayservice.response.APIResponse;
import com.xyz.calculatorgatewayservice.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MultiplicationServiceImpl implements MultiplicationService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${multiplication.service.url}")
	private String url;

	@Override
	@Cacheable(value = AppConstants.OperationTypeValue.MULTIPLICATION, key = "#firstNum + '-' + #secondNum")
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "100"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")})
	public BigDecimal multiply(BigDecimal firstNum, BigDecimal secondNum) throws GenericException {
		BigDecimal result = null;
		String endpointUrl = MessageFormat.format(url, firstNum, secondNum);
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.getForEntity(endpointUrl, String.class);
		} catch (Exception e) {
			log.error("Exception in multiplication service ", e);
			throw new GenericException(ErrorCodes.CA005, ErrorCodes.getErrorMsg(ErrorCodes.CA005));
		}
		if (response != null && StringUtils.isNotEmpty(response.getBody())) {
			APIResponse<BigDecimal> apiResponse = CommonUtils.readValue(response.getBody(), new TypeReference<APIResponse<BigDecimal>>() {});
			if (apiResponse.getResult() != null) {
				result = apiResponse.getResult();
			} else if (apiResponse.getError() != null) {
				throw new GenericException(apiResponse.getError().getCode(), apiResponse.getError().getMsg());
			}
		}
		return result;
	}

}
