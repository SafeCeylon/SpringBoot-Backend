package dev.safeceylon.SafeCeylon.uploads;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pdfupload")
public class PdfInputFormController {

    @PostMapping("/submit")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !file.getContentType().equals("application/pdf")) {
            return ResponseEntity.badRequest().body("Invalid file type. Please upload a PDF.");
        }

        // Process the uploaded file (save to server or database)
        System.out.println("Uploaded file: " + file.getOriginalFilename());
        return ResponseEntity.ok("PDF uploaded successfully!");
    }
}
