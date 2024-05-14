package gui;

import application.controller.Controller;
import application.model.Flaske;
import application.model.Tapning;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class FlaskePane extends GridPane {

    private ListView<Flaske> lvwFlasker;
    public FlaskePane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(30));
        this.setHgap(30);
        this.setVgap(30);

        lvwFlasker = new ListView<>();
        List<Flaske> flasker = new ArrayList<>();
        for (Tapning tapning : Controller.getTapninger()) {
            for (Flaske flaske : tapning.getFlasker()) {
                flasker.add(flaske);
            }
        }
        lvwFlasker.getItems().setAll(flasker);
        add(lvwFlasker,0,0);

        Button btnHistorik = new Button("Vis historik");
        btnHistorik.setOnAction(event -> historikAction());
        add(btnHistorik,1,1);

    }
    public void historikAction(){
        Flaske flaske = lvwFlasker.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(flaske.getNavn());
        alert.setContentText(flaske.historik());
        alert.showAndWait();
    }

    public void updateControls(){
        List<Flaske> flasker = new ArrayList<>();
        for (Tapning tapning : Controller.getTapninger()) {
            for (Flaske flaske : tapning.getFlasker()) {
                flasker.add(flaske);
            }
        }
        lvwFlasker.getItems().setAll(flasker);
    }
}
