package com.example.demo.controllers;

import com.example.demo.models.UsersDTO.GewoneUser;
import com.example.demo.models.UsersDTO.RequestNieuweUser;
import com.example.demo.models.UsersDTO.ResponseUser;
import com.example.demo.models.UsersDTO.User;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

        RequestNieuweUser user=new RequestNieuweUser("a","b","b@gmail.com","c",false);

        Mockito.when(userService.BestondAlDitEmail(anyString()))
               .thenReturn(true);

        IllegalStateException exception=assertThrows(IllegalStateException.class,()->
        System.out.println(userController.niewueUser(user)));

      assertEquals("Er bestaat al deze email",exception.getMessage());

    }


    @Test
    public void NiewueUserTestTwee(){ // Nieuwe user toevoegen


        RequestNieuweUser user=new RequestNieuweUser("a","b","b@gmail.com","c",false);
        ResponseUser user2=new ResponseUser(1L,"a","b","b@gmail.com",false);

        Mockito.when(userService.BestondAlDitEmail(anyString()))
                .thenReturn(false);

        Mockito.when(userService.niewueUser(user))
                .thenReturn(user2);

        assertEquals(user.getVoorName(),userController.niewueUser(user).getBody().getVoorName());

    }

}
