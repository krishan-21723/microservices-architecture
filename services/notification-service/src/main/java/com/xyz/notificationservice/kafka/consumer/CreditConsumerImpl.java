package com.xyz.notificationservice.kafka.consumer;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xyz.notificationservice.enums.CreditTransactionType;
import com.xyz.notificationservice.models.CreditTransactionKafkaPayload;
import com.xyz.notificationservice.service.EmailService;
import com.xyz.notificationservice.util.CommonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreditConsumerImpl extends BaseConsumer {

	@Value("${spring.application.name}")
	private String groupPrefix;

	@Value("${add.credit.topic}")
	private String addCreditTopic;

	@Value("${redeem.credit.topic}")
	private String redeemCreditTopic;

	private static String creditConsumerGroup = "creditGroup";

	@Autowired
	private EmailService emailService;

	@PostConstruct
	public void consume() {
		super.consume(Arrays.asList(addCreditTopic, redeemCreditTopic), groupPrefix + creditConsumerGroup);
	}

	@Override
	public void process(String topic, byte[] payload) throws Exception {
		CreditTransactionKafkaPayload creditTransctionKafkaPayload = null;
		try {
			String strMessgae = getMessageInBuilder(payload);
			creditTransctionKafkaPayload = CommonUtils.fromJson(strMessgae, CreditTransactionKafkaPayload.class);
			log.info("Got payload to process for topic {} and {}", creditTransctionKafkaPayload.getTopicName(), strMessgae);
		} catch (Exception e) {
			log.error("Exception while mapping kafka payload to object for {} with ", topic, e);
		}
		CreditTransactionType type = null;
		if (creditTransctionKafkaPayload != null && StringUtils.isNotEmpty(creditTransctionKafkaPayload.getTopicName())) {
			if (addCreditTopic.equals(creditTransctionKafkaPayload.getTopicName())) {
				type = CreditTransactionType.ADDED;
			} else if (redeemCreditTopic.equals(creditTransctionKafkaPayload.getTopicName())) {
				type = CreditTransactionType.REDDEMED;
			}
		}
		emailService.sendCreditTransctionEmail(creditTransctionKafkaPayload, type);
	}

}
