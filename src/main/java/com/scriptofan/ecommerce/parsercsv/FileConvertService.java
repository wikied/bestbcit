package com.scriptofan.ecommerce.parsercsv;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FIleConvertService {

    File convFile;

    public File convertFile(MultipartFile file) throws IOException{
        convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;

    }

    public void deleteFile(){
        convFile.delete();
    }


}
