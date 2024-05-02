package application.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Fad {
    private int nummer;
    private String type;
    private int stoerrelse;
    private String lager;
    private int antalLiterPaafyldt;
    private String leverandør;
    private LocalDate startDato;
    private LocalDate slutDato;

    private Batch batch;
    private Medarbejder[] medarbejderer;

    public Fad(int nummer, String type, int stoerrelse, String lager, String leverandør) {
        this.nummer = nummer;
        this.type = type;
        this.stoerrelse = stoerrelse;
        this.lager = lager;
        this.leverandør = leverandør;
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

    public String getLeverandør() {
        return leverandør;
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

    public void paafyld(Medarbejder medarbejder,Batch batch, LocalDate startDato, int antalLiterPaafyldt){
        if (this.batch.equals(null)){
            medarbejderer[0] = medarbejder;
            this.batch = batch;
            this.startDato = startDato;
            this.antalLiterPaafyldt = antalLiterPaafyldt;
        }
    }
    public Set<Flaske> aftap(Medarbejder medarbejder, LocalDate slutDato, String flaskeNavn){
        if (!this.batch.equals(null)){
            Set<Flaske> flasker = new HashSet<>();
            medarbejderer[1] = medarbejder;
            this.slutDato = slutDato;
            for (int i = 0; i < antalLiterPaafyldt; i++) {
                Flaske flaske = new Flaske(1, flaskeNavn);
                flasker.add(flaske);
            }
            this.antalLiterPaafyldt = 0;
            this.batch = null;
            return flasker;
        } else return null;

    }
}
