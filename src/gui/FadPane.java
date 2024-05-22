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
    private TextArea txaHistorik;





    public FadPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(30));
        this.setHgap(30);
        this.setVgap(30);

        VBox vbxTommeFade = new VBox(10);
        this.add(vbxTommeFade, 0, 0, 3, 10);
        Label lblTommeFade = new Label("Tomme fade");
        vbxTommeFade.getChildren().add(lblTommeFade);
        lvwTommeFade.getItems().setAll(Controller.getTommeFade());
        vbxTommeFade.getChildren().add(lvwTommeFade);
        lvwTommeFade.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwTommeFade.setPrefWidth(185);

        VBox vbxFyldteFade = new VBox(10);
        vbxFyldteFade.setPrefWidth(185);
        this.add(vbxFyldteFade, 3, 0, 2, 10);
        Label lblFyldteFade = new Label("Påfyldte fade");
        vbxFyldteFade.getChildren().add(lblFyldteFade);
        lvwPaafyldteFade.getItems().setAll(Controller.getFyldteFade());
        vbxFyldteFade.getChildren().add(lvwPaafyldteFade);
        lvwPaafyldteFade.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox vbxHistorik = new VBox(10);
        this.add(vbxHistorik, 5,0,2,10);
        Label lblHistorik = new Label("Fad info");
        vbxHistorik.getChildren().add(lblHistorik);
        txaHistorik = new TextArea();
        txaHistorik.setPrefHeight(400);
        vbxHistorik.getChildren().add(txaHistorik);


        Button btnOpretFad = new Button("Opret fad");
        this.add(btnOpretFad, 0, 10);
        btnOpretFad.setOnAction(event -> opretFad());


        Button btnFadInfo = new Button("Fad info");
        this.add(btnFadInfo, 5, 10);
        btnFadInfo.setOnAction(event -> infoAction());

        Button btnOmhaeld = new Button("Omhæld");
        add(btnOmhaeld,3,10);
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
        if (tomFad != null && paafyldtFad != null ) {
            lvwTommeFade.getSelectionModel().clearSelection();
            lvwPaafyldteFade.getSelectionModel().clearSelection();
            alertPopUp("Fad info", "For mange fade valgt", "Vælg kun et fad fra listerne");
        } else if (paafyldtFad != null) {
            infoFyldt();
        } else if (tomFad != null) {
            infotomt();
        } else {
            alertPopUp("Mangler fade", "Intet fad valgt", "Vælg et fad fra listerne");
        }
    }

    private void infoFyldt() {
        Fad fad = lvwPaafyldteFade.getSelectionModel().getSelectedItem();
        txaHistorik.setText(fad.historik());
    }

    private void infotomt() {
        Fad fad = lvwTommeFade.getSelectionModel().getSelectedItem();
        txaHistorik.setText(fad.historik());
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
