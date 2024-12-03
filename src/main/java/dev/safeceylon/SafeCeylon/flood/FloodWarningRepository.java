package dev.safeceylon.SafeCeylon.flood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloodWarningRepository extends JpaRepository<FloodWarning, String> {
}
