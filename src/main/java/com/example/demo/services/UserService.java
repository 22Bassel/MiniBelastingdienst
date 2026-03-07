package com.example.demo.services;

import com.example.demo.database.UserRepo;
import com.example.demo.models.Entities.UserEntity;
import com.example.demo.models.UsersDTO.Users.RequestNieuweUser;
import com.example.demo.models.UsersDTO.Users.ResponseUser;

import java.util.Optional;

@org.springframework.stereotype.Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public ResponseUser niewueUser(RequestNieuweUser user){

        UserEntity user1= userRepo.save(UserEntity.vanDto(user));

        return ResponseUser.NaarDTO(user1);
    }

    public ResponseUser GetUser(Long id){
        Optional<UserEntity> user1= userRepo.findById(id);

        if(user1.isPresent()){
            return ResponseUser.NaarDTO(user1.get());
        }

        return null;

    }


    public boolean BestondAlDitEmail(String email) {
        return userRepo.existsByEmail(email);
    }
}

