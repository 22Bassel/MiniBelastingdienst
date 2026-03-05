package com.example.demo.services.belastingberekenen;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class InkomenbelastingberekenenTest {

    @InjectMocks
    Inkomenbelastingberekenen calculator;

    @Test
    public void testBerekenen_LowIncome() {
        // Arrange

        double income = 10000;
        double expectedTax = 2000; // 10000 * 0.2

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_MediumIncome() {

        double income = 30000;
        double expectedTax = 9000; // 30000 * 0.3

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_HighIncome() {

        double income = 50000;
        double expectedTax = 20000; // 50000 * 0.4

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_VeryHighIncome() {

        double income = 70000;
        double expectedTax = 35000; // 70000 * 0.5

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_Boundary20k() {

        double income = 20000;
        double expectedTax = 6000; // 20000 * 0.3

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_Boundary40k() {

        double income = 40000;
        double expectedTax = 16000; // 40000 * 0.4

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_Boundary60k() {

        double income = 60000;
        double expectedTax = 30000; // 60000 * 0.5

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_ZeroIncome() {

        double income = 0;
        double expectedTax = 0;

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_NegativeIncome() {

        double income = -5000;
        double expectedTax = 0; // Assumption: No tax for negative income

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }
}
