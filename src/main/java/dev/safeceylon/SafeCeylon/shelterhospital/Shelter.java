package dev.safeceylon.SafeCeylon.shelterhospital;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shelters")
@Getter
@Setter
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String type;
    private double latitude;
    private double longitude;
    private int capacity; // Shelter capacity
    private String name;  // Shelter name
    private String contact; // Contact information

    public Shelter() {}

    public Shelter( String type, String name, double latitude, double longitude, int capacity, String contact) {
        this.type = type;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacity = capacity;
        this.contact = contact;
    }
}
