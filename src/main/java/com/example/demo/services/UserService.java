package com.example.demo.services;

import com.example.demo.database.Database;
import com.example.demo.models.Belasting;
import com.example.demo.models.Users.GewoneUser;
import com.example.demo.models.Users.User;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class UserService {

    private Database database=new Database();


    public User niewueUser(User user){
        return database.UserToevoegen(user);
    }

    public User GetUser(Long id){
        return database.UserOphalenMetID(id);
    }


    public boolean BestaatAlEmail(String email) {
        return database.Emailzoeken(email);
    }
}

