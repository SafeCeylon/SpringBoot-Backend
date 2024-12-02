package dev.safeceylon.SafeCeylon.airquality;

import dev.safeceylon.SafeCeylon.landslide.LandslideWarning;
import dev.safeceylon.SafeCeylon.landslide.LandslideWarningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class AirQualityService {
    private final AirQualityRepository repository;

    @Autowired
    public AirQualityService(AirQualityRepository repository) {
        this.repository = repository;
    }

    public void saveAirQualityFromFile(File file) {
        List<AirQuality> airQualityList = new ArrayList<>();
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
                AirQuality airQuality = new AirQuality();
                airQuality.setDistrict(columns[0].trim());
                airQuality.setAqi(Integer.parseInt(columns[1].trim()));

                airQualityList.add(airQuality);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while processing the file: " + e.getMessage());
        }

        repository.saveAll(airQualityList);
    }
}
