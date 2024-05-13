package gui;

import application.controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;


public class OpretDestillatWindow extends Stage {

    private TextField txfName, txfKornsort, txfMængdeLiter, txfAlkoPro, txfMalt, txfMedarbejder;

    private DatePicker dpStart, dpSlut;

    public OpretDestillatWindow(String title) {
        this.setTitle(title);
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        Scene scene = new Scene(pane);
        this.setScene(scene);

        Label lblName = new Label("Navn");
        pane.add(lblName,0,0);

        txfName = new TextField();
        pane.add(txfName, 0, 1);
        txfName.setPrefWidth(200);

        Label lblStart = new Label("Start Dato");
        pane.add(lblStart,0,2);

        dpStart = new DatePicker();
        pane.add(dpStart, 0,3);

        Label lblSlut = new Label("Slut Dato");
        pane.add(lblSlut,0,4);

        dpSlut = new DatePicker();
        pane.add(dpSlut, 0,5);

        Label lblKorn = new Label("Korn Sort");
        pane.add(lblKorn,0,6);

        txfKornsort = new TextField();
        pane.add(txfKornsort,0,7);

        Label lblMalt = new Label("Malt Batch");
        pane.add(lblMalt,0,8);

        txfMalt = new TextField();
        pane.add(txfMalt,0,9);

        Label lblMed = new Label("Medarbejder");
        pane.add(lblMed,0,10);

        txfMedarbejder = new TextField();
        pane.add(txfMedarbejder, 0,11);

        Label lblMaeng = new Label("Mængde i liter");
        pane.add(lblMaeng,1,0);

        txfMængdeLiter = new TextField();
        pane.add(txfMængdeLiter,1,1);

        Label lblAlko = new Label("Alkohol Procent");
        pane.add(lblAlko,1,2);

        txfAlkoPro = new TextField();
        pane.add(txfAlkoPro,1,3);

        Button btnOpret = new Button("Opret Destilat");
        pane.add(btnOpret,1,11);
        btnOpret.setOnAction(event -> OpretAction());
    }

    private void OpretAction(){
        String navn = txfName.getText().trim();
        String kornsort = txfKornsort.getText().trim();
        Double alkoPro = Double.parseDouble(txfAlkoPro.getText().trim());
        LocalDate startDato = dpStart.getValue();
        LocalDate slutDato = dpSlut.getValue();
        String maltBatch = txfMalt.getText().trim();
        String medarbejder = txfMedarbejder.getText().trim();
        int mængde = Integer.parseInt(txfMængdeLiter.getText().trim());
        Controller.opretDestillat(navn,kornsort,startDato,slutDato,maltBatch,mængde,alkoPro,medarbejder);

        this.hide();

    }
}
