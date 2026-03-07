package com.example.demo.services;

import com.example.demo.database.Database;
import com.example.demo.database.UserRepo;
import com.example.demo.models.UsersDTO.GewoneUser;
import com.example.demo.models.UsersDTO.RequestNieuweUser;
import com.example.demo.models.UsersDTO.ResponseUser;
import com.example.demo.models.UsersDTO.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        // Test data
        RequestNieuweUser testUser = new RequestNieuweUser("Sam", "John", "john@example.com", "password",false);

        // Setup mock
        when(database.save(any())).thenReturn(testUser);

        // Act
        ResponseUser result = userService.niewueUser(testUser);

        // Assert
        assertNotNull(result);
        assertEquals(testUser, result);
        verify(database, times(1)).save(any());
    }
/*
    @Test
    public void testGetUser() {
        // Test data
        Long userId = 1L;
        User expectedUser = new GewoneUser(userId, "Jane", "jane@example.com", "password");

        // Setup mock
        when(database.UserOphalenMetID(userId)).thenReturn(expectedUser);

        // Act
        User result = userService.GetUser(userId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedUser, result);
        verify(database, times(1)).UserOphalenMetID(userId);
    }

    @Test
    public void testGetUser_WhenUserDoesNotExist() {
        // Test data
        Long userId = 1L;

        // Setup mock
        when(database.UserOphalenMetID(userId)).thenReturn(null);

        // Act
        User result = userService.GetUser(userId);

        // Assert
        assertNull(result);
        verify(database, times(1)).UserOphalenMetID(userId);
    }

    @Test
    public void testBestondAlDitEmail_EmailExists() {
        // Test data
        String email = "existing@example.com";

        // Setup mock
        when(database.Emailzoeken(email)).thenReturn(true);

        // Act
        boolean result = userService.BestondAlDitEmail(email);

        // Assert
        assertTrue(result);
        verify(database, times(1)).Emailzoeken(email);
    }

    @Test
    public void testBestondAlDitEmail_EmailDoesNotExist() {
        // Test data
        String email = "new@example.com";

        // Setup mock
        when(database.Emailzoeken(email)).thenReturn(false);

        // Act
        boolean result = userService.BestondAlDitEmail(email);

        // Assert
        assertFalse(result);
        verify(database, times(1)).Emailzoeken(email);
    }*/
}
