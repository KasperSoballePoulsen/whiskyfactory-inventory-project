package application.model;

import java.time.LocalDate;

public class Destilat {
    private String navn;
    private String kornsort;
    private LocalDate startdato;
    private LocalDate slutdato;
    private String maltDestilat;
    private int mængdeVæskeILiter;
    private double alkoholprocent;
    private Medarbejder medarbejder;

    public Destilat(String navn, String kornsort, LocalDate startdato, LocalDate slutdato, String maltDestilat, int mængdeVæskeILiter, double alkoholprocent, Medarbejder medarbejder) {
        this.navn = navn;
        this.kornsort = kornsort;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.maltDestilat = maltDestilat;
        this.mængdeVæskeILiter = mængdeVæskeILiter;
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

    public String getMaltDestilat() {
        return maltDestilat;
    }

    public int getMængdeVæskeILiter() {
        return mængdeVæskeILiter;
    }

    public double getAlkoholprocent() {
        return alkoholprocent;
    }
}
