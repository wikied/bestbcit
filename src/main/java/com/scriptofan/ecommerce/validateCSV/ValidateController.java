package com.scriptofan.ecommerce.validateCSV;

import com.scriptofan.ecommerce.parsercsv.FileConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class ValidateController {

    @Autowired
    ValidateService validateService;

    @Autowired
    FileConvertService fileConvertService;

    @PostMapping("/example-csv")
    public void getList(@RequestParam("file") MultipartFile multipartFile){
        File newFile;

        try{
            newFile = fileConvertService.convertFile(multipartFile);
            validateService.requiredHeaders(newFile);
            newFile.delete();

        }catch (Exception e){
            System.err.println("Failed to upload File");
        }
    }


    @GetMapping("/get-headers")
        public String[] getHeaders(){
            return validateService.printHeaders();
        }
}
