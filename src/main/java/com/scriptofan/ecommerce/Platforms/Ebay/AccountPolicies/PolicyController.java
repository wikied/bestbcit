package com.scriptofan.ecommerce.Platforms.Ebay.AccountPolicies;

import com.scriptofan.ecommerce.Platforms.Ebay.AccountPolicies.Entity.FulfillmentPolicy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class PolicyController {

    // Dummy user token. Replace with something actually secure later.
    private static final String DUMMY_USER_AUTH_TOKEN = "v^1.1#i^1#f^0#I^3#r^0#p^3#t^H4sIAAAAAAAAAOVYa2wURRzv9YVIkRpACUU8VxIVsnezj3ut9ODa0nBKH3ClqUVoZndnryt7u5ud3d6dMeZSFTBKQHyhIjYmDQY/kEBMIIGAJKQ+SIiI+oFoDJigBKNBrcYgOnt9XWuEPvjQxP2ymZn/6/f///6zMwty5TOXblm95ffZnhnFPTmQK/Z4mFlgZnnZsjtKiheWFYECAU9PbkmutLvk++UYpjRTWIewaegYeTMpTcdCfrKacixdMCBWsaDDFMKCLQmJWMMagfUBwbQM25AMjfLG66opmVVCwTAnc1yIhQrDk1l9yGaLQdYVFGRYyEOFZySWDZJ1jB0U17ENdbuaYgETpgFLs6EWwAqBkADCvkgw0k55W5GFVUMnIj5ARfPhCnldqyDWG4cKMUaWTYxQ0XisPtEUi9etamxZ7i+wFR3MQ8KGtoNHj2oNGXlboeagG7vBeWkh4UgSwpjyRwc8jDYqxIaCmUT4+VSHRADDkZAcDgEQCoTEW5LKesNKQfvGcbgzqkwreVEB6bZqZ2+WUZIN8Qkk2YOjRmIiXud1X2sdqKmKiqxqalVN7LH1iVXrKG+iudkyulQZyS5ShuPC4UAowlFRG2GSQmR1mFIntDSUHfQ1YHAw02Oc1Rq6rLp5w95Gw65BJHA0Nj18QXqIUJPeZMUU2w2qUI4fTiPb7tZ1oJCO3am7pUUpkgtvfnjzIgyxYoQHt4oXAcSgCB9hIzKHEB9Ao3nh9vrkuBF1yxNrbva7sSARZukUtDYj29SghGiJpNdJIUuVBS6gsFxYQbQcjCg0H1EUWgzIQZpREAIIiaIUCf/PKGLblio6NhqmydiFPM5qKiEZJmo2NFXKUmNF8jvPICkyuJrqtG1T8PvT6bQvzfkMK+lnAWD8bQ1rElInSkFqWFa9uTCt5ukhEbYQecHOmiSaDGEfca4nqShnyc3QsrM1TpaME0jTyGuIwaMijI6d/Q+o2IU6vUC6+pgYgKbqcwnuk4yU34Ckod2pjnzE3vEI+UUnS/zLyPJZCMqGrmXHr5d0CIEHtMenhEk1fAO9SGAUeHR7feIGJuBU1bsIlw0rO0GYo5UnoAMlyXB0ezLuBlUnoKE4mqJqmtuuk3FYoD6RMHWoZW1VwpNxWbAhk/RiNdlpj9iZUrfGTDMuT69ujWka1NeokK6pjbfkt+BETRvNMYCcP0WZoxFiQ4ALR6aEW0ZdqoQ61GmGXXc0bTSCfK9PDFsd6ppuNWUZAGGYfEoDIARoHjARGkZCkFbISVfkZBHKEXlK9WxITrdSNvpjU0JUq6lkh2nJTreP6WoD22hqxaolx9rpBcrdYYY2GMAHZZoXOZbmZZmlRZEccYNBxI8X8piJgqPhvy4G/tGX81JPtCj/MN2eY6Dbc4Tc8N1+YZaBh8pL1peWVFBYtZEPQ10WjYxPhYqPfAx0cvu0kG8zyppQtYrLPRuqLq+4VvBjoGcjWDD8a2BmCTOr4D8BWDSyUsbMuXs2Q9qUbLBsIATC7eD+kdVS5q7SeWq0403hxyOLc8/jM+myn/GjDeYpMHtYyOMpKyrt9hQ1zH+pftfuBt53cTf3yaLS+JILZf0Pn+j/+PCh58xN0md9r1iH3/7gwbfuEX5DnHE0eZo/RG+vOpa87F94W/LK05/mXj/wxdarJ06bc6vozGuvXrrwx/vH+dubvm5Ob/DQj6e9l/uOXJnf8tPJb/6mf9320Zf1XvM9cPCZypXbxWv7u1/8QTi94uWdmZX76j5cajX2vFubeeDqs2+0gj8v/jK3ctt9iz9X0l+Vr4/+1f9Cyt9579ronKOV/em+9qee7G1L79k8N/LI3p093/Ue0Oed3X1gU1/vnpULKs7s+Lbi3MHt7yy+dHLTrqqq88uvt4JZufOV+yq2nWq7Xra3tvfs/nNVW8/Lxy9u9Gdm3DlQxn8AxaSG87IRAAA=";

    // Services
    private FulfillmentPolicyService fulfillmentPolicyService;

    /**
     * Constructor.
     */
    public PolicyController() {
        fulfillmentPolicyService = new FulfillmentPolicyService();
    }

    /**
     * Returns an array of all of the user's Fulfillment Policies.
     *
     * @return Array of user's Fulfillment Policies.
     */
    @GetMapping("/policies/fulfillment")
    public FulfillmentPolicy[] getFulfillmentPolicies() {
        return fulfillmentPolicyService.getFulfillmentPolicies(DUMMY_USER_AUTH_TOKEN);
    }
}