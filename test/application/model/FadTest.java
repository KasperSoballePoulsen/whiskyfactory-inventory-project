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
    void TC1AtDerReturneresAntalLiterPaafyldt() {
        fad.paafyld(5, this.paafyldning);
        assertEquals(5, fad.getAntalLiterPaafyldt());
    }

    @Test
    void TC1AtDerReturneresLeverandoer() {
        assertEquals("Spanien", fad.getLeverandoer());
    }

    @Test
    void TC1AtDerReturneresPaafyldninger() {
        fad.paafyld(10, paafyldning);
        assertEquals(paafyldning, fad.getPaafyldninger().get(0));
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
    void TC4AtPaafyldningKasterException() {
        assertEquals(0, fad.getPaafyldninger().size());
        assertEquals(0, fad.getAntalLiterPaafyldt());
        assertThrows(IllegalArgumentException.class, () -> fad.paafyld(11, this.paafyldning));
    }

    @Test
    void TC5AtPaafyldningKasterException() {
        assertEquals(0, fad.getPaafyldninger().size());
        assertEquals(0, fad.getAntalLiterPaafyldt());
        assertThrows(IllegalArgumentException.class, () -> fad.paafyld(20, this.paafyldning));
    }


    @Test
    void TC1AtDerTilknyttesEnTapningTilFadet() {
        fad.paafyld(5, paafyldning);
        assertEquals(0, fad.getTapninger().size());
        assertEquals(5, fad.getAntalLiterPaafyldt());
        Tapning tapning = new Tapning(LocalDate.of(2027,01,14), "Snævar");
        fad.aftap(4, tapning);
        assertEquals(tapning, fad.getTapninger().get(0));
        assertEquals(1, fad.getTapninger().size());
        assertEquals(1, fad.getAntalLiterPaafyldt());
    }

    @Test
    void TC2AtTapningKasterException() {
        fad.paafyld(5, paafyldning);
        assertEquals(0, fad.getTapninger().size());
        assertEquals(5, fad.getAntalLiterPaafyldt());
        Tapning tapning = new Tapning(LocalDate.of(2027,01,13), "Snævar");
        assertThrows(IllegalArgumentException.class, () -> fad.aftap(4, tapning));
        assertEquals(0, fad.getTapninger().size());
        assertEquals(5, fad.getAntalLiterPaafyldt());
    }

    @Test
    void TC3AtTapningKasterException() {
        fad.paafyld(5, paafyldning);
        assertEquals(0, fad.getTapninger().size());
        assertEquals(5, fad.getAntalLiterPaafyldt());
        Tapning tapning = new Tapning(LocalDate.of(2027,01,14), "Snævar");
        assertThrows(IllegalArgumentException.class, () -> fad.aftap(6, tapning));
        assertEquals(0, fad.getTapninger().size());
        assertEquals(5, fad.getAntalLiterPaafyldt());
    }

    @Test
    void TC1AtDerReturneresTapninger() {
        fad.paafyld(5, paafyldning);
        assertEquals(0, fad.getTapninger().size());
        Tapning tapning = new Tapning(LocalDate.of(2027,01,14), "Snævar");
        fad.aftap(5, tapning);
        assertEquals(1, fad.getTapninger().size());
        assertEquals(tapning, fad.getTapninger().get(0));
    }

    @Test
    void TC1AtTapningTilføjes() {
        Tapning tapning = new Tapning(LocalDate.of(2027,01,14), "Snævar");
        assertEquals(0, fad.getTapninger().size());
        assertEquals(0, tapning.getFade().size());
        fad.addTapning(tapning);
        assertEquals(1, fad.getTapninger().size());
        assertEquals(1, tapning.getFade().size());
        assertEquals(tapning, fad.getTapninger().get(0));
        assertEquals(fad, tapning.getFade().get(0));
    }

    @Test
    void TC1AtTapningFjernes() {
        Tapning tapning = new Tapning(LocalDate.of(2027,01,14), "Snævar");
        assertEquals(0, fad.getTapninger().size());
        assertEquals(0, tapning.getFade().size());
        fad.addTapning(tapning);
        assertEquals(1, fad.getTapninger().size());
        assertEquals(1, tapning.getFade().size());
        assertEquals(tapning, fad.getTapninger().get(0));
        assertEquals(fad, tapning.getFade().get(0));
        fad.removeTapning(tapning);
        assertEquals(0, fad.getTapninger().size());
        assertEquals(0, tapning.getFade().size());
    }

    @Test
    void testAtTomtFadPrinterHistorik(){
        String expected = "fad: 1\n" +
                "type: Sherry\n" +
                "leverandør: Spanien\n" +
                "lå på lager: Container\n";
        assertEquals(expected, fad.historik());
    }

    @Test
    void testAtFyldtFadPrinterHistorik(){
        String expected = "Destilat: Destillat\n" +
                "Korn sort: Byg\n" +
                "Malt destilat: Maltdestillat\n" +
                "Startede destillation: 2024-01-01 og sluttede: 2024-01-14\n" +
                "--------------------------\n" +
                "blev fyldt: 2024-01-14\n" +
                "fad: 1\n" +
                "type: Sherry\n" +
                "leverandør: Spanien\n" +
                "lå på lager: Container\n";
        fad.paafyld(5,paafyldning);
        assertEquals(expected, fad.historik());
    }


}