package com.scriptofan.ecommerce.Ebay.Location;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post-location")
public class LocationPostController {
    private LocationPostController locationPostController;

    public LocationPostController() {
        this.locationPostController = locationPostController;
    }

    @RequestMapping(value = "/my-location",method = RequestMethod.POST)
    public String postLocation(){
        LocationBuilder lb = new LocationBuilder();
        return lb.dummyLocationData();
    }
}
