package com.example.demo.services;

import com.example.demo.database.BelastingRepo;
import com.example.demo.database.UserRepo;
import com.example.demo.models.entities.BelastingEntity;
import com.example.demo.models.entities.UserEntity;
import com.example.demo.models.usersDTO.belasting.ResponseBelasting;
import com.example.demo.models.usersDTO.users.ResponseUser;
import com.example.demo.services.belastingberekenen.Inkomenbelastingberekenen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BelastingServiceTest {

    @InjectMocks
    private BelastingService belastingService;

    @Mock
    private BelastingRepo belastingRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private Inkomenbelastingberekenen inkomenbelastingberekenen;

    @Test
    public void testNieuweInkomenBelastingToevoegen() {
        // Arrange
        Long userId = 1L;
        double inkomen = 50000.0;
        int jaar = 2023;

        UserEntity user = new UserEntity();
        user.setId(userId);

        // Mock userRepo to return the user
        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        // Mock berekenen method to return a fixed value
        when(inkomenbelastingberekenen.berekenen(inkomen)).thenReturn(10000.0);

        // Act
        List<ResponseBelasting> result = belastingService.NieuweInkomenBelastingToevoegen(userId, inkomen, jaar);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());

        // Verify interactions
        verify(userRepo, times(1)).findById(userId);
        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void testBestondAlInkomenBelasting_BelastingBestaatNiet() {
        // Arrange
        Long userId = 1L;
        int jaar = 2023;

        // Mock repository to return false (no existing tax)
        when(belastingRepo.existsByUserIdAndBelastingJaarAndBelastingsoort(userId, jaar, "Inkomen")).thenReturn(false);

        // Act
        boolean result = belastingService.BestondAlInkomenBelasting(userId, jaar);

        // Assert
        assertFalse(result);
        verify(belastingRepo, times(1)).existsByUserIdAndBelastingJaarAndBelastingsoort(userId, jaar, "Inkomen");
    }

    @Test
    public void testBestondAlInkomenBelasting_BelastingBestaatAl() {
        // Arrange
        Long userId = 1L;
        int jaar = 2023;

        // Mock repository to return true (tax exists)
        when(belastingRepo.existsByUserIdAndBelastingJaarAndBelastingsoort(userId, jaar, "Inkomen")).thenReturn(true);

        // Act
        boolean result = belastingService.BestondAlInkomenBelasting(userId, jaar);

        // Assert
        assertTrue(result);
        verify(belastingRepo, times(1)).existsByUserIdAndBelastingJaarAndBelastingsoort(userId, jaar, "Inkomen");
    }

    @Test
    public void testNieuweInkomenBelastingToevoegen_InvalidInput_NegatiefInkomen() {
        Long userId = 1L;
        double inkomen = 50000.0;
        int jaar = 2023;

        UserEntity user = new UserEntity();
        user.setId(userId);

        // Mock userRepo to return the user
        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        // Mock tax calculation to return a fixed value
        when(inkomenbelastingberekenen.berekenen(inkomen)).thenReturn(10000.0);

        // Act
        List<ResponseBelasting> result = belastingService.NieuweInkomenBelastingToevoegen(userId, inkomen, jaar);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());

        // Verify tax calculation was used
        assertEquals(10000.0, result.get(0).getBelastingBedrag(), 0.01);

        // Verify interactions
        verify(userRepo, times(1)).findById(userId);
        verify(userRepo, times(1)).save(user);
    }
}
