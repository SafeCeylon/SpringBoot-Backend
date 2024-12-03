package dev.safeceylon.SafeCeylon.metreologtdepartmentofficer;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = " metreologydepartmentofficer")

public class MetreologyDepartmentOfficer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empNumber;

    @Column(nullable = false , unique = true)
    private String nicNumber;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String contactNumber;

     @Column(name = "photo", nullable = false)
     private String officerPhotoUrl;

}
