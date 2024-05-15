package application.controller;

import application.model.Destillat;
import application.model.Fad;
import application.model.Lager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {

    Lager lager;
    Fad fad;
    Destillat destillat;
    String medarbejder = "Snævar";

    @BeforeEach
    void setUp() {
        lager = Controller.opretLager("Container", 10);
        fad = Controller.opretFad("Sherry", 10, lager, "Spanien");
        LocalDate destillationStartdato = LocalDate.of(2024, 01, 01);
        LocalDate destillationSlutdato = LocalDate.of(2024, 01, 14);
        destillat = Controller.opretDestillat("Destillat", "Byg", destillationStartdato, destillationSlutdato, "Maltdestillat", 11, 50, medarbejder);
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

        Controller.aftapFad(fadList,literlist,dato,medarbejder,15,"Whisky",45);
        assertEquals(20, Controller.getTapninger().get(1).getFlasker().size());


    }

    @Test
    void testAtFlaskenPrinterSinHistorieRigtigt(){
        String res = "Destilat: Destillat\n" +
                "Korn sort: Byg\n" +
                "Malt destilat: Maltdestillat\n" +
                "Startede destillation: 2024-01-01 og sluttede: 2024-01-14\n" +
                "--------------------------\n" +
                "blev fyldt: 2024-01-14\n" +
                "--------------------------\n" +
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

}
