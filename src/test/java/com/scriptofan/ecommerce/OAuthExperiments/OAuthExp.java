package com.scriptofan.ecommerce.OAuthExperiments;

import com.scriptofan.ecommerce.EtsyOAuthTests.TestOAuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuthExp {

    @Autowired
    TestOAuthService testOAuthService;

    @Test
    public void testOAuth() {
        testOAuthService.makeSecuredCall();
    }

}
