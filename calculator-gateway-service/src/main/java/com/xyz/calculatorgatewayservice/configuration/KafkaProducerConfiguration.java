package com.xyz.calculatorgatewayservice.configuration;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfiguration {

	@Value("${bootstrap.servers}")
	private String bootstrapServers;

	@Bean
	public KafkaProducer<String, byte[]> getProducerConfig() {
		Properties props = new Properties();
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
		props.put(ProducerConfig.RETRIES_CONFIG, 10);
		KafkaProducer<String, byte[]> producer = new KafkaProducer<String, byte[]>(props);
		return producer;
	}

}
