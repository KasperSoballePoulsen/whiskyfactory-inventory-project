package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {


    public static List<Lager> getLager(){
        return Storage.getLagerer();
    }

    public static List<Destilat>getDestilater(){

        return Storage.getDestilater();
    }

    public static List<Paafyldning> getPaafyldning(){
        return Storage.getPaafyldninger();
    }

    public static List<Tapning> getTapninger(){
        return Storage.getTapninger();
    }

    public static List<Flaske> PaafyldFlasker(Tapning tapning, List<Integer> literTappet, int vand, String medarbejder, String flaskeNavn, double alkoholprocent){
        return tapning.fyldPaaFlasker(literTappet, vand, medarbejder,flaskeNavn,alkoholprocent);

    public static Destilat opretDestilat(String navn, String kornsort, LocalDate startdato, LocalDate slutdato, String maltdestilat, int maengdeVaeskeILiter, double alkoholprocent, String medarbejder) {
        Destilat destilat = new Destilat(navn, kornsort, startdato, slutdato, maltdestilat, maengdeVaeskeILiter, alkoholprocent, medarbejder);
        Storage.addDestilat(destilat);
        return destilat;
    }

    public static Fad opretFad(String type, int stoerrelse, Lager lager, String leverandoer) {
        Fad fad = lager.createFad(type, stoerrelse, leverandoer);
        return fad;
    }

    public static void paaFyldFad(Fad fad, int antalLiterPaafyldt, LocalDate dato, String medarbejder) {
        Paafyldning paafyldning = new Paafyldning(dato, medarbejder);
        fad.paafyld(antalLiterPaafyldt, paafyldning);
    }

    public static void aftapFad(Fad fad, int literTapet, LocalDate dato, String medarbejder) {
        Tapning tapning = new Tapning(dato, medarbejder);
        fad.aftap(literTapet, tapning);
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

    public static void fyldFad(Paafyldning paafyldning, List<Integer> liter, String medarbejder){
        paafyldning.fyldFad(liter, medarbejder);
    }

    public static Lager opretLager(String navn, int antalPladser){
        Lager lager = new Lager(navn, antalPladser);
        Storage.addLager(lager);
        return lager;
    }
}
