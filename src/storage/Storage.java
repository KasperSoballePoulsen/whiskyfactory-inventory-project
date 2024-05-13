package storage;

import application.model.*;

import java.util.ArrayList;
import java.util.List;

public class Storage {


    private static List<Lager> lagerer = new ArrayList<>();
    private static List<Destilat> destilater = new ArrayList<>();
    private static List<Tapning> tapninger = new ArrayList<>();
    private static List<Paafyldning> paafyldninger = new ArrayList<>();


    public static void addLager(Lager lager) {
        lagerer.add(lager);
    }


    public static void addDestilat(Destilat destilat) {
        destilater.add(destilat);
    }

    public static void addPaafyldning(Paafyldning paafyldning) {
        paafyldninger.add(paafyldning);
    }

    public static void addTapning(Tapning tapning) {
        tapninger.add(tapning);
    }

    public static List<Lager> getLagerer() {
        return new ArrayList<>(lagerer);
    }


    public static List<Destilat> getDestilater() {
        return new ArrayList<>(destilater);
    }

    public static List<Tapning> getTapninger() {
        return new ArrayList<>(tapninger);
    }

    public static List<Paafyldning> getPaafyldninger() {
        return new ArrayList<>(paafyldninger);
    }
}
