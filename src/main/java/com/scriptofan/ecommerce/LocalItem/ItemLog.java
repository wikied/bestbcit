package com.scriptofan.ecommerce.LocalItem;

import java.util.ArrayList;
import java.util.List;

public class ItemLog {

    List<String> log;

    public ItemLog() {
        this.log = new ArrayList<String>();
    }

    public void add(String message) {
        this.log.add(message);
    }

    public List<String> getFullLog() {
        return this.log;
    }

}
