package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {

    private Medarbejder medarbejder;
    private Destilat destilat;


    @BeforeEach
    void setup() {
        medarbejder = new Medarbejder("Rasmus");
        destilat = new Destilat("Destilat1", "Byg", LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 02), "1", 37, 50.50, medarbejder);
    }


    @Test
    void testAtAlleVaerdierBliverOprettet() {
        Fad fad = new Fad(1, "Bourbon", 37, "Sall", "USA");
        assertEquals(1, fad.getNummer());
        assertEquals("Bourbon", fad.getType());
        assertEquals(37, fad.getStoerrelse());
        assertEquals("Sall", fad.getLager());
        assertEquals("USA", fad.getLeverandoer());
    }

    @Test
    void testAtFadetFaarSineVaerdierPaafyld() {
        Fad fad = new Fad(1, "Bourbon", 37, "Sall", "USA");
        assertEquals(null, fad.getDestilat());
        assertEquals(0, fad.getAntalLiterPaafyldt());
        assertEquals(null, fad.getPaafylder());

        fad.paafyld(medarbejder, destilat, LocalDate.of(2024, 01, 03), 37);
        assertEquals(destilat, fad.getDestilat());
        assertEquals(37, fad.getAntalLiterPaafyldt());
        assertEquals(medarbejder, fad.getPaafylder());
    }

    @Test
    void testAtFadIkkeKanOverfyldes() {
        Fad fad = new Fad(1, "Bourbon", 37, "Sall", "USA");
        assertThrows(IllegalArgumentException.class, () -> fad.paafyld(medarbejder, destilat, LocalDate.of(2024, 01, 03), 38));
    }
}