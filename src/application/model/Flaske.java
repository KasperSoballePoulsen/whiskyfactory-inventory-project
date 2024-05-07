package application.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Flaske {
    private int liter;
    private String navn;

    private double alkoholprocent;
    private List<Fad> fade;

    public Flaske(String navn, double alkoholprocent) {
        liter = 1;
        this.navn = navn;
        this.alkoholprocent = alkoholprocent;
        fade = new ArrayList<>();
    }

    public int getLiter() {
        return liter;
    }

    public String getNavn() {
        return navn;
    }

    public void addFad(Fad fad){
        if (!fade.contains(fad)) fade.add(fad);
    }


    public String historik() {
        String res = "";
        for (Fad fad : fade) {
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

    public void historikPaaFil(String filnavn) {
        try {
            PrintWriter printWriter = new PrintWriter(filnavn);
            printWriter.println(historik());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
