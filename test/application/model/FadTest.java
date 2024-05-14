package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {
    private Fad fad;
    private ArrayList<Destillat> destillater = new ArrayList<>();
    private Paafyldning paafyldning;
    @BeforeEach
    void setup() {
        Lager lager = new Lager("Container", 10);
        this.fad = lager.createFad("Sherry", 10, "Spanien");
        LocalDate destillationStartdato = LocalDate.of(2024,01,01);
        LocalDate destillationSlutdato = LocalDate.of(2024, 01,14);
        this.destillater.add(new Destillat("Destillat","Byg", destillationStartdato, destillationSlutdato , "Maltdestillat", 11,50,"Snævar"));
        LocalDate datoForPaafyldning = LocalDate.of(2024,01,14);
        this.paafyldning = new Paafyldning(datoForPaafyldning,"Snævar", this.fad, this.destillater);
    }

    @Test
    void TC1AtDerTilføjesEnPaafyldningTilFadet() {
        assertEquals(0, fad.getPaafyldninger().size());
        fad.paafyld(10, this.paafyldning);
        assertEquals(1,fad.getPaafyldninger().size());

        Paafyldning paafyldning = fad.getPaafyldninger().get(0);
        assertEquals(fad, paafyldning.getFad());
        assertEquals(destillater.get(0), paafyldning.getDestillater().get(0));
        assertEquals(LocalDate.of(2024, 01, 14), paafyldning.getDato());
        assertEquals("Snævar", paafyldning.getMedarbejder());
    }
}