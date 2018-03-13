package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;
import com.scriptofan.ecommerce.User.User;

import java.nio.channels.NotYetBoundException;
import java.rmi.AlreadyBoundException;
import java.util.*;

public class LocalItem {

    private Map<String, String> fields;
    private Collection<LocalOffer> localOffers;
    private User                user;
    private int                 totalQuantity;

    private ItemLog             log;

    /*
     * Constructor
     */
    public LocalItem() {
        this.fields = new HashMap<>();
        this.localOffers = new ArrayList<LocalOffer>();
        this.log    = new ItemLog();
        this.user   = null;
    }

    /*
     * Must throw an exception if the incoming value conflicts with an
     * already-existing entry (i.e., the keys are the same but the values
     * are different).
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

    public String getField(String key) {
        if(this.fields.get(key) == null){
            throw new NullPointerException();
        }
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

}
