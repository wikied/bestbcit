package com.scriptofan.ecommerce.Ebay.InventoryItem;

/**
 * Represents a unit of measurement for Ebay items.
 *
 * @author  Jason Huang
 * @author  Patrick Charles-Lundaahl
 * @version 1.1
 */
public enum LengthUnitOfMeasureEnum{
    INCH,
    FEET,
    CENTIMETER,
    METER;

    /**
     * Accepts a string and returns a matching LengthUnitOfMeasureEnum,
     * or throws IllegalArgumentException for invalid strings.
     *
     * @param string String to convert to LengthUnitOfMeasureEnum
     * @return LengthUnitOfMeasureEnum
     */
    public static LengthUnitOfMeasureEnum getUnit(String string)
            throws IllegalArgumentException {

        string = string.toUpperCase();

        if (string.equals("INCH")
         || string.equals("IN")
        ) {
            return LengthUnitOfMeasureEnum.INCH;
        }
        else
        if (string.equals("FEET")
         || string.equals("FOOT")
         || string.equals("FT")
        ) {
            return LengthUnitOfMeasureEnum.FEET;
        }
        else
        if (string.equals("CENTIMETER")
         || string.equals("CM")
        ) {
            return LengthUnitOfMeasureEnum.CENTIMETER;
        }
        else
        if (string.equals("METER")
         || string.equals("M")
        ) {
            return LengthUnitOfMeasureEnum.METER;
        }

        throw new IllegalArgumentException("Invalid unit of measurement string");
    }
}