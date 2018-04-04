package com.scriptofan.ecommerce.Platforms.Ebay.Entity.Category;

public class Category {

    private String BestOfferEnabled;
    private String AutoPayEnabled;
    private String CategoryID;
    private String CategoryLevel;
    private String CategoryName;
    private String CategoryParentID;

    @Override
    public String toString() {
        return "[Name: "            + CategoryName      + ", "
             + "ID: "               + CategoryID        + ", "
             + "Level: "            + CategoryLevel     + ", "
             + "Parent ID: "        + CategoryParentID  + ", "
             + "BestOfferEnabled: " + BestOfferEnabled  + ", "
             + "AutoPayEnabled: "   + AutoPayEnabled    + "]"
        ;
    }

    public String getBestOfferEnabled() {
        return BestOfferEnabled;
    }

    public void setBestOfferEnabled(String bestOfferEnabled) {
        BestOfferEnabled = bestOfferEnabled;
    }

    public String getAutoPayEnabled() {
        return AutoPayEnabled;
    }

    public void setAutoPayEnabled(String autoPayEnabled) {
        AutoPayEnabled = autoPayEnabled;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getCategoryLevel() {
        return CategoryLevel;
    }

    public void setCategoryLevel(String categoryLevel) {
        CategoryLevel = categoryLevel;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryParentID() {
        return CategoryParentID;
    }

    public void setCategoryParentID(String categoryParentID) {
        CategoryParentID = categoryParentID;
    }
}
