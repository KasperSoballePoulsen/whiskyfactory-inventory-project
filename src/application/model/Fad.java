package application.model;

import java.util.ArrayList;
import java.util.List;

public class Fad {
    private static int nummerCount;
    private int nummer;
    private String type;
    private int stoerrelse;
    private int antalLiterPaafyldt;
    private String leverandoer;
    private Lager lager;
    private final List<Tapning> tapninger = new ArrayList<>();
    private final List<Paafyldning> paafyldninger = new ArrayList<>();


    Fad(String type, int stoerrelse, Lager lager, String leverandoer) {

        this.nummer = nummerCount + 1;
        this.type = type;
        this.stoerrelse = stoerrelse;
        this.lager = lager;
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

    public List<Paafyldning> getPaafyldninger() {
        return new ArrayList<>(paafyldninger);
    }


    public void paafyld(int antalLiterPaafyldt, Paafyldning paafyldning) {
        if (this.antalLiterPaafyldt + antalLiterPaafyldt <= stoerrelse) {
            this.antalLiterPaafyldt += antalLiterPaafyldt;
            paafyldninger.add(paafyldning);
        } else throw new IllegalArgumentException("prøver at overfylde fadet");
    }

    public void aftap(int literTapet, Tapning tapning) {
        if (literTapet <= antalLiterPaafyldt) {
            antalLiterPaafyldt -= literTapet;
            tapninger.add(tapning);
        } else {
            throw new IllegalArgumentException("der er ikke nok liter");
        }
    }

    public ArrayList<Tapning> getTapninger() {
        return new ArrayList<>(tapninger);
    }

    public void addTapning(Tapning tapning) {
        if (!tapninger.contains(tapning)) {
            tapninger.add(tapning);
            tapning.addFad(this);
        }
    }

    public void removeTapning(Tapning tapning) {
        if (tapninger.contains(tapning)) {
            tapninger.remove(tapning);
            tapning.removeFad(this);
        }
    }

    public String toString() {
        return nummer + " " + type;
    }

}
