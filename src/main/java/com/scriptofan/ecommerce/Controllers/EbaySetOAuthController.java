package com.scriptofan.ecommerce.Controllers;

import com.scriptofan.ecommerce.DummyRequestData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ebay-token")
public class EbaySetOAuthController {

    @RequestMapping
    public String getEbayOAuthToken() {
        return "Ebay token is currently set to: " + DummyRequestData.ebayOAuthToken;
    }

    @RequestMapping("/{token}")
    public String setEbayOAuthToken(@PathVariable("token") String token) {
        DummyRequestData.ebayOAuthToken = token;
        return "Ebay token set to: " + DummyRequestData.ebayOAuthToken;
    }
}
