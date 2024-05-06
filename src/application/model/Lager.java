package application.model;

import java.util.NoSuchElementException;

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
    public Fad createFad(int nummer,String type, int stoerrelse, String leverandør){
        Fad fad = new Fad(nummer,type,stoerrelse,this,leverandør);
        int pladsNr = findFørsteLedigePlads();
        pladser[pladsNr] = fad;
        return fad;
    }

    public int findFørsteLedigePlads(){
        boolean found = false;
        int i = 0;
        int k = 0;
        while (!found && i < pladser.length){
             k = i;
            if (pladser[k] == null){
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
}
