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
@Table(name="D_Victim")

public class D_Victim {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long dvId;

        @Column(nullable = false)
        private String disasterType;

        @Column(nullable = false)
        private LocalDate reportedDate;

      /*  @ManyToOne
        @JoinColumn(name = "shelterId")
        private Shelter shelter;
*/
       // @ManyToOne
       // @JoinColumn(name = "assignedOfficerId")
       // private Officer assignedOfficer; // Foreign Key to Officer (Officer)
    }



