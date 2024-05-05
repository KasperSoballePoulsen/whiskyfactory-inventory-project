package application.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Fad {
    private int nummer;
    private String type;
    private int stoerrelse;
    private String lager;
    private int antalLiterPaafyldt;
    private String leverandoer;
    private LocalDate startDato;
    private LocalDate slutDato;

    private Batch batch;
    private Medarbejder[] medarbejderer;


    public Fad(int nummer, String type, int stoerrelse, String lager, String leverandoer) {
        batch = null;
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

    public String getLager() {
        return lager;
    }

    public int getAntalLiterPaafyldt() {
        return antalLiterPaafyldt;
    }


    public String getLeverandoer() {
        return leverandoer;

    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public Batch getBatch() {
        return batch;
    }

    public Medarbejder getPaafylder() {
        return medarbejderer[0];
    }

    public Medarbejder getAftapper() {
        return medarbejderer[1];
    }

    public void paafyld(Medarbejder medarbejder, Batch batch, LocalDate startDato, int antalLiterPaafyldt) {
        if (this.batch == null) {
            medarbejderer[0] = medarbejder;
            this.batch = batch;
            this.startDato = startDato;
            this.antalLiterPaafyldt = antalLiterPaafyldt;
        }
    }

    public Map<String, Integer> aftap(Medarbejder medarbejder, LocalDate slutDato, String flaskeNavn) {
        if (this.batch != null) {
            Map<String, Integer> flasker = new HashMap<>();
            medarbejderer[1] = medarbejder;
            this.slutDato = slutDato;

            for (int i = 0; i < antalLiterPaafyldt; i++) {
                Flaske flaske = new Flaske(1, flaskeNavn, this);

                if (!flasker.keySet().contains(flaskeNavn)) {
                    flasker.put(flaskeNavn, 1);
                } else {
                    flasker.put(flaskeNavn, flasker.get(flaskeNavn) + 1);
                }
            }
            this.antalLiterPaafyldt = 0;
            this.batch = null;
            return flasker;
        } else return null;

    }
}
