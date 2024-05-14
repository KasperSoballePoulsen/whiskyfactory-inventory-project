package gui;

import application.controller.Controller;
import application.model.Fad;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class FadPane extends GridPane {
    private final ListView<Fad> lvwTommeFade = new ListView<>();
    private final ListView<Fad> lvwFyldteFade = new ListView<>();

    public FadPane() {
        this.setGridLinesVisible(true);
        this.setPadding(new Insets(30));
        this.setHgap(30);
        this.setVgap(30);

        VBox vbxTommeFade = new VBox(10);
        this.add(vbxTommeFade, 0,0,2,5);
        Label lblTommeFade = new Label("Tomme fade");
        vbxTommeFade.getChildren().add(lblTommeFade);
        lvwTommeFade.setPrefHeight(150);
        lvwTommeFade.getItems().setAll(Controller.getTommeFade());
        vbxTommeFade.getChildren().add(lvwTommeFade);
        lvwTommeFade.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox vbxFyldteFade = new VBox(10);
        this.add(vbxFyldteFade, 2,0,2,5);
        Label lblFyldteFade = new Label("Fyldte fade");
        vbxFyldteFade.getChildren().add(lblFyldteFade);
        lvwFyldteFade.setPrefHeight(150);
        lvwFyldteFade.getItems().setAll(Controller.getFyldteFade());
        lvwFyldteFade.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        vbxFyldteFade.getChildren().add(lvwFyldteFade);
        lvwFyldteFade.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Button btnOpretFad = new Button("Opret fad");
        this.add(btnOpretFad,0,5);
        btnOpretFad.setOnAction(event -> opretFad());



        Button btnFadInfo = new Button("Fad info");
        this.add(btnFadInfo,2,5);

        Button btnAftapFad = new Button("Aftap fad");
        this.add(btnAftapFad,3,5);
        btnAftapFad.setOnAction(event -> openTapning());

    }

    public void openTapning(){
        TapningWindow dia = new TapningWindow("Tapning", lvwFyldteFade.getSelectionModel().getSelectedItems());
        dia.showAndWait();
        lvwFyldteFade.getItems().setAll(Controller.getFyldteFade());
    }

    public void opretFad(){
        OpretFadWindow dia = new OpretFadWindow("Opret Fad");
        dia.showAndWait();
        lvwTommeFade.getItems().setAll(Controller.getTommeFade());
    }

    public void updateControls(){
        lvwFyldteFade.getItems().setAll(Controller.getFyldteFade());
        lvwTommeFade.getItems().setAll(Controller.getTommeFade());

    }







}
