package com.scriptofan.ecommerce.LocalItem;

import java.util.ArrayList;
import java.util.List;

public class ItemLog {

    /** Used in place of any null values passed into the log. */
    public static final String NULL_MESSAGE = "A null value was entered for this message.";

    private List<String> log;

    /**
     * Constructor.
     */
    public ItemLog() {
        this.log = new ArrayList<String>();
    }



    /**
     * Adds the passed message to the log. If message is null, adds placeholder
     * message and continues without exception.
     * @param message Message to add to the log.
     */
    public void add(String message) {
        if (message == null) {
            message = NULL_MESSAGE;
        }
        this.log.add(message);
    }



    /**
     * Returns all contained logs.
     * @return All contained logs.
     */
    public List<String> getFullLog() {
        return this.log;
    }
}
