package com.scriptofan.ecommerce.Platforms.Ebay;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.InventoryItem;
import com.scriptofan.ecommerce.Platforms.Interface.Offer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        String response = null;

        headers = new HttpHeaders();
        headers.set("authorization", TOKEN_PREFIX + getLocalItem().getUser().getUserToken());
        headers.set("Content-Language", CONTENT_LANGUAGE);
        headers.set("Accept","application/json");
        headers.set("Content-Type", "application/json");

        template = new RestTemplate();
        template.setErrorHandler(new CreateInventoryItemErrorHandler());
        System.err.println("Converters: " + template.getMessageConverters().size());

        for (HttpMessageConverter<?> converter: template.getMessageConverters()) {
            System.err.println(converter);
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ((MappingJackson2HttpMessageConverter) converter).getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
            }
        }

        inventoryItem   = EbayCreateOrReplaceItemService.createInventoryItem(this);
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
            BufferedReader  bufferReader;
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

}
