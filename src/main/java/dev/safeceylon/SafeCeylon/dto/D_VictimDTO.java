package dev.safeceylon.SafeCeylon.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class D_VictimDTO {

    private Long dvId;


    private String disasterType;


    private LocalDate reportedDate;

    //private Long shelterId;
}
