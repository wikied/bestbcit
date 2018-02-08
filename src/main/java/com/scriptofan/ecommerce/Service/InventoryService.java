package com.scriptofan.ecommerce.Service;

import com.scriptofan.ecommerce.DAO.InventoryDao;
import com.scriptofan.ecommerce.Entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InventoryService {

    @Autowired
    private InventoryDao inventoryDao;

    public Collection<Item> getEntireInventory(String userId) {
        return this.inventoryDao.getEntireInventory(userId);
    }

    public Item getItemBySKU(String userId, String SKU) {
        return this.inventoryDao.getItemBySKU(userId, SKU);
    }


}
