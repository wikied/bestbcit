package com.scriptofan.ecommerce.Ebay.InventoryItem;

/**
 * Represents a weight unit of measurement for Ebay items.
 *
 * @author  Jason Huang
 * @author  Patrick Charles-Lundaahl
 * @version 1.1
 */
public enum WeightUnitOfMeasureEnum{
    POUND,
    KILOGRAM,
    OUNCE,
    GRAM;

    /**
     * Accepts a string and returns a matching WeightUnitOfMeasureEnum,
     * or throws IllegalArgumentException for invalid strings.
     *
     * @param string String to convert to WeightUnitOfMeasureEnum
     * @return WeightUnitOfMeasureEnum An ebay-friendly Enum representation of passed string.
     */
    public static WeightUnitOfMeasureEnum getUnit(String string)
            throws IllegalArgumentException {

        string = string.toUpperCase();

        if (string.equals("POUND")
         || string.equals("LB")
         || string.equals("LBS")
                ) {
            return WeightUnitOfMeasureEnum.POUND;
        }
        else
        if (string.equals("KILOGRAM")
         || string.equals("KG")
         || string.equals("KGS")
                ) {
            return WeightUnitOfMeasureEnum.KILOGRAM;
        }
        else
        if (string.equals("OUNCE")
         || string.equals("OZ")
                ) {
            return WeightUnitOfMeasureEnum.OUNCE;
        }
        else
        if (string.equals("GRAM")
         || string.equals("GM")
                ) {
            return WeightUnitOfMeasureEnum.GRAM;
        }

        throw new IllegalArgumentException("String passed as a unit of weight was invalid.");
    }
}
