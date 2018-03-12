package com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scriptofan.ecommerce.Platforms.Ebay.EbayLocalOffer;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.Entity.Availability;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.Entity.InventoryItem;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.Entity.Product;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.Entity.ShipToLocationAvailability;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
    public static String createOrReplaceInventoryItem(String token, String sku, EbayLocalOffer ebayLocalOffer) {
        InventoryItem inventoryItem;
        HttpHeaders headers;
        HttpEntity<InventoryItem> httpEntity;
        RestTemplate template;
        String response = null;

        headers = createHttpHeaders(ebayLocalOffer);
        template = new RestTemplate();
        template.setErrorHandler(new CreateInventoryItemErrorHandler());
        inventoryItem   = EbayCreateOrReplaceItemService.createInventoryItem(ebayLocalOffer);
        httpEntity      = new HttpEntity<>(inventoryItem, headers);

        try {
            ObjectMapper jacksonMapper = new ObjectMapper();
            jacksonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            System.err.println(jacksonMapper.writeValueAsString(httpEntity));
            System.err.println("Making request");
            inventoryItem = template.exchange(CREATE_OR_REPLACE_INVENTORY_ITEM_URI + sku,
                    HttpMethod.PUT,
                    httpEntity,
                    EbayInventoryItemWrapper.class).getBody()
                    .getInventoryItem();
            response = "success";
        } catch (HttpServerErrorException ex) {
            ex.printStackTrace();
            response = ex.getMessage();
        }
        catch (ResourceAccessException e) {
            System.err.println("ResourceAccessException");
            System.err.println(e.getRootCause());
            throw e;
        }
        catch (JsonProcessingException e) {
            System.err.println("JSON PROCESSING EXCEPTION");
            e.printStackTrace();
            System.exit(1);
        }
        return response;
    }

    public static class CreateInventoryItemErrorHandler implements ResponseErrorHandler {
        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) {
            // Extract error body
            String          line;
            String          output;
            BufferedReader bufferReader;
            StringBuilder   stringBuilder;

            System.err.println("HANDLING ERROR");
            stringBuilder = new StringBuilder();

            output = "";
            try {

                output += "clientHttpResponse error:\n";
                output += clientHttpResponse.getStatusCode() + " " + clientHttpResponse.getStatusText() + "\n";

                bufferReader = new BufferedReader(new InputStreamReader(clientHttpResponse.getBody()));
                while ((line = bufferReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                output += stringBuilder.toString();
            }
            catch (IOException e) {
                System.err.println("IO Exception reading clientHttpResponse");
                e.printStackTrace();
            }

            System.err.println(output);

            return false;
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            System.err.println("HERE");
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
        Product product = new Product();

        product.setTitle(offer.getLocalItem().getField("productTitle"));
        product.setDescription(offer.getLocalItem().getField("productDescription"));

        // Sets the imageUrls
        String listOfImageUrls = offer.getLocalItem().getField("productImageUrls");
        String[] imageUrls = listOfImageUrls.split("\\s+");
        ArrayList<String> productImageUrls = new ArrayList<>();

        for(String imageUrl : imageUrls) {
            productImageUrls.add(imageUrl);
        }
        product.setImageUrls(productImageUrls);

        return product;
    }


}
