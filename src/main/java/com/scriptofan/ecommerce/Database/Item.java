package com.scriptofan.ecommerce.Database;

import com.scriptofan.ecommerce.User.User;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String  sku;
    private String  marketplaceId;
    private String  format;
    private String  description;
    private Integer availableQuantity;
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
                Integer availableQuantity,
                String categoryId,
                String description,
                String priceCurrency,
                String priceValue,
                String limitPerBuyer,
                Integer userId,
                String userName) {
        this.sku = sku;
        this.marketplaceId = marketplaceId;
        this.format = format;
        this.availableQuantity = availableQuantity;
        this.categoryId = categoryId;
        this.description = description;
        this.priceCurrency = priceCurrency;
        this.priceValue = priceValue;
        this.limitPerBuyer = limitPerBuyer;
        this.user = new User(userId, userName);
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

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
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


