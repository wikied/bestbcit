package com.scriptofan.ecommerce.Ebay.InventoryItem;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.ArrayList;

public class Product {

    private String              brand;
    private String              description;
    private ArrayList<String>   imageUrls;
    private String              mpn;
    private String              subtitle;
    private String              title;
    private ArrayList<String>   isbn;
    private ArrayList<String>   upc;
    private ArrayList<String>   ean;
    private String              epid;


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

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
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

    public ArrayList<String> getIsbn() {
        return isbn;
    }

    public void setIsbn(ArrayList<String> isbn) {
        this.isbn = isbn;
    }

    public ArrayList<String> getUpc() {
        return upc;
    }

    public void setUpc(ArrayList<String> upc) {
        this.upc = upc;
    }

    public ArrayList<String> getEan() {
        return ean;
    }

    public void setEan(ArrayList<String> ean) {
        this.ean = ean;
    }

    public String getEpid() {
        return epid;
    }

    public void setEpid(String epid) {
        this.epid = epid;
    }

}
