package com.example.demo.services;


import com.example.demo.services.belastingberekenen.Inkomenbelastingberekenen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BelastingServiceTest {
/*
    @InjectMocks
    private BelastingService belastingService;

    @Mock
    private Database database;

    @Mock
    private GewoneUser gewoneUser;

    @Mock
    private Inkomenbelastingberekenen inkomenbelastingberekenen;




    @Test
    public void testNieuweInkomenBelastingToevoegen() {
        // Test data
        Long userId = 1L;
        double inkomen = 50000.0;
        int jaar = 2023;

        // Setup mock
        when(database.UserOphalenMetID(userId)).thenReturn(gewoneUser);

        // Act
        belastingService.NieuweInkomenBelastingToevoegen(userId, inkomen, jaar);



        // Verifieer database interacties
        verify(database, times(1)).UserOphalenMetID(userId);
        verify(gewoneUser, times(1)).nieuweBelastingMetJaar(any(Belasting.class));
        verify(database, times(1)).UserBijwerken(gewoneUser);
    }


    @Test
    public void testBestondAlInkomenBelasting_WanneerGeenBelastingen() {
        // Test data
        Long userId = 1L;
        int jaar = 2023;

        // Setup mock
        when(database.UserOphalenMetID(userId)).thenReturn(gewoneUser);
        when(gewoneUser.getBelastingListMetJaar(jaar)).thenReturn(new ArrayList<>());

        // Act
        boolean result = belastingService.BestondAlInkomenBelasting(userId, jaar);

        // Assert
        assertFalse(result);
        verify(database, times(1)).UserOphalenMetID(userId);
    }*/
}

