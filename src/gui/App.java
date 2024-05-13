package gui;

import application.controller.Controller;
import javafx.application.Application;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Controller.opretLager("Ralle", 10);
        Controller.opretDestilat("lol","lol", LocalDate.now(),LocalDate.now(),"Swag",10,10,"Ralle");
        Controller.opretDestilat("lol2","lol", LocalDate.now(),LocalDate.now(),"Swag",10,10,"Ralle");
        Application.launch(StartWindow.class);
    }
}
