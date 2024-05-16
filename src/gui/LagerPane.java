package gui;

import application.controller.Controller;
import application.model.Flaske;
import application.model.Lager;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class LagerPane extends GridPane {
    ListView lvwLager, lvwFadePaaLager;
    TextField txfledigePladser, txfSoegeFade;

    CheckBox chb3Aar;
    private Label lblTapErr;



    public LagerPane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(30));
        this.setHgap(30);
        this.setVgap(10);

        Label lblLager = new Label("Lager:");
        add(lblLager, 0, 0);

        lvwLager = new ListView<>();
        lvwLager.getItems().setAll(Controller.getLager());
        add(lvwLager, 0, 1, 1, 20);
        ChangeListener<Lager> lagerChangeListener = (ov, oldLager, newLager) -> lagerSelectedChanged();
        lvwLager.getSelectionModel().selectedItemProperty().addListener(lagerChangeListener);


        Label lblFade = new Label("Fade på lager:");
        add(lblFade, 1, 0);

        lvwFadePaaLager = new ListView<>();
        add(lvwFadePaaLager, 1, 1, 1, 20);
        lvwFadePaaLager.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Label lblPlads = new Label("Ledige Pladser");
        add(lblPlads, 2, 0);

        txfledigePladser = new TextField();
        txfledigePladser.setEditable(false);
        add(txfledigePladser, 2, 1);

        Label lblSoeg = new Label("Søg");
        add(lblSoeg,2,2);

        txfSoegeFade = new TextField();
        add(txfSoegeFade,2,3);

        Button btnSoeg = new Button("Søg");
        add(btnSoeg,2,4);
        btnSoeg.setOnAction(event -> soegAction());


        chb3Aar = new CheckBox("Vis kun færdige fade");
        add(chb3Aar,2,5);
        chb3Aar.setOnAction(event -> visAction());

        Button btnOpret = new Button("Opret Lager");
        add(btnOpret, 2, 6);
        btnOpret.setOnAction(event -> opretAction());

        Button btnAftapFad = new Button("Aftap fad");
        this.add(btnAftapFad, 2, 7);
        btnAftapFad.setOnAction(event -> openTapning());

        lblTapErr = new Label("");
        lblTapErr.setStyle("-fx-text-fill: red");
        add(lblTapErr, 2, 8);



    }

    public void lagerSelectedChanged() {
        Lager lager = (Lager) lvwLager.getSelectionModel().getSelectedItem();
        List fade = new ArrayList<>(lager.fadePaaLager());
        lvwFadePaaLager.getItems().setAll(fade);
        txfledigePladser.setText("" + (lager.antalLedigePladser()));
    }

    public void opretAction() {
        OpretLagerWindow dia = new OpretLagerWindow("Opret Lager");
        dia.showAndWait();
        lvwLager.getItems().setAll(Controller.getLager());
    }

    public void visAction() {
        if (chb3Aar.isSelected()) {
            Lager lager = (Lager) lvwLager.getSelectionModel().getSelectedItem();
            lvwFadePaaLager.getItems().setAll(lager.faerdigeFade());
        } else {
           lagerSelectedChanged();
        }
    }

    public void soegAction(){
        Lager lager = (Lager) lvwLager.getSelectionModel().getSelectedItem();
        String string = txfSoegeFade.getText();
        lvwFadePaaLager.getItems().setAll(Controller.soegteFade(string,lager));
    }

    public void openTapning() {
        if (lvwFadePaaLager.getSelectionModel().getSelectedItems().size() != 0) {
            TapningWindow dia = new TapningWindow("Tapning", lvwFadePaaLager.getSelectionModel().getSelectedItems());
            dia.showAndWait();
            lvwFadePaaLager.getItems().setAll(Controller.getFyldteFade());
        } else {
            lblTapErr.setText("Vælg fyldt fad");
        }
    }

}
