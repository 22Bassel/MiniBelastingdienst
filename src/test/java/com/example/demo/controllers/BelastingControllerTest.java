package com.example.demo.controllers;

import com.example.demo.models.usersDTO.belasting.ResponseBelasting;
import com.example.demo.services.BelastingService;
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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BelastingControllerTest {

    @InjectMocks
    BelastingController belastingController;

    @Mock
    BelastingService belastingService;

    @Mock
    UserService userService;


    @Test
    public void NieuweInkomenBelastingTest_BelastingBestaatAl() {
        // Arrange
        Long userId = 1L;
        int jaar = 2023;

        // Mock service to return true (tax exists)
        when(belastingService.BestondAlInkomenBelasting(userId, jaar)).thenReturn(true);

        // Act & Assert
        ResponseEntity<?> response = belastingController.NieuweInkomenBelasting(userId, 50000.0, jaar);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(Map.of("message", "De belasting in dit jaar bestond al!!"), response.getBody());

        // Verify service method was called
        verify(belastingService, times(1)).BestondAlInkomenBelasting(userId, jaar);
    }

    @Test
    public void NieuweInkomenBelastingTest_BelastingToegevoegd() {
        // Arrange
        Long userId = 1L;
        int jaar = 2023;
        double inkomen = 50000.0;

        // Mock service to return false (tax does not exist)
        when(belastingService.BestondAlInkomenBelasting(userId, jaar)).thenReturn(false);

        // Create mock response
        ResponseBelasting belasting = new ResponseBelasting("Inkomen", 2023, 50000.0, 10000.0);
        List<ResponseBelasting> belastingen = new ArrayList<>();
        belastingen.add(belasting);

        // Mock service to return the updated list
        when(belastingService.NieuweInkomenBelastingToevoegen(userId, inkomen, jaar)).thenReturn(belastingen);

        // Act
        ResponseEntity<?> response = belastingController.NieuweInkomenBelasting(userId, inkomen, jaar);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(belastingen, response.getBody());

        // Verify service methods were called
        verify(belastingService, times(1)).BestondAlInkomenBelasting(userId, jaar);
        verify(belastingService, times(1)).NieuweInkomenBelastingToevoegen(userId, inkomen, jaar);
    }


    @Test
    public void testGetAlleBelastingVanDezeUser_Success() {
        // Arrange
        Long userId = 1L;
        List<ResponseBelasting> mockBelastingen = new ArrayList<>();
               mockBelastingen.add(new ResponseBelasting("Inkomen", 2023, 50000.0, 10000.0));
               mockBelastingen.add(new ResponseBelasting("Inkomen", 2024, 60000.0, 12000.0));

        when(userService.BestondAlDezeUser(userId)).thenReturn(true);
        when(belastingService.GetBelastingen(userId)).thenReturn(mockBelastingen);

        // Act
        ResponseEntity<List<ResponseBelasting>> response = (ResponseEntity<List<ResponseBelasting>>) belastingController.getAlleBelastingVanDezeUser(userId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBelastingen, response.getBody());
        verify(belastingService, times(1)).GetBelastingen(userId);
    }

    @Test
    public void testGetAlleBelastingVanDezeUser_UserNotFound() {
        // Arrange
        Long userId = 1L;
        when(userService.BestondAlDezeUser(userId)).thenReturn(false);

        // Act
        ResponseEntity<List<ResponseBelasting>> response = (ResponseEntity<List<ResponseBelasting>>) belastingController.getAlleBelastingVanDezeUser(userId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(belastingService, times(0)).GetBelastingen(userId);
    }

    @Test
    public void testGetBelastingenVanDezeUserInJaar_Success() {
        // Arrange
        Long userId = 1L;
        int jaar = 2023;
        List<ResponseBelasting> mockBelastingen = Arrays.asList(
                new ResponseBelasting("Inkomen", 2023, 50000.0, 10000.0)
        );
        when(userService.BestondAlDezeUser(userId)).thenReturn(true);
        when(belastingService.GetBelastingeninJaar(userId, jaar)).thenReturn(mockBelastingen);

        // Act
        ResponseEntity<List<ResponseBelasting>> response = (ResponseEntity<List<ResponseBelasting>>) belastingController.getBelastingenVanDezeUserInJaar(userId, jaar);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockBelastingen, response.getBody());
        verify(belastingService, times(1)).GetBelastingeninJaar(userId, jaar);
    }

    @Test
    public void testGetBelastingenVanDezeUserInJaar_BelastingenNotFound() {
        // Arrange
        Long userId = 1L;
        int jaar = 2023;

        when(userService.BestondAlDezeUser(userId)).thenReturn(false);

        // Act
        ResponseEntity<List<ResponseBelasting>> response = (ResponseEntity<List<ResponseBelasting>>) belastingController.getBelastingenVanDezeUserInJaar(userId, jaar);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(belastingService, times(0)).GetBelastingeninJaar(userId, jaar);
    }



}
