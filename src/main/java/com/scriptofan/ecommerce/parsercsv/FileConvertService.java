package com.scriptofan.ecommerce.parsercsv;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileConvertService {

    private File convFile;

    public File convertFile(MultipartFile file) throws IOException{
        this.convFile = new File(file.getOriginalFilename());
        this.convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;

    }

    public void deleteFile(){
        this.convFile.delete();
    }


}
