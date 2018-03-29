package com.scriptofan.ecommerce.Controllers;


import com.scriptofan.ecommerce.CSVParser.StorageService;
import groovy.lang.GrabExclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UploadController {

    @Autowired
    private ParserController parserController;

    @Autowired
    private StorageService storageService;

    @GetMapping("/")
    public String displayIntialPage(){

        return "uploadForm";
    }
    /*
        The inventory CSV multipart file is uploaded through this end point.
        The multipart is saved to the upload-dir folder in the CSVParser directory
        @param  file     multipart csv file
     */
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) {
        try {
            storageService.store(file);
            parserController.parseMultipartFile(file);
            redirectAttributes.addFlashAttribute("message", "You have successfully uploaded " + file.getOriginalFilename() + "!");


        } catch (Exception e) {
            System.err.println("Failed to upload " + file.getOriginalFilename());
        }

        System.out.println(file.getOriginalFilename() + " uploaded successfully");

        return "redirect:/";

    }

}
