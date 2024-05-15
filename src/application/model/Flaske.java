package application.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Flaske {
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


    public String historik() {
        String res = "";
        for (Fad fad : tapning.getFade()) {
            Paafyldning paafyldning = fad.getPaafyldninger().get(fad.getPaafyldninger().size()-1);
                for (Destillat destilat : paafyldning.getDestillater()) {

                    res += "Destillat: " + destilat.getNavn() + "\n";
                    res += "Korn sort: " + destilat.getKornsort() + "\n";
                    res += "Malt destillat: " + destilat.getMaltdestillat() + "\n";
                    res += "Startede destillation: " + destilat.getStartdato() + " og sluttede: " + destilat.getSlutdato() + "\n";
                    res += "-------------------------- \n";
                }

            res += "fad: " + fad.getNummer() + "\n";
            res += "type: " + fad.getType() + "\n";
            res += "leverandør: " + fad.getLeverandoer() + "\n";
            res += "lå på lager: " + fad.getLager().getNavn() + "\n";
            res += "lagt på fad: " + paafyldning.getDato() + " og blev tappet " + tapning.getDato() + "\n";
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

    public String toString(){
        return navn + " " + alkoholprocent + "%";
    }
}
