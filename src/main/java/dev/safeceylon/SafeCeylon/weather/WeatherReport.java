package dev.safeceylon.SafeCeylon.weather;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "weather_reports")
@Getter
@Setter
public class WeatherReport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String date;
    private String timeIssued;
    private String province;
    private String district;
    private String condition;
    private String windSpeeds;
    private String rainfallType;
}
