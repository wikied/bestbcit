package com.scriptofan.ecommerce.Controllers;


import com.scriptofan.ecommerce.CSVParser.ParserCsvService;
import com.scriptofan.ecommerce.CSVParser.StorageService;
import com.scriptofan.ecommerce.Exception.CsvParserException;
import com.scriptofan.ecommerce.Exception.NotImplementedException;
import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.ItemDistributor.DistributionService;
import com.scriptofan.ecommerce.LocalItem.ItemSyncService;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.LocalItem.LocalItemFactory;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;
import com.scriptofan.ecommerce.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller("/")
public class UploadController {

    @Autowired
    private ParserCsvService parserCsvService;

    @Autowired
    private LocalItemFactory localItemFactory;

    @Autowired
    private ItemSyncService itemSyncService;

    @Autowired
    private DistributionService distributionService;



    @GetMapping("/")
    public String homePage(){
        return "index";
    }


    /**
     * The inventory CSV multipart file is uploaded through this end point.
     * The multipart is saved to the upload-dir folder in the CSVParser directory
     * @param file  Uploaded CSV file
     * @param model Map< String, Object > to pass to Thymeleaf
     * @param redirectAttributes n/a
     * @return
     */
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   Map<String, Object> model, RedirectAttributes redirectAttributes) {
        try {

            String                      filename;
            User                        user;
            List<Map<String, String>>   rawParsedItems;
            List<LocalItem>             localItems;
            List<LocalItem>             erroredLocalItems;

            rawParsedItems      = parserCsvService.parseCsv(file);
            localItems          = this.localItemFactory.createLocalItems(rawParsedItems);
            erroredLocalItems   = new ArrayList<>();

            user = new User();
            for (Iterator<LocalItem> i = localItems.iterator(); i.hasNext();) {
                LocalItem item = i.next();
                item.associateUser(user);

                if (item.getState() != LocalItem.LocalItemState.CREATED) {
                    erroredLocalItems.add(item);
                    i.remove();
                }
            }
            localItems = itemSyncService.sync(localItems);



            localItems = distributionService.distribute(localItems);
            for (Iterator<LocalItem> i = localItems.iterator(); i.hasNext();) {
                LocalItem item = i.next();

                if (item.getState() != LocalItem.LocalItemState.POSTED) {
                    erroredLocalItems.add(item);
                    i.remove();
                }
            }
            localItems = itemSyncService.sync(localItems);

            localItems.addAll(erroredLocalItems);

            for (LocalItem item : localItems) {
                if (!item.getAllFields().containsKey("title")) {
                    item.addField("title", null);
                }
            }

            model.put("items", localItems);

            return "uploadResults";

        }
        catch (AlreadyBoundException
             | RulesetCollisionException
             | CsvParserException
             | IOException e) {
            e.printStackTrace();

            model.put("exception", e);
            return "uploadError";
        }
    }

}
