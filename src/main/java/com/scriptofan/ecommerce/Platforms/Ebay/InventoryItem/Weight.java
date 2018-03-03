package com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem;

public class Weight{
        private WeightUnitOfMeasureEnum     unit;
        private double                      value;

    public WeightUnitOfMeasureEnum getUnit() {
        return unit;
    }

    public void setUnit(WeightUnitOfMeasureEnum unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

