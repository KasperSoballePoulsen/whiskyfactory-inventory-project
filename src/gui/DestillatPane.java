package gui;

import application.controller.Controller;
import application.model.Destillat;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DestillatPane extends GridPane {

    private final ListView<Destillat> lvwDestillater = new ListView<>();
    public DestillatPane() {
        this.setGridLinesVisible(true);
        this.setPadding(new Insets(30));
        this.setHgap(30);
        this.setVgap(30);

        VBox vbxDestillater = new VBox(10);
        this.add(vbxDestillater, 0,0,2,5);
        Label lblDestilater = new Label("Destillater");
        vbxDestillater.getChildren().add(lblDestilater);
        lvwDestillater.setPrefHeight(150);
        lvwDestillater.getItems().setAll(Controller.getDestillater());
        vbxDestillater.getChildren().add(lvwDestillater);

        Button btnOpretDestillat = new Button("Opret destillat");
        this.add(btnOpretDestillat,1,5);
        btnOpretDestillat.setOnAction(event -> opretDestillat());
                
        Button btnPaafyldFad = new Button("Påfyld fad");
        this.add(btnPaafyldFad,0,5);
        btnPaafyldFad.setOnAction(event -> paafyld());

    }

    public void opretDestillat(){
        OpretDestillatWindow dia = new OpretDestillatWindow("Opret Destillat");
        dia.showAndWait();
        lvwDestillater.getItems().setAll(Controller.getDestillater());
    }
        
    public void paafyld(){
        PaafyldningWindow dia = new PaafyldningWindow("Påfyld", lvwDestillater.getSelectionModel().getSelectedItems());
        dia.showAndWait();

    }
}
