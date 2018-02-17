package com.scriptofan.ecommerce.Ebay.InventoryItem;

/**
 * Represents package dimensions for Ebay items.
 *
 * @author  Jason Huang
 * @author  Patrick Charles-Lundaahl
 * @vesrion 1.1
 */
public class Dimension{
    private double              length;
    private double              width;
    private double              height;
    LengthUnitOfMeasureEnum     unit;

    /**
     * Default constructor.
     */
    public Dimension() {
    }

    /**
     * Initializer constructor. Sets the dimensions and unit.
     *
     * @param length Length.
     * @param width  Width.
     * @param height Height.
     * @param unit   Unit of measurement.
     */
    public Dimension(
            double length,
            double width,
            double height,
            LengthUnitOfMeasureEnum unit) {

        if (length < 0 || width < 0 || height < 0) {
            throw new IllegalArgumentException("Dimensions must be non-negative.");
        }

        this.length = length;
        this.width  = width;
        this.height = height;
        this.unit   = unit;
    }

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
