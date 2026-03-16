package com.example.demo.controllers;

import com.example.demo.enums.Role;
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
import java.util.Arrays;
import java.util.List;

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
        RequestNieuweUser user = new RequestNieuweUser("a", "b", "b@gmail.com", "c", Role.User);

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
        RequestNieuweUser requestUser = new RequestNieuweUser("a", "b", "b@gmail.com", "c", Role.User);
        ResponseUser expectedUser = new ResponseUser(1L, "a", "b", "b@gmail.com", Role.User,new ArrayList<>());

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


    @Test
    public void testGetAllUsers_Success() {
        // Arrange
        List<ResponseUser> mockUsers = Arrays.asList(
                new ResponseUser(1L, "John", "Doe", "john@example.com", Role.User, new ArrayList<>()),
                new ResponseUser(2L, "Jane", "Smith", "jane@example.com", Role.User, new ArrayList<>())
        );

        when(userService.GetAlleUsers()).thenReturn(mockUsers);

        // Act
        ResponseEntity<List<ResponseUser>> response = (ResponseEntity<List<ResponseUser>>) userController.getAlleUsers();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsers, response.getBody());
        verify(userService, times(1)).GetAlleUsers();
    }


    @Test
    public void testDeleteUser_Success() {
        // Arrange
        Long userId = 1L;
        when(userService.BestondAlDezeUser(userId)).thenReturn(true);

        // Act
        ResponseEntity<?> response = userController.deleteUser(userId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).DeleteUser(userId);
    }

    @Test
    public void testDeleteUser_UserNotFound() {
        // Arrange
        Long userId = 1L;
        when(userService.BestondAlDezeUser(userId)).thenReturn(false);

        // Act
        ResponseEntity<?> response = userController.deleteUser(userId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(0)).DeleteUser(userId);
    }

}
