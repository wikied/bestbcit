package com.scriptofan.ecommerce.Ebay.AccountPolicies;

import com.scriptofan.ecommerce.Ebay.AccountPolicies.Entity.FulfillmentPolicy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PolicyController {

    private FulfillmentPolicyService fulfillmentPolicyService;

    public PolicyController() {
        fulfillmentPolicyService = new FulfillmentPolicyService();
    }

    @GetMapping("/policies/fulfillment")
    public FulfillmentPolicy[] getFulfillmentPolicies() {
        return fulfillmentPolicyService.getFulfillmentPolicies();
    }
}
