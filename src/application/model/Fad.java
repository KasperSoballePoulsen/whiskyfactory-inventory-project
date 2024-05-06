package application.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
    private Medarbejder[] medarbejderer;


    public Fad(int nummer, String type, int stoerrelse, Lager lager, String leverandoer) {
        destilat = null;
        this.nummer = nummer;
        this.type = type;
        this.stoerrelse = stoerrelse;
        this.lager = lager;

        this.leverandoer = leverandoer;

        medarbejderer = new Medarbejder[2];
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
        return medarbejderer[0];
    }

    public Medarbejder getAftapper() {
        return medarbejderer[1];
    }

    public void paafyld(Medarbejder medarbejder, Destilat destilat, LocalDate startdato, int antalLiterPaafyldt) {
        if (this.destilat == null) {
            medarbejderer[0] = medarbejder;
            this.destilat = destilat;
            this.startdato = startdato;
            this.antalLiterPaafyldt = antalLiterPaafyldt;
        }
    }

    public Map<String, Integer> aftap(Medarbejder medarbejder, LocalDate slutdato, String flaskeNavn) {
        if (this.destilat != null) {
            Map<String, Integer> flasker = new HashMap<>();
            medarbejderer[1] = medarbejder;
            this.slutdato = slutdato;

            for (int i = 0; i < antalLiterPaafyldt; i++) {
                Flaske flaske = new Flaske(1, flaskeNavn, this);

                if (!flasker.keySet().contains(flaskeNavn)) {
                    flasker.put(flaskeNavn, 1);
                } else {
                    flasker.put(flaskeNavn, flasker.get(flaskeNavn) + 1);
                }
            }
            this.antalLiterPaafyldt = 0;
            this.destilat = null;
            return flasker;
        } else return null;

    }
}
