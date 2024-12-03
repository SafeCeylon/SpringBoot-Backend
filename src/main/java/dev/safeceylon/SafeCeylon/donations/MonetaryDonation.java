package dev.safeceylon.SafeCeylon.donations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "monetary_donations")
public class MonetaryDonation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String idDonor;
    private double amount;

    public MonetaryDonation() { }

    public MonetaryDonation(String idDonor, double amount) {
        this.idDonor = idDonor;
        this.amount = amount;
    }

}
