package com.scriptofan.ecommerce.Ebay.InventoryItem;


public class Dimension{
    private double              height;
    private double              width;
    private double              length;
    LengthUnitOfMeasureEnum     unit;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public LengthUnitOfMeasureEnum getUnit() {
        return unit;
    }

    public void setUnit(LengthUnitOfMeasureEnum unit) {
        this.unit = unit;
    }
}
