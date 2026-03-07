package com.example.demo.models.usersDTO.users;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestNieuweUser{

    String voorName;
    String achterName;
    String email;
    String password;
    Boolean isAdmin;

}
