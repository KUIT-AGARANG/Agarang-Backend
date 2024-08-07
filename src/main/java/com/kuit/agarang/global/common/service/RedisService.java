package com.kuit.agarang.global.common.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

  private final RedisTemplate<String, Object> redisTemplate;
  private final ObjectMapper objectMapper;

  public void save(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }

  public <T> Optional<T> get(String key, Class<T> clazz) {
    Object value = redisTemplate.opsForValue().get(key);
    if (value == null) return Optional.empty();
    return Optional.ofNullable(objectMapper.convertValue(value, clazz));
  }

  public boolean existsByKey(String key) {
    return redisTemplate.hasKey(key);
  }

  public void delete(String key) {
    redisTemplate.delete(key);
  }

  public void saveWithExpiry(String key, String value, long timeout, TimeUnit timeUnit) {
    redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
  }
}
