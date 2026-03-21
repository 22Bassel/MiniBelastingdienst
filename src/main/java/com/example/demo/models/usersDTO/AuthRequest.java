package com.example.demo.models.usersDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest{
    private String username;
    private String password;
}
