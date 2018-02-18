package com.scriptofan.ecommerce.internal.user;

import com.scriptofan.ecommerce.Ebay.InventoryItem.InventoryItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ebay-json-test/item")
public class EbayItemTestController {

    private InventoryService inventoryService;

    public EbayItemTestController() {
        inventoryService = new InventoryService();
    }

    @GetMapping
    public InventoryItem getTestEbayItem() {
        return inventoryService.getEbayItem("ITEM-0001");
    }

}
