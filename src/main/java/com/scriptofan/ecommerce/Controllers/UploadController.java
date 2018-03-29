package com.scriptofan.ecommerce.Controllers;


import com.scriptofan.ecommerce.CSVParser.ParserCsvService;
import com.scriptofan.ecommerce.CSVParser.StorageService;
import com.scriptofan.ecommerce.Exception.NotImplementedException;
import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.ItemDistributor.DistributionService;
import com.scriptofan.ecommerce.LocalItem.ItemSyncService;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.LocalItem.LocalItemFactory;
import com.scriptofan.ecommerce.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Map;


@Controller
public class UploadController {

    @Autowired
    private ParserCsvService parserCsvService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private LocalItemFactory localItemFactory;

    @Autowired
    private ItemSyncService itemSyncService;

    @Autowired
    private DistributionService distributionService;

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
//            redirectAttributes.addFlashAttribute("message", "You have successfully uploaded " + file.getOriginalFilename() + "!");

            String                      filename;
            User                        user;
            List<Map<String, String>>   rawParsedItems;
            List<LocalItem>             localItems;

            rawParsedItems  = parserCsvService.parseCsv(file);

            localItems      = this.localItemFactory.createLocalItems(rawParsedItems);


            for (LocalItem item : localItems) {
                System.err.println(item);
                System.err.println(item.fieldsToString());
            }

            user = new User();
            for (LocalItem item : localItems) {
                item.associateUser(user);
            }

            localItems = itemSyncService.sync(localItems);
            localItems = distributionService.distribute(localItems);
            localItems = itemSyncService.sync(localItems);

            for (LocalItem item : localItems) {
                for (String log : item.getFullLog()) {
                    System.err.println(log);
                }
                System.err.println();
            }

        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (RulesetViolationException e) {
            e.printStackTrace();
        } catch (NotImplementedException e) {
            e.printStackTrace();
        } catch (RulesetCollisionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println(file.getOriginalFilename() + " uploaded successfully");

        return "redirect:/";

    }

}
