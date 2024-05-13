package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public static List<Lager> getLager() {
        return Storage.getLagerer();
    }

    public static List<Destillat> getDestillater() {

        return Storage.getDestillater();
    }

    public static List<Paafyldning> getPaafyldning() {
        return Storage.getPaafyldninger();
    }

    public static List<Tapning> getTapninger() {
        return Storage.getTapninger();
    }

    public static List<Flaske> PaafyldFlasker(Tapning tapning, List<Integer> literTappet, int vand, String medarbejder, String flaskeNavn, double alkoholprocent) {
        return tapning.fyldPaaFlasker(literTappet, vand, medarbejder, flaskeNavn, alkoholprocent);
    }

    public static Destillat opretDestillat(String navn, String kornsort, LocalDate startdato, LocalDate slutdato, String maltdestillat, int maengdeVaeskeILiter, double alkoholprocent, String medarbejder) {
        Destillat destillat = new Destillat(navn, kornsort, startdato, slutdato, maltdestillat, maengdeVaeskeILiter, alkoholprocent, medarbejder);
        Storage.addDestilat(destillat);
        return destillat;
    }

    public static Fad opretFad(String type, int literKapacitet, Lager lager, String leverandoer) {
        Fad fad = lager.opretFad(type, literKapacitet, leverandoer);
        return fad;
    }

    public static void paaFyldFad(List<Destillat> destillater, Fad fad, List<Integer> liter, LocalDate dato, String medarbejder) {
        Paafyldning paafyldning = new Paafyldning(dato, medarbejder, fad);
        for (int i = 0; i < destillater.size(); i++) {
            paafyldning.addDestillat(destillater.get(i));
        }
        paafyldning.fyldFad(liter);
        int sumLiter = 0;
        for (Integer i : liter) {
            sumLiter += i;
        }
        fad.paafyld(sumLiter, paafyldning);
    }

    public static void aftapFad(List<Fad> fade, List<Integer> literTapet, LocalDate dato, String medarbejder, int vand, String flaskeNavn, double alkoholprocent) {
        Tapning tapning = new Tapning(dato, medarbejder);
        for (int i = 0; i < fade.size(); i++) {
            fade.get(i).aftap(literTapet.get(i), tapning);
        }
        tapning.fyldPaaFlasker(literTapet, vand, medarbejder, flaskeNavn, alkoholprocent);

    }

    public static List<Fad> getTommeFade() {
        List<Lager> lagerer = Storage.getLagerer();
        List<Fad> fade = new ArrayList<>();
        for (Lager lager : lagerer) {
            for (Fad fad : lager.getPladser()) {
                if (fad != null) {
                    if (fad.getAntalLiterPaafyldt() == 0) {
                        fade.add(fad);
                    }
                }
            }
        }

        return fade;
    }

    public static List<Fad> getFyldteFade() {
        List<Lager> lagerer = Storage.getLagerer();
        List<Fad> fade = new ArrayList<>();
        for (Lager lager : lagerer) {
            for (Fad fad : lager.getPladser()) {
                if (fad != null) {
                    if (fad.getAntalLiterPaafyldt() != 0) {
                        fade.add(fad);
                    }
                }
            }
        }

        return fade;
    }


    public static Lager opretLager(String navn, int antalPladser) {
        Lager lager = new Lager(navn, antalPladser);
        Storage.addLager(lager);
        return lager;
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

        Destillat destillat1 = Controller.opretDestillat("Første distillat", "Byg",LocalDate.of(2024,01,01), LocalDate.of(2024,01,14),"Malthuset",200, 50.2, "Snævar Njall Albertsson");
        Controller.opretDestillat("Andet distillat", "Byg",LocalDate.of(2024,01,01), LocalDate.of(2024,01,14),"Malthuset",200, 50.2, "Snævar Njall Albertsson");

        List<Destillat> destillater = new ArrayList<>();
        destillater.add(destillat1);
        List<Integer> literMængder = new ArrayList<>();
        literMængder.add(30);
        Controller.paaFyldFad(destillater, fad1, literMængder,LocalDate.of(2024,01,15), "Snævar Njall Albertsson");
    }
}
