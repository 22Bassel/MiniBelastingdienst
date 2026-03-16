package com.example.demo.models.usersDTO.users;

import com.example.demo.enums.Role;
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
    Role Role;

}
