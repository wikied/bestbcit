package com.scriptofan.ecommerce.CSVParser;

import com.scriptofan.ecommerce.Exception.CsvParserException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Responsible for parsing CSV files.
 */
@Service
public class ParserCsvService {

    /**
     * Parses a CSV file from a MultipartFile object.
     * @param multipartFile multipart file to parse.
     * @return a list of Map< String, String > objects to be passed to the LocalItemFactory.
     * @throws IOException Error reading from file.
     */
    public List<Map<String, String>> parseCsv(MultipartFile multipartFile) throws IOException, CsvParserException {
        List<Map<String, String>>   itemList;
        InputStream                 itemInputStream;
        InputStream                 headerInputStream;
        InputStreamReader           itemInputStreamReader;
        InputStreamReader           headerInputStreamReader;
        BufferedReader              itemReader;
        BufferedReader              headerReader;

        itemInputStream         = multipartFile.getInputStream();
        headerInputStream       = multipartFile.getInputStream();

        itemInputStreamReader   = new InputStreamReader(itemInputStream);
        headerInputStreamReader = new InputStreamReader(headerInputStream);

        itemReader              = new BufferedReader(itemInputStreamReader);
        headerReader            = new BufferedReader(headerInputStreamReader);

        try {
            itemList = parseCsvBufferedReader(itemReader, headerReader);
            return itemList;
        }
        catch (IOException e) {
            throw new CsvParserException(e);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new CsvParserException("Your CSV file appears to be malformed."
                      + " Most likely, the rows have different numbers of cells.");
        }
    }



    /**
     * Parses a CSV file from a File object.
     * @param file File to parse.
     * @return a list of Map< String, String > objects to be passed to the LocalItemFactory.
     * @throws IOException Error reading from file.
     */
    public List<Map<String, String>> parseCsv(File file) throws IOException {
        List<Map<String, String>>   itemList;
        BufferedReader              itemReader;
        BufferedReader              headerReader;
        FileReader                  itemFileReader;
        FileReader                  headerFileReader;

        itemFileReader      = new FileReader(file);
        headerFileReader    = new FileReader(file);

        itemReader      = new BufferedReader(itemFileReader);
        headerReader    = new BufferedReader(headerFileReader);

        itemList        = parseCsvBufferedReader(itemReader, headerReader);
        return itemList;
    }



    /*
     * Parses a CSV file from a BufferedReader. Returns a list of
     * Map< String, String > objects to be passed to the
     * LocalItemFactory.
     */
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
