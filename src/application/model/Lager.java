package application.model;

public class Lager {
    private Fad[] pladser;
    private String navn;

    public Lager(String navn, int antalPladser) {
        pladser = new Fad[antalPladser];
        this.navn = navn;
    }

    public Fad[] getPladser() {
        return pladser;
    }

    public String getNavn() {
        return navn;
    }

    public Fad createFad(String type, int stoerrelse, String leverandoer) {
        Fad fad = new Fad(type, stoerrelse, this, leverandoer);
        int pladsNr = findLedigPlads();
        pladser[pladsNr] = fad;
        return fad;
    }


    public int findLedigPlads() {
        boolean found = false;
        int i = 0;
        int k = 0;
        while (!found && i < pladser.length) {
            k = i;
            if (pladser[k] == null) {
                found = true;
            }
            i++;
        }
        if (found) {
            return k;
        } else {
            throw new IndexOutOfBoundsException("lager er fyldt");
        }
    }

    public int antalLedigePladser() {
        int k = 0;
        for (int i = 0; i < pladser.length; i++) {
            if (pladser[i] == null) {
                k++;
            }
        }
        return k;
    }

    public String toString(){
        return navn;
    }
}
