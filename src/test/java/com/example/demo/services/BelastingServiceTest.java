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
import java.util.Arrays;
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



    @Test
    public void testGetBelastingen_Success() {
        // Arrange
        Long userId = 1L;
        UserEntity user = new UserEntity();
        user.setId(userId);

        BelastingEntity belasting1 = new BelastingEntity();
        belasting1.setBelastingsoort("Inkomen");
        belasting1.setBelastingJaar(2023);
        belasting1.setInkomen(50000.0);
        belasting1.setBelastingBedrag(10000.0);

        BelastingEntity belasting2 = new BelastingEntity();
        belasting2.setBelastingsoort("Inkomen");
        belasting2.setBelastingJaar(2024);
        belasting2.setInkomen(60000.0);
        belasting2.setBelastingBedrag(12000.0);

        user.setBelastingen(Arrays.asList(belasting1, belasting2));

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        // Act
        List<ResponseBelasting> result = belastingService.GetBelastingen(userId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Inkomen", result.get(0).getBelastingsoort());
        assertEquals(2023, result.get(0).getBelastingJaar());
        assertEquals(60000.0, result.get(1).getInkomem());
        assertEquals(12000.0, result.get(1).getBelastingBedrag());
    }



    @Test
    public void testGetBelastingeninJaar_Success() {
        // Arrange
        Long userId = 1L;
        int jaar = 2023;

        UserEntity user = new UserEntity();
        user.setId(userId);

        BelastingEntity belasting1 = new BelastingEntity();
        belasting1.setBelastingsoort("Inkomen");
        belasting1.setBelastingJaar(jaar);
        belasting1.setInkomen(50000.0);
        belasting1.setBelastingBedrag(10000.0);

        BelastingEntity belasting2 = new BelastingEntity();
        belasting2.setBelastingsoort("Inkomen");
        belasting2.setBelastingJaar(2024);
        belasting2.setInkomen(60000.0);
        belasting2.setBelastingBedrag(12000.0);

        user.setBelastingen(Arrays.asList(belasting1,belasting2));

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        // Act
        List<ResponseBelasting> result = belastingService.GetBelastingeninJaar(userId, jaar);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Inkomen", result.get(0).getBelastingsoort());
        assertEquals(jaar, result.get(0).getBelastingJaar());
        assertEquals(50000.0, result.get(0).getInkomem());
        assertEquals(10000.0, result.get(0).getBelastingBedrag());
    }

    @Test
    public void testGetBelastingeninJaar_GebruikerHeeftGeenBelastingenInJaar() {
        // Arrange
        Long userId = 1L;
        int jaar = 2023;

        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setBelastingen(new ArrayList<>());

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        // Act
        List<ResponseBelasting> result = belastingService.GetBelastingeninJaar(userId, jaar);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
