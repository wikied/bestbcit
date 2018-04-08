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
import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
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

            redirectAttributes.addFlashAttribute("message", "You have successfully uploaded " + file.getOriginalFilename() + "!");

            String                      filename;
            User                        user;
            List<Map<String, String>>   rawParsedItems;
            List<LocalItem>             localItems;
            List<LocalItem>             erroredLocalItems;

            rawParsedItems      = parserCsvService.parseCsv(file);
            localItems          = this.localItemFactory.createLocalItems(rawParsedItems);
            erroredLocalItems   = new ArrayList<>();

            user = new User();
            for (LocalItem item : localItems) {
                item.associateUser(user);

                if (item.getState() != LocalItem.LocalItemState.CREATED) {
                    erroredLocalItems.add(item);
                    localItems.remove(item);
                }
            }
            localItems = itemSyncService.sync(localItems);



            localItems = distributionService.distribute(localItems);
            for (LocalItem item : localItems) {
                if (item.getState() != LocalItem.LocalItemState.POSTED) {
                    erroredLocalItems.add(item);
                    localItems.remove(item);
                }
            }
            localItems = itemSyncService.sync(localItems);



            model.put("items", localItems);
            model.put("items", erroredLocalItems);

            return "uploadResults";

        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        catch (RulesetCollisionException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println(file.getOriginalFilename() + " uploaded successfully");

        return "redirect:/";

    }

}
