package com.scriptofan.ecommerce.CSVParser;

import com.scriptofan.ecommerce.Exception.CsvParserException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;


/**
 * Responsible for parsing CSV files.
 */
@Service
public class ParserCsvService {

    public static final char CSV_DELIMITER = ',';

    /**
     * Parses a CSV file from a MultipartFile object.
     * @param multipartFile multipart file to parse.
     * @return a list of Map< String, String > objects to be passed to the LocalItemFactory.
     * @throws IOException Error reading from file.
     */
    public List<Map<String, String>> parseCsv(MultipartFile multipartFile) throws IOException, CsvParserException {
        List<Map<String, String>>   itemList;
        InputStream                 itemInputStream;
        InputStreamReader           itemInputStreamReader;
        BufferedReader              itemReader;

        itemInputStream         = multipartFile.getInputStream();
        itemInputStreamReader   = new InputStreamReader(itemInputStream);
        itemReader              = new BufferedReader(itemInputStreamReader);

        try {
            itemList = parseCsvBufferedReader(itemReader);
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
        FileReader                  itemFileReader;

        itemFileReader  = new FileReader(file);
        itemReader      = new BufferedReader(itemFileReader);
        itemList        = parseCsvBufferedReader(itemReader);
        return itemList;
    }



    /*
     * Parses a CSV file from a BufferedReader. Returns a list of
     * Map< String, String > objects to be passed to the
     * LocalItemFactory.
     */
    private List<Map<String, String>> parseCsvBufferedReader(BufferedReader fileReader)
            throws IOException
    {
        List<Map<String, String>>   itemList;
        Iterable<CSVRecord>         records;

        itemList    = new ArrayList<>();
        records     = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(fileReader);

        Iterator<CSVRecord> i = records.iterator();
        while (i.hasNext()) {
            Map<String, String> currentItem;
            currentItem = i.next().toMap();
            itemList.add(currentItem);
        }
        return itemList;
    }
}
