package com.learnerProject.TaskManager.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
//TO-DO (implementation of redis with api)
    @Autowired
    private RedisTemplate redisTemplate;

//    public <T> get(String key){
//       Object obj=  redisTemplate.opsForValue().get(key);
//       ObjectMapper mapper = new ObjectMapper();
//       return mapper.readValues(obj.toString(),entityClass);
//    }


}
