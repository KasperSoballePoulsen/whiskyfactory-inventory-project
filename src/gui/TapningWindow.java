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

    private ListView<Fad> lvwFade;
    private List<TextField> literTappet;
    private TextField txfVand;
    private TextField txfVandkilde;
    private TextField txfAlkoholprocent;
    private DatePicker dpDato;
    private TextField txfMedarbejder;
    private TextField txfFlaskeNavn;

    public TapningWindow(String title, List<Fad> fade){
        this.setTitle(title);
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        Scene scene = new Scene(pane);
        this.setScene(scene);

        Label lblFade = new Label("Fade");
        pane.add(lblFade,0,0);

        lvwFade = new ListView<>();
        lvwFade.getItems().setAll(fade);
        pane.add(lvwFade,0,1,2,fade.size());
        literTappet = new ArrayList<>();
        for (int i = 0; i < fade.size(); i++) {
            Label lblLiter = new Label("mængde (L):" );
            TextField txfLiterFad = new TextField();
            literTappet.add(txfLiterFad);
            pane.add(lblLiter,2,i+1);
            pane.add(txfLiterFad,3,i+1);
        }

        Label lblVand = new Label("Mængde vand (L):");
        pane.add(lblVand,0,fade.size()+1);
        txfVand = new TextField();
        pane.add(txfVand,1,fade.size()+1);

        Label lblVandkilde = new Label("Vandkilde");
        pane.add(lblVandkilde,0,fade.size()+2);
        txfVandkilde = new TextField();
        pane.add(txfVandkilde,1,fade.size()+2);

        Label lblAlkoholprocent = new Label("Alkoholprocent");
        pane.add(lblAlkoholprocent,0,fade.size()+3);
        txfAlkoholprocent = new TextField();
        pane.add(txfAlkoholprocent,1,fade.size()+3);

        Label lblDato = new Label("dato");
        pane.add(lblDato,0,fade.size()+4);
         dpDato = new DatePicker();
        pane.add(dpDato,1,fade.size()+4);

        Label lblMedarbejder = new Label("Medarbejder");
        pane.add(lblMedarbejder,0,fade.size()+5);
        txfMedarbejder = new TextField();
        pane.add(txfMedarbejder,1,fade.size()+5);

        Label lblFlaske = new Label("Flaskens navn;");
        pane.add(lblFlaske,0,fade.size()+6);
        txfFlaskeNavn = new TextField();
        pane.add(txfFlaskeNavn,1,fade.size()+6);

        Button btnFyldFlasker = new Button("Fyld på Flasker");
        btnFyldFlasker.setOnAction(event -> fyldFlasker());
        pane.add(btnFyldFlasker,1,fade.size()+7);

    }
    public void fyldFlasker(){
        List<Fad> fadlist = lvwFade.getItems();
        List<Integer> literTapes = new ArrayList<>();
        for (int i = 0; i < literTappet.size(); i++) {
            int liter = Integer.parseInt(literTappet.get(i).getText());
            literTapes.add(liter);
        }
        LocalDate dato = dpDato.getValue();
        String medarbejder = txfMedarbejder.getText();
        int vand = Integer.parseInt(txfVand.getText());
        double alkoholprocent = Double.parseDouble(txfAlkoholprocent.getText());
        String flaskeNavn = txfFlaskeNavn.getText();
        for (int i = 0; i < fadlist.size(); i++) {
            Controller.aftapFad(fadlist,literTapes,dato,medarbejder,vand,flaskeNavn,alkoholprocent);
        }

    }
}
