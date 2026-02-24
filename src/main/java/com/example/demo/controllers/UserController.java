package com.example.demo.controllers;

import com.example.demo.models.Users.User;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
public class UserController {

    UserService userService=new UserService();

    @PostMapping("/User")
    public User niewueUser(@RequestBody User NieweUser){
        return userService.niewueUser(NieweUser);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
      return userService.GetUser(id);
    }

}
