package com.scriptofan.ecommerce.Ebay.InventoryItem;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents the weight and size of an Ebay item.
 *
 * @author  Jason Huang
 * @author  Patrick Charles-Lundaah
 * @version 1.1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    /**
     * Sets the package's weight.
     *
     * @param unit   String representing unit of measurement.
     * @param weight Value of weight in specified unit.
     */
    public void setWeight(String unit, double weight) {
        this.weight = new Weight(unit, weight);
    }

}
