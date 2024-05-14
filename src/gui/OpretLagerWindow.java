package gui;

import application.controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OpretLagerWindow extends Stage {

    TextField txfPladser, txfNavn;

    public OpretLagerWindow (String  title){
        this.setTitle(title);
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        Scene scene = new Scene(pane);
        this.setScene(scene);

        Label lblPladser = new Label("Antal Pladser");
        pane.add(lblPladser,1,0);

        txfNavn = new TextField();
        pane.add(txfNavn,0,1);

        Label lblNavn = new Label("Navn på lager");
        pane.add(lblNavn,0,0);

        txfPladser = new TextField();
        pane.add(txfPladser,1,1);

        Button btnOpret = new Button("Opret Lager");
        pane.add(btnOpret,1,2);
        btnOpret.setOnAction(event -> opretLagerAction());
    }

    public void opretLagerAction(){
        Controller.opretLager(txfNavn.getText().trim(), Integer.parseInt(txfPladser.getText().trim()));
        this.hide();
    }
}
