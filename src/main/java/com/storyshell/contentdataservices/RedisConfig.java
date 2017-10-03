package com.storyshell.contentdataservices;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

	@Inject
	private Environment env;

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setUsePool(true);
		jedisConFactory.setHostName(env.getProperty("redis.url"));
		jedisConFactory.setPort(Integer.parseInt(env.getProperty("redis.port")));
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
