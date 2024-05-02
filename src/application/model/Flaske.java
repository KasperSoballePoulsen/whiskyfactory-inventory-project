package application.model;

public class Flaske {
    private int liter;
    private String navn;

    public Flaske(int liter, String navn) {
        this.liter = liter;
        this.navn = navn;
    }

    public int getLiter() {
        return liter;
    }

    public String getNavn() {
        return navn;
    }
}
