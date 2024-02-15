package com.app.ecommercial.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void setValue(String key, Object value) {
        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
        valueOps.set(key, value);
    }

    public void setValue(String key, Object value, long ttl) {
        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
        valueOps.set(key, value, ttl, TimeUnit.MILLISECONDS);
    }

    public Object getValue(String key) {
        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
        return valueOps.get(key);
    }
}
