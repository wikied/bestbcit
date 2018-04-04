package com.scriptofan.ecommerce.Platforms.Ebay.Services;

import com.scriptofan.ecommerce.Platforms.Ebay.EbayItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Ebay.EbayLocalOffer;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.BadEbayTokenException;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.Ebay500ServerException;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.EbayCreateInventoryItemException;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.InventoryItem.Availability;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.InventoryItem.InventoryItem;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.InventoryItem.Product;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.InventoryItem.ShipToLocationAvailability;
import com.scriptofan.ecommerce.Platforms.Ebay.GenericEbayErrorHandler;
import com.scriptofan.ecommerce.Platforms.Interface.OfferState;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class creates or replaces a item for ebay
 **/
public class EbayCreateOrReplaceItemService {

    public static final int MAX_RETRIES = 3;
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String CONTENT_LANGUAGE = "en-US";
    private static final String CREATE_OR_REPLACE_INVENTORY_ITEM_URI
            = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/";

    /**
     * Creates or replaces a inventory item with a given sku
     * @param token - the ebay user token associated with the user
     * @param sku - the user defined sku
     * @param offer - the ebay offer
     * @return - string
     */
    public static void createOrReplaceInventoryItem(
            String          token,
            String          sku,
            EbayLocalOffer  offer)
            throws BadEbayTokenException, Ebay500ServerException, EbayCreateInventoryItemException {
        InventoryItem               inventoryItem;
        HttpHeaders                 headers;
        HttpEntity<InventoryItem>   httpEntity;
        RestTemplate                template;
        String                      response = null;
        int                         tries    = 0;

        inventoryItem   = createInventoryItem(offer);
        headers         = createHttpHeaders(offer);
        httpEntity      = new HttpEntity<>(inventoryItem, headers);

        template        = new RestTemplate();
        template.setErrorHandler(new CreateInventoryItemErrorHandler());

        while (response == null && tries < MAX_RETRIES) {
            try {
                tries++;
                template.put(CREATE_OR_REPLACE_INVENTORY_ITEM_URI + sku, httpEntity);
            }
            catch (ResourceAccessException e) {
                Throwable rootEx = e.getRootCause();

                // User's OAuth token is bad. Nothing we can do.
                if (rootEx instanceof BadEbayTokenException) {
                    throw (BadEbayTokenException) rootEx;
                }
                // Ebay had a server error. Retry.
                else if (rootEx instanceof Ebay500ServerException) {
                    // Retry
                    offer.log("Ebay Server Error. Retry " + tries + ". " + rootEx.getMessage());
                    if (tries == 0) {
                        throw (Ebay500ServerException) rootEx;
                    }
                    response = null;
                }
                else if (rootEx instanceof EbayCreateInventoryItemException) {
                    throw (EbayCreateInventoryItemException) rootEx;
                }
                else {
                    throw e;
                }
            }
        }
    }


    // Creates and sets the Http headers
    private static HttpHeaders createHttpHeaders(EbayLocalOffer ebayLocalOffer) {
        HttpHeaders headers;
        headers = new HttpHeaders();
        headers.set("authorization", TOKEN_PREFIX + ebayLocalOffer.getLocalItem().getUser().getUserToken());
        headers.set("Content-Language", CONTENT_LANGUAGE);
        headers.set("Accept","application/json");
        headers.set("Content-Type", "application/json");
        return headers;
    }

    // Creates the inventory item
    private static InventoryItem createInventoryItem(EbayLocalOffer offer) {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setAvailability(createAvailability(offer));
        inventoryItem.setCondition(offer.getLocalItem().getField("condition"));
        inventoryItem.setProduct(createProduct(offer));

        return inventoryItem;
    }

    // Creates a Availability object for the inventory item
    private static Availability createAvailability(EbayLocalOffer offer) {
        Availability availability = new Availability();
        ShipToLocationAvailability shipToLocationAvailability= new ShipToLocationAvailability();
        shipToLocationAvailability.setQuantity(offer.getQuantity());
        availability.setShipToLocationAvailability(shipToLocationAvailability);
        return availability;
    }

    // Creates a Product object for the inventory item
    private static Product createProduct(EbayLocalOffer offer) {
        Product             product;
        String              listOfImageUrls;
        String[]            imageUrls;
        ArrayList<String>   productImageUrls;
        String              productInternalKey;
        String              productDescriptionKey;
        String              productImageUrlKey;

        productInternalKey    = EbayItemBuilderRuleset.getInternalKeyByExternal("productTitle");
        productDescriptionKey = EbayItemBuilderRuleset.getInternalKeyByExternal("productDescription");
        productImageUrlKey    = EbayItemBuilderRuleset.getInternalKeyByExternal("productImageUrls");

        product = new Product();
        product.setTitle(offer.getLocalItem().getField(productInternalKey));
        product.setDescription(offer.getLocalItem().getField(productDescriptionKey));

        // Sets the imageUrls
        listOfImageUrls     = offer.getLocalItem().getField(productImageUrlKey);
        imageUrls           = listOfImageUrls.split("\\s+");

        productImageUrls    = new ArrayList<>();
        for(String imageUrl : imageUrls) {
            productImageUrls.add(imageUrl);
        }
        product.setImageUrls(productImageUrls);

        return product;
    }



    /**
     * Error handler.
     */
    public static class CreateInventoryItemErrorHandler extends GenericEbayErrorHandler
    {
        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            loadErrorDetails(clientHttpResponse);

            if (getEbayErrorId() == 25019) {
                // Listing already exists on eBay and is ended. You are not allowed to revise ended listings.
                throw new IOException(new EbayCreateInventoryItemException(getEbayMessage()));
            }

            super.handleError(clientHttpResponse);
        }
    }
}
