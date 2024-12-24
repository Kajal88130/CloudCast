package com.cloudcast.weather.Model;

import lombok.Data;

@Data
public class ForecastRequestDto {

    private double lat;
    private double lon;
}
