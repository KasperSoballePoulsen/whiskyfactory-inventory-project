package application.model;

import java.time.LocalDate;

public class Destilat {
    private String navn;
    private String kornsort;
    private LocalDate startdato;
    private LocalDate slutdato;
    private String maltdestilat;
    private int maengdeVaeskeILiter;
    private double alkoholprocent;
    private Medarbejder medarbejder;


    public Destilat(String navn, String kornsort, LocalDate startdato, LocalDate slutdato, String maltdestilat, int maengdeVaeskeILiter, double alkoholprocent, Medarbejder medarbejder) {
        this.navn = navn;
        this.kornsort = kornsort;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.maltdestilat = maltdestilat;
        this.maengdeVaeskeILiter = maengdeVaeskeILiter;
        this.alkoholprocent = alkoholprocent;
        this.medarbejder = medarbejder;
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

    public String getMaltdestilat() {
        return maltdestilat;
    }

    public int getMaengdeVaeskeILiter() {
        return maengdeVaeskeILiter;
    }

    public double getAlkoholprocent() {
        return alkoholprocent;
    }
}
