package com.scriptofan.ecommerce.Ebay.InventoryItem;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.ArrayList;
import java.util.List;

public class Product {

    // Required Attributes
    private String       title;
    private String       description;
    private List<Object> aspects;
    private List<String> imageUrls;

    // Optional Attributes
    private String       brand;
    private String       mpn;
    private String       subtitle;
    private List<String> isbn;
    private List<String> upc;
    private List<String> ean;
    private String       epid;


    public List<Object> getAspects() {
        return aspects;
    }

    public void setAspects(List<Object> aspects) {
        this.aspects = aspects;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getMpn() {
        return mpn;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getIsbn() {
        return isbn;
    }

    public void setIsbn(List<String> isbn) {
        this.isbn = isbn;
    }

    public List<String> getUpc() {
        return upc;
    }

    public void setUpc(List<String> upc) {
        this.upc = upc;
    }

    public List<String> getEan() {
        return ean;
    }

    public void setEan(List<String> ean) {
        this.ean = ean;
    }

    public String getEpid() {
        return epid;
    }

    public void setEpid(String epid) {
        this.epid = epid;
    }

}
