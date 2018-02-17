package com.scriptofan.ecommerce.internal.user;

import com.scriptofan.ecommerce.Ebay.EbayItemMapper;
import com.scriptofan.ecommerce.Ebay.InventoryItem.InventoryItem;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    InventoryDAO    inventoryDAO;
    EbayItemMapper  ebayItemMapper;

    public InventoryService() {
        inventoryDAO    = new InventoryDAO();
        ebayItemMapper  = new EbayItemMapper();
    }

    public InventoryItem getEbayItem(String sku) {
        return ebayItemMapper.createEbayItem(inventoryDAO.getItem(sku));
    }
}
