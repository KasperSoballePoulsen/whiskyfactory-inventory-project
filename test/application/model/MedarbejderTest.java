package application.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedarbejderTest {

    @Test
    public void testAtDerOprettesEnMedarbejder() {
        Medarbejder medarbejder = new Medarbejder("Snavaer");
        assertEquals("Snavaer", medarbejder.getNavn());
    }

    public void testAtDerOprettesEnMedarbejder2() {
        Medarbejder medarbejder = new Medarbejder("Mikkel");
        assertEquals("Mikkel", medarbejder.getNavn());
    }
}
