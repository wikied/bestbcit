package com.scriptofan.ecommerce.Ebay.Location;



import com.scriptofan.ecommerce.Ebay.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationPostController {

    @Autowired
    private LocationBuilder locationBuilder;

    @Autowired
    private LocationService locationService;

    @PostMapping("/my-location/")
    public String postLocation(){
        Location    location;
        String      response;

        location = locationBuilder.dummyLocationData();
        response = locationService.postLocation(location, null);

        return response;
    }
}
