package gui;

import application.controller.Controller;
import application.model.*;
import javafx.application.Application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        initStorage();
        System.out.println(Controller.getTapninger().get(0).getFlasker().get(0).historik());
        Application.launch(StartWindow.class);

    }

    public static void initStorage(){
        Lager lager = Controller.opretLager("Ralle", 10);
        Fad fad1 = Controller.opretFad("Cherry",20,lager,"Spanien");
        Fad fad2 = Controller.opretFad("Cherry",20,lager,"Spanien");
        Fad fad3 = Controller.opretFad("Cherry",20,lager,"Spanien");
        Fad fad4 = Controller.opretFad("Cherry",20,lager,"Spanien");
        List<Fad> fadList = new ArrayList<>();
        fadList.add(fad1);
        fadList.add(fad2);
        fadList.add(fad4);

        Destilat destilat1 = Controller.opretDestilat("lol","lol", LocalDate.now(),LocalDate.now(),"Swag",1000,10,"Ralle");
        Destilat destilat2 = Controller.opretDestilat("lol2","lol", LocalDate.now(),LocalDate.now(),"Swag",1000,10,"Ralle");
        List<Destilat> destilatList = new ArrayList<>();
        destilatList.add(destilat1);
        destilatList.add(destilat2);
        List<Integer> fyldningstal = new ArrayList<>();
        fyldningstal.add(5);
        fyldningstal.add(10);
        Controller.paaFyldFad(destilatList,fad1,fyldningstal,LocalDate.now(),"snavær");
        Controller.paaFyldFad(destilatList,fad2,fyldningstal,LocalDate.now(),"snavær");
        Controller.paaFyldFad(destilatList,fad4,fyldningstal,LocalDate.now(),"snavær");


        List<Integer> literlist = new ArrayList<>();
        literlist.add(15);
        literlist.add(10);
        literlist.add(5);
        Controller.aftapFad(fadList,literlist,LocalDate.now(),"snavær",20,"MULD",6.4);

    }
}
