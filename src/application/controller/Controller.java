package application.controller;

import application.model.Fad;
import application.model.Flaske;
import application.model.Medarbejder;

import java.util.ArrayList;
import java.util.List;

public class Controller {


    public List<Flaske> fyldPaaFlasker(List<Fad> fade, List<Integer> literTapet, int Vand, Medarbejder medarbejder, String flaskeNavn, double alkoholprocent){
        List<Flaske> flasker = new ArrayList<>();
        int vaeske = Vand;
        for (int i = 0; i < fade.size(); i++) {
            Fad fad = fade.get(i);
            int liter = literTapet.get(i);
            fad.aftap(medarbejder,liter);
            vaeske += liter;
        }

        for (int i = 0; i < vaeske; i++) {
            Flaske flaske = new Flaske(flaskeNavn, alkoholprocent);
            for (Fad fad : fade) {
                flaske.addFad(fad);
            }
            flasker.add(flaske);
        }
        return flasker;
    }
}
