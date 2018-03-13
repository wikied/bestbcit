package com.scriptofan.ecommerce.LocalItem;

import org.apache.tomcat.jni.Local;

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
        String responsePrompt = "Response generated: ";
        this.reports.add(responsePrompt + response);

    }

    public void appendToItemLog(LocalItem localItem){
        localItem.getFullLog().addAll(this.reports);
    }

    public List<String> getReports() {
        return this.reports;
    }





}
