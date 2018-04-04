package com.scriptofan.ecommerce.Controllers;

import com.scriptofan.ecommerce.DummyRequestData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Controller
@RequestMapping("/ebay-token")
public class EbaySetOAuthController {

    @RequestMapping
    public String getEbayOAuthToken() {
        return "Ebay token is currently set to: " + DummyRequestData.ebayOAuthToken;
    }

    @RequestMapping("/{token}")
    public void setEbayOAuthToken(@PathVariable("token") String token) {
        DummyRequestData.ebayOAuthToken = token;
    }
}
