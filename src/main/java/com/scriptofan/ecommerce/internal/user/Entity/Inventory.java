package com.scriptofan.ecommerce.internal.user.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.scriptofan.ecommerce.internal.user.Exception.DuplicateSKUException;
import com.scriptofan.ecommerce.internal.user.Exception.InventoryAlreadyAssociatedException;
import com.scriptofan.ecommerce.internal.user.Exception.MalformedItemException;
import com.scriptofan.ecommerce.internal.user.Exception.NoSuchItemException;

public class Inventory {

    private User                associatedUser;
    private Map<String, Item>   contents;

    // Constructors
    public Inventory() {
        this.contents = new HashMap<String, Item>();
    }
    public Inventory(User associatedUser) {
        this.associatedUser = associatedUser;
        this.contents       = new HashMap<String, Item>();
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
    public Collection<Item> getAll()
    {
        return this.contents.values();
    }

    /**
     * Returns whether or not this Inventory contains an item with the specified SKU.
     *
     * @param sku SKU to search for.
     * @return Whether or not this Inventory contains an item with the specified SKU.
     */
    public boolean contains(String sku) {
        return this.contents.containsKey(sku);
    }

    /**
     * Returns Item with the specified SKU. If no item with that SKU
     * exists, a NoSuchItemException is thrown.
     *
     * @param sku SKU of item to search for.
     * @return Item associated with SKU.
     * @throws NoSuchItemException No item exists with this SKU.
     */
    public Item get(String sku) throws NoSuchItemException {
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
    public void add(Item item) throws MalformedItemException {
        String sku = item.getSKU();

        if (sku != null && !sku.equals("")) {
            this.contents.put(sku, item);
        } else {
            throw new MalformedItemException("SKU not specified");
        }
    }

    /**
     * Adds items to inventory. Fails if duplicate SKUs are detected.
     *
     * @param items Collection of items.
     * @throws DuplicateSKUException Duplicate SKUs detected in submission or in inventory.
     * @throws MalformedItemException Item SKU is not specified.
     */
    public void add(Collection<Item> items)
        throws  DuplicateSKUException,
                MalformedItemException
    {
        this.add((Item[]) items.toArray());
    }

    /**
     * Adds items to inventory. Fails if duplicate SKUs are detected.
     *
     * @param items Array or variable arguments set of items.
     * @throws DuplicateSKUException Duplicate SKUs detected in submission or in inventory.
     * @throws MalformedItemException Item SKU is not specified.
     */
    public void add(Item... items)
        throws  DuplicateSKUException,
                MalformedItemException
    {
        String              sku;
        Map<String, Item>   itemBuffer;

        itemBuffer = new HashMap<String, Item>();

        for (Item item : items)
        {
            sku = item.getSKU();

            if (sku == null || sku.equals("")) {
                throw new MalformedItemException("Item doesn't have an SKU");
            }

            if (itemBuffer.containsKey(sku)) {
                throw new DuplicateSKUException("Items with SKUs submitted.");
            }

            if (this.contents.containsKey(sku)) {
                throw new DuplicateSKUException("Duplicate SKU found in inventory.");
            }

            itemBuffer.put(sku, item);
        }

        this.contents.putAll(itemBuffer);
    }
}
