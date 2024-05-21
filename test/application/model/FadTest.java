package application.model;

import application.controller.Controller;
import org.junit.jupiter.api.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Vi antager at alle test køres på en gang
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
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
    @Order(2)
    void TC1AtDerReturneresNummer() {
        assertEquals(3, fad.getNummer());
    }

    @Test
    @Order(3)
    void TC1AtDerReturneresType() {
        assertEquals("Sherry", fad.getType());
    }

    @Test
    @Order(4)
    void TC1AtDerReturneresLiterKapacitet() {
        assertEquals(10, fad.getLiterKapacitet());
    }

    @Test
    @Order(5)
    void TC1AtDerReturneresLager() {
        assertEquals(lager, fad.getLager());
    }

    @Test
    @Order(5)
    void TC1AtDerReturneresAntalLiterPaafyldt() {
        fad.paafyld(5, this.paafyldning);
        assertEquals(5, fad.getAntalLiterPaafyldt());
    }

    @Test
    @Order(6)
    void TC1AtDerReturneresLeverandoer() {
        assertEquals("Spanien", fad.getLeverandoer());
    }

    @Test
    @Order(7)
    void TC1AtDerReturneresPaafyldninger() {
        fad.paafyld(10, paafyldning);
        assertEquals(paafyldning, fad.getPaafyldninger().get(0));
    }


    @Test
    @Order(8)
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
    @Order(9)
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
    @Order(10)
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
    @Order(11)
    void TC4AtPaafyldningKasterException() {
        assertEquals(0, fad.getPaafyldninger().size());
        assertEquals(0, fad.getAntalLiterPaafyldt());
        assertThrows(IllegalArgumentException.class, () -> fad.paafyld(11, this.paafyldning));
    }

    @Test
    @Order(12)
    void TC5AtPaafyldningKasterException() {
        assertEquals(0, fad.getPaafyldninger().size());
        assertEquals(0, fad.getAntalLiterPaafyldt());
        assertThrows(IllegalArgumentException.class, () -> fad.paafyld(20, this.paafyldning));
    }


    @Test
    @Order(13)
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
    @Order(14)
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
    @Order(15)
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
    @Order(16)
    void TC1AtDerReturneresTapninger() {
        fad.paafyld(5, paafyldning);
        assertEquals(0, fad.getTapninger().size());
        Tapning tapning = new Tapning(LocalDate.of(2027,01,14), "Snævar");
        fad.aftap(5, tapning);
        assertEquals(1, fad.getTapninger().size());
        assertEquals(tapning, fad.getTapninger().get(0));
    }

    @Test
    @Order(17)
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
    @Order(18)
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
    @Order(19)
    void TC1AtPaafyldningTilføjes() {
        assertEquals(0, fad.getPaafyldninger().size());
        fad.addPaafyldning(paafyldning);
        assertEquals(1, fad.getPaafyldninger().size());
        assertEquals(fad, paafyldning.getFad());
        assertEquals(paafyldning, fad.getPaafyldninger().get(0));
    }

    @Test
    @Order(20)
    void TC1AtPaafyldningFlyttes() {
        fad.paafyld(5, paafyldning);
        assertEquals(1, fad.getPaafyldninger().size());
        assertEquals(paafyldning, fad.getPaafyldninger().get(0));
        Fad fad2 = lager.createFad("Bourbon", 10, "USA");
        assertEquals(0, fad2.getPaafyldninger().size());
        fad.flytPaafyldning(fad2);
        assertEquals(1, fad2.getPaafyldninger().size());
        assertEquals(paafyldning, fad2.getPaafyldninger().get(0));
        assertEquals(1, fad.getPaafyldninger().size());
        assertEquals(paafyldning, fad.getPaafyldninger().get(0));
    }

    @Test
    @Order(21)
    void TC1AtSætteAntalLiterPaafyldt() {
        assertEquals(0, fad.getAntalLiterPaafyldt());
        fad.setAntalLiterPaafyldt(2);
        assertEquals(2, fad.getAntalLiterPaafyldt());
    }

    @Test
    @Order(22)
    void TC1AtReturnereSidstePaafyldning() {
        assertEquals(0, fad.getPaafyldninger().size());
        fad.paafyld(5, paafyldning);
        assertEquals(paafyldning, fad.sidstePaafyldning());
    }
    @Test
    @Order(23)
    void testAtTomtFadPrinterHistorik(){
        String expected = "Nr.: 26\n" +
                "Type: Sherry\n" +
                "Literkapacitet: 10\n" +
                "Leverandør: Spanien\n" +
                "Lagerlokation: Container\n" +
                "Liter påfyldt: 0\n" +
                "\n\t\tTIDLIGERE PÅFYLDNINGER PÅ FAD nr. 26\n" +
                "Ingen tidligere påfyldninger";
        assertEquals(expected, fad.historik());
    }

    @Test
    @Order(24)
    void testAtFyldtFadPrinterHistorik(){
        String expected = "Nr.: 27\n" +
                "Type: Sherry\n" +
                "Literkapacitet: 10\n" +
                "Leverandør: Spanien\n" +
                "Lagerlokation: Container\n" +
                "Liter påfyldt: 5\n" +
                "\n\t\tTIDLIGERE PÅFYLDNINGER PÅ FAD nr. 27\n" +
                "Påfyldningsdato: 2024-01-14\n" +
                "Tapningsdato: Endnu ikke tappet\n" +
                "Medarbejder: Snævar\n" +
                "Destillat info:\n" +
                "\tNavn: Destillat\n" +
                "\tKornsort: Byg\n" +
                "\tMaltdestillat: Maltdestillat\n" +
                "\tAlkoholprocent: 50.0\n" +
                "\tStartdato: 2024-01-01\n" +
                "\tSlutdato: 2024-01-14\n" +
                "\tMedarbejder: Snævar\n\n";

        fad.paafyld(5,paafyldning);
        assertEquals(expected, fad.historik());
    }


}