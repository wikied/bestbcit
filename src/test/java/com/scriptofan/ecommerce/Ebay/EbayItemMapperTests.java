package com.scriptofan.ecommerce.Ebay;

import com.scriptofan.ecommerce.Ebay.InventoryItem.InventoryItem;
import com.scriptofan.ecommerce.internal.user.Entity.LocalItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EbayItemMapperTests {

    @Test
    public void createEbayItemTest1() {
        LocalItem       item;
        EbayItemMapper  ebayMapper;
        InventoryItem   ebayItem;

        item = new LocalItem();

        item.setSKU("ITEM-0001");
        item.setTitle("An item");
        item.setDescription("This item is sweet! You should buy it.");
        item.setImages(null);

        item.setCondition("New");
        item.setQuantity(42);
        item.setDimensions(22, 15, 8, "cm");
        item.setWeight(8.5, "kg");

        ebayMapper = new EbayItemMapper();
        ebayItem   = ebayMapper.createEbayItem(item);

        assert(ebayItem.getSku().equals(item.getSKU()));
        assert(ebayItem.getProduct().getTitle().equals(item.getTitle()));
        assert(ebayItem.getProduct().getDescription().equals(item.getDescription()));
    }

}
