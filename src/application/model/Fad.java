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
        if (tapning.getDato().isBefore(this.sidstePaafyldning().getDato().plusYears(3))){
            throw new IllegalArgumentException("det er ikke klar til tapning");
        }
        if (literTappet <= antalLiterPaafyldt) {
            antalLiterPaafyldt -= literTappet;
            tapninger.add(tapning);

        } else {
            throw new IllegalArgumentException("Der er ikke liter nok på fadet");
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
     *
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
            for (Fad fad : paafyldning.getGamleFad()) {
                res += "fad: " + fad.getNummer() + "\n";
                res += "type: " + fad.getType() + "\n";
                res += "leverandør: " + fad.getLeverandoer() + "\n";
                res += "lå på lager: " + fad.getLager().getNavn() + "\n";
                res += "--------------------------\n";
            }

        }
        res += "fad: " + nummer + "\n";
        res += "type: " + type + "\n";
        res += "leverandør: " + leverandoer + "\n";
        res += "lå på lager: " + lager.getNavn() + "\n";


        return res;
    }
    public void addPaafyldning(Paafyldning paafyldning){
        if (!paafyldninger.contains(paafyldning)){
            paafyldninger.add(paafyldning);
        }
    }
    public void flytPaafyldning(Fad fad){
        Paafyldning paafyldning = sidstePaafyldning();
        paafyldning.setFad(fad);
        fad.addPaafyldning(paafyldning);
        fad.setAntalLiterPaafyldt(antalLiterPaafyldt);
        setAntalLiterPaafyldt(0);

    }

    public void setAntalLiterPaafyldt(int antalLiterPaafyldt) {
        this.antalLiterPaafyldt = antalLiterPaafyldt;
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
