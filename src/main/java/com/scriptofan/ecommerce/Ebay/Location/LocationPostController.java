package com.scriptofan.ecommerce.Ebay.Location;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationPostController {

    @Autowired
    private LocationBuilder lb;

    @PostMapping("/my-location/{s}")
    public String postLocation(@PathVariable String s){
        s = lb.dummyLocationData();
        return s;
    }
}
