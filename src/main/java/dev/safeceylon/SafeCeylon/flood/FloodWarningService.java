package dev.safeceylon.SafeCeylon.flood;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;

@Service
public class FloodWarningService {

    private final FloodWarningRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(FloodWarningService.class);

    @Autowired
    public FloodWarningService(FloodWarningRepository repository) {
        this.repository = repository;
    }

    public void saveFloodData(String path) {
        FloodWarning floodData = new FloodWarning();
        floodData.setDateIssued(LocalDate.now().toString());
        floodData.setFilePath(path); // Store file path
        repository.save(floodData);
    }
}
