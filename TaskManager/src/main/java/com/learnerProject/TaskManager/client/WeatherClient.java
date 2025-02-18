package com.learnerProject.TaskManager.client;

import com.learnerProject.TaskManager.entity.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather-service", url = "http://api.weatherstack.com")
public interface WeatherClient {

    @GetMapping("/current")
    WeatherResponse getWeather(@RequestParam("access_key") String apiKey,
                               @RequestParam("query") String city);

}


