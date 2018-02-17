package com.scriptofan.ecommerce.Ebay.InventoryItem;

/**
 * Represents the weight and size of an Ebay item.
 *
 * @author  Jason Huang
 * @author  Patrick Charles-Lundaah
 * @version 1.1
 */
public class PackageWeightAndSize {

    private Dimension           dimensions;
    private PackageTypeEnum     packageTypeEnum;
    private Weight              weight;


    public Dimension getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimension dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Sets dimensions.
     *
     * @param length Length.
     * @param width  Width.
     * @param height Height.
     * @param unit   String representing the unit of measurement.
     */
    public void setDimensions(double length, double width, double height, String unit) {
        this.dimensions = new Dimension(length, width, height, unit);
    }

    public PackageTypeEnum getPackageTypeEnum() {
        return packageTypeEnum;
    }

    public void setPackageTypeEnum(PackageTypeEnum packageTypeEnum) {
        this.packageTypeEnum = packageTypeEnum;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

}
