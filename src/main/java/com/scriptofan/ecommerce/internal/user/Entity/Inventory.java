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
     * @throws DuplicateSKUException Duplicate SKU found in Inventory.
     */
    public void add(Item item)
        throws  MalformedItemException,
                DuplicateSKUException
    {
        this.add(SkuConflictOption.FAIL, item);
    }

    /**
     * Adds a collection of Items to the Inventory. Throws an exception if SKU is not
     * specified, or if there are any duplicate SKUs, either in the collection, or
     * in the inventory.
     *
     * This method is transactional. Either all items are added, or none.
     *
     * @param items Item to add to Inventory.
     * @throws MalformedItemException Item did not have SKU (or SKU was empty).
     * @throws DuplicateSKUException Duplicate SKU found in Inventory.
     */
    public void add(Collection<Item> items)
            throws  DuplicateSKUException,
            MalformedItemException
    {
        this.add(SkuConflictOption.FAIL, (Item[]) items.toArray());
    }

    /**
     * Adds an array or variable list of of Items to the Inventory. Throws an exception
     * if SKU is not specified, or if there are any duplicate SKUs, either in the
     * collection, or in the inventory.
     *
     * This method is transactional. Either all items are added, or none.
     *
     * @param items Item to add to Inventory.
     * @throws MalformedItemException Item did not have SKU (or SKU was empty).
     * @throws DuplicateSKUException Duplicate SKU found in Inventory.
     */
    public void add(Item... items)
            throws  DuplicateSKUException,
            MalformedItemException
    {
        this.add(SkuConflictOption.FAIL, items);
    }

    /**
     * Adds an Item to the Inventory. If the item's SKU is already
     * in the inventory, skuConflictOption determines how to handle
     * that collision.
     *
     * SkuConflictOption.FAIL throws an exception at conflict.
     * SkuConflictOption.OVERWRITE overwrites the existing item.
     * SKuConflictOption.MERGE_QUANTITIES_AND_UPDATE adds the quantity
     *                   of the existing item to the new item, then
     *                   overwrites the conflicting item in the Inventory.
     *
     * @param skuConflictOption Determines how to handle duplicate SKUs.
     * @param item Item to add to Inventory.
     * @throws MalformedItemException Item did not have SKU (or SKU was empty).
     * @throws DuplicateSKUException Duplicate SKU found in Inventory.
     */
    public void add(SkuConflictOption skuConflictOption, Item item)
        throws  MalformedItemException,
                DuplicateSKUException
    {
        String sku = item.getSKU();

        if (sku == null || sku.equals("")) {
            throw new MalformedItemException("SKU not specified");
        }

        item = handleSkuConflict(skuConflictOption, item);

        this.contents.put(sku, item);
    }

    /**
     * Adds Items to the Inventory. If any item's SKUs are already
     * in the inventory, skuConflictOption determines how to handle
     * that collision.
     *
     * SkuConflictOption.FAIL throws an exception at conflict.
     * SkuConflictOption.OVERWRITE overwrites the existing item.
     * SKuConflictOption.MERGE_QUANTITIES_AND_UPDATE adds the quantity
     *                   of the existing item to the new item, then
     *                   overwrites the conflicting item in the Inventory.
     *
     * Regardless, if any duplicate SKUs are detected in the submitted
     * collection, an exception is thrown and the entire operation is
     * cancelled.
     *
     * This method is transactional. Either all items are written, or none.
     *
     * @param skuConflictOption Determines how to handle duplicate SKUs.
     * @param items Collection of Items to add to Inventory.
     * @throws MalformedItemException An Item did not have SKU (or SKU was empty).
     * @throws DuplicateSKUException Duplicate SKU found in Inventory or in submission.
     */
    public void add(SkuConflictOption skuConflictOption, Collection<Item> items)
        throws  DuplicateSKUException,
                MalformedItemException
    {
        this.add(skuConflictOption, (Item[]) items.toArray());
    }

    /**
     * Adds Items to the Inventory. If any item's SKUs are already
     * in the inventory, skuConflictOption determines how to handle
     * that collision.
     *
     * SkuConflictOption.FAIL throws an exception at conflict.
     * SkuConflictOption.OVERWRITE overwrites the existing item.
     * SKuConflictOption.MERGE_QUANTITIES_AND_UPDATE adds the quantity
     *                   of the existing item to the new item, then
     *                   overwrites the conflicting item in the Inventory.
     *
     * Regardless, if any duplicate SKUs are detected in the submitted
     * collection, an exception is thrown and the entire operation is
     * cancelled.
     *
     * This method is transactional. Either all items are written, or none.
     *
     * @param skuConflictOption Determines how to handle duplicate SKUs.
     * @param items Array or variable arguments list of Items to add to Inventory.
     * @throws MalformedItemException An Item did not have SKU (or SKU was empty).
     * @throws DuplicateSKUException Duplicate SKU found in Inventory or in submission.
     */
    public void add(SkuConflictOption skuConflictOption, Item... items)
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

            item = handleSkuConflict(skuConflictOption, item);

            itemBuffer.put(sku, item);
        }

        this.contents.putAll(itemBuffer);
    }

    /*
     * Helper function. Decides what to do with items whose
     * SKUs match an item already in inventory.
     */
    private Item handleSkuConflict(
            SkuConflictOption   skuConflictOption,
            Item                item)
        throws
            DuplicateSKUException
    {
        String sku = item.getSKU();

        switch (skuConflictOption) {
            case FAIL:
                if (this.contents.containsKey(sku)) {
                    throw new DuplicateSKUException();
                }
                break;
            case OVERWRITE:
                break;
            case MERGE_QUANTITIES_AND_UPDATE:
                if (this.contents.containsKey(sku)) {
                    int quantity = item.getQuantity();
                    quantity += this.contents.get(sku).getQuantity();
                    item.setQuantity(quantity);
                }
                break;
            default:
        }

        return item;
    }

    /**
     * Defines what to do when an item with a duplicate SKU is added to the inventory.
     */
    public enum SkuConflictOption {
        FAIL,
        OVERWRITE,
        MERGE_QUANTITIES_AND_UPDATE
    }
}