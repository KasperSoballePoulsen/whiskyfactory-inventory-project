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

    public static Fad opretFad(String type, int stoerrelse, Lager lager, String leverandoer) {
        Fad fad = lager.createFad(type, stoerrelse, leverandoer);
        return fad;
    }

    /**
     * pre: destillater.size() == liter.size()
     *
     * @param destillater
     * @param fad
     * @param liter
     * @param dato
     * @param medarbejder flytter væske over på fadet
     */
    public static void paaFyldFad(List<Destillat> destillater, Fad fad, List<Integer> liter, LocalDate dato, String medarbejder) {
        Paafyldning paafyldning = new Paafyldning(dato, medarbejder, fad, destillater);
        paafyldning.fyldFad(liter);
        int sumLiter = 0;
        for (Integer i : liter) {
            sumLiter += i;
        }
    }

    /**
     * pre: fade.size() == litertappet.size()
     * pre: datoen skal være 3 år efter paafyldnings datoen
     *
     * @param fade
     * @param literTapet
     * @param dato
     * @param medarbejder
     * @param vand
     * @param flaskeNavn
     * @param alkoholprocent tager væske fra fade og opretter flasker med
     */
    public static void aftapFad(List<Fad> fade, List<Integer> literTapet, LocalDate dato, String medarbejder, int vand, String flaskeNavn, double alkoholprocent) {
        Tapning tapning = new Tapning(dato, medarbejder);
        for (Fad fad : fade) {
            tapning.addFad(fad);
        }
        Storage.addTapning(tapning);
        tapning.fyldPaaFlasker(literTapet, vand, medarbejder, flaskeNavn, alkoholprocent);

    }

    /**
     * vi henter fadene fra storage og sortere de fyldte fade fra
     *
     * @return list<Fad> indeholder alle de tomme fade i storage
     */
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

    /**
     * vi henter fadene fra storage og sortere de tomme fade fra
     *
     * @return list<Fad> indeholder alle de fyldte fade i storage
     */
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


    /**
     * @param flaske
     * @return en String med historien bag flasken
     */
    public static String flaskeHistorik(Flaske flaske) {
        return flaske.historik();
    }


}
