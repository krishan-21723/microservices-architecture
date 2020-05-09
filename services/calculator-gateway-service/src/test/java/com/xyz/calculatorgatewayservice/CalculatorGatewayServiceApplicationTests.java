package com.xyz.calculatorgatewayservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyz.calculatorgatewayservice.exception.GenericException;
import com.xyz.calculatorgatewayservice.service.CalculatorService;

@RunWith(SpringRunner.class)
@SpringBootTest
class CalculatorGatewayServiceApplicationTests {

	@MockBean
	private CalculatorService calculatorService;

	@Test
	void contextLoads() throws GenericException {
		Integer userId = 123;
		BigDecimal firstNum = new BigDecimal(10);
		BigDecimal secondNum = new BigDecimal(5);
		
		Mockito.when(calculatorService.add(userId, firstNum, secondNum)).thenReturn(new BigDecimal(15));
		Mockito.when(calculatorService.subtract(userId, firstNum, secondNum)).thenReturn(new BigDecimal(5));
		Mockito.when(calculatorService.multiply(userId, firstNum, secondNum)).thenReturn(new BigDecimal(50));
		Mockito.when(calculatorService.divide(userId, firstNum, secondNum)).thenReturn(new BigDecimal(2));
		
		assertThat(calculatorService.add(userId, firstNum, secondNum)).isEqualTo(new BigDecimal(15));
		assertThat(calculatorService.subtract(userId, firstNum, secondNum)).isEqualTo(new BigDecimal(5));
		assertThat(calculatorService.multiply(userId, firstNum, secondNum)).isEqualTo(new BigDecimal(50));
		assertThat(calculatorService.divide(userId, firstNum, secondNum)).isEqualTo(new BigDecimal(2));
		
	}

}
