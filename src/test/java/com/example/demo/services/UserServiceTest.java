package com.example.demo.services;

import com.example.demo.database.UserRepo;
import com.example.demo.enums.Role;
import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.usersDTO.users.RequestNieuweUser;
import com.example.demo.models.usersDTO.users.ResponseUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepo database;

    @Test
    public void testNiewueUser() {
        // Arrange
        RequestNieuweUser request = new RequestNieuweUser("John", "Doe", "john@example.com", "password", Role.User);

        when(database.save(any())).thenReturn(new UserEntity(1L, "John", "Doe", "john@example.com", "password", Role.User,new ArrayList<>()));

        // Act
        ResponseUser result = userService.niewueUser(request);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getVoorName());
        assertEquals("john@example.com", result.getEmail());
        verify(database, times(1)).save(any());
    }
    @Test
    public void testGetUser_UserExists() {
        // Arrange
        Long userId = 1L;
        UserEntity user = new UserEntity(userId, "John", "Doe", "john@example.com", "password", Role.User,new ArrayList<>());
        when(database.findById(userId)).thenReturn(Optional.of(user));

        // Act
        ResponseUser result = userService.GetUser(userId);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getVoorName());
        assertEquals("john@example.com", result.getEmail());
        verify(database, times(1)).findById(userId);
    }
    @Test
    public void testGetUser_UserDoesNotExist() {
        // Arrange
        Long userId = 1L;

        when(database.findById(userId)).thenReturn(Optional.empty());

        // Act
        ResponseUser result = userService.GetUser(userId);

        // Assert
        assertNull(result);
        verify(database, times(1)).findById(userId);
    }

    @Test
    public void testBestondAlDitEmail_EmailExists() {
        // Arrange
        String email = "john@example.com";
        when(database.existsByEmail(email)).thenReturn(true);

        // Act
        boolean result = userService.BestondAlDitEmail(email);

        // Assert
        assertTrue(result);
        verify(database, times(1)).existsByEmail(email);
    }

}
