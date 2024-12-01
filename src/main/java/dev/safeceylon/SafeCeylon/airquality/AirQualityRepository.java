package dev.safeceylon.SafeCeylon.airquality;

import dev.safeceylon.SafeCeylon.flood.FloodWarning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirQualityRepository extends JpaRepository<AirQuality, String> {
}
