package com.example.demo.services.belastingberekenen;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InkomenbelastingberekenenTest {

    @Test
    public void testBerekenen_LowIncome() {
        // Arrange
        Inkomenbelastingberekenen calculator = new Inkomenbelastingberekenen();
        double income = 10000;
        double expectedTax = 2000; // 10000 * 0.2

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_MediumIncome() {
        // Arrange
        Inkomenbelastingberekenen calculator = new Inkomenbelastingberekenen();
        double income = 30000;
        double expectedTax = 9000; // 30000 * 0.3

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_HighIncome() {
        // Arrange
        Inkomenbelastingberekenen calculator = new Inkomenbelastingberekenen();
        double income = 50000;
        double expectedTax = 20000; // 50000 * 0.4

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_VeryHighIncome() {
        // Arrange
        Inkomenbelastingberekenen calculator = new Inkomenbelastingberekenen();
        double income = 70000;
        double expectedTax = 35000; // 70000 * 0.5

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_Boundary20k() {
        // Arrange
        Inkomenbelastingberekenen calculator = new Inkomenbelastingberekenen();
        double income = 20000;
        double expectedTax = 6000; // 20000 * 0.3

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_Boundary40k() {
        // Arrange
        Inkomenbelastingberekenen calculator = new Inkomenbelastingberekenen();
        double income = 40000;
        double expectedTax = 16000; // 40000 * 0.4

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_Boundary60k() {
        // Arrange
        Inkomenbelastingberekenen calculator = new Inkomenbelastingberekenen();
        double income = 60000;
        double expectedTax = 30000; // 60000 * 0.5

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_ZeroIncome() {
        // Arrange
        Inkomenbelastingberekenen calculator = new Inkomenbelastingberekenen();
        double income = 0;
        double expectedTax = 0;

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }

    @Test
    public void testBerekenen_NegativeIncome() {
        // Arrange
        Inkomenbelastingberekenen calculator = new Inkomenbelastingberekenen();
        double income = -5000;
        double expectedTax = 0; // Assumption: No tax for negative income

        // Act
        double actualTax = calculator.berekenen(income);

        // Assert
        assertEquals(expectedTax, actualTax, 0.001);
    }
}
