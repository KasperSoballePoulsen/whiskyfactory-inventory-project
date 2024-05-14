package application.model;

import java.util.ArrayList;
import java.util.List;

public class Lager {
    private Fad[] pladser;
    private String navn;

    public Lager(String navn, int antalPladser) {
        pladser = new Fad[antalPladser];
        this.navn = navn;
    }

    public Fad[] getPladser() {
        return pladser;
    }

    public String getNavn() {
        return navn;
    }

    public Fad createFad(String type, int literKapacitet, String leverandoer) {
        Fad fad = new Fad(type, literKapacitet, this, leverandoer);
        int pladsNr = findLedigPlads();
        pladser[pladsNr] = fad;
        return fad;
    }


    public int findLedigPlads() {
        boolean found = false;
        int i = 0;
        int k = 0;
        while (!found && i < pladser.length) {
            k = i;
            if (pladser[k] == null) {
                found = true;
            }
            i++;
        }
        if (found) {
            return k;
        } else {
            throw new IndexOutOfBoundsException("lager er fyldt");
        }
    }

    public int antalLedigePladser() {
        int k = 0;
        boolean found = false;
        while (!found && k < pladser.length){
            if (pladser[k] == null){
                found = true;
            } else k++;
        }
        return pladser.length - k;
    }

    public List<Fad> fadePaaLager(){
        List<Fad> fade = new ArrayList<>();
        for (Fad fad : pladser){
            if (fad != null) {
                fade.add(fad);
            }
        }
        return fade;
    }

    public String toString(){
        return navn;
    }
}
