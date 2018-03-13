package com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.Entity;

public class TimeDuration {

    private TimeDurationUnitEnum unit;

    public TimeDurationUnitEnum getUnit() {
        return unit;
    }

    public void setUnit(TimeDurationUnitEnum unit) {
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;

}
