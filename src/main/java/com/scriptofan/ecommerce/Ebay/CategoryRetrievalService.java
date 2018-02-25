package com.scriptofan.ecommerce.Ebay;

import com.scriptofan.ecommerce.Ebay.Category.Category;
import jdk.internal.org.xml.sax.Attributes;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("get-categories")
public class CategoryRetrievalService {

    private static final String TOKEN = "AgAAAA**AQAAAA**aAAAAA**9N6SWg**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GpAJWFqA+dj6x9nY+seQ**KHYEAA**AAMAAA**qSW0YZRiuS5EcekB0E8AmFJFxV5QaxUEcIiuLbFGGTWXwVBsZbQxmZFYjV1rL9zdZ06lOYPyzMmrVOfyZHOCOb8FviT0D3VT8OeIWX2h9+b0aV32HI5z5oeN6ELTxGG4aG5SdnCQThMEzs+56pePrl/IskZErd9vJDpErWD3P05JdsnJIT+nIq0A4lggjNmQIJrLzfkj6Mx9OnCeXzldGGhnuelWPVCEWzsuzyVeE8GRFPDnktBpW5XT27O7Teh/cmvd+C2oV7Gy7H51K3AvqOYfPayKFxTeWZNLVRcliAP1KuQvAW1R4zjKnuAF22wR1c8Um+rCwwL6NDd4O+i+qYUFw/2xbUgdNio7wlrZBx9AtRIV/9FvPV1kiaT/T795+Dx1Gt5HNMVPi/YX57NquDrS7h4wKK6HW6QoijPVVZCnPlFqAxAQvBjMF6L9rFLrlAvukc/x6UgxeM0OSyj8Z3SVfJBtvuJcBiZtazJnJtIiDlt6t5McUhAtx4J2G26nI06Pb/U9aoiyDrOmiwazJJ3ciF3ug192NsJGXR3BEHRAIJTA7eHsPtJDhhTOfyAuCG+BBulv2loYvKwcrCj+tTGIh1ExME28DxV36jodZA1WSjHic0sDVvYMwSmgkpkfP8TJ/7TjSDR5amyk4gM5WOlzeqf427FeKPq43d/SUTCFURsUF3pq+H5TgzG14LgUQJKN2r6zMU2zYk0ZTjP2BkqBviU6xzlXf46kyCxABFctmr6sw72BZ/wAY0rHdxYt";

    @GetMapping
    public String retrieveCategories() {
        String                  body;
        HttpHeaders             headers;
        HttpEntity<String>      request;
        ResponseEntity<String>  response;
        RestTemplate            template;
        List<Category>          categories;

        headers = new HttpHeaders();
        headers.add("X-EBAY-API-SITEID", "0");
        headers.add("X-EBAY-API-COMPATIBILITY-LEVEL", "967");
        headers.add("X-EBAY-API-CALL-NAME", "GetCategories");

        body =  "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<GetCategoriesRequest xmlns=\"urn:ebay:apis:eBLBaseComponents\">\n" +
                "  <RequesterCredentials>\n" +
                "    <eBayAuthToken>"+ TOKEN + "</eBayAuthToken>\n" +
                "  </RequesterCredentials>\n" +
                "\t<ErrorLanguage>en_US</ErrorLanguage>\n" +
                "\t<WarningLevel>High</WarningLevel>\n" +
                "     <!--Ensure that site ID, in the Header, is set to the site you want-->\n" +
                "  <DetailLevel>ReturnAll</DetailLevel>\n" +
                "  <ViewAllNodes>true</ViewAllNodes>\n" +
                "</GetCategoriesRequest> ";

        request  = new HttpEntity<>(body, headers);
        template = new RestTemplate();
        response = template.postForEntity(
                    "https://api.sandbox.ebay.com/ws/api.dll",
                    request,
                    String.class);


        return parseXmlResponseWithSAX(response.getBody());
    }

    private String parseXmlResponseWithSAX(String xmlString) {
        String      output;
        InputStream stream;




        try {
            SAXParserFactory parserFactor = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser parser = parserFactor.newSAXParser();
            SAXHandler handler = new SAXHandler();
            stream      = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));

            parser.parse(stream, handler);

            output = "";
            // Printing the list of employees obtained from XML
//            for ( Category category : handler.categories){
//                output += category + " ";
//            }

            for (int i = 0; i < handler.categories.size() && i < 99; ++i ) {
                output += handler.categories.get(i) + " ";
            }

            return output;
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String parseXmlResponseWithDOM(String xmlString) {
        DocumentBuilderFactory  factory;
        DocumentBuilder         builder;
        InputStream             stream;
        Document                document;
        List<Category>          categories;
        NodeList                nodeList;

        String                  output;

        categories  = new ArrayList<>();

        final String[] elements = {
                "BestOfferEnabled",
                "AutoPayEnabled",
                "CategoryID",
                "CategoryLevel",
                "CategoryName",
                "CategoryParentID"
        };

        try {
            factory     = DocumentBuilderFactory.newInstance();
            builder     = factory.newDocumentBuilder();
            stream      = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            document    = builder.parse(stream);
            nodeList    = document.getDocumentElement().getChildNodes();

            output      = "";

            // Iterate through all nodes

            int i = 0;
            while (!nodeList.item(i).getNodeName().equals("CategoryArray")) {
                ++i;
            }

            NodeList categoryArray = nodeList.item(i).getChildNodes();

            output += "CATEGORIES (" + categoryArray.getLength() + "):\n";

            for (int j = 0; j < categoryArray.getLength() && j < 10; ++j) {
                Category                    category;
                NodeList                    categoryDetails;
                HashMap<String, String>     attributes;

                attributes                  = new HashMap<>();
                categoryDetails             = categoryArray.item(i).getChildNodes();

                for (int l = 0; l < categoryDetails.getLength(); ++l) {
                    Node item = categoryDetails.item(l);

                    attributes.put(item.getNodeName(), item.getTextContent());
                }

                category = new Category();

                category.setBestOfferEnabled(   attributes.get("BestOfferEnabled")  );
                category.setAutoPayEnabled(     attributes.get("AutoPayEnabled")    );
                category.setCategoryID(         attributes.get("CategoryID")        );
                category.setCategoryLevel(      attributes.get("CategoryLevel")     );
                category.setCategoryName(       attributes.get("CategoryName")      );
                category.setCategoryParentID(   attributes.get("CategoryParentID")  );

                output += category + "\n";
            }

            return output;
        }
        catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

// Solution found: https://dzone.com/articles/parsing-xml-using-dom-sax-and
class SAXHandler extends org.xml.sax.helpers.DefaultHandler {

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
                             String qName, org.xml.sax.Attributes attributes) {
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
            //Add the employee to list once end tag is found
            case "Category":
                categories.add(category);
                break;

            //For all other end tags the employee has to be updated.
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