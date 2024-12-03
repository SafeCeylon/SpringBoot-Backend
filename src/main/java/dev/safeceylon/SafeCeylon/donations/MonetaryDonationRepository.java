package dev.safeceylon.SafeCeylon.donations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.safeceylon.SafeCeylon.donations.MonetaryDonation;

@Repository
public interface MonetaryDonationRepository extends JpaRepository<MonetaryDonation, String> {
    // Additional query methods can be added if needed
}