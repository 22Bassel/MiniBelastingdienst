package com.example.demo.controllers;

import com.example.demo.models.UsersDTO.RequestNieuweUser;
import com.example.demo.models.UsersDTO.ResponseUser;
import com.example.demo.models.UsersDTO.User;
import com.example.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/User")
    public ResponseEntity<?> niewueUser(@RequestBody RequestNieuweUser NieweUser){
        if(userService.BestondAlDitEmail(NieweUser.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message","Deze email bestond al!!"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.niewueUser(NieweUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        ResponseUser responseUser= userService.GetUser(id);

        if(responseUser==null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Deze id staat niet in DataBase!! "));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

}
