package com.example.demo.models;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.models.UsersDTO.GewoneUser;

import java.util.List;

public class GewoneUserTest {

    private GewoneUser user;

    @BeforeEach
    void setUp() {
        user = new GewoneUser(1L, "Test User", "test@example.com", "password");
    }

    @Test
    void testNieuweBelastingMetJaar_NieuwJaar() {
        // Arrange
        Belasting belasting = mock(Belasting.class);
        when(belasting.getBelastingJaar()).thenReturn(2023);

        // Act
        user.nieuweBelastingMetJaar(belasting);

        // Assert
        List<Belasting> list = user.getBelastingListMetJaar(2023);
        assertNotNull(list);
        assertEquals(1, list.size());
        assertTrue(list.contains(belasting));
    }

    @Test
    void testNieuweBelastingMetJaar_BestondAl() {
        // Arrange
        Belasting belasting1 = mock(Belasting.class);
        when(belasting1.getBelastingJaar()).thenReturn(2023);
        Belasting belasting2 = mock(Belasting.class);
        when(belasting2.getBelastingJaar()).thenReturn(2023);

        // Act
        user.nieuweBelastingMetJaar(belasting1);
        user.nieuweBelastingMetJaar(belasting2);

        // Assert
        List<Belasting> list = user.getBelastingListMetJaar(2023);
        assertNotNull(list);
        assertEquals(2, list.size());
        assertTrue(list.contains(belasting1));
        assertTrue(list.contains(belasting2));
    }

    @Test
    void testNieuweBelastingMetJaar_VerschillendeJaren() {
        // Arrange
        Belasting belasting2023 = mock(Belasting.class);
        when(belasting2023.getBelastingJaar()).thenReturn(2023);
        Belasting belasting2024 = mock(Belasting.class);
        when(belasting2024.getBelastingJaar()).thenReturn(2024);

        // Act
        user.nieuweBelastingMetJaar(belasting2023);
        user.nieuweBelastingMetJaar(belasting2024);

        // Assert
        List<Belasting> list2023 = user.getBelastingListMetJaar(2023);
        List<Belasting> list2024 = user.getBelastingListMetJaar(2024);

        assertNotNull(list2023);
        assertNotNull(list2024);
        assertEquals(1, list2023.size());
        assertEquals(1, list2024.size());
        assertTrue(list2023.contains(belasting2023));
        assertTrue(list2024.contains(belasting2024));
    }
}
