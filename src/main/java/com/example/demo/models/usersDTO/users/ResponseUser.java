package com.example.demo.models.usersDTO.users;

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
    Boolean isAdmin;
    List<ResponseBelasting> belastingen;

    public static ResponseUser NaarDTO(UserEntity user){
        return ResponseUser.builder()
                .id(user.getId())
                .voorName(user.getVoorNaam())
                .achterName(user.getAchterNaam())
                .email(user.getEmail())
                .isAdmin(user.getIsAdmin())
                .belastingen(Optional.ofNullable(user.getBelastingen())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(ResponseBelasting::NaarDTO)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
