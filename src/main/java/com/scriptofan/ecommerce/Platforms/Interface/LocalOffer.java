package com.scriptofan.ecommerce.Platforms.Interface;

import com.scriptofan.ecommerce.LocalItem.ItemLog;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import org.springframework.scheduling.annotation.Async;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class LocalOffer {

    private int         quantity;
    private LocalItem   localItem;
    private OfferState  state;
    private ItemLog     log;

    public LocalOffer(LocalItem localItem) {
        this.quantity   = 0;
        this.localItem  = localItem;
        state           = OfferState.INSTANTIATED;
        log             = new ItemLog();
    }



    /*
     * Posts this offer to the respective platform.
     */
    @Async
    public abstract CompletableFuture<LocalOffer> post() throws MalformedURLException, UnsupportedEncodingException;



    /**
     * Returns this offer's quantity.
     * @return this offer's quantity.
     */
    public final int getQuantity() {
        return quantity;
    }



    /**
     * Sets quantity associated with this offer.
     * @param quantity
     * @throws IllegalArgumentException Quantity must be non-negative.
     */
    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
        else {
            throw new IllegalArgumentException("Quantity may not be less than 0");
        }
    }



    /*
     * Returns the associated localItem.
     */
    public LocalItem getLocalItem() {
        return localItem;
    }


    /**
     * Sets the state of this LocalOffer.
     * @param state State to set this LocalOffer to.
     */
    public void setState(OfferState state) {
        this.state = state;
    }


    /**
     * Returns the state of this LocalOffer.
     * @return the state of this LocalOffer.
     */
    public OfferState getState() {
        return state;
    }


    /**
     * Adds an entry to this LocalOffer's log.
     * @param message message to add to log.
     */
    public void log(String message) {
        this.log.add(message);
    }


    /**
     * Returns the full log contained in this LocalEntry.
     * @return the full log contained in this LocalEntry.
     */
    public List<String> getFullLog() {
        return this.log.getFullLog();
    }
}
