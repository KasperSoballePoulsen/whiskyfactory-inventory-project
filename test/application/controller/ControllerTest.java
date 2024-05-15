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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerTest {

    Lager lager;
    Fad fad;
    Destillat destillat;
    @BeforeEach
    void setUp(){
        lager = Controller.opretLager("Container", 10);
        fad =Controller.opretFad("Sherry", 10, lager, "Spanien");
        LocalDate destillationStartdato = LocalDate.of(2024,01,01);
        LocalDate destillationSlutdato = LocalDate.of(2024,01,14);
        destillat = Controller.opretDestillat("Destillat", "Byg", destillationStartdato, destillationSlutdato, "Maltdestillat", 11, 50, "Snævar");


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
