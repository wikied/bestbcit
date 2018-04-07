package com.scriptofan.ecommerce;

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

/**
 * Responsible for building OAuth headers for API calls to Etsy.
 *
 * By the time we got here in the project, we didn't have time to
 * implement full OAuth security. This is a hacky workaround, and
 * will need to be replaced at some point.
 */
@Service
public class DummyEtsyOAuthHeaderGen {



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


    /*
     * Constructs the contents for an OAuth header, based on the passed
     * URL string, HttpMethod, and the dummy secrets and keys we have
     * associated with Etsy.
     */
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


    /*
     * Adds the App key and secret to the ProtectedResourceDetails object.
     */
    private ProtectedResourceDetails getDetails() {
        BaseProtectedResourceDetails details = new BaseProtectedResourceDetails();
        details.setConsumerKey("7brj55hkzgzmz5iqz9399q98");
        details.setSharedSecret(new SharedConsumerSecretImpl("txmv7mco03"));
        return details;
    }


    /*
     * Constructs a new OAuth token, set with our dummy Etsy value and secret.
     */
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
