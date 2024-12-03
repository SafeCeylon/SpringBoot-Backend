package dev.safeceylon.SafeCeylon.donations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.safeceylon.SafeCeylon.donations.SupplyDonation;

@Repository
public interface SupplyDonationRepository extends JpaRepository<SupplyDonation, String> {
    // Additional query methods can be added if needed

    // Get quantity value of supplies = water row from the database nad if no row found return 0
    @Query("SELECT COALESCE(quantity, 0) FROM SupplyDonation WHERE supplies = 'water'")
    public Double getSuppliesDonationsWater();

    // Get quantity value of supplies = food row from the database nad if no row found return 0
    @Query("SELECT COALESCE(quantity, 0) FROM SupplyDonation WHERE supplies = 'food'")
    public Double getSuppliesDonationsFood();

    // Get quantity value of supplies = medical_supplies row from the database nad if no row found return 0
    @Query("SELECT COALESCE(quantity, 0) FROM SupplyDonation WHERE supplies = 'medical_supplies'")
    public Double getSuppliesDonationsMedicalSupplies();

    // Get quantity value of supplies = clothing row from the database nad if no row found return 0
    @Query("SELECT COALESCE(quantity, 0) FROM SupplyDonation WHERE supplies = 'clothing'")
    public Double getSuppliesDonationsClothing();

    // sum of quantity values of all rows except water, food, medical_supplies, clothing rows anf if no row found return 0
    @Query("SELECT COALESCE(SUM(quantity), 0) FROM SupplyDonation WHERE supplies NOT IN ('water', 'food', 'medical_supplies', 'clothing')")
    public Double getSuppliesDonationsOther();

}
