package dev.safeceylon.SafeCeylon.flood;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "flood_warnings")
@Getter
@Setter
public class FloodWarning {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "date_issued", nullable = false)
    private String dateIssued = LocalDate.now().toString();

    @Column(name = "file_path", nullable = false)
    private String filePath; // Store file path instead of binary data
}
