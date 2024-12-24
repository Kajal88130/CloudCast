package com.cloudcast.weather.Service;

import com.cloudcast.weather.Model.ForecastRequestDto;
import com.cloudcast.weather.Model.WeatherResponseDto;
import com.cloudcast.weather.Model.ApiResponse;
import com.cloudcast.weather.Model.WeatherReportEntity;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class WeatherReportService {
    private static final Logger log = LoggerFactory.getLogger(WeatherReportService.class);


    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url.city}")
    private String cityApiUrl;

    @Value("${weather.api.url.forecast.hourly}")
    private String forecastUrl;

    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    public WeatherReportService(RestTemplate restTemplate, ModelMapper modelMapper) {
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }

    public WeatherResponseDto getWeatherByCity(String city) {
        String url = cityApiUrl.replace("{city}", city).replace("{apiKey}", apiKey);
        log.info("url is: " + url);

        ApiResponse apiResponse = restTemplate.getForObject(url, ApiResponse.class, apiKey);
        log.info("API response: {}", apiResponse);

        WeatherReportEntity weatherReportEntity = new WeatherReportEntity();
        if (apiResponse != null) {
            weatherReportEntity.setCity(city);
            weatherReportEntity.setHumidity(apiResponse.getMain().getHumidity());
            weatherReportEntity.setTemp(apiResponse.getMain().getTemp());
            weatherReportEntity.setWindSpeed(apiResponse.getWind().getSpeed());
            weatherReportEntity.setDescription(apiResponse.getWeather().get(0).getDescription());
            weatherReportEntity.setTimeStamp(new Date());
            log.info("Weather Report: " + weatherReportEntity);

        }
        return modelMapper.map(weatherReportEntity, WeatherResponseDto.class);
    }


    public WeatherResponseDto get5DaysForecast(ForecastRequestDto forecastRequestDto) {
        String url = forecastUrl
                .replace("{lat}", String.valueOf(forecastRequestDto.getLat()))
                .replace("{lon}", String.valueOf(forecastRequestDto.getLon()))
                .replace("{apiKey}", apiKey);
        log.info("url is: " + url);
        ApiResponse apiResponse = restTemplate.getForObject(url, ApiResponse.class, apiKey);
        log.info("API response: {}", apiResponse);

        WeatherReportEntity weatherReportEntity = new WeatherReportEntity();
        if (apiResponse != null) {
            weatherReportEntity.setHumidity(apiResponse.getMain().getHumidity());
            weatherReportEntity.setCity(weatherReportEntity.getCity());
            weatherReportEntity.setTemp(apiResponse.getMain().getTemp());
            weatherReportEntity.setLon(apiResponse.getCoord().getLon());
            weatherReportEntity.setLat(apiResponse.getCoord().getLat());
            weatherReportEntity.setWindSpeed(apiResponse.getWind().getSpeed());
            weatherReportEntity.setDescription(apiResponse.getWeather().get(0).getDescription());
            weatherReportEntity.setTimeStamp(new Date());
            log.info("Weather forecast Report: " + weatherReportEntity);
        }
        return modelMapper.map(weatherReportEntity, WeatherResponseDto.class);
    }
}
