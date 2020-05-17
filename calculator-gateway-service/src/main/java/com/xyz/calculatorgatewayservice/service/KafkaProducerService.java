package com.xyz.calculatorgatewayservice.service;

public interface KafkaProducerService {

	public boolean produceMessage(String topic, String payload);
}
