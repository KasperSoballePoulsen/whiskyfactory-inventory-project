package gui;

import application.controller.Controller;
import application.model.Destilat;
import application.model.Fad;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DestilatPane extends GridPane {

    private final ListView<Destilat> lvwDestilater = new ListView<>();
    public DestilatPane() {
        this.setGridLinesVisible(true);
        this.setPadding(new Insets(30));
        this.setHgap(30);
        this.setVgap(30);

        VBox vbxDestilater = new VBox(10);
        this.add(vbxDestilater, 0,0,2,5);
        Label lblDestilater = new Label("Destilater");
        vbxDestilater.getChildren().add(lblDestilater);
        lvwDestilater.setPrefHeight(150);
        lvwDestilater.getItems().setAll(Controller.getDestilater());
        vbxDestilater.getChildren().add(lvwDestilater);

        Button btnOpretDestilat = new Button("Opret destilat");
        this.add(btnOpretDestilat,1,5);
        btnOpretDestilat.setOnAction(event -> opretDestilat());

        Button btnPaafyldFad = new Button("Påfyld fad");
        this.add(btnPaafyldFad,0,5);
        btnPaafyldFad.setOnAction(event -> paafyld());


    }

    public void opretDestilat(){
        OpretDestilatWindow dia = new OpretDestilatWindow("Opret Destilat");
        dia.showAndWait();
        lvwDestilater.getItems().setAll(Controller.getDestilater());
    }

    public void paafyld(){
        PaafyldningWindow dia = new PaafyldningWindow("Påfyld", lvwDestilater.getSelectionModel().getSelectedItems());
        dia.showAndWait();

    }



    /*public void updateControls() {

    }
     */
}
