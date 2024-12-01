package dev.safeceylon.SafeCeylon.reportmanagement;

import dev.safeceylon.SafeCeylon.reportmanagement.WeatherReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherReportRepository extends JpaRepository<WeatherReport, String> {
}
