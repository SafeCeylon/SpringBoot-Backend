package dev.safeceylon.SafeCeylon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Disaster")
public class Disaster {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long disasterId;

    @Column(nullable = false)
    private String disasterType;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false)
    private LocalDate reportedDate;

    @Column(nullable = false)
    private LocalDate startedDate;

    @Column(nullable = false)
    private LocalDate expectedEndDate;
}
