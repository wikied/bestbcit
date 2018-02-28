package com.scriptofan.ecommerce.validateCSV;

import com.scriptofan.ecommerce.parsercsv.FileConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class ValidateController {

    @Autowired
    ValidateService validateService;

    @Autowired
    FileConvertService fileConvertService;

    @PostMapping("/admin-upload")
    public String getList(@RequestParam("file") MultipartFile adminFile){
        File newFile;

        if(!adminFile.isEmpty()){
            try{
                newFile = fileConvertService.convertFile(adminFile);
                validateService.openFile(newFile);
                newFile.delete();

            }catch (Exception e){
                System.err.println("Failed to upload admin File");
            }
        }
        return "You have successfully uploaded " + adminFile.getOriginalFilename() + " and set the requirements for the user.";
    }

    @GetMapping("/validate-ebay-csv")
    public String validateEbayCSV() throws IOException{
        validateService.validate();
        return validateService.getErrors();
    }

    @GetMapping("/get-required-keys")
    public String[] getRequiredKeys(){
        return validateService.getRequiredKeys();
    }

}
