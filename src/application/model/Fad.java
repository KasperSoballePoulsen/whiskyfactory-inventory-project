package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fad {
    private int nummer;
    private String type;
    private int stoerrelse;
    private Lager lager;
    private int antalLiterPaafyldt;
    private String leverandoer;
    private LocalDate startdato;
    private LocalDate slutdato;

    private Destilat destilat;
    private Medarbejder paafylder;


    public Fad(int nummer, String type, int stoerrelse, Lager lager, String leverandoer) {
        destilat = null;
        this.nummer = nummer;
        this.type = type;
        this.stoerrelse = stoerrelse;
        this.lager = lager;

        this.leverandoer = leverandoer;

        paafylder = new ArrayList<>();
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
        return paafylder;
    }

    public void paafyld(Medarbejder medarbejder, Destilat destilat, LocalDate startdato, int antalLiterPaafyldt) {
        if (this.destilat == null) {
            this.paafylder = medarbejder;
            this.destilat = destilat;
            this.startdato = startdato;
            this.antalLiterPaafyldt = antalLiterPaafyldt;
        }
    }

    public void aftap(int literTapet) {
        if (literTapet <= antalLiterPaafyldt) {
            antalLiterPaafyldt -= literTapet;
        } else {
            throw new IllegalArgumentException("der er ikke nok liter");
        }
    }
}
