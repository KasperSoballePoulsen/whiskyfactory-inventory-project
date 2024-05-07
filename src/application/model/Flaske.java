package application.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Flaske {
    private int liter;
    private String navn;
    private PaaFyldning paaFyldning;

    private double alkoholprocent;

    public Flaske(String navn, double alkoholprocent, PaaFyldning paaFyldning) {
        liter = 1;
        this.navn = navn;
        this.alkoholprocent = alkoholprocent;
        this.paaFyldning = paaFyldning;
    }

    public int getLiter() {
        return liter;
    }

    public String getNavn() {
        return navn;
    }




    public String historik() {
        String res = "";
        for (Fad fad : paaFyldning.getFade()) {
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

    public PaaFyldning getPaaFyldning() {
        return paaFyldning;
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
