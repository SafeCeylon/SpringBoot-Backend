package dev.safeceylon.SafeCeylon.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MD_OfficerDTO {

    private Long empNumber;

    private String nicNumber;

    private String firstName;


    private String lastName;


    private String email;


    private String contactNumber;


    // private String officerPhotoUrl;


}
