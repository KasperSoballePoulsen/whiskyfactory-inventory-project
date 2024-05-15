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
    private Lager lager;

    @BeforeEach
    void setup() {
        this.lager = new Lager("Container", 10);
        this.fad = lager.createFad("Sherry", 10, "Spanien");
        LocalDate destillationStartdato = LocalDate.of(2024,01,01);
        LocalDate destillationSlutdato = LocalDate.of(2024, 01,14);
        this.destillater.add(new Destillat("Destillat","Byg", destillationStartdato, destillationSlutdato , "Maltdestillat", 11,50,"Snævar"));
        LocalDate datoForPaafyldning = LocalDate.of(2024,01,14);
        this.paafyldning = new Paafyldning(datoForPaafyldning,"Snævar", this.fad, this.destillater);
    }

    @Test
    void TC1AtDerOprettesEtFad() {
        Fad fad1 = new Fad("Sherry", 10, lager, "Spanien");
        assertEquals(2, fad1.getNummer());
        assertEquals("Sherry", fad1.getType());
        assertEquals(10, fad1.getLiterKapacitet());
        assertEquals(0, fad1.getAntalLiterPaafyldt());
        assertEquals("Spanien", fad1.getLeverandoer());
        assertEquals(lager, fad1.getLager());
        assertEquals(0, fad1.getTapninger().size());
        assertEquals(0, fad1.getPaafyldninger().size());
    }

    @Test
    void TC1AtDerReturneresNummer() {
        assertEquals(1, fad.getNummer());
    }

    @Test
    void TC1AtDerReturneresType() {
        assertEquals("Sherry", fad.getType());
    }

    @Test
    void TC1AtDerReturneresLiterKapacitet() {
        assertEquals(10, fad.getLiterKapacitet());
    }

    @Test
    void TC1AtDerReturneresLager() {
        assertEquals(lager, fad.getLager());
    }



    @Test
    void TC1AtDerTilknyttesEnPaafyldningTilFadet() {
        assertEquals(0, fad.getPaafyldninger().size());
        assertEquals(0, fad.getAntalLiterPaafyldt());
        fad.paafyld(5, this.paafyldning);
        assertEquals(1, fad.getPaafyldninger().size());
        assertEquals(5,fad.getAntalLiterPaafyldt());
        Paafyldning paafyldning = fad.getPaafyldninger().get(0);
        assertEquals(fad, paafyldning.getFad());
        assertEquals(destillater.get(0), paafyldning.getDestillater().get(0));
        assertEquals(LocalDate.of(2024, 01, 14), paafyldning.getDato());
        assertEquals("Snævar", paafyldning.getMedarbejder());
    }


    @Test
    void TC2AtDerTilknyttesEnPaafyldningTilFadet() {
        assertEquals(0, fad.getPaafyldninger().size());
        assertEquals(0, fad.getAntalLiterPaafyldt());
        fad.paafyld(1, this.paafyldning);
        assertEquals(1, fad.getPaafyldninger().size());
        assertEquals(1,fad.getAntalLiterPaafyldt());
        Paafyldning paafyldning = fad.getPaafyldninger().get(0);
        assertEquals(fad, paafyldning.getFad());
        assertEquals(destillater.get(0), paafyldning.getDestillater().get(0));
        assertEquals(LocalDate.of(2024, 01, 14), paafyldning.getDato());
        assertEquals("Snævar", paafyldning.getMedarbejder());
    }

    @Test
    void TC3AtDerTilknyttesEnPaafyldningTilFadet() {
        assertEquals(0, fad.getPaafyldninger().size());
        assertEquals(0, fad.getAntalLiterPaafyldt());
        fad.paafyld(10, this.paafyldning);
        assertEquals(1, fad.getPaafyldninger().size());
        assertEquals(10,fad.getAntalLiterPaafyldt());
        Paafyldning paafyldning = fad.getPaafyldninger().get(0);
        assertEquals(fad, paafyldning.getFad());
        assertEquals(destillater.get(0), paafyldning.getDestillater().get(0));
        assertEquals(LocalDate.of(2024, 01, 14), paafyldning.getDato());
        assertEquals("Snævar", paafyldning.getMedarbejder());
    }

    @Test
    void TC4AtDerKastesEnException() {
        assertEquals(0, fad.getPaafyldninger().size());
        assertEquals(0, fad.getAntalLiterPaafyldt());
        assertThrows(IllegalArgumentException.class, () -> fad.paafyld(11, this.paafyldning));
    }

    @Test
    void TC5AtDerKastesEnException() {
        assertEquals(0, fad.getPaafyldninger().size());
        assertEquals(0, fad.getAntalLiterPaafyldt());
        assertThrows(IllegalArgumentException.class, () -> fad.paafyld(20, this.paafyldning));
    }




}