package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {
    private Fad fad;
    private Destillat destillat;
    private Paafyldning paafyldning;
    @BeforeEach
    void setup() {
        Lager lager = new Lager("Container", 10);
        this.fad = lager.createFad("Sherry", 10, "Spanien");
        this.destillat = new Destillat("Destillat","Byg", LocalDate.of(2024,01,01), LocalDate.of(2024, 01,14), "Maltdestillat", 11,50,"Snævar");
        this.paafyldning = new Paafyldning(LocalDate.of(2024,01,14),"Snævar", this.fad);
    }

    @Test
    void TC1AtDerTilføjesEnPaafyldningTilFadet() {
        assertEquals(0, fad.getPaafyldninger().size());
        fad.paafyld(10, this.paafyldning);
        assertEquals(1,fad.getPaafyldninger().size());

        Paafyldning paafyldning = fad.getPaafyldninger().get(0);
        assertEquals(fad, paafyldning.getFad());
        assertEquals(destillat, paafyldning.getDestillater().get(0));
        assertEquals(LocalDate.of(2024, 01, 14), paafyldning.getDato());
        assertEquals("Snævar", paafyldning.getMedarbejder());
    }


}