package application.model;

import java.time.LocalDate;

public class Batch {
    private String navn;
    private String kornsort;
    private LocalDate startDato;
    private LocalDate slutDato;
    private String maltBatch;
    private int mængdeVæskeILiter;
    private double alkoholProcent;
    private Medarbejder medarbejder;

    public Batch(String navn, String kornsort, LocalDate startDato, LocalDate slutDato, String maltBatch, int mængdeVæskeILiter, double alkoholProcent, Medarbejder medarbejder) {
        this.navn = navn;
        this.kornsort = kornsort;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.maltBatch = maltBatch;
        this.mængdeVæskeILiter = mængdeVæskeILiter;
        this.alkoholProcent = alkoholProcent;
        this.medarbejder = medarbejder;
    }

    public String getNavn() {
        return navn;
    }

    public String getKornsort() {
        return kornsort;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public String getMaltBatch() {
        return maltBatch;
    }

    public int getMængdeVæskeILiter() {
        return mængdeVæskeILiter;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }
}
