package com.example.demo.services;

import com.example.demo.database.Database;
import com.example.demo.database.UserRepo;
import com.example.demo.models.Entities.UserEntity;
import com.example.demo.models.UsersDTO.RequestNieuweUser;
import com.example.demo.models.UsersDTO.ResponseUser;
import com.example.demo.models.UsersDTO.User;

@org.springframework.stereotype.Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private Database database=new Database();


    public ResponseUser niewueUser(RequestNieuweUser user){

        UserEntity user1= userRepo.save(UserEntity.vanDto(user));

        return ResponseUser.NaarDTO(user1);
    }

    public User GetUser(Long id){
        return database.UserOphalenMetID(id);
    }


    public boolean BestondAlDitEmail(String email) {
        return database.Emailzoeken(email);
    }
}

