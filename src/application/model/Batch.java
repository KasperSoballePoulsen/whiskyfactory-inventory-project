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
