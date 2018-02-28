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
    private String   errors;
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
            for (Map.Entry<String, String> entry : map.entrySet()) {
                for(String required : requiredKeys){
                    checkKeys(required, entry.getKey());
                }

                //String value = entry.getValue();
                //String key   = entry.getKey();

            }
        }
    }

    public void checkKeys(String required, String given){

        if(required.isEmpty()){
           errors += "Your missing " + required + "\n";
        }

        if(!given.equals(required)){
            errors += given + " does not equal " + required + "\n";
        }
    }

    public String getErrors() {
        if(this.errors.isEmpty()){
            this.errors = "There are no errors !";
        }

        return errors;
    }

    public String[] getRequiredKeys(){
        return this.requiredKeys;
    }
}
