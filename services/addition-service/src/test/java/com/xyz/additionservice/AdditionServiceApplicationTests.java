package com.xyz.additionservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyz.additionservice.exception.GenericException;
import com.xyz.additionservice.service.AdditionService;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdditionServiceApplicationTests {

	@Autowired
	private AdditionService additionService;

	@Test
	void correctAddition() throws GenericException {
		assertThat(additionService.add(new BigDecimal(2), new BigDecimal(3))).isEqualTo(new BigDecimal(5));
	}

	@Test
	void inCorrectAddition() throws GenericException {
		assertThat(additionService.add(new BigDecimal(2), new BigDecimal(3))).isNotEqualTo(new BigDecimal(10));
	}

}
