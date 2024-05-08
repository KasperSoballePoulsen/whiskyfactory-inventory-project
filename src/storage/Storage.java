package storage;

import application.model.*;

import java.util.ArrayList;
import java.util.List;

public class Storage {


    private final List<Lager> lagerer = new ArrayList<>();
    private final List<Medarbejder> medarbejderer = new ArrayList<>();
    private final List<Destilat> destilater = new ArrayList<>();
    private final List<Tapning> tapninger = new ArrayList<>();
    private final List<Paafyldning> paafyldninger = new ArrayList<>();


    public void addLager(Lager lager) {
        lagerer.add(lager);
    }

    public void addMedarbejder(Medarbejder medarbejder) {
        medarbejderer.add(medarbejder);
    }

    public void addDestilat(Destilat destilat) {
        destilater.add(destilat);
    }

    public void addPaafyldning(Paafyldning paafyldning) {
        paafyldninger.add(paafyldning);
    }

    public void addTapning(Tapning tapning) {
        tapninger.add(tapning);
    }

    public List<Lager> getLagerer() {
        return new ArrayList<>(lagerer);
    }

    public List<Medarbejder> getMedarbejderer() {
        return new ArrayList<>(medarbejderer);
    }

    public List<Destilat> getDestilater() {
        return new ArrayList<>(destilater);
    }

    public List<Tapning> getTapninger() {
        return new ArrayList<>(tapninger);
    }

    public List<Paafyldning> getPaafyldninger() {
        return new ArrayList<>(paafyldninger);
    }
}
