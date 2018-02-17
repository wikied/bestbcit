package com.scriptofan.ecommerce.internal.user;

import com.scriptofan.ecommerce.internal.user.Entity.LocalItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InventoryDAO {

    private Map<String, LocalItem> localItems;

    public InventoryDAO() {

        LocalItem item;

        localItems = new HashMap<String, LocalItem>();

        item = new LocalItem();
        item.setSKU("ITEM-0001");
        item.setTitle("Flashy Item");
        item.setDescription("This item is sweet! You should buy it.");
        item.setImages(null);
        item.setCondition("New");
        item.setQuantity(42);
        item.setDimensions(22, 15, 8, "cm");
        item.setWeight(8.5, "kg");
        // Add item to collection
        localItems.put(item.getSKU(), item);

        item = new LocalItem();
        item.setSKU("ITEM-0002");
        item.setTitle("Shiny Item");
        item.setDescription("This item is shiny! I'm sure you like shiny things.");
        item.setImages(null);
        item.setCondition("New");
        item.setQuantity(42);
        item.setDimensions(10, 19, 4, "cm");
        item.setWeight(4.1, "kg");
        // Add item to collection
        localItems.put(item.getSKU(), item);

        item = new LocalItem();
        item.setSKU("ITEM-0003");
        item.setTitle("Fluffy Item");
        item.setDescription("Soft and furry, like rabbits.");
        item.setImages(null);
        item.setCondition("New");
        item.setQuantity(42);
        item.setDimensions(14, 22, 14, "cm");
        item.setWeight(2.7, "kg");
        // Add item to collection
        localItems.put(item.getSKU(), item);
    }

    public LocalItem getItem(String sku) {
        return this.localItems.get(sku);
    }
}
