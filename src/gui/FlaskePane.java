package gui;

import application.controller.Controller;
import application.model.Flaske;
import application.model.Tapning;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class FlaskePane extends GridPane {


    private ListView<Flaske> lvwFlasker;
    private TextField txfAntal;
    private TextArea txaHistorik;

    public FlaskePane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(30));
        this.setHgap(30);
        this.setVgap(10);

        lvwFlasker = new ListView<>();
        lvwFlasker.getItems().setAll(flaskeList());
        lvwFlasker.setPrefHeight(400);
        add(lvwFlasker, 0, 0, 1, 20);
        ChangeListener<Flaske> flaskeChangeListener = (ov, oldFlaske, newFlaske) -> flaskeSelectedChanged();
        lvwFlasker.getSelectionModel().selectedItemProperty().addListener(flaskeChangeListener);

        Button btnHistorik = new Button("Vis historik");
        btnHistorik.setOnAction(event -> historikAction());
        add(btnHistorik, 1, 2);

        Label lblAntal = new Label("antal:");
        add(lblAntal, 1, 0);
        txfAntal = new TextField();
        txfAntal.setEditable(false);
        add(txfAntal, 1, 1);
        txfAntal.setPrefWidth(50);

        Label lblHistorik = new Label("Historik");
        add(lblHistorik,2,0);
        txaHistorik = new TextArea();
        add(txaHistorik,2,1,1,19);
        txaHistorik.setPrefWidth(350);
        txaHistorik.setPrefHeight(400);
        txaHistorik.setEditable(false);


    }

    public void historikAction() {
        Flaske flaske = lvwFlasker.getSelectionModel().getSelectedItem();
        if (flaske != null) {
            txaHistorik.setText(flaske.historik());
        } else {
            alertPopUp("Fejl", "Ingen flaske valgt", "Vælg en flaske fra listen");
        }
    }

    public List<Flaske> flaskeList() {
        List<Flaske> flasker = new ArrayList<>();
        for (Tapning tapning : Controller.getTapninger()) {
            flasker.add(tapning.getFlasker().get(0));
        }
        return flasker;
    }

    public void flaskeSelectedChanged() {
        Flaske flaske = lvwFlasker.getSelectionModel().getSelectedItem();
        if (flaske != null) txfAntal.setText(flaske.getPaaFyldning().getFlasker().size() + "");
    }


    public void updateControls() {
        lvwFlasker.getItems().setAll(flaskeList());
    }

    private void alertPopUp(String titel, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }
}
