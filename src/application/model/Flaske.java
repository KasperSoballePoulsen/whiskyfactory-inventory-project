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

    public String historik(){
        String res = "";
        res += "Batch: " + fad.getBatch().getNavn() + "\n";
        res += "Korn sort: " + fad.getBatch().getKornsort() + "\n";
        res += "Malt batch: " + fad.getBatch().getMaltBatch() + "\n";
        res += "Startede destillation: " + fad.getBatch().getStartDato() + " og sluttede: " + fad.getBatch().getSlutDato() + "\n";
        res += "fad: " + fad.getNummer() + "\n";
        res += "type: " + fad.getType() + "\n";
        res += "leverandør: " + fad.getLeverandoer() + "\n";
        res += "lå på lager: " + fad.getLager() + "\n";
        res += "lagt på fad: " + fad.getStartDato() + " og blev tappet " + fad.getSlutDato() + "\n";
        return res;
    }

    public void historikPaaFil(String filename){
        try {
            PrintWriter printWriter = new PrintWriter(filename);
            printWriter.println(historik());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
