package dev.safeceylon.SafeCeylon.landslide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class LandslideWarningService {

    private final LandslideWarningRepository repository;

    @Autowired
    public LandslideWarningService(LandslideWarningRepository repository) {
        this.repository = repository;
    }

    public void saveLandslideWarningsFromFile(File file) {
        List<LandslideWarning> warnings = new ArrayList<>();
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
                LandslideWarning warning = new LandslideWarning();
                warning.setDateIssued(columns[0].trim());
                warning.setTimeIssued(columns[1].trim());
                warning.setDistrict(columns[2].trim());
                warning.setWarningLevel(columns[3].trim());
                warning.setDivisionalSecretariatDivisions(columns[4].trim());

                warnings.add(warning);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while processing the file: " + e.getMessage());
        }

        repository.saveAll(warnings);
    }
}

