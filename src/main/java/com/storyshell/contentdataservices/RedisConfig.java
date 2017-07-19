package com.storyshell.contentdataservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setUsePool(true);
		jedisConFactory.setHostName("localhost");
		jedisConFactory.setPort(6379);
		return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		// template.setStringSerializer(new StringRedisSerializer());
		// template.setKeySerializer(new StringRedisSerializer());
		// template.setHashValueSerializer(new
		// GenericToStringSerializer<Object>(Object.class));
		// template.setValueSerializer(new
		// GenericToStringSerializer<Object>(Object.class));
		return template;
	}
}
