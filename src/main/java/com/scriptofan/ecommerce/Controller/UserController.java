package com.scriptofan.ecommerce.Controller;

import com.scriptofan.ecommerce.Entity.Item;
import com.scriptofan.ecommerce.Entity.User;
import com.scriptofan.ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return this.userService.getUser(userId);
    }


    @GetMapping("/{userId}/inventory")
    public Collection<Item> getEntireInventory(
        @PathVariable("userId") String userId
    ) {
        return this.inventoryService.getEntireInventory(userId);
    }

    @GetMapping("/{userId}/inventory/{itemSKU}")
    public Item getItemBySKU(
        @PathVariable("userId")     String userId,
        @PathVariable("itemSKU")    String SKU
    ) {
        return this.inventoryService.getItemBySKU(userId, SKU);
    }
}
