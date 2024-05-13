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

    public static List<Destilat> getDestilater() {

        return Storage.getDestilater();
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

    public static Destilat opretDestilat(String navn, String kornsort, LocalDate startdato, LocalDate slutdato, String maltdestilat, int maengdeVaeskeILiter, double alkoholprocent, String medarbejder) {
        Destilat destilat = new Destilat(navn, kornsort, startdato, slutdato, maltdestilat, maengdeVaeskeILiter, alkoholprocent, medarbejder);
        Storage.addDestilat(destilat);
        return destilat;
    }

    public static Fad opretFad(String type, int stoerrelse, Lager lager, String leverandoer) {
        Fad fad = lager.createFad(type, stoerrelse, leverandoer);
        return fad;
    }

    public static void paaFyldFad(List<Destilat> destilater, Fad fad, List<Integer> liter, LocalDate dato, String medarbejder) {
        Paafyldning paafyldning = new Paafyldning(dato, medarbejder);
        paafyldning.setFad(fad); //tjek i morgen
        for (int i = 0; i < destilater.size(); i++) {
            paafyldning.addDestilat(destilater.get(i));
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
}
