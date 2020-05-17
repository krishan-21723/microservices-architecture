package com.xyz.divisionservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyz.divisionservice.exception.GenericException;
import com.xyz.divisionservice.service.DivisionService;

@RunWith(SpringRunner.class)
@SpringBootTest
class DivisionServiceApplicationTests {

	@Autowired
	private DivisionService divisionService;

	@Test
	void correctDivision() throws GenericException {
		assertThat(divisionService.divide(new BigDecimal(6), new BigDecimal(2))).isEqualTo(new BigDecimal(3));
	}

	@Test
	void inCorrectDivision() throws GenericException {
		assertThat(divisionService.divide(new BigDecimal(6), new BigDecimal(2))).isNotEqualTo(new BigDecimal(5));
	}

}
