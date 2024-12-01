package dev.safeceylon.SafeCeylon.landslide;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "landslide_warnings")
@Getter
@Setter
public class LandslideWarning {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "date_issued")
    private String dateIssued;

    @Column(name = "time_issued")
    private String timeIssued;

    private String district;

    @Column(name = "warning_level")
    private String warningLevel;

    @Column(name = "divisional_secretariat_divisions", columnDefinition = "TEXT")
    private String divisionalSecretariatDivisions;
}
