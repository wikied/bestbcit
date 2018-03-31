package com.scriptofan.ecommerce.EtsyOAuthTests;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth.common.signature.CoreOAuthSignatureMethodFactory;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.OAuthConsumerToken;
import org.springframework.security.oauth.consumer.OAuthRequestFailedException;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.CoreOAuthConsumerSupport;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class TestOAuthService {

    public void makeSecuredCall() {

        OAuthConsumerToken              consumerToken;
        CoreOAuthConsumerSupport        consumerSupport;
        BaseProtectedResourceDetails    resourceDetails;
        OAuthRestTemplate               template;
        String                          response;
        CoreOAuthSignatureMethodFactory sigFactory;

        resourceDetails = new BaseProtectedResourceDetails();
        resourceDetails.setUse10a(false);
        resourceDetails.setConsumerKey("7brj55hkzgzmz5iqz9399q98");
        resourceDetails.setSharedSecret(new SharedConsumerSecretImpl("txmv7mco03"));
        resourceDetails.setAcceptsAuthorizationHeader(true);

        template = new OAuthRestTemplate(resourceDetails);
        template.setErrorHandler(new DebugResponseErrorHandler());

        consumerSupport = getOAuthConsumerSupport();
        sigFactory      = (CoreOAuthSignatureMethodFactory) consumerSupport.getSignatureFactory();

        template.setSupport(consumerSupport);



        try {
            response = template.getForObject("https://openapi.etsy.com/v2/users/wtdhtpyy/shops", String.class);
        }
        catch (RestClientException e) {
            System.err.println("EXCEPTION " + e.toString());
        }
    }



    private OAuthConsumerToken getOAuthConsumerToken() {
        OAuthConsumerToken token = new OAuthConsumerToken();

        return token;
    }



    /*
     * Hacky implementation of OAuthConsumerSupport
     */
    private CoreOAuthConsumerSupport getOAuthConsumerSupport() {
        CoreOAuthConsumerSupport consumerSupport = new CoreOAuthConsumerSupport()
        {
            @Override
            public OAuthConsumerToken getUnauthorizedRequestToken(ProtectedResourceDetails details, String callback)
                    throws OAuthRequestFailedException
            {
                return getDummyUnauthToken();
            }

            @Override
            public OAuthConsumerToken getUnauthorizedRequestToken(String resourceId, String callback)
                    throws OAuthRequestFailedException
            {
                return getDummyUnauthToken();
            }





            @Override
            public OAuthConsumerToken getAccessToken(ProtectedResourceDetails details,
                                                     OAuthConsumerToken requestToken,
                                                     String verifier)
            {
                return getDummyAccessToken();
            }

            @Override
            public OAuthConsumerToken getAccessToken(OAuthConsumerToken requestToken, String verifier)
                    throws OAuthRequestFailedException
            {
                return getDummyAccessToken();
            }



            private OAuthConsumerToken getDummyAccessToken() {
                OAuthConsumerToken token;

                token = new OAuthConsumerToken();
                token.setValue("3c1d64df55ecc102368d33c809eb8b");
                token.setSecret("d6df5517f2");
                token.setAccessToken(true);

                return token;
            }



            private OAuthConsumerToken getDummyUnauthToken() {
                OAuthConsumerToken token;

                token = new OAuthConsumerToken();
                token.setValue("7brj55hkzgzmz5iqz9399q98");
                token.setSecret("txmv7mco03");
                token.setAccessToken(false);

                return token;
            }
        };

        return consumerSupport;
    }





    private class DebugResponseErrorHandler implements ResponseErrorHandler {
        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
            return true;
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

            String line;
            BufferedReader bufferReader  = new BufferedReader(new InputStreamReader(clientHttpResponse.getBody()));
            StringBuilder  stringBuilder = new StringBuilder();
            while ((line = bufferReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            System.err.println(stringBuilder.toString());
        }
    }
}
