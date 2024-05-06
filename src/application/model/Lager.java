package application.model;

public class Lager {
    private Fad[] pladser;

    private String navn;

    public Lager(String navn, int antalPladser){
        pladser = new Fad[antalPladser];
        this.navn = navn;
    }

    public Fad[] getPladser() {
        return pladser;
    }

    public String getNavn() {
        return navn;
    }
}
