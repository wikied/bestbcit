package com.scriptofan.ecommerce.Platforms.Ebay.Services;

import com.scriptofan.ecommerce.Platforms.Ebay.EbayItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Ebay.EbayLocalOffer;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.EbayCreateInventoryItemException;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.InventoryItem.Availability;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.InventoryItem.InventoryItem;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.InventoryItem.Product;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.InventoryItem.ShipToLocationAvailability;
import com.scriptofan.ecommerce.Platforms.Ebay.GenericEbayErrorHandler;
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

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String CONTENT_LANGUAGE = "en-US";
    private static final String CREATE_OR_REPLACE_INVENTORY_ITEM_URI
            = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/";

    /**
     * Creates or replaces a inventory item with a given sku
     * @param token - the ebay user token associated with the user
     * @param sku - the user defined sku
     * @param ebayLocalOffer - the ebay offer
     * @return - string
     */
    public static String createOrReplaceInventoryItem(
            String          token,
            String          sku,
            EbayLocalOffer  ebayLocalOffer)
            throws EbayCreateInventoryItemException
    {
        InventoryItem               inventoryItem;
        HttpHeaders                 headers;
        HttpEntity<InventoryItem>   httpEntity;
        RestTemplate                template;
        String                      response = null;

        inventoryItem   = createInventoryItem(ebayLocalOffer);
        headers         = createHttpHeaders(ebayLocalOffer);
        httpEntity      = new HttpEntity<>(inventoryItem, headers);

        template        = new RestTemplate();
        template.setErrorHandler(new CreateInventoryItemErrorHandler());

        try {
            template.put(CREATE_OR_REPLACE_INVENTORY_ITEM_URI + sku, httpEntity);
            response = "success";
        }
        catch (HttpServerErrorException ex) {
            ex.printStackTrace();
            response = ex.getMessage();
            throw new EbayCreateInventoryItemException(ex);
        }
        catch (ResourceAccessException e) {
            System.err.println("ResourceAccessException");
            System.err.println(e.getRootCause());
            throw new EbayCreateInventoryItemException(e);
        }
        return response;
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
            super.handleError(clientHttpResponse);
        }
    }
}
