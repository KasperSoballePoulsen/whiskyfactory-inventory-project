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

    /**
     *
     * @param type
     * @param literKapacitet
     * @param leverandoer
     * @return Fad objekt
     */
    public Fad createFad(String type, int literKapacitet, String leverandoer) {
        Fad fad = new Fad(type, literKapacitet, this, leverandoer);
        int pladsNr = findLedigPlads();
        pladser[pladsNr] = fad;
        return fad;
    }

    /**
     * lavet med en standard søge skabelon
     * @return den første ledige plads der er på laget, hvis lageret er fyldt så thrower den en out of bound exception
     */
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

    /**
     * da vi fylder lageret fra venstre til højre, kan vi søge efter den første ledige plads index,
     * og trække det fra antallet af pladser på lageret for at få antallet af ledige pladser
     * @return antallet af ledige pladser på lageret
     */
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

    /**
     *
     * da vi fylder fra venstre til højre kan vi fylde på vores liste indtil vi finder en tom plads
     * så ved vi, vi har alle fad på lageret
     * @return alle fade der lægger på et lager
     */
    public List<Fad> fadePaaLager(){
        List<Fad> fade = new ArrayList<>();
        int i = 0;
        while (pladser[i] != null) {
            fade.add(pladser[i]);
        }
        return fade;
    }

    public String toString(){
        return navn;
    }
}
