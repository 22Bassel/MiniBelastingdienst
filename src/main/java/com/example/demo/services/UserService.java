package com.example.demo.services;

import com.example.demo.database.UserRepo;
import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.usersDTO.users.RequestNieuweUser;
import com.example.demo.models.usersDTO.users.ResponseUser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ResponseUser>  GetAlleUsers(){
        return userRepo.findAll().stream().map(ResponseUser::NaarDTO).collect(Collectors.toList());
    }
}

