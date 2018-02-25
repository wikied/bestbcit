package com.scriptofan.ecommerce.Ebay;

import com.oracle.tools.packager.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

        return response.toString();
    }
}
