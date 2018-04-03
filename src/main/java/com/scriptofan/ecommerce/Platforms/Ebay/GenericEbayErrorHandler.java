package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Platforms.Ebay.Exception.BadEbayTokenException;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.Ebay500ServerException;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.EbayCreateInventoryItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Error handler.
 */
public class GenericEbayErrorHandler implements ResponseErrorHandler
{
    private static final String ERROR_ID_TEXT       = "errorId\":";
    private static final String ERROR_MESSAGE_TEXT  = "message\":";

    private ClientHttpResponse  clientHttpResponse  = null;
    private HttpStatus          statusCode;
    private String              statusText;
    private String              body;
    private int                 ebayErrorId;
    private String              ebayMessage;

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        statusCode = clientHttpResponse.getStatusCode();
        statusText = clientHttpResponse.getStatusText();

        boolean wasSuccessful = clientHttpResponse.getStatusCode().is2xxSuccessful();
        return !wasSuccessful;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        loadErrorDetails(clientHttpResponse);

        if (statusCode.equals(HttpStatus.UNAUTHORIZED)) {
            // The user's eBay OAuth token is bad.
            throw new IOException(new BadEbayTokenException());
        }
        else if (statusCode.is5xxServerError()) {
            // Something went bad on eBay's end. Usually best thing to do is retry.
            throw new IOException(new Ebay500ServerException());
        }

        throw new IOException(getErrorString());
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
            this.clientHttpResponse = clientHttpResponse;
            try {
                loadStatusCode(clientHttpResponse);
                loadStatusText(clientHttpResponse);
                loadBody(clientHttpResponse);
                parseEbayErrorId();
                parseEbayMessage();
            } catch (IOException e) {
            }
        }
    }

    public String getErrorString() {
        return "ERROR: " + statusCode + " " + statusText + " " + body;
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

    // Parses the eBay error ID out from the body.
    private void parseEbayErrorId() {
        try {
            ebayErrorId = Integer.parseInt(parse(ERROR_ID_TEXT, ","));
        }
        catch (NumberFormatException e) {
            // Couldn't parse ebayErrorId
            ebayErrorId = 0;
        }
    }

    // Parses the eBay error ID out from the body.
    private void parseEbayMessage() {
        ebayMessage = parse(ERROR_MESSAGE_TEXT, ",\"");
    }

    /**
     * Parses the response body, extracting the text between fieldTitle and
     * postDelim.
     *
     * @param fieldTitle All the text up to this value will be ignored.
     * @param postDelimiter Everything between fieldTitle and this value will be ignored.
     * @return Text between fieldTitle and postDelim.
     */
    protected String parse(String fieldTitle, String postDelimiter) {
        String  value;
        Scanner scan;

        scan = new Scanner(body);
        scan.findInLine(fieldTitle);
        scan.useDelimiter(postDelimiter);
        value = scan.next().trim();

        scan.close();
        return value;
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

    public int getEbayErrorId() {
        return ebayErrorId;
    }

    public String getEbayMessage() {
        return ebayMessage;
    }
}
