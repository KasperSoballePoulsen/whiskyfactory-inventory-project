package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Paafyldning {

    private LocalDate dato;
    private Medarbejder medarbejder;
    private List<Destilat> destilater = new ArrayList<>();
    ;
    private Fad fad;

    public LocalDate getDato() {
        return dato;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public List<Destilat> getDestilater() {
        return new ArrayList<>(destilater);
    }

    public Fad getFad() {
        return fad;
    }

    public void addDestilat(Destilat destilat) {
        if (!destilater.contains(destilat)) {
            destilater.add(destilat);
            destilat.addPaafyldning(this);
        }
    }

    public void fyldFad(List<Integer> liter, Medarbejder medarbejder) {
        this.medarbejder = medarbejder;
        dato = LocalDate.now();
        for (int i = 0; i < destilater.size(); i++) {
            Destilat destilat = destilater.get(i);
            int vaeske = liter.get(i);
            fad.paafyld(vaeske, this);
            destilat.setMaengdeVaeskeILiter(destilat.getMaengdeVaeskeILiter() - vaeske);
        }
    }

}
