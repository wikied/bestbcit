package com.scriptofan.ecommerce.parsercsv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
public class ParserController {

    @Autowired
    ParserService parserService;



    @PostMapping("/parsecsv")
    public void praseCsv(@RequestParam("file") MultipartFile file){
        try{
            parserService.parseCsv(file);
        } catch (IOException e){
            System.err.println("Unable to parse the csv file");
        }

    }


}
