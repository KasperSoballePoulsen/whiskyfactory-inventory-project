package application.model;

import java.time.LocalDate;

public class Batch {
    private String navn;
    private String kornsort;
    private LocalDate startdato;
    private LocalDate slutdato;
    private String maltBatch;
    private int mængdeVæskeILiter;
    private double alkoholprocent;
    private Medarbejder medarbejder;

    public Batch(String navn, String kornsort, LocalDate startDato, LocalDate slutDato, String maltBatch, int mængdeVæskeILiter, double alkoholProcent, Medarbejder medarbejder) {
        this.navn = navn;
        this.kornsort = kornsort;
        this.startdato = startDato;
        this.slutdato = slutDato;
        this.maltBatch = maltBatch;
        this.mængdeVæskeILiter = mængdeVæskeILiter;
        this.alkoholprocent = alkoholProcent;
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

    public String getMaltBatch() {
        return maltBatch;
    }

    public int getMængdeVæskeILiter() {
        return mængdeVæskeILiter;
    }

    public double getAlkoholprocent() {
        return alkoholprocent;
    }
}
