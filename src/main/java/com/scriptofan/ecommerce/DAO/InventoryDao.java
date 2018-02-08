package com.scriptofan.ecommerce.DAO;

import com.scriptofan.ecommerce.Entity.Item;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InventoryDao {

    private Map<String, Map<String, Item>>  items;

    // Default Constructor
    public InventoryDao() {

        // Create dummy data
        items = new HashMap<String, Map<String, Item>>(){
            {

                // User 0
                put("00000000",
                    new HashMap<String, Item>(){
                        {
                            put("abc-123", new Item("abc-123", 22, 22.5, "Block Letters"));
                            put("abc-444", new Item("abc-444", 15, 10.0, "Alphabet Fridge Magnets"));
                            put("abc-232", new Item("abc-232", 66, 6.0, "Alphabet Stickers"));
                        }
                    }
                );

                // User 1
                put("00000001",
                    new HashMap<String, Item>(){
                        {
                            put("amz-123", new Item("amz-123", 22, 22.5, "Outsourcing"));
                        }
                    }
                );
            }
        };
    }

    public Item getItemBySKU(String userId, String SKU) {

        Item item;

        try {
            item = items.get(userId).get(SKU);
        }
        catch (NullPointerException ex)
        {
            item = null;
        }

        return item;
    }

    public Collection<Item> getEntireInventory(String userId) {
        return this.items.get(userId).values();
    }

}
