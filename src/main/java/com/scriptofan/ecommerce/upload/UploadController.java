package com.scriptofan.ecommerce.upload;


import com.scriptofan.ecommerce.parsercsv.ParserController;
import com.scriptofan.ecommerce.parsercsv.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UploadController {

    @Autowired
    ParserController parserController;

    @Autowired
    StorageService storageService;

    @PostMapping("/upload-inventory")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            storageService.store(file);
            parserController.praseCsv(file);

        } catch (Exception e) {
            System.err.println(" Failed to upload " + file.getOriginalFilename());
        }

        return "file success";
    }

}
