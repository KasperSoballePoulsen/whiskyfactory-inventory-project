package application.controller;

import application.model.Destillat;
import application.model.Fad;
import application.model.Lager;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

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

}
