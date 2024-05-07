package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fad {
    private static int nummerCount;
    private int nummer;
    private String type;
    private int stoerrelse;
    private Lager lager;
    private int antalLiterPaafyldt;
    private String leverandoer;
    private LocalDate startdato;
    private LocalDate slutdato;
    private Destilat destilat;
    private List<Medarbejder> medarbejderer;


    public Fad(String type, int stoerrelse, Lager lager, String leverandoer) {
        destilat = null;
        this.nummer = nummerCount + 1;
        this.type = type;
        this.stoerrelse = stoerrelse;
        this.lager = lager;

        this.leverandoer = leverandoer;


        medarbejderer = new Medarbejder[2];
        nummerCount++;

    }

    public int getNummer() {
        return nummer;
    }

    public String getType() {
        return type;
    }

    public int getStoerrelse() {
        return stoerrelse;
    }

    public Lager getLager() {
        return lager;
    }

    public int getAntalLiterPaafyldt() {
        return antalLiterPaafyldt;
    }


    public String getLeverandoer() {
        return leverandoer;

    }

    public LocalDate getStartdato() {
        return startdato;
    }

    public LocalDate getSlutdato() {
        return slutdato;
    }

    public Destilat getDestilat() {
        return destilat;
    }

    public Medarbejder getPaafylder() {
        return medarbejderer.get(0);
    }

    public List<Medarbejder> getAftapper() {
        return medarbejderer.subList(1, medarbejderer.size());
    }

    public void paafyld(Medarbejder medarbejder, Destilat destilat, LocalDate startdato, int antalLiterPaafyldt) {
        if (this.destilat == null) {
            medarbejderer.set(0,medarbejder);
            this.destilat = destilat;
            this.startdato = startdato;
            this.antalLiterPaafyldt = antalLiterPaafyldt;
        }
    }

    public void aftap(Medarbejder medarbejder, int literTapet, LocalDate slutdato) {
        if (literTapet <= antalLiterPaafyldt) {
            this.slutdato = slutdato;
            medarbejderer.add(medarbejder);
            antalLiterPaafyldt -= literTapet;
        } else {
            throw new IllegalArgumentException("der er ikke nok liter");
        }
    }
}
