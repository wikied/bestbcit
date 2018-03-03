package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.LocalItem.Offer;
import com.scriptofan.ecommerce.Platforms.Core.PlatformPublishingService;
import org.apache.tomcat.util.digester.Rules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LocalItemTest {
    private LocalItem localItem;


    //Should throw IndexOutOfBoundsException due to passed null parameter.
    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldCatchNullParameters() throws RulesetCollisionException {
        localItem = new LocalItem();
        String key = null;
        String value = null;
        localItem.addField(key, value);
    }

    //Should throw RulesetCollisionException for already existing key and value.
    @Test(expected = RulesetCollisionException.class)
    public void shouldCatchCollision() throws RulesetCollisionException{
        localItem = new LocalItem();
        String key = "samekey";
        String value1 = "value1";
        String value2 = "value2";
        localItem.addField(key, value1);
        localItem.addField(key, value2);
    }

    //Should throw IndexOutOfBoundsException for a null field.
    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldCatchNullField() {
        localItem = new LocalItem();
        String key = "TestKey";
        localItem.getField(key);
    }

    //Should throw IndexOutOfBoundsException for a null field.
    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldCatchNullOffer(){
        localItem = new LocalItem();
        localItem.addOffer();
    }


}
