package com.scriptofan.ecommerce.Platforms.Ebay.Controllers;



import com.scriptofan.ecommerce.Platforms.Ebay.Services.DummyEbayUserService;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.Location.Location;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.Location.LocationBuilder;
import com.scriptofan.ecommerce.Platforms.Ebay.Services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationPostController {

    @Autowired
    private LocationBuilder locationBuilder;

    @Autowired
    private LocationService locationService;

    @PostMapping("/my-location")
    public String postInventoryLocation(){
        Location location;
        String      response;

        location = locationBuilder.buildLocation();
        response = locationService.createInventoryLocation(location, DummyEbayUserService.TOKEN);

        return response;
    }
}
