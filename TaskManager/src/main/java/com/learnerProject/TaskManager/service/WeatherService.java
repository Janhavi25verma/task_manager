package com.learnerProject.TaskManager.service;

import com.learnerProject.TaskManager.client.WeatherClient;
import com.learnerProject.TaskManager.entity.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


//Service created to inject apiKey from application.properties as cannot be done in interface(WeatherClient)
@Service
@RequiredArgsConstructor
public class WeatherService {

    @Autowired
     private WeatherClient weatherClient;

    @Value("${service.weather.api.key}")
    private String apiKey;

    @Autowired
    private RedisService redisService;


    public WeatherResponse fetchWeather(String city){
       WeatherResponse weatherResponse = redisService.get("weather_of_"+city,WeatherResponse.class);
       if(weatherResponse != null){
           return weatherResponse;
       }else {
           WeatherResponse response = weatherClient.getWeather(apiKey, city);
           if(response != null){
               redisService.set("weather_of_"+city,response,300l);
           }
           return response;
       }
    }
}
