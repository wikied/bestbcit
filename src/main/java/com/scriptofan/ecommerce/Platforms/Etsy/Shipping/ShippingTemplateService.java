package com.scriptofan.ecommerce.Platforms.Etsy.Shipping;


import com.scriptofan.ecommerce.Platforms.Etsy.EtsyLocalOffer;
import com.scriptofan.ecommerce.User.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

@Service
public class ShippingTemplateService {

    private String shipping_template_id;
    private static final String SHIPPING_INFO_URI = "/shipping/templates";
    private String GET_SHIPPING_INFO_URI = SHIPPING_INFO_URI + shipping_template_id;

    public ShippingTemplate shippingTemplateBuilder(EtsyLocalOffer etsyLocalOffer){
        ShippingTemplate shippingTemplate = new ShippingTemplate();
        shippingTemplate.setTitle(etsyLocalOffer.getLocalItem().getField(""));
        shippingTemplate.setOrigin_country_id(Integer.parseInt(etsyLocalOffer.getLocalItem().getField("")));
    return shippingTemplate;
    }

}

