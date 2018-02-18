package com.scriptofan.ecommerce.Ebay.Entry;


import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EntryController.EBAY_BASE_URI)
public class EntryController {

    public static final String EBAY_BASE_URI = "https://api.ebay.com/sell/inventory/v1/inventory_item";


}
