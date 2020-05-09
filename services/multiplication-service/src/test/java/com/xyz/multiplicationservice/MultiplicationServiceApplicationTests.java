package com.xyz.multiplicationservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyz.multiplicationservice.exception.GenericException;
import com.xyz.multiplicationservice.service.MultiplicationService;

@RunWith(SpringRunner.class)
@SpringBootTest
class MultiplicationServiceApplicationTests {

	@Autowired
	private MultiplicationService multiplicationService;

	@Test
	void correctMultiplication() throws GenericException {
		assertThat(multiplicationService.multiply(new BigDecimal(6), new BigDecimal(2))).isEqualTo(new BigDecimal(12));
	}

	@Test
	void inCorrectMultiplication() throws GenericException {
		assertThat(multiplicationService.multiply(new BigDecimal(6), new BigDecimal(2))).isNotEqualTo(new BigDecimal(2));
	}

}
