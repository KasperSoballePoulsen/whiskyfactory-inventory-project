package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Paafyldning {

    private LocalDate dato;
    private String medarbejder;
    private final List<Destillat> destillater = new ArrayList<>();
    private Fad fad;

    public Paafyldning(LocalDate dato, String medarbejder, Fad fad){
        this.dato = dato;
        this.medarbejder = medarbejder;
        this.fad = fad;
    }

    public LocalDate getDato() {
        return dato;
    }

    public String getMedarbejder() {
        return medarbejder;
    }

    public List<Destillat> getDestillater() {
        return new ArrayList<>(destillater);
    }

    public Fad getFad() {
        return fad;
    }

    public void addDestillat(Destillat destillat) {
        if (!destillater.contains(destillat)) {
            destillater.add(destillat);
            destillat.addPaafyldning(this);
        }
    }

    public void fyldFad(List<Integer> liter) {

        for (int i = 0; i < destillater.size(); i++) {
            Destillat destillat = destillater.get(i);
            int vaeske = liter.get(i);
            fad.paafyld(vaeske, this);
            destillat.setMaengdeVaeskeILiter(destillat.getMaengdeVaeskeILiter() - vaeske);
        }
    }

}
