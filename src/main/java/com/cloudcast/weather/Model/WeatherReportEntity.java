package com.cloudcast.weather.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherReportEntity {

    @Id
    String city;
    int humidity;
    double temp;
    double windSpeed;
    Date timeStamp;
    String description;
    double lat;
    double lon;

}
