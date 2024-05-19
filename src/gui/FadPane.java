package gui;

import application.controller.Controller;
import application.model.Fad;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class FadPane extends GridPane {
    private final ListView<Fad> lvwTommeFade = new ListView<>();
    private final ListView<Fad> lvwPaafyldteFade = new ListView<>();




    public FadPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(30));
        this.setHgap(30);
        this.setVgap(30);

        VBox vbxTommeFade = new VBox(10);
        this.add(vbxTommeFade, 0, 0, 2, 5);
        Label lblTommeFade = new Label("Tomme fade");
        vbxTommeFade.getChildren().add(lblTommeFade);
        lvwTommeFade.setPrefHeight(150);
        lvwTommeFade.getItems().setAll(Controller.getTommeFade());
        vbxTommeFade.getChildren().add(lvwTommeFade);
        lvwTommeFade.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox vbxFyldteFade = new VBox(10);
        this.add(vbxFyldteFade, 2, 0, 2, 5);
        Label lblFyldteFade = new Label("Påfyldte fade");
        vbxFyldteFade.getChildren().add(lblFyldteFade);
        lvwPaafyldteFade.setPrefHeight(150);
        lvwPaafyldteFade.getItems().setAll(Controller.getFyldteFade());
        lvwPaafyldteFade.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        vbxFyldteFade.getChildren().add(lvwPaafyldteFade);
        lvwPaafyldteFade.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Button btnOpretFad = new Button("Opret fad");
        this.add(btnOpretFad, 0, 5);
        btnOpretFad.setOnAction(event -> opretFad());


        Button btnFadInfo = new Button("Fad info");
        this.add(btnFadInfo, 2, 5);
        btnFadInfo.setOnAction(event -> infoAction());





        Button btnOmhaeld = new Button("Omhæld");
        add(btnOmhaeld,1,5);
        btnOmhaeld.setOnAction(event -> omhaeld());



    }



    public void opretFad() {
        OpretFadWindow dia = new OpretFadWindow("Opret Fad");
        dia.showAndWait();
        lvwTommeFade.getItems().setAll(Controller.getTommeFade());
    }

    public void infoAction() {
        Fad tomFad = lvwTommeFade.getSelectionModel().getSelectedItem();
        Fad paafyldtFad = lvwPaafyldteFade.getSelectionModel().getSelectedItem();
        if (tomFad != null || paafyldtFad != null) {
            if (tomFad != null) {
                infotomt();
            } else if (paafyldtFad != null) {
                infoFyldt();
            }
        } else {
            alertPopUp("Mangler fad", "Intet fad valgt", "Vælg fad fra listerne");
        }
    }

    private void infoFyldt() {
        Fad fad = lvwPaafyldteFade.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(fad.toString());
        alert.setContentText(fad.historik());
        alert.showAndWait();
    }

    private void infotomt() {
        Fad fad = lvwTommeFade.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(fad.toString());
        alert.setContentText(fad.historik());
        alert.showAndWait();
    }
    public void omhaeld(){
        Fad nytFad = lvwTommeFade.getSelectionModel().getSelectedItem();
        Fad gamleFad = lvwPaafyldteFade.getSelectionModel().getSelectedItem();
        if (nytFad != null && gamleFad != null) {
            Controller.flytPaafyldning(gamleFad, nytFad);
            updateControls();
        } else {
            alertPopUp("Mangler fade", "Vælg to fade", "Vælg fade fra listerne for at omhælde");
        }
    }

    public void updateControls() {
        lvwPaafyldteFade.getItems().setAll(Controller.getFyldteFade());
        lvwTommeFade.getItems().setAll(Controller.getTommeFade());

    }

    private void alertPopUp(String titel, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }




}
