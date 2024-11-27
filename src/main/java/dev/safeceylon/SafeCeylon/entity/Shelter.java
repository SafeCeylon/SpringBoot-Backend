package dev.safeceylon.SafeCeylon.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Shelter")
public class Shelter {




        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        @Column(nullable = false)
        private Long shelterId;

        @Column(nullable = false)
        private String location;

        @Column(nullable = false)
        private String contactNumber;

        @Column(nullable = false)
        private Integer capacity;


}
