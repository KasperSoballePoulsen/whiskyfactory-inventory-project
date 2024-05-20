package application.controller;

import application.model.Destillat;
import application.model.Fad;
import application.model.Lager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ControllerTest {

    Lager lager;
    Fad fad;
    Destillat destillat;
    String medarbejder = "Snævar";

    @BeforeEach
    void setUp() {
        Storage storage = new Storage();
        Controller.setStorage(storage);
        lager = Controller.opretLager("Container", 10);
        fad = Controller.opretFad("Sherry", 10, lager, "Spanien");
        LocalDate destillationStartdato = LocalDate.of(2024, 01, 01);
        LocalDate destillationSlutdato = LocalDate.of(2024, 01, 14);
        destillat = Controller.opretDestillat("Destillat", "Byg", destillationStartdato, destillationSlutdato, "Maltdestillat", 15, 50, medarbejder);

    }

    @Test
    void testAtAftapningLaverFlaskerRigtigt() {
        List<Destillat> destillatList = new ArrayList<>();
        destillatList.add(destillat);
        List<Integer> literlist = new ArrayList<>();
        literlist.add(5);
        LocalDate datoForPaafyldning = LocalDate.of(2024, 01, 14);
        Controller.paaFyldFad(destillatList, fad, literlist, datoForPaafyldning, medarbejder);
        List<Fad> fadList = new ArrayList<>();
        fadList.add(fad);
        LocalDate dato = LocalDate.of(2027, 02, 20);

        Controller.aftapFad(fadList,literlist,dato,medarbejder,5,"Whisky",45);
        assertEquals(LocalDate.of(2027, 02, 20), Controller.getTapninger().get(0).getDato());
        assertEquals(10, Controller.getTapninger().get(0).getFlasker().size());
        assertEquals("Whisky", Controller.getTapninger().get(0).getFlasker().get(0).getNavn());



    }

    @Test
    void testAtFlaskenPrinterSinHistorieRigtigt(){
        String res = "Destilat: Destillat\n" +
                "Korn sort: Byg\n" +
                "Malt destilat: Maltdestillat\n" +
                "Startede destillation: 2024-01-01 og sluttede: 2024-01-14\n" +
                "--------------------------\n" +
                "blev fyldt: 2024-01-14\n" +
                "fad: 1\n" +
                "type: Sherry\n" +
                "leverandør: Spanien\n" +
                "lå på lager: Container\n" +
                "lagt på fad: 2024-01-14 og blev tappet 2027-02-20\n";

        List<Destillat> destillatList = new ArrayList<>();
        destillatList.add(destillat);
        List<Integer> literlist = new ArrayList<>();
        literlist.add(5);
        LocalDate datoForPaafyldning = LocalDate.of(2024, 01, 14);
        Controller.paaFyldFad(destillatList, fad, literlist, datoForPaafyldning, medarbejder);
        List<Fad> fadList = new ArrayList<>();
        fadList.add(fad);
        LocalDate dato = LocalDate.of(2027, 02, 20);

        Controller.aftapFad(fadList,literlist,dato,medarbejder,5,"Whisky",45);
        assertEquals(res,Controller.getTapninger().get(0).getFlasker().get(0).historik());

    }


    @Test
    void TC1testAtFadetErBlevetPaafyldt(){
        List<Integer> literVaeske = new ArrayList<>();
        LocalDate datoForPaafyldning = LocalDate.of(2023,1,14);
        literVaeske.add(10);
        List<Destillat> destillater = new ArrayList<>();
        destillater.add(destillat);
        Controller.paaFyldFad(destillater,fad,literVaeske,datoForPaafyldning,"Snævar");
        assertEquals(10,fad.getAntalLiterPaafyldt());
        assertEquals(LocalDate.of(2023,1,14),fad.sidstePaafyldning().getDato());
        assertEquals("Snævar",fad.sidstePaafyldning().getMedarbejder());
        assertTrue(fad.sidstePaafyldning().getDestillater().contains(destillat));
    }


}
