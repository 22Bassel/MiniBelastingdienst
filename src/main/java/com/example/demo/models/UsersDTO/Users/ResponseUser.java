package com.example.demo.models.UsersDTO.Users;

import com.example.demo.models.Entities.BelastingEntity;
import com.example.demo.models.Entities.UserEntity;
import com.example.demo.models.UsersDTO.belasting.ResponseBelasting;
import lombok.*;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
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
                .voorName(user.getVoorName())
                .achterName(user.getAchterName())
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
