package dev.safeceylon.SafeCeylon.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherReportService {

    private final WeatherReportRepository repository;

    @Autowired
    public WeatherReportService(WeatherReportRepository repository) {
        this.repository = repository;
    }

    public void saveWeatherReportsFromFile(File file) {
        List<WeatherReport> weatherReports = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;

            repository.deleteAll(); // Clear all existing records

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header row
                    continue;
                }

                String[] columns = line.split(",");
                WeatherReport report = new WeatherReport();
                report.setDate(columns[0].trim());
                report.setTimeIssued(columns[1].trim());
                report.setProvince(columns[2].trim());
                report.setDistrict(columns[3].trim());
                report.setCondition(columns[4].trim());
                report.setWindSpeeds(columns[5].trim());
                report.setRainfallType(columns[6].trim());

                weatherReports.add(report);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while processing the file: " + e.getMessage());
        }

        repository.saveAll(weatherReports);
    }
}
