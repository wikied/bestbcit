package com.scriptofan.ecommerce.Controllers;


import com.scriptofan.ecommerce.Database.UserRepository;
import com.scriptofan.ecommerce.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/add-user")
    public String addNewUser(){
        User user = new User();

        user.setName("testing");

        repository.save(user);

        return "User saved";
    }

    @GetMapping("/get-user")
    public Iterable<User> getAllUsers(){
        return repository.findAll();
    }

}
