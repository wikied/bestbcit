package com.scriptofan.ecommerce.Platforms.Ebay.Services;

import com.scriptofan.ecommerce.DummyEbayRequestData;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.Category.Category;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class CategoryRetrievalService {

    private static final String TOKEN = DummyEbayRequestData.ebayOAuthToken;
    private static final String TRADING_API_URL = "https://api.sandbox.ebay.com/ws/api.dll";

    /**
     * Returns list of eBay categories, taken from eBay's Trading API.
     *
     * @return list of eBay categories.
     */
    public List<Category> getCategories() {
        String          getCategoryResponse;
        List<Category>  categoryList            = null;

        try {

            getCategoryResponse = getCategoriesXmlString(TOKEN, TRADING_API_URL);
            categoryList = parseGetCategoriesXml(getCategoryResponse);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categoryList;
    }

    /**
     * Queries eBay's Trading API, using the getCategories method. Passes specified
     * token and URL, which allows user to specify target market site.
     *
     * @param token user authentication token.
     * @param url target market site.
     * @return XML response from ebay API, as string.
     */
    private String getCategoriesXmlString(String token, String url) {
        String                  body;
        HttpHeaders             headers;
        HttpEntity<String>      request;
        ResponseEntity<String>  response;
        RestTemplate            template;

        headers = new HttpHeaders();
        headers.add("X-EBAY-API-SITEID", "0");
        headers.add("X-EBAY-API-COMPATIBILITY-LEVEL", "967");
        headers.add("X-EBAY-API-CALL-NAME", "GetCategories");

        body =  "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                +  "<GetCategoriesRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\">\n"
                +    "<RequesterCredentials>\n"
                +      "<eBayAuthToken>"+ TOKEN + "</eBayAuthToken>\n"
                +    "</RequesterCredentials>\n"
                +    "<ErrorLanguage>en_US</ErrorLanguage>\n"
                +    "<WarningLevel>High</WarningLevel>\n"
                +      "<!--Ensure that site ID, in the Header, is set to the site you want-->\n"
                +    "<DetailLevel>ReturnAll</DetailLevel>\n"
                +    "<ViewAllNodes>true</ViewAllNodes>\n"
                +  "</GetCategoriesRequest> ";

        request  = new HttpEntity<>(body, headers);
        template = new RestTemplate();
        response = template.postForEntity(
                url,
                request,
                String.class);

        return response.getBody();
    }

    /**
     * Parses response from eBay's Trading API - getCategories method.
     *
     * @param xmlString XML String. Should be valid XML returned from calling getCatgories.
     * @return List of Category objects.
     *
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    private List<Category> parseGetCategoriesXml(String xmlString)
            throws IOException, SAXException, ParserConfigurationException {

        // TODO: ADD ERROR HANDLING (Malformed XML / Error Response)

        String              output;
        InputStream         stream;
        SAXParserFactory    parserFactor;
        SAXParser           parser;
        SAXHandler          handler;

        parserFactor    = SAXParserFactory.newInstance();
        parser          = parserFactor.newSAXParser();
        handler         = new SAXHandler();
        stream          = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));

        parser.parse(stream, handler);

        return handler.categories;
    }
}

/**
 * Handles parsing XML into list of Category objects.
 */
class SAXHandler extends DefaultHandler {
    // Solution found: https://dzone.com/articles/parsing-xml-using-dom-sax-and

    List<Category> categories;
    Category       category;
    String         content;

    public SAXHandler() {
        categories  = new ArrayList<>();
        category    = null;
        content     = null;
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        switch(qName){
            case "Category":
                category = new Category();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) {
        switch(qName){
            // Adds current category to list.
            case "Category":
                categories.add(category);
                break;

            // Adds fields for category.
            case "AutoPayEnabled":
                category.setAutoPayEnabled(content);
                break;
            case "BestOfferEnabled":
                category.setBestOfferEnabled(content);
                break;
            case "CategoryID":
                category.setCategoryID(content);
                break;
            case "CategoryName":
                category.setCategoryName(content);
                break;
            case "CategoryLevel":
                category.setCategoryLevel(content);
                break;
            case "CategoryParentID":
                category.setCategoryParentID(content);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        content = String.copyValueOf(ch, start, length).trim();
    }
}