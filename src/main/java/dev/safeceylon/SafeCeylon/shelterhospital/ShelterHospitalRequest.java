package dev.safeceylon.SafeCeylon.shelterhospital;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShelterHospitalRequest {
    private String type;       // "shelter" or "hospital"
    private double latitude;
    private double longitude;
    private int capacity;      // Only for shelters
    private String name;
    private String contact;
}
