package application.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;

public class Flaske implements Serializable {
    private int liter;
    private String navn;
    private double alkoholprocent;
    private Tapning tapning;


    Flaske(String navn, double alkoholprocent, Tapning tapning) {
        liter = 1;
        this.navn = navn;
        this.alkoholprocent = alkoholprocent;
        this.tapning = tapning;
    }

    public int getLiter() {
        return liter;
    }

    public String getNavn() {
        return navn;
    }

    /**
     * @return en string med hele historien bag flaske. Alle destillaternes informationer, samt alle fadenes historie
     */
    public String historik() {
        String res = "";
        res += "\t\tINFO OM FLASKEN\n";
        res += "Navn: " + navn + "\n";
        res += "Alkoholprocent: " + alkoholprocent + "\n";
        res += "Tapningsdato: " + tapning.getDato() + "\n";
        res += "\n\t\tINFO OM FLASKENS FADE\n";
        for (Fad fad : tapning.getFade()) {
            res += fad.historik();
        }
        return res;
    }

    public Tapning getPaaFyldning() {
        return tapning;
    }

    public void historikPaaFil(String filnavn) {
        try {
            PrintWriter printWriter = new PrintWriter(filnavn);
            printWriter.println(historik());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return navn + " " + alkoholprocent + "%";
    }
}
