package com.scriptofan.ecommerce.Controllers;


import com.scriptofan.ecommerce.Database.*;
import com.scriptofan.ecommerce.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DatabaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;


    @GetMapping("/add-item")
    public String addNewItem(){

        User user = new User();

        Item item = new Item("3455632452345",
                "EBAY_US",
                "FIXED_PRICE",
                75,
                "30120",
                "Lumia Phone",
                "USD",
                "272.17",
                "2",
                1,
                "testing");

        itemRepository.save(item);

        return "Saved !";
    }

    @GetMapping("/get-item/{id}")
    public Item getItem(@PathVariable Integer id){
        return itemRepository.getItemById(id);
    }

    @GetMapping("/get-items/{quantity}")
    public List<Item> getAllItem(@PathVariable Integer quantity){
        return itemRepository.findAllByAvailableQuantity(quantity);
    }

}
