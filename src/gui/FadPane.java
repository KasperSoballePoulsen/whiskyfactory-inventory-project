package gui;

import application.controller.Controller;
import application.model.Fad;
import application.model.Lager;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class FadPane extends GridPane {
    private final ListView<Fad> lvwTommeFade = new ListView<>();
    private final ListView<Fad> lvwFyldteFade = new ListView<>();
    private Label lblTapErr;



    public FadPane() {
        this.setGridLinesVisible(true);
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
        Label lblFyldteFade = new Label("Fyldte fade");
        vbxFyldteFade.getChildren().add(lblFyldteFade);
        lvwFyldteFade.setPrefHeight(150);
        lvwFyldteFade.getItems().setAll(Controller.getFyldteFade());
        lvwFyldteFade.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        vbxFyldteFade.getChildren().add(lvwFyldteFade);
        lvwFyldteFade.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Button btnOpretFad = new Button("Opret fad");
        this.add(btnOpretFad, 0, 5);
        btnOpretFad.setOnAction(event -> opretFad());


        Button btnFadInfo = new Button("Fad info");
        this.add(btnFadInfo, 2, 5);
        btnFadInfo.setOnAction(event -> infoAction());

        lblTapErr = new Label("");
        lblTapErr.setStyle("-fx-text-fill: red");
        add(lblTapErr, 3, 6);

        Button btnAftapFad = new Button("Aftap fad");
        this.add(btnAftapFad, 3, 5);
        btnAftapFad.setOnAction(event -> openTapning());



    }

    public void openTapning() {
        if (lvwFyldteFade.getSelectionModel().getSelectedItems().size() != 0) {
            TapningWindow dia = new TapningWindow("Tapning", lvwFyldteFade.getSelectionModel().getSelectedItems());
            dia.showAndWait();
            lvwFyldteFade.getItems().setAll(Controller.getFyldteFade());
        } else {
            lblTapErr.setText("Vælg fyldt fad");
        }
    }

    public void opretFad() {
        OpretFadWindow dia = new OpretFadWindow("Opret Fad");
        dia.showAndWait();
        lvwTommeFade.getItems().setAll(Controller.getTommeFade());
    }

    public void infoAction() {
        if (lvwTommeFade.getSelectionModel().getSelectedItem() != null) {
            infotomt();
        } else if (lvwFyldteFade.getSelectionModel().getSelectedItem() != null) {
            infoFyldt();
        }
    }

    private void infoFyldt() {
        Fad fad = lvwFyldteFade.getSelectionModel().getSelectedItem();
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

    public void updateControls() {
        lvwFyldteFade.getItems().setAll(Controller.getFyldteFade());
        lvwTommeFade.getItems().setAll(Controller.getTommeFade());

    }




}
