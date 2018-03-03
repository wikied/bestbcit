package com.scriptofan.ecommerce.Platforms.Ebay.Location;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class LocationBuilder {

    public Location dummyLocationData(){

        Address address = addressObjectCreator();
        LocationDetails locationDetails = new LocationDetails(address);
        Location location = locationObjectCreator(locationDetails);

        return location;
    }

    public String getLocationAsString(Location location) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.writeValueAsString(location);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Address addressObjectCreator(){
        Address address = new Address();
        address.setAddressLine1("3700 Willingdon Ave");
        address.setAddressLine2("SE12 Room 306");
        address.setCity("Burnaby");
        address.setStateOrProvince("BC");
        address.setPostalCode("V5G 3H2");
        address.setCountry("CA");
        return address;
    }

    public Location locationObjectCreator(LocationDetails locationDetails){
        Location location = new Location(locationDetails);
        location.setLocationInstructions("Items shipped from here");
        location.setName("Warehouse-1");
        location.setMerchantLocationStatus(StatusEnum.ENABLED);
        StoreTypeEnum temp[] = new StoreTypeEnum[]{StoreTypeEnum.WAREHOUSE};
        location.setLocationTypes(temp);

        return location;
    }

}

