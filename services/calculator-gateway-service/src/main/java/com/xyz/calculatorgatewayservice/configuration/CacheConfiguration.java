package com.xyz.calculatorgatewayservice.configuration;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
@EnableCaching(mode = AdviceMode.PROXY, proxyTargetClass = false)
public class CacheConfiguration {

	@Value("${redis.host}")
	private String redisHost;

	@Value("${redis.port}")
	private int redisPort;
	
	@Value("${redis.ttl}")
	private int ttl;

	@Bean
	public CacheManager cacheManager() {
		RedisCacheManager rcm = RedisCacheManager.builder(jedisConnectionFactory())
				.cacheDefaults(cacheConfiguration())
				.transactionAware()
				.build();
		return (CacheManager) rcm;
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(redisHost);
		redisStandaloneConfiguration.setPort(redisPort);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean(name = "calculatorCache")
	public RedisCacheConfiguration cacheConfiguration() {
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(ttl))
				.disableCachingNullValues();
		return cacheConfig;
	}
}
