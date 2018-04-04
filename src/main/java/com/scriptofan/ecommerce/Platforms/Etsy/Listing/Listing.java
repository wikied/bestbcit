package com.scriptofan.ecommerce.Platforms.Etsy.Listing;

import java.util.List;

public class Listing {
    private int quantity;
    private int title;
    private int description;
    private float price;
    private boolean is_supply;
    private who_made who_made;
    private String when_made;
    private List<String> materials;
    private int shipping_template_id;
    private boolean is_customizable;
    private boolean non_taxable;
    private state state;
    private int processing_min;
    private int processing_max;
    private int category_id;
    private int taxonomy_id;
    private List<String> tags;
    private recipient recipient;
    private List<String> style;

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public int getShipping_template_id() {
        return shipping_template_id;
    }

    public void setShipping_template_id(int shipping_template_id) {
        this.shipping_template_id = shipping_template_id;
    }

    public boolean isIs_customizable() {
        return is_customizable;
    }

    public void setIs_customizable(boolean is_customizable) {
        this.is_customizable = is_customizable;
    }

    public boolean isNon_taxable() {
        return non_taxable;
    }

    public void setNon_taxable(boolean non_taxable) {
        this.non_taxable = non_taxable;
    }

    public Listing.state getState() {
        return state;
    }

    public void setState(Listing.state state) {
        this.state = state;
    }

    public int getProcessing_min() {
        return processing_min;
    }

    public void setProcessing_min(int processing_min) {
        this.processing_min = processing_min;
    }

    public int getProcessing_max() {
        return processing_max;
    }

    public void setProcessing_max(int processing_max) {
        this.processing_max = processing_max;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getTaxonomy_id() {
        return taxonomy_id;
    }

    public void setTaxonomy_id(int taxonomy_id) {
        this.taxonomy_id = taxonomy_id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Listing.recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Listing.recipient recipient) {
        this.recipient = recipient;
    }

    public List<String> getStyle() {
        return style;
    }

    public void setStyle(List<String> style) {
        this.style = style;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isIs_supply() {
        return is_supply;
    }

    public void setIs_supply(boolean is_supply) {
        this.is_supply = is_supply;
    }

    public Listing.who_made getWho_made() {
        return who_made;
    }

    public void setWho_made(Listing.who_made who_made) {
        this.who_made = who_made;
    }

    public String getWhen_made() {
        return when_made;
    }

    public void setWhen_made(String when_made) {
        this.when_made = when_made;
    }



    enum who_made{
        i_did,
        collective,
        someone_else;



    }
    enum state {
        active,
        draft;

    }

    enum recipient{
        men,
        wmeon,
        unisex_adults,
        teen_boys,
        teen_girls,
        teens,
        boys,
        girls,
        children,
        baby_boys,
        baby_girls,
        babies,
        birds,
        cats,
        dogs,
        pets,
        non_specific;
    }

    enum occasion{
        anniversary,
        baptism,
        bar_or_bat_mitzvah,
        brithday,
        canada_day,
        chinese_new_year,
        cinco_de_mayo,
        confirmation,
        christmas,
        day_of_the_dead,
        easter,
        eid,
        engagement,
        fathers_day,
        get_well,
        graduation,
        halloween,
        hannukkah,
        housewarming,
        kwanzaa,
        prom,
        huly_4th,
        mothers_day,
        new_baby,
        new_years,
        quinceanera,
        retirement,
        st_patricks_day,
        sweet_16,
        sympathy,
        thanksgiving,
        valentives,
        wedding
    }

}
