package dev.safeceylon.SafeCeylon.fileupload;

import dev.safeceylon.SafeCeylon.airquality.AirQualityService;
import dev.safeceylon.SafeCeylon.flood.FloodWarningService;
import dev.safeceylon.SafeCeylon.landslide.LandslideWarningService;
import dev.safeceylon.SafeCeylon.weather.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FileUploadService {

    private static final String BASE_UPLOAD_DIR = "D:/2 - My Projects/SpringBoot-Backend/uploads/";

    private final WeatherReportService weatherReportService;
    private final LandslideWarningService landslideWarningService;
    private final FloodWarningService floodWarningService;
    private final AirQualityService airQualityService;

    @Autowired
    public FileUploadService(WeatherReportService weatherReportService, LandslideWarningService landslideWarningService, FloodWarningService floodWarningService, AirQualityService airQualityService) {
        this.weatherReportService = weatherReportService;
        this.landslideWarningService = landslideWarningService;
        this.floodWarningService = floodWarningService;
        this.airQualityService = airQualityService;
    }

    public ResponseEntity<String> handleFileUpload(MultipartFile file, String fileType) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        String subDir = getSubDirectory(fileType);
        if (subDir == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type");
        }

        File directory = new File(BASE_UPLOAD_DIR + subDir);
        if (!directory.exists() && !directory.mkdirs()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create subdirectory for file type");
        }

        String newFileName = generateFileName(fileType, file.getOriginalFilename());
        String mainPath = subDir + "/" + newFileName;
        String filePath = BASE_UPLOAD_DIR + mainPath;
        File destinationFile = new File(filePath);

        try {
            file.transferTo(destinationFile);

            // Process weather reports if file type is "weather report"
            if ("weather report".equalsIgnoreCase(fileType)) {
                weatherReportService.saveWeatherReportsFromFile(destinationFile);
            }

            if ("landslide warning".equalsIgnoreCase(fileType)) {
                landslideWarningService.saveLandslideWarningsFromFile(destinationFile);
            }

            if ("flood warning".equalsIgnoreCase(fileType)) {
                floodWarningService.saveFloodData(mainPath);
            }

            if ("quality status".equalsIgnoreCase(fileType)) {
                airQualityService.saveAirQualityFromFile(destinationFile);
            }

            return ResponseEntity.ok("File uploaded and processed successfully as " + newFileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }

    private String getSubDirectory(String fileType) {
        return switch (fileType.toLowerCase()) {
            case "weather report" -> "weather-reports";
            case "landslide warning" -> "landslide-warning";
            case "flood warning" -> "flood-warning";
            case "quality status" -> "quality-status";
            default -> null;
        };
    }

    private File getLatestFileInDirectory(String directoryPath) {
        try (Stream<Path> files = Files.list(Paths.get(directoryPath))) {
            Optional<Path> latestFilePath = files.filter(Files::isRegularFile)
                    .max(Comparator.comparingLong(f -> f.toFile().lastModified()));
            return latestFilePath.map(Path::toFile).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve the latest file: " + e.getMessage());
        }
    }

    private String generateFileName(String fileType, String originalFileName) {
        String dateTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date());
        String cleanedFileName = originalFileName.replaceAll("[^a-zA-Z0-9.-]", "_");
        String fileExtension = cleanedFileName.substring(cleanedFileName.lastIndexOf("."));
        return dateTime + "-" + fileType.replaceAll(" ", "-").toLowerCase() + fileExtension;
    }
}
