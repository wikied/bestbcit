package com.scriptofan.ecommerce.EtsyOAuthTests;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.OAuthConsumerToken;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.CoreOAuthConsumerSupport;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Service
public class TestOAuthService {

    /**
     * Adds our dummy AccessToken to the provided HttpHeaders object.
     *
     * @param headers HttpHeaders object to add the Authorization header to.
     * @param url URL that you're visiting.
     * @param method HttpMethod we're using.
     * @return the HttpHeaders object added in.
     * @throws MalformedURLException Your URL is malformed.
     */
    public HttpHeaders addEtsyAuthorizationHeader(
            HttpHeaders headers,
            String      url,
            HttpMethod  method)
            throws MalformedURLException
    {
        headers.set("Authorization", getAuthHeader(url, method));
        return headers;
    }

    private String getAuthHeader(String urlStr, HttpMethod method) throws MalformedURLException {
        CoreOAuthConsumerSupport    consumerSupport;
        ProtectedResourceDetails    details;
        String                      authHeader;
        OAuthConsumerToken          token;
        URL                         url;
        String                      httpMethod;
        Map<String, String>         additionalParams;

        details             = getDetails();
        token               = getToken();
        url                 = getUrl(urlStr);
        httpMethod          = getMethodString(method);
        additionalParams    = null;

        assert(token.getValue() != null);
        assert(token.getSecret() != null);
        assert(details.getConsumerKey() != null);
        assert(details.getSharedSecret() != null);

        consumerSupport = new CoreOAuthConsumerSupport();
        authHeader      = consumerSupport.getAuthorizationHeader(details, token, url, httpMethod, additionalParams);

        return authHeader;
    }


    private ProtectedResourceDetails getDetails() {
        BaseProtectedResourceDetails details = new BaseProtectedResourceDetails();
        details.setConsumerKey("7brj55hkzgzmz5iqz9399q98");
        details.setSharedSecret(new SharedConsumerSecretImpl("txmv7mco03"));
        return details;
    }


    private OAuthConsumerToken getToken() {
        OAuthConsumerToken token = new OAuthConsumerToken();
        token.setValue("3c1d64df55ecc102368d33c809eb8b");
        token.setSecret("d6df5517f2");
        return token;
    }


    private URL getUrl(String urlString) throws MalformedURLException {
        return new URL(urlString);
    }


    private String getMethodString(HttpMethod method) {
        return method.name();
    }
}
