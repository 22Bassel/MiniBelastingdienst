package com.example.demo.controllers;

import com.example.demo.models.UsersDTO.RequestNieuweUser;
import com.example.demo.models.UsersDTO.ResponseUser;
import com.example.demo.models.UsersDTO.User;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/User")
    public ResponseUser niewueUser(@RequestBody RequestNieuweUser NieweUser){
        if(userService.BestondAlDitEmail(NieweUser.getEmail())){
            throw new IllegalStateException("Er bestaat al deze email");
        }
        return userService.niewueUser(NieweUser);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
      return userService.GetUser(id);
    }

}
