package com.scriptofan.ecommerce.Database;

import com.scriptofan.ecommerce.User.User;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String  sku;
    private String  marketplaceId;
    private String  format;
    private String  description;
    private Integer avaliableQuantity;
    private String  categoryId;
    private String  priceCurrency;
    private String  priceValue;
    private String  limitPerBuyer;


    @ManyToOne
    @JoinColumn(name = "fk_User")
    private User user;

    public Item(String sku,
                String marketplaceId,
                String format,
                String description,
                Integer avaliableQuantity,
                String categoryId,
                String priceCurrency,
                String priceValue,
                String limitPerBuyer,
                User user) {
        this.sku = sku;
        this.marketplaceId = marketplaceId;
        this.format = format;
        this.description = description;
        this.avaliableQuantity = avaliableQuantity;
        this.categoryId = categoryId;
        this.priceCurrency = priceCurrency;
        this.priceValue = priceValue;
        this.limitPerBuyer = limitPerBuyer;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvaliableQuantity() {
        return avaliableQuantity;
    }

    public void setAvaliableQuantity(Integer avaliableQuantity) {
        this.avaliableQuantity = avaliableQuantity;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }

    public String getLimitPerBuyer() {
        return limitPerBuyer;
    }

    public void setLimitPerBuyer(String limitPerBuyer) {
        this.limitPerBuyer = limitPerBuyer;
    }
}


