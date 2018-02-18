package com.scriptofan.ecommerce.parsercsv;


import com.opencsv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;


@Service
public class ParserService {


    public void parseCsv(MultipartFile file) throws IOException {

        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String[] item_keys = bufferedReader.readLine().split(",");
        List<Map<String, String>> list_of_items = new ArrayList<>();


        for(CSVRecord record : records){
                int i = 0;
                Map<String, String> tempMap = new HashMap<>();
                for(String s : record){

                    tempMap.put(item_keys[i], s);
                    i++;
                }
                list_of_items.add(tempMap);
        }

    }

}
