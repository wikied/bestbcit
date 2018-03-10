package com.scriptofan.ecommerce.CSVParser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;


@Service
public class ParserCsvService {

    public List<Map<String, String>> parseCsv(File file) throws IOException {

        List<Map<String, String>> list_of_items = new ArrayList<>();

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

        return list_of_items;

    }

}
