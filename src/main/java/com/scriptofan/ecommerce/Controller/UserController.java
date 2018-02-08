package com.scriptofan.ecommerce.Controller;

import com.scriptofan.ecommerce.Entity.User;
import com.scriptofan.ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return this.userService.getUser(userId);
    }

}
