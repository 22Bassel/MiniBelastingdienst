package com.example.demo.models.usersDTO.users;

import com.example.demo.enums.Role;
import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.usersDTO.belasting.ResponseBelasting;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

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
    Role role;
    List<ResponseBelasting> belastingen;

    public static ResponseUser NaarDTO(UserEntity user){
        return ResponseUser.builder()
                .id(user.getId())
                .voorName(user.getVoorNaam())
                .achterName(user.getAchterNaam())
                .email(user.getEmail())
                .role(user.getRole())
                .belastingen(Optional.ofNullable(user.getBelastingen())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(ResponseBelasting::NaarDTO)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
