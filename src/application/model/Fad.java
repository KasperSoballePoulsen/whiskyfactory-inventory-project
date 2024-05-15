package application.model;

import java.util.ArrayList;
import java.util.List;

public class Fad {
    private static int nummerCount;
    private int nummer;
    private String type;
    private int literKapacitet;
    private int antalLiterPaafyldt;
    private String leverandoer;
    private Lager lager;
    private final List<Tapning> tapninger = new ArrayList<>();
    private final List<Paafyldning> paafyldninger = new ArrayList<>();


    Fad(String type, int literKapacitet, Lager lager, String leverandoer) {

        this.nummer = nummerCount + 1;
        this.type = type;
        this.literKapacitet = literKapacitet;
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

    public int getLiterKapacitet() {
        return literKapacitet;
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


    /**
     * @param antalLiterSomPaafyldes
     * @param paafyldning
     */
    public void paafyld(int antalLiterSomPaafyldes, Paafyldning paafyldning) {
        if (this.antalLiterPaafyldt + antalLiterSomPaafyldes <= literKapacitet) {
            this.antalLiterPaafyldt += antalLiterSomPaafyldes;
            paafyldninger.add(paafyldning);
        } else throw new IllegalArgumentException("prøver at overfylde fadet");
    }

    public void aftap(int literTappet, Tapning tapning) {
        if (literTappet <= antalLiterPaafyldt) {
            antalLiterPaafyldt -= literTappet;
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

    /**
     * Pre: paafyldninger != null
     *
     * @return en string med hele historien bag fadet. Alle destillaternes informationer
     */
    public String historik() {
        String res = "";

        if (paafyldninger.size() != 0) {
            Paafyldning paafyldning = sidstePaafyldning();
            for (Destillat destilat : paafyldning.getDestillater()) {
                res += "Destilat: " + destilat.getNavn() + "\n";
                res += "Korn sort: " + destilat.getKornsort() + "\n";
                res += "Malt destilat: " + destilat.getMaltdestillat() + "\n";
                res += "Startede destillation: " + destilat.getStartdato() + " og sluttede: " + destilat.getSlutdato() + "\n";
                res += "--------------------------\n";
            }
            res += "blev fyldt: " + paafyldning.getDato() + "\n";
            res += "--------------------------\n";
        }
        res += "fad: " + nummer + "\n";
        res += "type: " + type + "\n";
        res += "leverandør: " + leverandoer + "\n";
        res += "lå på lager: " + lager.getNavn() + "\n";


        return res;
    }

    public Paafyldning sidstePaafyldning() {
        return paafyldninger.get(paafyldninger.size() - 1);
    }

    public String toString() {
        if (antalLiterPaafyldt == 0) {
            return nummer + " " + type + " " + literKapacitet;
        } else {
            return nummer + " " + type + " " + literKapacitet + ". " + antalLiterPaafyldt + " liter påfyldt " + sidstePaafyldning().getDato();
        }
    }

}
