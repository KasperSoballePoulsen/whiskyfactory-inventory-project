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
            for (Paafyldning paafyldning : fad.getPaafyldninger()) {
                for (Destilat destilat : paafyldning.getDestilater()) {

                    res += "Destilat: " + destilat.getNavn() + "\n";
                    res += "Korn sort: " + destilat.getKornsort() + "\n";
                    res += "Malt destilat: " + destilat.getMaltdestilat() + "\n";
                    res += "Startede destillation: " + destilat.getStartdato() + " og sluttede: " + destilat.getSlutdato() + "\n";
                }
            }
            res += "fad: " + fad.getNummer() + "\n";
            res += "type: " + fad.getType() + "\n";
            res += "leverandør: " + fad.getLeverandoer() + "\n";
            res += "lå på lager: " + fad.getLager().getNavn() + "\n";
            //mangler lige noget her
            res += "lagt på fad: "   + " og blev tappet " + tapning.getDato() + "\n";
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
