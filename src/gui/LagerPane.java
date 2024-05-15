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
    TextField txfledigePladser;

    CheckBox chb3Aar;



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

        Label lblPlads = new Label("Ledige Pladser");
        add(lblPlads, 2, 0);

        txfledigePladser = new TextField();
        txfledigePladser.setEditable(false);
        add(txfledigePladser, 2, 1);

        Button btnOpret = new Button("Opret Lager");
        add(btnOpret, 2, 3);
        btnOpret.setOnAction(event -> opretAction());

        chb3Aar = new CheckBox("Vis kun færdige fade");
        add(chb3Aar,2,2);
        chb3Aar.setOnAction(event -> visAction());


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

}
