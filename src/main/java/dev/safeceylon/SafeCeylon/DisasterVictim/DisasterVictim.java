package dev.safeceylon.SafeCeylon.DisasterVictim;

// The disaster victims are the people who are the
// entries of users table role -> users

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "disaster_victims")
public class DisasterVictim {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    private String idVictim;
    private String idDisaster;
    @Enumerated(EnumType.STRING)
    private DisasterType disasterType;
    @Enumerated(EnumType.STRING)
    private VictimStatus victimStatus;

    public DisasterVictim() { }

    // Parameterized constructor
    public DisasterVictim(String idVictim, String idDisaster, DisasterType disasterType, VictimStatus victimStatus) {
        this.idVictim = idVictim;
        this.idDisaster = idDisaster;
        this.disasterType = disasterType;
        this.victimStatus = victimStatus;
    }


}
