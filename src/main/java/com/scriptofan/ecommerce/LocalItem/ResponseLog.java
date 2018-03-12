package com.scriptofan.ecommerce.LocalItem;

import java.util.ArrayList;
import java.util.List;

public class ResponseLog {

    private List<String> reports;
    private String response;
    private static final String nullResponse = "This response was null";

    public ResponseLog(){
        this.reports = new ArrayList<>();
    }

    public void add(String response){
        this.response = response;
        if(this.response == null){
            this.response = nullResponse;
        }
        String responsePrompt = "Response was generated:\n";
        this.reports.add(responsePrompt + response);

    }






}
