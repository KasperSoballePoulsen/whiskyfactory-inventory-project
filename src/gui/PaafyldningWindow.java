package gui;

import application.controller.Controller;
import application.model.Destillat;
import application.model.Fad;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaafyldningWindow extends Stage {

    private Fad fad;
    private ListView<Destillat> lvwDestilater;
    private TextField txfMedarbejder;
    private DatePicker dpDato;

    public PaafyldningWindow(String title, Fad fad) {
        this.setTitle(title);
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        Scene scene = new Scene(pane);
        this.setScene(scene);

        this.fad = fad;
        Label lblFad = new Label(fad.toString());
        pane.add(lblFad, 0, 0);

        lvwDestilater = new ListView<>();
        lvwDestilater.getItems().setAll(Controller.getDestillater());
        pane.add(lvwDestilater, 0, 1, 2, 5);

        Label lblMedarbejder = new Label("Medarbejder");
        pane.add(lblMedarbejder, 0, 5);
        txfMedarbejder = new TextField();
        pane.add(txfMedarbejder, 1, 5);

        Label lblDato = new Label("dato");
        pane.add(lblDato, 0, 6);
        dpDato = new DatePicker();
        pane.add(dpDato, 1, 6);

        Button btnPaafyld = new Button("Fyld fad");
        btnPaafyld.setOnAction(event -> paaFyldAction());
        pane.add(btnPaafyld,0,7);

    }

    public void paaFyldAction() {
        List<Destillat> selectedDestillater = lvwDestilater.getSelectionModel().getSelectedItems();
        List<Integer> liter = new ArrayList<>();
        LocalDate dato = dpDato.getValue();
        String medarbejder = txfMedarbejder.getText();
        Controller.paaFyldFad(selectedDestillater, fad, liter,dato,medarbejder);
    }
}
