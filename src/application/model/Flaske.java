package application.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Flaske {
    private int liter;
    private String navn;
    private Tapning tapning;

    private double alkoholprocent;

    public Flaske(String navn, double alkoholprocent, Tapning tapning) {
        liter = 1;
        this.navn = navn;
        this.alkoholprocent = alkoholprocent;
        this.tapning = tapning;
        tapning.addFlaske(this);
    }

    public int getLiter() {
        return liter;
    }

    public String getNavn() {
        return navn;
    }




    public String historik() {
        String res = "";
        for (Fad fad : tapning.getFade()) {
            res += "Destilat: " + fad.getDestilat().getNavn() + "\n";
            res += "Korn sort: " + fad.getDestilat().getKornsort() + "\n";
            res += "Malt destilat: " + fad.getDestilat().getMaltdestilat() + "\n";
            res += "Startede destillation: " + fad.getDestilat().getStartdato() + " og sluttede: " + fad.getDestilat().getSlutdato() + "\n";
            res += "fad: " + fad.getNummer() + "\n";
            res += "type: " + fad.getType() + "\n";
            res += "leverandør: " + fad.getLeverandoer() + "\n";
            res += "lå på lager: " + fad.getLager().getNavn() + "\n";
            res += "lagt på fad: " + fad.getStartdato() + " og blev tappet " + fad.getSlutdato() + "\n";
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
}
