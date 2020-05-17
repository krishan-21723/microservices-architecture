package com.example.centralconfigservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableConfigServer
@EnableSwagger2
public class CentralConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralConfigServiceApplication.class, args);
	}

}
