package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;
import com.scriptofan.ecommerce.User.User;

import java.nio.channels.NotYetBoundException;
import java.rmi.AlreadyBoundException;
import java.util.*;

/**
 * Represents a user's item in their inventory, as modelled on our server.
 */
public class LocalItem {

    private Map<String, String>     fields;             // Item details
    private Collection<LocalOffer>  localOffers;        // Listings on remote platforms (Ebay, Etsy, etc.)
    private User                    user;               // User that owns this item
    private int                     totalQuantity;      // Total quantity to be distributed among all offers
    private ItemLog                 log;                // Log of events that happen to the item.
    private LocalItemState          state;              // Current state of the item.


    /*
     * Constructor
     */
    public LocalItem() {
        this.fields = new HashMap<>();
        this.localOffers = new ArrayList<LocalOffer>();
        this.log    = new ItemLog();
        this.user   = null;
    }



    /**
     * Adds a field to the item's data.
     * Note: any collisions will through a RulesetCollisionException. This
     * is intentional, as we want to catch any issues between different
     * ItemBuilderRulesets as early as possible.
     *
     * @param key name of the field to add
     * @param value value of the field to add
     * @throws RulesetCollisionException The field you are trying to add
     * has the same name as an existing field, but is attempting to insert
     * a different value.
     */
    public void addField(String key, String value) throws RulesetCollisionException {

        if(key == null){
            throw new NullPointerException();
        }
        // Make sure that the field being added to this LocalItem
        // doesn't conflict with one that already exists.
        if (this.fields.containsKey(key)
                && !this.fields.get(key).equals(value)) {
            throw new RulesetCollisionException();
        }

        this.fields.put(key, value);
    }



    /**
     * Returns the requested field from the item's data map.
     * @param key field to request.
     * @return value of the field.
     */
    public String getField(String key) {
        return this.fields.get(key);
    }




    public Map<String, String> getAllFields() {
        Map<String, String> returnMap;

        returnMap = new HashMap<>();
        returnMap.putAll(this.fields);
        return returnMap;
    }




    public void addOffer(LocalOffer localOffer) {
        this.localOffers.add(localOffer);
    }

    public Collection<LocalOffer> getLocalOffers() {
        return localOffers;
    }





    public void associateUser(User user) throws AlreadyBoundException{
        if (this.user == null) {
            this.user = user;
        } else {
            throw new AlreadyBoundException("User " + this.user + " is already associated with this item.");
        }
    }

    public User getUser() throws NotYetBoundException {
        if (this.user == null) {
            throw new NotYetBoundException();
        }
        return this.user;
    }




    public void setTotalQuantity(int totalQuantity) {
        if (totalQuantity < 0) {
            throw new IllegalArgumentException("Total quantity must not be negative");
        }
        this.totalQuantity = totalQuantity;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }





    public void log(String message) {
        this.log.add(message);
    }

    public List<String> getFullLog() {
        return this.log.getFullLog();
    }

    @Override
    public String toString() {
        return    "LocalItem ("
                + fields.size() + " fields, "
                + localOffers.size() + " localOffers, "
                + "quantity = " + totalQuantity + ", "
                + "user = " + user + ", "
                + log.getFullLog().size() + " logs"
                + ")";
    }

    public String fieldsToString() {
        String  output  = "";
        int     max     = fields.size();
        int     i       = 0;
        for (Map.Entry<String, String> field : fields.entrySet()) {
            output += field.getKey() + "=" + field.getValue();
            ++i;
            if (i < max) {
                output += ", ";
            }
        }
        return output;
    }


    public LocalItemState getState() {
        return state;
    }

    public void setState(LocalItemState state) {
        this.state = state;
    }

    /**
     * Responsible for tracking the current state of the LocalItem.
     */
    public enum LocalItemState {
        CREATED,
        CREATE_FAILED,
        DISTRIBUTED,
        DISTRIBUTION_FAILED,
        POSTED,
        POST_FAILED
    }
}
