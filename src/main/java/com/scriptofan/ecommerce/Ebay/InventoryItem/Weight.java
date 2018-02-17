package com.scriptofan.ecommerce.Ebay.InventoryItem;

/**
 * Holds details on weight for Ebay items.
 *
 * @author  Jason Huang
 * @author  Patrick Charles-Lundaahl
 * @version 1.1
 */
public class Weight{

    /**
     * Default weight unit to use for Ebay items.
     */
    public static final WeightUnitOfMeasureEnum DEFAULT_UNIT = WeightUnitOfMeasureEnum.KILOGRAM;

    private WeightUnitOfMeasureEnum     unit;
    private double                      value;

    /**
     * Default constructor.
     */
    public Weight() {
        unit    = DEFAULT_UNIT;
        value   = 0;
    }

    /**
     * Initializer constructor.
     *
     * @param unit  String representing unit of measurement.
     * @param value Amount of weight in specified unit.
     */
    public Weight(String unit, double value) {
        this.unit   = WeightUnitOfMeasureEnum.getUnit(unit);
        this.value  = value;
    }

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

