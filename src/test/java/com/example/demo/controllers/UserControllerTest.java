package com.example.demo.controllers;

import com.example.demo.models.Belasting;
import com.example.demo.models.Users.GewoneUser;
import com.example.demo.models.Users.User;
import com.example.demo.services.BelastingService;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Test
    public void NiewueUserTest(){ // de email adres is al in database

        User user=new GewoneUser(1L,"a","b@gmail.com","c");

        Mockito.when(userService.BestaatAlEmail(anyString()))
               .thenReturn(true);

        IllegalStateException exception=assertThrows(IllegalStateException.class,()->
        System.out.println(userController.niewueUser(user)));

      assertEquals("Er bestaat al deze email",exception.getMessage());

    }


    @Test
    public void NiewueUserTestTwee(){ // Nieuwe user toevoegen

        User user=new GewoneUser(1L,"a","b@gmail.com","c");

        Mockito.when(userService.BestaatAlEmail(anyString()))
                .thenReturn(false);

        Mockito.when(userService.niewueUser(user))
                .thenReturn(user);

        assertEquals(user.getName(),userController.niewueUser(user).getName());

    }

}
