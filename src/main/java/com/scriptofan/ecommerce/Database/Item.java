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


//    @ElementCollection()
//    //@CollectionTable(name = "item_fields", joinColumns = @JoinColumn(name = "item_id"))
//    @MapKeyClass(ItemAttributes.class)
//    @Column(name = "Value")
//    Map<String, String> fields;


    public Item(){
        //fields = new HashMap<>();
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

//    public Map<String, String> getFields() {
//        return fields;
//    }
//
//    public void setFields(Map<String, String> fields) {
//        this.fields = fields;
//    }
}


