package com.scriptofan.ecommerce.parsercsv;

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
    ParserCsvService parserCsvService;

    @Autowired
    FileConvertService fileConvertService;

    String fileName;

    public void praseCsv(MultipartFile file){
        File newFile;

        try{

            newFile = fileConvertService.convertFile(file);
            fileName = newFile.getName();

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

    @GetMapping("/get-filename")
    public String getFileNam(){
        return fileName;
    }

}
