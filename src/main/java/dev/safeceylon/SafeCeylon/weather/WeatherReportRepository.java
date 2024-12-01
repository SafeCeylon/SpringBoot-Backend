package dev.safeceylon.SafeCeylon.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WeatherReportRepository extends JpaRepository<WeatherReport, String> {
    
    @Query("SELECT w FROM WeatherReport w WHERE w.date BETWEEN :today AND :tomorrow")
    List<WeatherReport> findReportsForTodayAndTomorrow(@Param("today") String today, @Param("tomorrow") String tomorrow);

}
