package dev.safeceylon.SafeCeylon.donations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "supplies_donations")
public class SuppliesDonations {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String idDonor;
    private String supplies;
    private Double quantity;

    public SuppliesDonations() { }

    public SuppliesDonations(String idDonor, String supplies, double quantity) {
        this.idDonor = idDonor;
        this.supplies = supplies;
        this.quantity = quantity;
    }
}
