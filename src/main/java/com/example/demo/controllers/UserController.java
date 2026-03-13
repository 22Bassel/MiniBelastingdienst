package com.example.demo.controllers;

import com.example.demo.models.usersDTO.users.RequestNieuweUser;
import com.example.demo.models.usersDTO.users.ResponseUser;
import com.example.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    @GetMapping("/AlleUsers")
    public ResponseEntity<?> getAlleUsers(){
        List<ResponseUser> responseUsers= userService.GetAlleUsers();

        return ResponseEntity.status(HttpStatus.OK).body(responseUsers);
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){

        if(!userService.BestondAlDezeUser(id)){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Deze id staat niet in DataBase!! "));
        }
        userService.DeleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","Deze User is verwijderd!!"));
    }

}
