package com.scriptofan.ecommerce.Platforms.Ebay.Services;

import com.scriptofan.ecommerce.Platforms.Ebay.Exception.EbayCreateInventoryItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Error handler.
 */
public class GenericEbayErrorHandler implements ResponseErrorHandler
{
    private ClientHttpResponse  clientHttpResponse  = null;
    private HttpStatus          statusCode          = null;
    private String              statusText          = null;
    private String              body                = null;

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        loadErrorDetails(clientHttpResponse);

        // Handle error
        if (statusCode == HttpStatus.BAD_REQUEST) {
            throw new IOException(new EbayCreateInventoryItemException(
                    "BAD REQUEST: " + statusCode + statusText + body));
        } else {
            throw new IOException(new EbayCreateInventoryItemException());
        }

    }

    /**
     * Loads the error details into local variables so we can
     * use them in an inheritance hierarchy.
     *
     * @param clientHttpResponse
     * @throws IOException
     */
    public final void loadErrorDetails(ClientHttpResponse clientHttpResponse) throws IOException {
        if (this.clientHttpResponse == null) {
            return;
        }

        this.clientHttpResponse = clientHttpResponse;
        try {
            loadStatusCode(clientHttpResponse);
            loadStatusText(clientHttpResponse);
            loadBody(clientHttpResponse);
        }
        catch (IOException e) {
            if (statusCode == null) {
                throw e;
            }
            else if (statusText == null || body == null) {
                // Not relevant to actually handling the issue, just to reporting.
            }
        }
    }

    // Loads the status code.
    private void loadStatusCode(ClientHttpResponse clientHttpResponse) throws IOException {
        statusCode = clientHttpResponse.getStatusCode();
    }

    // Loads status text.
    private void loadStatusText(ClientHttpResponse clientHttpResponse) throws IOException {
        statusText = clientHttpResponse.getStatusText();
    }

    // Loads the body of the HttpResponse as a string.
    private void loadBody(ClientHttpResponse clientHttpResponse) throws IOException {
        String              line;
        String              output;

        BufferedReader      bufferReader;
        StringBuilder       stringBuilder;
        InputStreamReader   responseBodyReader;

        /* Generate debugging output */
        stringBuilder       = new StringBuilder();
        responseBodyReader  = new InputStreamReader(clientHttpResponse.getBody());
        bufferReader        = new BufferedReader(responseBodyReader);

        output = "";
        while ((line = bufferReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        output += stringBuilder.toString() + "\n";
        this.body = output;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public String getBody() {
        return body;
    }
}
