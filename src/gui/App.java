package gui;

import application.controller.Controller;
import javafx.application.Application;

public class App {
    public static void main(String[] args) {
        Controller.createSomeObjects();
        Application.launch(StartWindow.class);

    }
}
