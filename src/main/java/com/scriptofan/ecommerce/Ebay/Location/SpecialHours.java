package com.scriptofan.ecommerce.Ebay.Location;

public class SpecialHours {
    private String date;
    private Interval intervals[];

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Interval[] getIntervals() {
        return intervals;
    }

    public void setIntervals(Interval[] intervals) {
        this.intervals = intervals;
    }
}
