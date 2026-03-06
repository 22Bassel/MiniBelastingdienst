package com.example.demo.services;

import com.example.demo.database.Database;
import com.example.demo.models.Users.GewoneUser;
import com.example.demo.models.Users.User;
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
    private Database database;

    @Test
    public void testNiewueUser() {
        // Test data
        User testUser = new GewoneUser(1L, "John", "john@example.com", "password");

        // Setup mock
        when(database.UserToevoegen(testUser)).thenReturn(testUser);

        // Act
        User result = userService.niewueUser(testUser);

        // Assert
        assertNotNull(result);
        assertEquals(testUser, result);
        verify(database, times(1)).UserToevoegen(testUser);
    }

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
    }
}
