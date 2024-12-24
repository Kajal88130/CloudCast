package com.cloudcast.weather.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private Main main;
    private Wind wind;
    private Coord coord;
    private List<Weather> weather;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main {
        private double temp;
        private int humidity;

    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Wind {
        private double speed;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Weather {
        private String description;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Coord {
        private double lat;
        private double lon;

    }


}
