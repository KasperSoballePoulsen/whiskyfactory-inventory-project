package application.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Flaske {
    private int liter;
    private String navn;
    private Fad fad;

    public Flaske(int liter, String navn, Fad fad) {
        this.liter = liter;
        this.navn = navn;
        this.fad = fad;
    }

    public int getLiter() {
        return liter;
    }

    public String getNavn() {
        return navn;
    }

    public String historik() {
        String res = "";
        res += "Batch: " + fad.getDestilat().getNavn() + "\n";
        res += "Korn sort: " + fad.getDestilat().getKornsort() + "\n";
        res += "Malt batch: " + fad.getDestilat().getMaltDestilat() + "\n";
        res += "Startede destillation: " + fad.getDestilat().getStartdato() + " og sluttede: " + fad.getDestilat().getSlutdato() + "\n";
        res += "fad: " + fad.getNummer() + "\n";
        res += "type: " + fad.getType() + "\n";
        res += "leverandør: " + fad.getLeverandoer() + "\n";
        res += "lå på lager: " + fad.getLager() + "\n";
        res += "lagt på fad: " + fad.getStartdato() + " og blev tappet " + fad.getSlutdato() + "\n";
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
