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

    @Autowired
    private ItemRepository2 itemRepository2;

    @GetMapping("/add-item")
    public String addNewItem(){

        User user = new User(1, "testing");
        Item2 item2 = new Item2();



        Map<String, String> tester = new HashMap<>();
        tester.put("Quantity", "2");
        tester.put("Price", "23");

        item2.setFields(tester);
        item2.setUser(user);


//        Item item = new Item("3455632452345",
//                "EBAY_US",
//                "FIXED_PRICE",
//                75,
//                "30120",
//                "Lumia Phone",
//                "USD",
//                "272.17",
//                "2",
//                1,
//                "testing");

        userRepository.save(user);
        itemRepository2.save(item2);

        return "Saved !";
    }

    @GetMapping("/get-item/{id}")
    public Item getItem(@PathVariable Integer id){
        return itemRepository.getItemById(id);
    }

    @GetMapping("/get-items/{quantity}")
    public List<Item> getAllItem(@PathVariable Integer quantity){
        return itemRepository.findAllByAvaliableQuantity(quantity);
    }

}
