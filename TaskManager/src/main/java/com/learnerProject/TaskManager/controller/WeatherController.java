package com.learnerProject.TaskManager.controller;

import com.learnerProject.TaskManager.entity.WeatherResponse;
import com.learnerProject.TaskManager.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/city/{city}")
    public ResponseEntity<?> weather(@PathVariable String city){
        try {
            WeatherResponse weather = weatherService.fetchWeather(city);
            return ResponseEntity.ok(weather);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Weather not found for city" + city);
        }
    }
}

