package application.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Fad implements Serializable {
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
     * @param paafyldning != null
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
        res += "Nr.: " + nummer + "\n";
        res += "Type: " + type + "\n";
        res += "Literkapacitet: " + literKapacitet + "\n";
        res += "Leverandør: " + leverandoer + "\n";
        res += "Lagerlokation: " + lager.getNavn() + "\n";
        res += "\n\t\tTIDLIGERE PÅFYLDNINGER PÅ FAD nr. " + nummer + "\n";

        if (paafyldninger.size() != 0) {
            for (int i = 0; i < paafyldninger.size(); i++) {
                Paafyldning paafyldning = paafyldninger.get(i);
                res += "Påfyldningsdato: " + paafyldning.getDato() + "\n";
                /*if (tapninger.size() != 0) {
                    res += "Tapningsdato: " + tapninger.get(i).getDato() + "\n";
                }*/
                res += "Medarbejder: " + paafyldning.getMedarbejder() + "\n";
                for (Destillat destilat : paafyldning.getDestillater()) {
                    res += "Destillat info:\n";
                    res += "\tNavn: " + destilat.getNavn() + "\n";
                    res += "\tKornsort: " + destilat.getKornsort() + "\n";
                    res += "\tMaltdestillat: " + destilat.getMaltdestillat() + "\n";
                    res += "\tAlkoholprocent: " + destilat.getAlkoholprocent() + "\n";
                    res += "\tStartdato: " + destilat.getStartdato() + "\n";
                    res += "\tSlutdato: " + destilat.getSlutdato() + "\n";
                    res += "\tMedarbejder: " + destilat.getMedarbejder() + "\n";
                }
                res += "\n";
            }
        } else {
            res += "Ingen tidligere påfyldninger";
        }
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
            return "nr. " + nummer + ": " + type + ", " + literKapacitet + " liter";
        } else {
            return "nr. " + nummer + ": " + type + ", " + literKapacitet + " liter. Liter påfyldt: " + antalLiterPaafyldt;
        }
    }

}
