package com.scriptofan.ecommerce.Controllers;

import com.scriptofan.ecommerce.Database.Item;
import com.scriptofan.ecommerce.Database.ItemRepository;

import com.scriptofan.ecommerce.Database.UserRepository;
import com.scriptofan.ecommerce.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/add-item")
    public String addNewUser(){

        User user1 = new User("Robert");
        User user2 = new User("William");

        Item item1 = new Item("3455632452345",
                             "EBAY_US",
                             "FIXED_PRICE,",
                             75,
                             "30120",
                             "Lumia phone.....",
                             "USD",
                             "272.17",
                             "2",
                                         user1);

        Item item2 = new Item("287439387429847",
                              "EBAY_US",
                              "FIXED_PRICE,",
                              122,
                              "238820",
                              "Iphone X.....",
                              "USD",
                              "1000.00",
                              "1",
                                           user2);


        userRepository.save(user1);
        userRepository.save(user2);
        itemRepository.save(item1);
        itemRepository.save(item2);
        return "Saved !";
    }

    @GetMapping("/get-items")
    public Iterable<Item> getALLItems(){
        return itemRepository.findAll();
    }

    @GetMapping("/get-by-id/{id}")
    public Iterable<Item> getItemById(@PathVariable Integer id){
        return itemRepository.findItemById(id);
    }

    @GetMapping("/get-by-user/{name}")
    public Iterable<Item> getItemByUser(@PathVariable String name){
        return itemRepository.findItemsByUser_Name(name);
    }


}
