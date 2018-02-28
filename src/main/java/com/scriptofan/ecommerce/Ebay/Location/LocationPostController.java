package com.scriptofan.ecommerce.Ebay.Location;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationPostController {

    @Autowired
    private LocationBuilder lb;

    @GetMapping("/my-location/")
    public String postLocation(){
        String s = lb.dummyLocationData();
        return s;
    }
}
