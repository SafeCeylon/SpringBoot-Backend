package dev.safeceylon.SafeCeylon.airquality;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "air_quality")
@Getter
@Setter
public class AirQuality {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String district;
    private int aqi;
}
