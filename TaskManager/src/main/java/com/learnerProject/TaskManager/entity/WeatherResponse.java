package com.learnerProject.TaskManager.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;


@Data
public class WeatherResponse {

    private Current current;

    @Data
    public static class Current {
        @JsonProperty("temperature")
        private int temperature;

        @JsonProperty("feelslike")
        private int feelsLike;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
    }
}



