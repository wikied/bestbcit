package com.scriptofan.ecommerce.Entity;

import java.util.HashMap;
import java.util.Map;
import com.scriptofan.ecommerce.Exception.DuplicateSKUException;
import com.scriptofan.ecommerce.Exception.InventoryAlreadyAssociatedException;
import com.scriptofan.ecommerce.Exception.MalformedItemException;
import com.scriptofan.ecommerce.Exception.NoSuchItemException;

public class Inventory {

    private User                associatedUser;
    private Map<String, Item>   contents;

    // Constructors
    public Inventory() {}
    public Inventory(User associatedUser) {
        this.associatedUser = associatedUser;
        contents            = new HashMap<String, Item>();
    }

    /**
     * Returns the User object associated with this inventory.
     *
     * @return User associated with this inventory.
     */
    public User getAssociatedUser() {
        return associatedUser;
    }

    /**
     * Sets the user associated with this Inventory.
     *
     * @param associatedUser User to associate with this inventory.
     */
    public void setAssociatedUser(User associatedUser)
        throws InventoryAlreadyAssociatedException {

        if (this.associatedUser == null)
        {
            this.associatedUser = associatedUser;
        }
        else
        {
            throw new InventoryAlreadyAssociatedException();
        }
    }

    /**
     * Returns contents of inventory.
     * @return Contents of Inventory.
     */
    public Map<String, Item> getContents() {
        return contents;
    }

    /**
     * Sets contents of Inventory.
     * @param contents Map containing contents of inventory.
     */
    public void setContents(Map<String, Item> contents) {

        this.contents = contents;
    }

    /**
     * Sets contents of Inventory from an array. Note: overwrites entire
     * current inventory.
     *
     * This method is transactional. Each item is processed for errors
     * before any are added.
     *
     * @param contents Array of items to set as contents of Inventory.
     * @exception MalformedItemException the listed item was malformed.
     */
    public void setContents(Item[] contents)
        throws  MalformedItemException,
                DuplicateSKUException
    {
        String              sku;
        Map<String, Item>   bufferContents;

        bufferContents = new HashMap<String, Item>();

        for (Item item : contents) {
            sku = item.getSKU();

            if (sku == null || sku.equals("")) {
                throw new MalformedItemException();
            }
            if (bufferContents.containsKey(sku)) {
                throw new DuplicateSKUException();
            }

            bufferContents.put(sku, item);
        }

        this.contents = bufferContents;
    }

    /**
     * Returns Item with the specified SKU. If no item with that SKU
     * exists, a NoSuchItemException is thrown.
     *
     * @param sku SKU of item to search for.
     * @return Item associated with SKU.
     * @throws NoSuchItemException No item exists with this SKU.
     */
    public Item getItem(String sku) throws NoSuchItemException {
        if (this.contents.containsKey(sku)) {
            return this.contents.get(sku);
        } else {
            throw new NoSuchItemException();
        }
    }

    /**
     * Adds an Item to the Inventory. Throws an error if SKU is not
     * specified.
     *
     * @param item Item to add to Inventory.
     * @throws MalformedItemException Item did not have SKU (or SKU was empty).
     */
    public void putItem(Item item) throws MalformedItemException {
        String sku = item.getSKU();

        if (sku != null && !sku.equals("")) {
            this.contents.put(sku, item);
        } else {
            throw new MalformedItemException("SKU not specified");
        }
    }
}
