package com.example.demo.models.UsersDTO;

import com.example.demo.models.Entities.UserEntity;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseUser {
    Long id;
    String voorName;
    String achterName;
    String email;
    Boolean isAdmin;

    public static ResponseUser NaarDTO(UserEntity user){
        return ResponseUser.builder()
                .id(user.getId())
                .voorName(user.getVoorName())
                .achterName(user.getAchterName())
                .email(user.getEmail())
                .isAdmin(user.getIsAdmin())
                .build();
    }
}
