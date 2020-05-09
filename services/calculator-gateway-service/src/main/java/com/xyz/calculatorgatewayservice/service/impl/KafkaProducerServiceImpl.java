package com.xyz.calculatorgatewayservice.service.impl;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.calculatorgatewayservice.service.KafkaProducerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

	@Autowired
	private KafkaProducer<String, byte[]> kafkaProducer;

	@Override
	public boolean produceMessage(String topic, String payload) {
		ProducerRecord<String, byte[]> data = new ProducerRecord<String, byte[]>(topic, payload.getBytes());
		try {
			RecordMetadata c = kafkaProducer.send(data).get();
		} catch (Exception e) {
			log.error("Error while producing payload in kafka for topic {}", topic, e);
		}
		return false;
	}

}
