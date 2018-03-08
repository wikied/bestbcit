package com.scriptofan.ecommerce.Controllers;

import com.scriptofan.ecommerce.CSVParser.FileConvertService;
import com.scriptofan.ecommerce.CSVParser.ParserCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
public class ParserController {

    @Autowired
    private ParserCsvService parserCsvService;

    @Autowired
    private FileConvertService fileConvertService;

    public void praseCsv(MultipartFile file){
        File newFile;

        try{

            newFile = fileConvertService.convertFile(file);
            parserCsvService.parseCsv(newFile);
            fileConvertService.deleteFile(); // need to delete the temp file

        } catch (IOException e){
            System.err.println("Unable to create the new file");
        }
    }

    @GetMapping("/get-list-of-items")
    public List<Map<String, String>> getListOfItems(){
       return  parserCsvService.getListOfItems();
    }

}
