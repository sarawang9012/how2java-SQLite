package com.how2java.springboot.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * ClassName: RedisCacheService
 * Package: com.how2java.springboot.service
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/11/16 20:12
 * @Version 1.0
 */
@Service
public class RedisCacheService {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;
}
