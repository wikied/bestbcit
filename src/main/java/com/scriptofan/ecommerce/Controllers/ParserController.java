package com.scriptofan.ecommerce.Controllers;

import com.scriptofan.ecommerce.CSVParser.FileConvertService;
import com.scriptofan.ecommerce.CSVParser.ParserCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class ParserController {

    @Autowired
    private ParserCsvService parserCsvService;

    @Autowired
    private FileConvertService fileConvertService;

    //FOR TESTING PURPOSES
    //SHOULD BE MADE A LOCAL VARIABLE
    List<Map<String, String>> list_of_items = new ArrayList<>();

    /*
        Converts the multi-part file to a a regular file.
        The new file is then mapped to a List of Maps.
        The temporary file is deleted that was created when converting
        multipart file to a regular file

        @param file     a multipart csv file

     */
    public void parseMultipartFile(MultipartFile file){
        File newFile;

        try{

            newFile = fileConvertService.convertFile(file);
            list_of_items = parserCsvService.parseCsv(newFile);
            fileConvertService.deleteFile(); // need to delete the temp file

        } catch (IOException e){
            System.err.println("Unable to create the new file");
        }
    }

    /*
        Parses a regular file instead of a multi part file.
     */
    public void parseFile(File file){
        try{
            list_of_items = parserCsvService.parseCsv(file);
        } catch (IOException e){
            System.err.println("Unable to parse the CSV file in parseFile");
        }
    }

    //FOR Testing Purposes
    @GetMapping("/get-list-of-items")
    public List<Map<String, String>> getListOfItems(){

        return list_of_items;

    }

}
