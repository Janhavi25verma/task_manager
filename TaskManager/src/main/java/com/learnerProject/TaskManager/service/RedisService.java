package com.learnerProject.TaskManager.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnerProject.TaskManager.entity.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

@Slf4j
@Service
public class RedisService {
//implementation of redis with weather api
    @Autowired
    private RedisTemplate redisTemplate;
  //get data from redis cache
    public <T> T get(String key, Class<T> entityClass){
        try {
            String obj = (String) redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(obj, entityClass);
        }catch (Exception e){
            return null;
        }
    }
  //set cache , ttl is time to live (for how long cache will be saved)
    public void set(String key, Object obj, Long ttl){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonValue = mapper.writeValueAsString(obj);
            redisTemplate.opsForValue().set(key,jsonValue,ttl, SECONDS);

        }catch (Exception e){
            log.error("Exception ", e);

        }
    }

}
