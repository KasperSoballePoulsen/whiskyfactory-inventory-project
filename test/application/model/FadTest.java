package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {

    private Medarbejder medarbejder;
    private Batch batch;


    @BeforeEach
    void setup() {
        medarbejder = new Medarbejder("Rasmus");
        batch = new Batch("Batch1", "Byg", LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 02), "1", 37, 50.50, medarbejder);
    }


    @Test
    void TestAtAlleVaerdierBliverOprettet() {
        Fad fad = new Fad(1, "Bourbon", 37, "Sall", "USA");
        assertEquals(1, fad.getNummer());
        assertEquals("Bourbon", fad.getType());
        assertEquals(37, fad.getStoerrelse());
        assertEquals("Sall", fad.getLager());
        assertEquals("USA", fad.getLeverandoer());
    }

    @Test
    void TestAtFadetFaarSineVaerdierPaafyld() {
        Fad fad = new Fad(1, "Bourbon", 37, "Sall", "USA");
        assertEquals(null, fad.getBatch());
        assertEquals(0, fad.getAntalLiterPaafyldt());
        assertEquals(null, fad.getPaafylder());

        fad.paafyld(medarbejder, batch, LocalDate.of(2024, 01, 03), 37);
        assertEquals(batch, fad.getBatch());
        assertEquals(37, fad.getAntalLiterPaafyldt());
        assertEquals(medarbejder, fad.getPaafylder());
    }

    @Test
    void TestAtFadIkkeKanOverfyldes() {
        Fad fad = new Fad(1, "Bourbon", 37, "Sall", "USA");
        assertThrows(IllegalArgumentException.class, () -> fad.paafyld(medarbejder, batch, LocalDate.of(2024, 01, 03), 38));
    }
}