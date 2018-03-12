package com.scriptofan.ecommerce.Controllers;


import com.scriptofan.ecommerce.Database.Item;
import com.scriptofan.ecommerce.Database.ItemRepository;
import com.scriptofan.ecommerce.Database.UserRepository;
import com.scriptofan.ecommerce.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DatabaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/add-user")
    public String addNewUser(){
//
//        User user = new User();
//
//        user.setName("testing");
//
//
//        Map<String, String> tester = new HashMap<>();
//
//        tester.put("Quantity", "2");
//        tester.put("Price", "23");
//
//        Item item = new Item();
//        item.setFields(tester);
//        item.setUser(user);
//
//        userRepository.save(user);
//        itemRepository.save(item);

        return "Saved !";
    }

    @GetMapping("/get-user")
    public Iterable<Item> getALLItems(){
        return itemRepository.findAll();
    }

}
