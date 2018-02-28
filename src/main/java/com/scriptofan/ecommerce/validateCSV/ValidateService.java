package com.scriptofan.ecommerce.validateCSV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scriptofan.ecommerce.parsercsv.ParserCsvService;

import java.awt.image.ImagingOpException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ValidateService {

    @Autowired
    private ParserCsvService parserCsvService;
    private List<Map<String, String>> list;
    private String[] requiredKeys;
    private String   errors = "";
    private BufferedReader bufferedReader;

    public void openFile(File file){

        try{
            bufferedReader = new BufferedReader(new FileReader(file));

        }catch (Exception f){
            System.err.println("Unable to find the file in Validate Service");
        }
    }

    public void validate() throws IOException{

        list = parserCsvService.getListOfItems();
        requiredKeys = bufferedReader.readLine().split(",");


        for (Map<String, String> map : list) {
                for(String required : requiredKeys){
                    if(!map.containsKey(required)){
                       this.errors += "Your missing the column " + required;
                    }
                }

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String value = entry.getValue();
                    String key   = entry.getKey();

                    if(value.isEmpty()){
                        this.errors += "\nYour are missing a value for " + key;
                    }
                }
            }
        }

    public String getErrors() {
        if(this.errors.isEmpty()){
            return "There are no errors";
        }
        return this.errors;
    }

    public String[] getRequiredKeys(){
        return this.requiredKeys;
    }
}
