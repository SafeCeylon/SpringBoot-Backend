package dev.safeceylon.SafeCeylon.uploads;

public class PdfInputFormRequest {
    private String fileName; // File name of the uploaded PDF
    private long fileSize;   // Size of the file in bytes

    // Getters and Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
