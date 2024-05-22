package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class  Controller {

    private static Storage storage;

    public static List<Lager> getLager() {
        return storage.getLagerer();
    }

    public static List<Destillat> getDestillater() {

        return storage.getDestillater();
    }

    public static List<Paafyldning> getPaafyldning() {
        return storage.getPaafyldninger();
    }

    public static List<Tapning> getTapninger() {
        return storage.getTapninger();
    }


    public static Destillat opretDestillat(String navn, String kornsort, LocalDate startdato, LocalDate slutdato, String maltdestillat, int maengdeVaeskeILiter, double alkoholprocent, String medarbejder) {
        Destillat destillat = new Destillat(navn, kornsort, startdato, slutdato, maltdestillat, maengdeVaeskeILiter, alkoholprocent, medarbejder);
        storage.addDestilat(destillat);
        return destillat;
    }

    public static Lager opretLager(String navn, int antalPladser) {
        Lager lager = new Lager(navn, antalPladser);
        storage.addLager(lager);
        return lager;
    }

    public static Fad opretFad(String type, int stoerrelse, Lager lager, String leverandoer) {
        Fad fad = lager.createFad(type, stoerrelse, leverandoer);
        return fad;
    }

    /**
     * pre: destillater.size() == liter.size()
     *
     * @param destillater != null
     * @param fad != null
     * @param liter != null
     * @param dato != null
     * @param medarbejder != null
     * flytter væske over på fadet
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
     * @param fade != null
     * @param literTapet != null
     * @param dato != null
     * @param medarbejder != null
     * @param vand
     * @param flaskeNavn != null
     * @param alkoholprocent != null
     * tager væske fra fade og opretter flasker med
     */
    public static void aftapFad(List<Fad> fade, List<Integer> literTapet, LocalDate dato, String medarbejder, int vand, String flaskeNavn, double alkoholprocent) {
        Tapning tapning = new Tapning(dato, medarbejder);
        for (int i = 0; i < fade.size(); i++) {
            fade.get(i).aftap(literTapet.get(i), tapning);
        }
        tapning.fyldPaaFlasker(literTapet, vand, medarbejder, flaskeNavn, alkoholprocent);
        storage.addTapning(tapning);

    }

    /**
     * vi henter fadene fra storage og sortere de fyldte fade fra
     *
     * @return list<Fad> indeholder alle de tomme fade i storage
     */
    public static List<Fad> getTommeFade() {
        List<Lager> lagerer = storage.getLagerer();
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

    public static void flytPaafyldning(Fad gamleFad, Fad NyeFad){
        gamleFad.flytPaafyldning(NyeFad);
    }

    /**
     * vi henter fadene fra storage og sortere de tomme fade fra
     *
     * @return list<Fad> indeholder alle de fyldte fade i storage
     */
    public static List<Fad> getFyldteFade() {
        List<Lager> lagerer = storage.getLagerer();
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


    /**
     * @param flaske != null
     * @return en String med historien bag flasken
     */
    public static String flaskeHistorik(Flaske flaske) {
        return flaske.historik();
    }

    public static List<Fad> soegteFade(String string, Lager lager){
        List<Fad> soegteFade = new ArrayList<>();
        for (Fad fade : lager.fadePaaLager()){
            if (fade.getLeverandoer().equals(string)){
                soegteFade.add(fade);
            } else if (fade.getType().equals(string)){
                soegteFade.add(fade);
            }
        }
        return soegteFade;
    }

    public static void setStorage(Storage Storage){
        Controller.storage = Storage;
    }


}
