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


    public WeatherResponse fetchWeather(String city){
        return weatherClient.getWeather(apiKey,city);

    }
}
