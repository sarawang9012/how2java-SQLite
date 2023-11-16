package com.how2java.springboot.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * ClassName: RedisConfig
 * Package: com.how2java.springboot.config
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/11/16 18:11
 * @Version 1.0
 */
@Configuration
public class RedisConfig{
    @Bean(name="customConnectionFactory")
    public JedisConnectionFactory redisConnectionFactory() {

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("192.168.56" +
                ".103", 6379);
        return new JedisConnectionFactory(config);
    }
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(@Qualifier("customConnectionFactory") RedisConnectionFactory connectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
    @Bean
    public StringRedisTemplate stringRedisTemplate(@Qualifier("customConnectionFactory") RedisConnectionFactory connectionFactory){
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
