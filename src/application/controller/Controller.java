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
    }

    public static void fyldFad(Paafyldning paafyldning, List<Integer> liter, String medarbejder){
        paafyldning.fyldFad(liter, medarbejder);
    }

    public static Lager createLager(String navn, int antalPladser){
        Lager lager = new Lager(navn, antalPladser);
        Storage.addLager(lager);
        return lager;
    }
}
