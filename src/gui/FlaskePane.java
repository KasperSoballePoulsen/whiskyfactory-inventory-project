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
    public FlaskePane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(30));
        this.setHgap(30);
        this.setVgap(10);

        lvwFlasker = new ListView<>();
        lvwFlasker.getItems().setAll(flaskeList());
        lvwFlasker.setPrefHeight(200);
        add(lvwFlasker,0,0,1,20);
        ChangeListener<Flaske> flaskeChangeListener = (ov,oldFlaske,newFlaske) -> flaskeSelectedChanged();
        lvwFlasker.getSelectionModel().selectedItemProperty().addListener(flaskeChangeListener);

        Button btnHistorik = new Button("Vis historik");
        btnHistorik.setOnAction(event -> historikAction());
        add(btnHistorik,1,2);

        Label lblAntal = new Label("antal:");
        add(lblAntal,1,0);
        txfAntal = new TextField();
        txfAntal.setEditable(false);
        add(txfAntal,1,1);


    }
    public void historikAction(){
        Flaske flaske = lvwFlasker.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(flaske.getNavn());
        alert.setContentText(Controller.flaskeHistorik(flaske));
        alert.showAndWait();
    }

    public List<Flaske> flaskeList(){
        List<Flaske> flasker = new ArrayList<>();
        for (Tapning tapning : Controller.getTapninger()) {
            flasker.add(tapning.getFlasker().get(0));
        }
        return flasker;
    }
    public void flaskeSelectedChanged(){
        Flaske flaske = lvwFlasker.getSelectionModel().getSelectedItem();
        if (flaske != null) txfAntal.setText(flaske.getPaaFyldning().getFlasker().size() + "");
    }


    public void updateControls(){
        lvwFlasker.getItems().setAll(flaskeList());
    }
}
