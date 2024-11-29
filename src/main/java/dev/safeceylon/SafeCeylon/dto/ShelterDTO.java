package dev.safeceylon.SafeCeylon.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShelterDTO {



    private Long shelterId;

    private String location;

    private String contactNumber;

    private Integer capacity;
}
