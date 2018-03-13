package com.scriptofan.ecommerce.Database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Offer {

    @Id
    private String id;

    @ManyToOne
    private Item item;

    public Offer(){}


}
