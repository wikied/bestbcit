package com.scriptofan.ecommerce.parsercsv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;


@Service
public class ParserCsvService {

    List<Map<String, String>> list_of_items;

    public void parseCsv(File file) throws IOException {
        list_of_items = new ArrayList<>();
        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
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
    }

    public List<Map<String, String>> getListOfItems(){
        return list_of_items;
    }

}
