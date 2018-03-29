package com.scriptofan.ecommerce.Controllers;


import com.scriptofan.ecommerce.CSVParser.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UploadController {

    @Autowired
    private ParserController parserController;

    @Autowired
    private StorageService storageService;

    /*
        The inventory CSV file is uploaded through this end point
        @param  file     multipart csv file
     */
    @PostMapping("/upload-inventory")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            storageService.store(file);
            parserController.parseMultipartFile(file);

        } catch (Exception e) {
            System.err.println("Failed to upload " + file.getOriginalFilename());
        }

        return file.getOriginalFilename() + " uploaded successfully";
    }

}
