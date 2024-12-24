package com.cloudcast.weather.Controller;

import com.cloudcast.weather.Model.ForecastRequestDto;
import com.cloudcast.weather.Model.WeatherResponseDto;
import com.cloudcast.weather.Service.WeatherReportService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

@RestController
public class WeatherReportController {
//    private static final Logger log = LoggerFactory.getLogger(WeatherReportController.class);

    WeatherReportService weatherReportService;

    public WeatherReportController(WeatherReportService weatherReportService) {
        this.weatherReportService = weatherReportService;
    }

    @GetMapping("/weather/{city}")
    public ResponseEntity<WeatherResponseDto> getWeatherByCity(@PathVariable String city) {
        WeatherResponseDto responseDto = weatherReportService.getWeatherByCity(city);
        if (responseDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/weather/hourly")
    public ResponseEntity<WeatherResponseDto> getHourlyForecast(@RequestBody ForecastRequestDto forecastRequestDto) {
        WeatherResponseDto responseDto = weatherReportService.get5DaysForecast(forecastRequestDto);
        if (responseDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
