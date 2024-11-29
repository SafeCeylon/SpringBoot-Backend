package dev.safeceylon.SafeCeylon.fileupload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileUploadService {

    private static final String BASE_UPLOAD_DIR = "D:/2 - My Projects/SpringBoot-Backend/uploads/";

    public ResponseEntity<String> handleFileUpload(MultipartFile file, String fileType) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        String subDir = getSubDirectory(fileType);
        if (subDir == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type");
        }

        // Create the directory for file type if it doesn't exist
        File directory = new File(BASE_UPLOAD_DIR + subDir);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to create subdirectory for file type");
            }
        }

        String newFileName = generateFileName(fileType, file.getOriginalFilename());

        String filePath = BASE_UPLOAD_DIR + subDir + "/" + newFileName;
        File destinationFile = new File(filePath);

        try {
            // Save the file
            file.transferTo(destinationFile);

            // Return success message with new file name
            return ResponseEntity.ok("File uploaded successfully as " + newFileName);
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

    private String generateFileName(String fileType, String originalFileName) {
        String dateTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date());
        String cleanedFileName = originalFileName.replaceAll("[^a-zA-Z0-9.-]", "_");
        String fileExtension = cleanedFileName.substring(cleanedFileName.lastIndexOf("."));
        return dateTime + "-" + fileType.replaceAll(" ", "-").toLowerCase() + fileExtension;
    }
}
