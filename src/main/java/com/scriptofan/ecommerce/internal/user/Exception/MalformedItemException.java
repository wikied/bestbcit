package com.scriptofan.ecommerce.internal.user.Exception;

import java.util.ArrayList;
import java.util.Collection;

import com.scriptofan.ecommerce.internal.user.Entity.LocalItem;

/**
 * An item was improperly formed. Contains an optional collection of
 * Items that are marked as malformed (should allow for more precise
 * error handling further up the chain).
 */
public class MalformedItemException extends Exception {

    private Collection<LocalItem> malformedItems;

    /**
     * Default constructor.
     */
    public MalformedItemException() {
        malformedItems = new ArrayList<LocalItem>();
    }

    /**
     * Message constructor.
     *
     * @param message Message to include with exception.
     */
    public MalformedItemException(String message) {
        super(message);
        malformedItems = new ArrayList<LocalItem>();
    }

    /**
     * Constructor. Includes collection of items to throw with the exception.
     * Note: including items here makes no guarantee that they are actually
     * malformed. This is the programmer's responsibility.
     *
     * @param malformedItems Collection of malformed items to throw with exception.
     */
    public MalformedItemException(Collection<LocalItem> malformedItems)
    {
        super();
        this.malformedItems = malformedItems;
    }

    /**
     * Constructor. Includes message and collection of items to throw
     * with the exception. Note: including items here makes no guarantee
     * that they are actually malformed. This is the programmer's responsibility.
     *
     * @param message Message to include with the exception.
     * @param malformedItems Collection of malformed items to throw with exception.
     */
    public MalformedItemException(String message, Collection<LocalItem> malformedItems)
    {
        super(message);
        this.malformedItems = malformedItems;
    }

    /**
     * Returns items that were added to MalformedItemException.
     * Please note that this class provides no guarantee that
     * these items are, in fact, malformed.
     *
     * @return collection of malformed items.
     */
    public Collection<LocalItem> getMalformedItems() {
        return malformedItems;
    }

    /**
     * Adds an item to the collection of malformed items. Note:
     * it is the programmer's responsibility to ensure that this
     * item is actually malformed.
     *
     * @param item Item to add to the collection
     */
    public void addMalformedItem(LocalItem item) {
        this.malformedItems.add(item);
    }
}
