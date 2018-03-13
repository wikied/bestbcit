package com.scriptofan.ecommerce.Platforms.Etsy.Money;

public class Money {
    private int amount;
    private int divisor;
    private String currency_code;
    private String formatted_raw;
    private String formatted_short;
    private String formatted_long;
    private String original_currency_code;
    private String before_conversion;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getFormatted_raw() {
        return formatted_raw;
    }

    public void setFormatted_raw(String formatted_raw) {
        this.formatted_raw = formatted_raw;
    }

    public String getFormatted_short() {
        return formatted_short;
    }

    public void setFormatted_short(String formatted_short) {
        this.formatted_short = formatted_short;
    }

    public String getFormatted_long() {
        return formatted_long;
    }

    public void setFormatted_long(String formatted_long) {
        this.formatted_long = formatted_long;
    }

    public String getOriginal_currency_code() {
        return original_currency_code;
    }

    public void setOriginal_currency_code(String original_currency_code) {
        this.original_currency_code = original_currency_code;
    }

    public String getBefore_conversion() {
        return before_conversion;
    }

    public void setBefore_conversion(String before_conversion) {
        this.before_conversion = before_conversion;
    }
}
