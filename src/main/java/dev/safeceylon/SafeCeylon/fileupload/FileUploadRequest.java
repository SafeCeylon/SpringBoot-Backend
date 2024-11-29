package dev.safeceylon.SafeCeylon.fileupload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequest {
    @NotNull(message = "File must not be null")
    private MultipartFile file;

    @NotEmpty(message = "File type must not be empty")
    private String fileType;

    // Default constructor
    public FileUploadRequest() {}

    // Constructor with parameters
    public FileUploadRequest(MultipartFile file, String fileType) {
        this.file = file;
        this.fileType = fileType;
    }

    // Getters and Setters
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
