package com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem;

public class PickUpAtLocationAvailibility {

    private AvailabilityTypeEnum    availabilityTypeEnum;
    private String                  merchantLocationKey;
    private int                     quantity;
    private TimeDuration            fulfillmentTime;


    public AvailabilityTypeEnum getAvailabilityTypeEnum() {
        return availabilityTypeEnum;
    }

    public void setAvailabilityTypeEnum(AvailabilityTypeEnum availabilityTypeEnum) {
        this.availabilityTypeEnum = availabilityTypeEnum;
    }

    public String getMerchantLocationKey() {
        return merchantLocationKey;
    }

    public void setMerchantLocationKey(String merchantLocationKey) {
        this.merchantLocationKey = merchantLocationKey;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TimeDuration getFulfillmentTime() {
        return fulfillmentTime;
    }

    public void setFulfillmentTime(TimeDuration fulfillmentTime) {
        this.fulfillmentTime = fulfillmentTime;
    }
}
