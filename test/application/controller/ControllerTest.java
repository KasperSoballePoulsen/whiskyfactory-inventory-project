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
        String res = "\t\tINFO OM FLASKEN\n" +
                "Navn: Whisky\n" +
                "Alkoholprocent: 45.0\n" +
                "Tapningsdato: 2027-02-20\n"+
                "\n\t\tINFO OM FLASKENS FADE\n" +
                "Nr.: 1\n" +
                "Type: Sherry\n" +
                "Literkapacitet: 10\n" +
                "Leverandør: Spanien\n" +
                "Lagerlokation: Container\n" +
                "Liter påfyldt: 0\n" +
                "\n\t\tTIDLIGERE PÅFYLDNINGER PÅ FAD nr. 1\n" +
                "Påfyldningsdato: 2024-01-14\n" +
                "Tapningsdato: 2027-02-20\n" +
                "Medarbejder: Snævar\n" +
                "Destillat info:\n" +
                "\tNavn: Destillat\n" +
                "\tKornsort: Byg\n" +
                "\tMaltdestillat: Maltdestillat\n" +
                "\tAlkoholprocent: 50.0\n" +
                "\tStartdato: 2024-01-01\n" +
                "\tSlutdato: 2024-01-14\n" +
                "\tMedarbejder: Snævar\n\n";


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
