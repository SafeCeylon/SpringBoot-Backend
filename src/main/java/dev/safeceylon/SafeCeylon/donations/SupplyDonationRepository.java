package dev.safeceylon.SafeCeylon.donations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.safeceylon.SafeCeylon.donations.SupplyDonation;

@Repository
public interface SupplyDonationRepository extends JpaRepository<SupplyDonation, String> {
    // Additional query methods can be added if needed
}
