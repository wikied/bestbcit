package com.scriptofan.ecommerce.validateCSV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scriptofan.ecommerce.parsercsv.ParserCsvService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

@Service
public class ValidateService {

    @Autowired
    ParserCsvService parserCsvService;

    List<Map<String, String>> list;

    String[] requiredHeaders;

    public void requiredHeaders(File file){

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            requiredHeaders = bufferedReader.readLine().split(",");
        }catch (Exception f){
            System.err.println("Unable to find the file in Validate Service");
        }
    }

    public String validate(){
        list = parserCsvService.getListOfItems();

        String error = "";

        for (Map<String, String> map : list) {
            for (Map.Entry<String, String> entry : map.entrySet()) {


                String value = entry.getValue();
                String key   = entry.getKey();

                if(value.isEmpty()){
                    error =  key + " is empty";
                }
            }
        }
        return error;
    }

    public String[] printHeaders(){
        return requiredHeaders;
    }
}
