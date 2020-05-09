package com.xyz.calculatorgatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableCircuitBreaker
@EnableHystrixDashboard
public class CalculatorGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorGatewayServiceApplication.class, args);
	}
}
