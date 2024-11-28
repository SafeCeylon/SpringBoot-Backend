package dev.safeceylon.SafeCeylon.disastermanagement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "disasters")
public class Disaster {

    // Getters and setters

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String type;
    private double latitude;
    private double longitude;
    private double radius;

    @Column(name = "reported_at")
    private LocalDateTime reportedAt;

    @Column(name = "resolved")
    private boolean resolved = false;

    public Disaster() {}

    public Disaster(String id, String type, String location, double latitude, double longitude, double radius, String details, LocalDateTime reportedAt, boolean resolved) {
        this.id = id;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.reportedAt = reportedAt;
        this.resolved = resolved;
    }

}
