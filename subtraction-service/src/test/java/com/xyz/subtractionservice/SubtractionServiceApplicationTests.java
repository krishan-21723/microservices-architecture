package com.xyz.subtractionservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyz.subtractionservice.exception.GenericException;
import com.xyz.subtractionservice.service.SubtractionService;

@RunWith(SpringRunner.class)
@SpringBootTest
class SubtractionServiceApplicationTests {

	@Autowired
	private SubtractionService subtractionService;

	@Test
	void correctSubtraction() throws GenericException {
		assertThat(subtractionService.subtract(new BigDecimal(5), new BigDecimal(3))).isEqualTo(new BigDecimal(2));
	}

	@Test
	void inCorrectSubtraction() throws GenericException {
		assertThat(subtractionService.subtract(new BigDecimal(5), new BigDecimal(3))).isNotEqualTo(new BigDecimal(1));
	}

}
