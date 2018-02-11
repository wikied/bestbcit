package com.scriptofan.ecommerce.Ebay.InventoryItem;

public class PackageWeightAndSize {

    private Dimension dimensions;
    private PackageTypeEnum packageTypeEnum;
    private Weight weight;


    public Dimension getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimension dimensions) {
        this.dimensions = dimensions;
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
