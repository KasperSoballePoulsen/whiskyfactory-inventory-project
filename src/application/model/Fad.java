package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private List<Destilat> destilater;
    private Medarbejder paafylder;

    private final List<Tapning> tapningList = new ArrayList<>();


    public Fad(String type, int stoerrelse, Lager lager, String leverandoer) {

        this.nummer = nummerCount + 1;
        this.type = type;
        this.stoerrelse = stoerrelse;
        this.lager = lager;
        destilater = new ArrayList<>();
        this.leverandoer = leverandoer;

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
        return paafylder;
    }

    public void paafyld(int antalLiterPaafyldt) {
        if (this.antalLiterPaafyldt + antalLiterPaafyldt <= stoerrelse){
            this.antalLiterPaafyldt += antalLiterPaafyldt;
        } else throw new IllegalArgumentException("prøver at overfylde fadet");
    }

    public void aftap(int literTapet) {
        if (literTapet <= antalLiterPaafyldt) {
            antalLiterPaafyldt -= literTapet;
        } else {
            throw new IllegalArgumentException("der er ikke nok liter");
        }
    }

    public ArrayList<Tapning> getPaaFyldninger() {
        return new ArrayList<>(tapningList);
    }

    public void addPaaFyldning(Tapning tapning) {
        if (!tapningList.contains(tapning)) {
            tapningList.add(tapning);
            tapning.addFad(this);
        }
    }

    public void removePaaFyldning(Tapning tapning) {
        if (tapningList.contains(tapning)) {
            tapningList.remove(tapning);
            tapning.removeFad(this);
        }
    }

}
