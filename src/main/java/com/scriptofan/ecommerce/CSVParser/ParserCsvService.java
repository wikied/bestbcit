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
        List<Map<String, String>>   itemList;
        BufferedReader              fileReader;
        BufferedReader              headerReader;

        fileReader      = new BufferedReader(new InputStreamReader(file.getInputStream()));
        headerReader    = new BufferedReader(new InputStreamReader(file.getInputStream()));
        itemList        = parseCsvBufferedReader(fileReader, headerReader);

        return itemList;
    }

    public List<Map<String, String>> parseCsv(File file) throws IOException {
        List<Map<String, String>>   itemList;
        BufferedReader              fileReader;
        BufferedReader              headerReader;

        fileReader      = new BufferedReader(new FileReader(file));
        headerReader    = new BufferedReader(new FileReader(file));
        itemList        = parseCsvBufferedReader(fileReader, headerReader);

        return itemList;
    }

    private List<Map<String, String>> parseCsvBufferedReader(
            BufferedReader fileReader,
            BufferedReader headerReader)
            throws IOException
    {
        List<Map<String, String>>   itemList;
        Iterable<CSVRecord>         records;
        String[]                    itemKeys;

        itemList    = new ArrayList<>();
        records     = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(fileReader);
        itemKeys    = headerReader.readLine().split(",");

        for(CSVRecord record : records){
            int i = 0;
            Map<String, String> tempMap = new HashMap<>();
            for(String s : record){

                tempMap.put(itemKeys[i], s);
                i++;
            }
            itemList.add(tempMap);
        }
        return itemList;
    }
}
