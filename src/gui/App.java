package gui;

import application.controller.Controller;
import application.model.*;
import javafx.application.Application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        createSomeObjects();
        Application.launch(StartWindow.class);
    }

    public static void createSomeObjects() {
        Lager lagerContainer = Controller.opretLager("Container", 8);
        Lager lagerLade = Controller.opretLager("Lade", 14);

        Fad fad1 = Controller.opretFad("Sherryfad",30, lagerContainer, "Spanien");
        Fad fad2 = Controller.opretFad("Sherryfad", 30, lagerContainer, "Spanien");
        Controller.opretFad("Sherryfad",30, lagerContainer,"Spanien");
        Controller.opretFad("Bourbonfad",50, lagerContainer, "Spanien");
        Controller.opretFad("Bourbonfad", 50, lagerContainer, "Spanien");

        Controller.opretFad("Sherryfad", 100, lagerLade, "Spanien");
        Controller.opretFad("Sherryfad", 100, lagerLade, "Spanien");
        Controller.opretFad("Sherryfad", 100, lagerLade, "Spanien");
        Controller.opretFad("Sherryfad", 250, lagerLade, "Spanien");
        Controller.opretFad("Sherryfad", 190, lagerLade, "Spanien");
        Controller.opretFad("Bourbonfad",50, lagerLade, "Spanien");
        Controller.opretFad("Bourbonfad",50, lagerLade, "Spanien");
        Controller.opretFad("Bourbonfad",50, lagerLade, "Spanien");
        Controller.opretFad("Bourbonfad",50, lagerLade, "Spanien");

        Destillat destillat1 = Controller.opretDestillat("Første distillat", "Byg", LocalDate.of(2024,01,01), LocalDate.of(2024,01,14),"Malthuset",200, 50.2, "Snævar Njall Albertsson");
        Controller.opretDestillat("Andet distillat", "Byg",LocalDate.of(2024,01,01), LocalDate.of(2024,01,14),"Malthuset",200, 50.2, "Snævar Njall Albertsson");

        List<Destillat> destillater = new ArrayList<>();
        destillater.add(destillat1);
        List<Integer> literMængder = new ArrayList<>();
        literMængder.add(30);
        Controller.paaFyldFad(destillater, fad1, literMængder,LocalDate.of(2024,01,15), "Snævar Njall Albertsson");
    }
}
