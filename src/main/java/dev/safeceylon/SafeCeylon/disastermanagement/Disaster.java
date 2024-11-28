package dev.safeceylon.SafeCeylon.disastermanagement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "disasters")
public class Disaster {

    // Getters and setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Assuming PostgreSQL is using UUID
    private String id;

    private String type; // Type of disaster (e.g., flood, earthquake)

    @Setter
    @Getter
    private double latitude;     // Latitude of the disaster location
    @Setter
    @Getter
    private double longitude;    // Longitude of the disaster location

    @Setter
    @Getter
    private double radius;       // Affected area radius in meters/kilometers

    @Setter
    @Getter
    @Column(name = "reported_at")
    private LocalDateTime reportedAt; // Timestamp of when the disaster was reported

    @Getter
    @Setter
    @Column(name = "resolved")
    private boolean resolved = false; // Status of the disaster (resolved or ongoing)

    // Default constructor
    public Disaster() {}

    // Parameterized constructor
    public Disaster(String id, String type, String location, double latitude, double longitude, double radius, String details, LocalDateTime reportedAt, boolean resolved) {
        this.id = id;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.reportedAt = reportedAt;
        this.resolved = resolved;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
