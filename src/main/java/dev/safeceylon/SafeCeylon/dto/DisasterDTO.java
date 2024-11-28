package dev.safeceylon.SafeCeylon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisasterDTO {


    private Long disasterId;
    private String disasterType;
    private String location;
    private String level;
    private LocalDate reportedDate;
    private LocalDate startedDate;
    private LocalDate expectedEndDate;

}
