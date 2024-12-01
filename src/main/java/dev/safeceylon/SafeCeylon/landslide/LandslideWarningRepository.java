package dev.safeceylon.SafeCeylon.landslide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandslideWarningRepository extends JpaRepository<LandslideWarning, String> {
}

