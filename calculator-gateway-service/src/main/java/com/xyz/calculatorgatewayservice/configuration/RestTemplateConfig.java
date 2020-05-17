package com.xyz.calculatorgatewayservice.configuration;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean(name = { "restTemplate" })
	@Primary
	public RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	private ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(10000);
		factory.setConnectTimeout(5000);
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(7200);
		cm.setDefaultMaxPerRoute(1200);
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		factory.setHttpClient(httpClient);
		return factory;
	}

}