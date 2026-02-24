package com.example.demo.database;

import com.example.demo.models.Users.Admin;
import com.example.demo.models.Users.GewoneUser;
import com.example.demo.models.Users.User;

public class Database {


    public User UserToevoegen(User user){
        if(user.getRol())return new Admin(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        return new GewoneUser(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
    public User UserOphalenMetID(Long id){return new GewoneUser(id,"a","a@gmail.com","hi");};

    public User UserBijwerken(User user){return user;}

}
