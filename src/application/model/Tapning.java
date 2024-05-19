package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tapning {

    private final List<Fad> fade = new ArrayList<>();
    private final List<Flaske> flasker = new ArrayList<>();
    private String medarbejder;
    private LocalDate dato;

    public Tapning(LocalDate dato, String medarbejder) {
        this.dato = dato;
        this.medarbejder = medarbejder;
    }

    /**
     * @param navn
     * @param alkoholprocent
     * @return Flaske objekt
     */
    public Flaske createFlaske(String navn, double alkoholprocent) {
        Flaske flaske = new Flaske(navn, alkoholprocent, this);
        this.addFlaske(flaske);
        return flaske;
    }

    public List<Flaske> getFlasker() {
        return new ArrayList<>(flasker);
    }

    public void addFlaske(Flaske flaske) {
        if (!flasker.contains(flaske)) {
            flasker.add(flaske);
        }
    }

    public List<Fad> getFade() {
        return new ArrayList<>(fade);
    }

    public LocalDate getDato() {
        return dato;
    }

    public void addFad(Fad fad) {
        if (!fade.contains(fad)) {
            fade.add(fad);
            fad.addTapning(this);
        }
    }

    public void removeFad(Fad fad) {
        if (fade.contains(fad)) {
            fade.remove(fad);
            fad.removeTapning(this);
        }
    }


    /**
     * Pre: literTappet.size() == fade.size()
     *
     * @param literTappet != null
     * @param vand
     * @param medarbejder != null
     * @param flaskeNavn != null
     * @param alkoholprocent != null
     * først trækker vi literne fra fadet og summere literne, for alle fade
     * så laver vi en flaske for hver liter væske vi har.
     * @return List<Flaske> med flaskerne der er oprettet
     */
    public List<Flaske> fyldPaaFlasker(List<Integer> literTappet, int vand, String medarbejder, String flaskeNavn, double alkoholprocent) {
        List<Flaske> flasker = new ArrayList<>();
        int vaeske = vand;
        for (int i = 0; i < fade.size(); i++) {
            Fad fad = fade.get(i);
            int liter = literTappet.get(i);
            fad.aftap(liter, this);
            vaeske += liter;
        }


        for (int i = 0; i < vaeske; i++) {
            Flaske flaske = this.createFlaske(flaskeNavn, alkoholprocent);
            flasker.add(flaske);
        }
        return flasker;
    }
}
