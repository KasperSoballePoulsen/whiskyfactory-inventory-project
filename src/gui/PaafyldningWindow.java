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
    private ListView<Fad> lvwFade;
    private TextField txfMedarbejder;
    private DatePicker dpDato;
    private List<Destilat> selectedDestillater;
    private List<TextField> literTappet;

    public PaafyldningWindow(String title, List<Destilat> destilater) {
        this.setTitle(title);
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        Scene scene = new Scene(pane);
        this.setScene(scene);
        selectedDestillater = destilater;



        lvwFade = new ListView<>();
        lvwFade.getItems().setAll(Controller.getTommeFade());
        pane.add(lvwFade, 0, 1, 2, 5);

        Label lblMedarbejder = new Label("Medarbejder");
        pane.add(lblMedarbejder, 0, 6);
        txfMedarbejder = new TextField();
        pane.add(txfMedarbejder, 1, 6);

        Label lblDato = new Label("dato");
        pane.add(lblDato, 0, 7);
        dpDato = new DatePicker();
        pane.add(dpDato, 1, 7);
        int test = selectedDestillater.size();
        literTappet = new ArrayList<>();
        int temp = 1;
        for (int i = 0; i < test; i++) {
            Label lblLiter = new Label("mængde (L):" );
            TextField txfLiterFad = new TextField();
            literTappet.add(txfLiterFad);
            pane.add(lblLiter,2,i+temp+1);
            pane.add(txfLiterFad,2,i+temp+2);
            temp++;
        }

        Button btnPaafyld = new Button("Fyld fad");
        btnPaafyld.setOnAction(event -> paaFyldAction());
        pane.add(btnPaafyld,0,8);
        btnPaafyld.setOnAction(event -> paaFyldAction());

    }

    public void paaFyldAction() {
        Fad selectedFad = lvwFade.getSelectionModel().getSelectedItem();
        List<Integer> literTappes = new ArrayList<>();
        for (int i = 0; i < literTappet.size(); i++) {
            int liter = Integer.parseInt(literTappet.get(i).getText());
            literTappes.add(liter);
        }
        LocalDate dato = dpDato.getValue();
        String medarbejder = txfMedarbejder.getText();
        Controller.paaFyldFad(selectedDestillater, selectedFad, literTappes,dato,medarbejder);
        this.hide();
    }
}
