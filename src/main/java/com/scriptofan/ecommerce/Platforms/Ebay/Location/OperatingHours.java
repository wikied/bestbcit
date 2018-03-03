package com.scriptofan.ecommerce.Platforms.Ebay.Location;

public class OperatingHours {
    private DayOfWeekEnum dayOfWeekEnum;
    private Interval intervals[];

    public DayOfWeekEnum getDayOfWeekEnum() {
        return dayOfWeekEnum;
    }

    public void setDayOfWeekEnum(DayOfWeekEnum dayOfWeekEnum) {
        this.dayOfWeekEnum = dayOfWeekEnum;
    }

    public Interval[] getIntervals() {
        return intervals;
    }

    public void setIntervals(Interval[] intervals) {
        this.intervals = intervals;
    }
}
