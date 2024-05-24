package gui;

import application.controller.Controller;
import application.model.*;
import javafx.application.Application;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Storage storage = Storage.loadStorage();
        if (storage == null) {
            storage = new Storage();
            System.out.println("Empty ListStorage created");
        }
        Controller.setStorage(storage);

        if (Controller.getTommeFade().isEmpty() && Controller.getDestillater().isEmpty()){
            createSomeObjects();
            System.out.println("Storage initialized");
        }
        Application.launch(StartWindow.class);

        Storage.saveStorage(storage);
    }

    public static void createSomeObjects() {
        Lager lagerContainer = Controller.opretLager("Container", 8);
        Lager lagerLade = Controller.opretLager("Lade", 14);

        Fad fad1 = Controller.opretFad("Sherryfad",30, lagerContainer, "Spanien");
        Fad fad2 = Controller.opretFad("Sherryfad", 30, lagerContainer, "Spanien");
        Fad fad3 = Controller.opretFad("Sherryfad",30, lagerContainer,"Spanien");
        Controller.opretFad("Bourbonfad",50, lagerContainer, "USA");
        Controller.opretFad("Bourbonfad", 50, lagerContainer, "USA");

        Controller.opretFad("Sherryfad", 100, lagerLade, "Spanien");
        Controller.opretFad("Sherryfad", 100, lagerLade, "Spanien");
        Controller.opretFad("Sherryfad", 100, lagerLade, "Spanien");
        Controller.opretFad("Sherryfad", 250, lagerLade, "Spanien");
        Controller.opretFad("Sherryfad", 190, lagerLade, "Spanien");
        Controller.opretFad("Bourbonfad",50, lagerLade, "USA");
        Controller.opretFad("Bourbonfad",50, lagerLade, "USA");
        Controller.opretFad("Bourbonfad",50, lagerLade, "USA");
        Controller.opretFad("Bourbonfad",50, lagerLade, "USA");



        Destillat destillat1 = Controller.opretDestillat("Første distillat", "Byg", LocalDate.of(2024,01,01), LocalDate.of(2024,01,14),"Malthuset",200, 50.2, "Snævar Njall Albertsson");
        Controller.opretDestillat("Andet distillat", "Byg",LocalDate.of(2024,01,01), LocalDate.of(2024,01,14),"Malthuset",200, 50.2, "Snævar Njall Albertsson");

        List<Destillat> destillater = new ArrayList<>();
        destillater.add(destillat1);
        List<Integer> literMængder = new ArrayList<>();
        literMængder.add(1);
        Controller.paaFyldFad(destillater, fad1, literMængder,LocalDate.of(2020,01,15), "Snævar Njall Albertsson");
        Controller.paaFyldFad(destillater, fad2, literMængder,LocalDate.of(2021,01,15), "Snævar Njall Albertsson");
        Controller.paaFyldFad(destillater, fad3, literMængder,LocalDate.of(2023,01,15), "Snævar Njall Albertsson");
        List<Fad> fadList = new ArrayList<>();
        fadList.add(fad1);
        Controller.aftapFad(fadList,literMængder,LocalDate.of(2024,3,15),"Snævar", 10, "MULD", 30.2);
    }
}
