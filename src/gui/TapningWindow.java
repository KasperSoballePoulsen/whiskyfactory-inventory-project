package gui;

import application.controller.Controller;
import application.model.Fad;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TapningWindow extends Stage {

    private List<TextField> literTappet;
    private TextField txfVand;
    private TextField txfVandkilde;
    private TextField txfAlkoholprocent;
    private DatePicker dpDato;
    private TextField txfMedarbejder;
    private TextField txfFlaskeNavn;

    public TapningWindow(String title, List<Fad> fade) {
        this.setTitle(title);
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        Scene scene = new Scene(pane);
        this.setScene(scene);

        Label lblFade = new Label("Fade");
        pane.add(lblFade, 2, 0);


        literTappet = new ArrayList<>();
        for (int i = 0; i < fade.size(); i++) {
            Label lblLiter = new Label("mængde (L): " + fade.get(i).getAntalLiterPaafyldt() + " L på fadet");
            TextField txfLiterFad = new TextField();
            literTappet.add(txfLiterFad);
            pane.add(lblLiter, 2, i + 1);
            pane.add(txfLiterFad, 3, i + 1);
        }

        Label lblVand = new Label("Mængde vand (L):");
        pane.add(lblVand, 0, fade.size() + 1);
        txfVand = new TextField();
        pane.add(txfVand, 1, fade.size() + 1);

        Label lblVandkilde = new Label("Vandkilde");
        pane.add(lblVandkilde, 0, fade.size() + 2);
        txfVandkilde = new TextField();
        pane.add(txfVandkilde, 1, fade.size() + 2);

        Label lblAlkoholprocent = new Label("Alkoholprocent");
        pane.add(lblAlkoholprocent, 0, fade.size() + 3);
        txfAlkoholprocent = new TextField();
        pane.add(txfAlkoholprocent, 1, fade.size() + 3);

        Label lblDato = new Label("dato");
        pane.add(lblDato, 0, fade.size() + 4);
        dpDato = new DatePicker();
        pane.add(dpDato, 1, fade.size() + 4);

        Label lblMedarbejder = new Label("Medarbejder");
        pane.add(lblMedarbejder, 0, fade.size() + 5);
        txfMedarbejder = new TextField();
        pane.add(txfMedarbejder, 1, fade.size() + 5);

        Label lblFlaske = new Label("Flaskens navn;");
        pane.add(lblFlaske, 0, fade.size() + 6);
        txfFlaskeNavn = new TextField();
        pane.add(txfFlaskeNavn, 1, fade.size() + 6);

        Button btnFyldFlasker = new Button("Fyld på Flasker");
        btnFyldFlasker.setOnAction(event -> fyldFlasker(fade));
        pane.add(btnFyldFlasker, 1, fade.size() + 7);

    }

    public void fyldFlasker(List<Fad> fadList) {
        try {
            List<Integer> literTappes = new ArrayList<>();
            for (int i = 0; i < literTappet.size(); i++) {
                int liter = Integer.parseInt(literTappet.get(i).getText());
                literTappes.add(liter);
            }
            LocalDate dato = dpDato.getValue();
            String medarbejder = txfMedarbejder.getText();
            int vand = Integer.parseInt(txfVand.getText());
            double alkoholprocent = Double.parseDouble(txfAlkoholprocent.getText());
            String flaskeNavn = txfFlaskeNavn.getText();
            if (txfVandkilde.getText().isEmpty()) {
                for (int i = 0; i < fadList.size(); i++) {
                    Controller.aftapFad(fadList, literTappes, dato, medarbejder, vand, flaskeNavn, alkoholprocent);
                }
            } else {
                String vandkilde = txfVandkilde.getText();
                for (int i = 0; i < fadList.size(); i++) {
                    Controller.aftapFad(fadList, literTappes, dato, medarbejder, vand, flaskeNavn, alkoholprocent, vandkilde);
                }
            }
            this.hide();
        } catch (Exception e) {
            alertPopUp("Fejl", "Flasker ikke fyldt", "Prøv igen");
        }
    }

    private void alertPopUp(String titel, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }


}
