package storage;

import application.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage implements Serializable {


    private List<Lager> lagerer = new ArrayList<>();
    private List<Destillat> destilater = new ArrayList<>();
    private List<Tapning> tapninger = new ArrayList<>();
    private List<Paafyldning> paafyldninger = new ArrayList<>();

    private int antalFade;


    public void addLager(Lager lager) {
        lagerer.add(lager);
    }


    public void addDestilat(Destillat destilat) {
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


    public List<Destillat> getDestillater() {
        return new ArrayList<>(destilater);
    }

    public List<Tapning> getTapninger() {
        return new ArrayList<>(tapninger);
    }

    public List<Paafyldning> getPaafyldninger() {
        return new ArrayList<>(paafyldninger);
    }


    public static Storage loadStorage() {
        String fileName = "storage.ser";
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)
        ) {
            Object obj = objIn.readObject();
            Storage storage = (Storage) obj;
            System.out.println("Storage loaded from file " + fileName);
            Fad.setNummerCount(storage.getAntalFade());
            return storage;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error deserializing storage");
            System.out.println(ex);
            return null;
        }
    }

    public static void saveStorage(Storage storage) {
        String fileName = "storage.ser";
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)
        ) {
            storage.setAntalFade(Fad.getNummerCount());
            objOut.writeObject(storage);
            System.out.println("Storage saved in file " + fileName);
        } catch (IOException ex) {
            System.out.println("Error serializing storage");
            System.out.println(ex);
            throw new RuntimeException();
        }
    }

    public int getAntalFade() {
        return antalFade;
    }

    public void setAntalFade(int antalFade) {
        this.antalFade = antalFade;
    }
}

