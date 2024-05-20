package application.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillat implements Serializable {
    private String navn;
    private String kornsort;
    private LocalDate startdato;
    private LocalDate slutdato;
    private String maltdestillat;
    private int maengdeVaeskeILiter;
    private double alkoholprocent;
    private String medarbejder;
    private final List<Paafyldning> paafyldninger;


    public Destillat(String navn, String kornsort, LocalDate startdato, LocalDate slutdato, String maltdestillat, int maengdeVaeskeILiter, double alkoholprocent, String medarbejder) {
        this.navn = navn;
        this.kornsort = kornsort;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.maltdestillat = maltdestillat;
        this.maengdeVaeskeILiter = maengdeVaeskeILiter;
        this.alkoholprocent = alkoholprocent;
        this.medarbejder = medarbejder;
        paafyldninger = new ArrayList<>();
    }

    public String getNavn() {
        return navn;
    }

    public String getKornsort() {
        return kornsort;
    }

    public LocalDate getStartdato() {
        return startdato;
    }

    public LocalDate getSlutdato() {
        return slutdato;
    }

    public String getMaltdestillat() {
        return maltdestillat;
    }

    public int getMaengdeVaeskeILiter() {
        return maengdeVaeskeILiter;
    }

    public String getMedarbejder() {
        return medarbejder;
    }

    public void setMaengdeVaeskeILiter(int vaeskeILiter) {
        maengdeVaeskeILiter = vaeskeILiter;
    }

    public void addPaafyldning(Paafyldning paafyldning) {
        if (!paafyldninger.contains(paafyldning)) {
            paafyldninger.add(paafyldning);
            paafyldning.addDestillat(this);
        }
    }

    public double getAlkoholprocent() {
        return alkoholprocent;
    }

    public String toString() {
        return navn + " " + maengdeVaeskeILiter + " liter";
    }
}
