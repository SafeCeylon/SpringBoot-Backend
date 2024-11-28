package dev.safeceylon.SafeCeylon.shelterhospital;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hospitals")
@Getter
@Setter
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String type;
    private double latitude;
    private double longitude;
    private String name;   // Hospital name
    private String contact; // Contact information

    public Hospital() {}

    public Hospital(String type, String name, double latitude, double longitude, String contact) {
        this.type = type;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contact = contact;
    }
}
