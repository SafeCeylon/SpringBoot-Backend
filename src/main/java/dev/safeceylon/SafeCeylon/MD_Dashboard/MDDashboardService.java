package dev.safeceylon.SafeCeylon.MD_Dashboard;

import dev.safeceylon.SafeCeylon.weather.WeatherReport;
import dev.safeceylon.SafeCeylon.weather.WeatherReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MDDashboardService {

    private final WeatherReportRepository weatherReportRepository;

    public MDDashboardService(WeatherReportRepository weatherReportRepository) {
        this.weatherReportRepository = weatherReportRepository;
    }

    // Get all wethear data
    public List<WeatherReport> getWeatherReport() {
        return weatherReportRepository.findAll();
    }
}
