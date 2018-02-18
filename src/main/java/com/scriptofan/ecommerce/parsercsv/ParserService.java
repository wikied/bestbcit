package com.scriptofan.ecommerce.parsercsv;


import com.opencsv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;


@Service
public class ParserService {


    public static void openFile() throws IOException {

        Reader in = new FileReader("upload-dir/inventory.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

        BufferedReader bufferedReader = new BufferedReader(new FileReader("upload-dir/inventory.csv"));
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
