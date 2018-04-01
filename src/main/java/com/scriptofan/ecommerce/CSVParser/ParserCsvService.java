package com.scriptofan.ecommerce.CSVParser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;


@Service
public class ParserCsvService {

    public List<Map<String, String>> parseCsv(MultipartFile file) throws IOException {


        List<Map<String, String>> list_of_items = new ArrayList<>();

        Reader in = new BufferedReader(new InputStreamReader(file.getInputStream()));
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String[] item_keys = bufferedReader.readLine().split(",");

        for(CSVRecord record : records){
                int i = 0;
                Map<String, String> tempMap = new HashMap<>();
                for(String s : record){

                    tempMap.put(item_keys[i], s);
                    i++;
                }
                list_of_items.add(tempMap);
        }

        return list_of_items;

    }

}
