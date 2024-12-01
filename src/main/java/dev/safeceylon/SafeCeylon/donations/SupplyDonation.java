package dev.safeceylon.SafeCeylon.donations;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "supplies_donations")
public class SupplyDonation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String idDonor;
    private String supplies;
    private Double quantity;
    private Date date;

    public SupplyDonation() { }

    public SupplyDonation(String idDonor, String supplies, double quantity, Date date) {
        this.idDonor = idDonor;
        this.supplies = supplies;
        this.quantity = quantity;
        this.date = date;
    }
}
