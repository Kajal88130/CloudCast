package com.cloudcast.weather.Model;

import lombok.Data;

@Data
public class WeatherResponseDto {

    private double temp;
    private String city;
    private int humidity;
    private double speed;
    private String description;
}
