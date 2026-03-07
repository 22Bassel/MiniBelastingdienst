package com.example.demo.models.Entities;

import com.example.demo.models.UsersDTO.RequestNieuweUser;
import com.example.demo.models.UsersDTO.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String voorName;
    String achterName;
    String email;
    String password;
    Boolean isAdmin;

    public static UserEntity vanDto(RequestNieuweUser user){
        return UserEntity.builder()
                .voorName(user.getVoorName())
                .achterName(user.getAchterName())
                .email(user.getEmail())
                .password(user.getPassword())
                .isAdmin(user.getIsAdmin())
                .build();
    }
}
