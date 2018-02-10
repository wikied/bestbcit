package com.scriptofan.ecommerce.DAO;

import com.scriptofan.ecommerce.Entity.Item;
import com.scriptofan.ecommerce.Exception.NoSuchItemException;
import com.scriptofan.ecommerce.Exception.NoSuchUserException;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;
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

    /**
     * Returns matching user's entire inventory.
     *
     * @param userId selected user.
     * @return user's inventory as collection
     */
    public Collection<Item> getEntireInventory(String userId)
        throws NoSuchUserException
    {
        if (this.items.containsKey(userId)) {
            return this.items.get(userId).values();
        } else {
            throw new NoSuchUserException();
        }
    }

    /**
     * Returns item SKU from matching user's inventory.
     *
     * @param userId selected user.
     * @param SKU SKU for selected item.
     * @return
     */
    public Item getItemBySKU(
        String  userId,
        String  SKU
    )
        throws  NoSuchUserException,
                NoSuchItemException
    {

        Map<String, Item>   inventory;
        Item                item;

        inventory = this.items.get(userId);
        if (inventory == null) {
            throw new NoSuchUserException();
        }

        item = inventory.get(SKU);
        if (item == null) {
            throw new NoSuchItemException();
        }

        return item;
    }

    /**
     * Update an item in user's inventory. Use for both PUT and POST.
     *
     * @param userId determines which user inventory to access.
     * @param item item to update.
     */
    public void putItem(
        String  userId,
        Item    item)
        throws NoSuchUserException
    {
        try {
            String SKU = item.getSKU();
            this.items.get(userId).put(SKU, item);
        }
        catch (NullPointerException ex) {
            throw new NoSuchUserException();
        }
    }

    /**
     * Deletes specified item from specified user's inventory.
     *
     * @param userId target user.
     * @param SKU SKU of item to delete.
     */
    public void deleteItem(
        String  userId,
        String  SKU
    ) {
        this.items.remove(SKU);
    }
}
