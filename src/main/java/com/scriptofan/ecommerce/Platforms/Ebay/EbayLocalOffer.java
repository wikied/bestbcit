package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.InventoryItem;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.Offer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class EbayLocalOffer extends Offer{

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String CONTENT_LANGUAGE = "en-US";
    private static final String CREATE_OR_REPLACE_INVENTORY_ITEM_URI
                                = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/";

    public EbayLocalOffer(LocalItem localItem) {
        super(localItem);
    }

    public void post() {
        createOrReplaceInventoryItem(getLocalItem().getUser().getUserToken(), getLocalItem().getField("sku"));

    }

    /**
     * Creates or replaces a inventory item with a given sku
     * @param token - the ebay user token associated with the user
     * @param sku - the user defined sku
     * @return - string
     */
    private String createOrReplaceInventoryItem(String token, String sku) {
        InventoryItem                 inventoryItem;
        EbayInventoryItemWrapper      inventoryItemWrapper;
        HttpHeaders                   headers;
        HttpEntity<InventoryItem>     httpEntity;
        RestTemplate                  template;
        String response;

        headers = new HttpHeaders();
        headers.set("authorization", TOKEN_PREFIX + getLocalItem().getUser().getUserToken());
        headers.set("content-language", CONTENT_LANGUAGE);

        template = new RestTemplate();
        template.setErrorHandler(new LocationService.MyErrorHandler());

        inventoryItem = EbayCreateOrReplaceItemService.createInventoryItem(this);

        httpEntity = new HttpEntity<>(inventoryItem, headers);

        try {
            inventoryItem = template.exchange(CREATE_OR_REPLACE_INVENTORY_ITEM_URI + sku,
                    HttpMethod.POST,
                    httpEntity,
                    EbayInventoryItemWrapper.class).getBody()
                    .getInventoryItem();
            response = "success";
        } catch (HttpServerErrorException ex) {
            ex.printStackTrace();
            response = ex.getMessage();
        }
        return response;
    }



}
