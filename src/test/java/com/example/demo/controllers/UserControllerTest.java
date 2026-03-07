package com.example.demo.controllers;

import com.example.demo.models.usersDTO.users.RequestNieuweUser;
import com.example.demo.models.usersDTO.users.ResponseUser;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;


    @Test
    public void NiewueUserTest_EmailBestaatAl() {
        // Arrange
        RequestNieuweUser user = new RequestNieuweUser("a", "b", "b@gmail.com", "c", false);

        // Mock service to return true (email exists)
        when(userService.BestondAlDitEmail(anyString())).thenReturn(true);

        // Act
        ResponseEntity<ResponseUser> response=(ResponseEntity<ResponseUser>)userController.niewueUser(user);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());// Assuming 201 Created
            // Verify service method was called
        verify(userService, times(1)).BestondAlDitEmail("b@gmail.com");
    }

    @Test
    public void NiewueUserTest_NieuweUserToevoegen() {
        // Arrange
        RequestNieuweUser requestUser = new RequestNieuweUser("a", "b", "b@gmail.com", "c", false);
        ResponseUser expectedUser = new ResponseUser(1L, "a", "b", "b@gmail.com", false,new ArrayList<>());

        // Mock service to return false (email does not exist)
        when(userService.BestondAlDitEmail(anyString())).thenReturn(false);
        // Mock service to return the created user
        when(userService.niewueUser(requestUser)).thenReturn(expectedUser);

        // Act
        ResponseEntity<ResponseUser> response=(ResponseEntity<ResponseUser>)userController.niewueUser(requestUser);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());// Assuming 201 Created
        assertEquals(expectedUser.getVoorName(), response.getBody().getVoorName());
        assertEquals(expectedUser.getEmail(), response.getBody().getEmail());

        // Verify service methods were called
        verify(userService, times(1)).BestondAlDitEmail("b@gmail.com");
        verify(userService, times(1)).niewueUser(requestUser);
    }

}
