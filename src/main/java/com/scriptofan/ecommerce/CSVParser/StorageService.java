package com.scriptofan.ecommerce.CSVParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StorageService {

    private final Path rootLocation = Paths.get("src/main/java/com/scriptofan/ecommerce/CSVParser/upload-dir");


    /**
     * Saves a multipart file to the upload-dir folder
     * @param file is a  multipart file to to be saved
     * @throws RuntimeException if the file failed to store
     */
    public void store(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to store the file");
        }
    }

    /**
     * Deletes all the existing files in the upload-dir folder
     */
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    /**
     * Create a root directory to store the uploaded files
     */
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}