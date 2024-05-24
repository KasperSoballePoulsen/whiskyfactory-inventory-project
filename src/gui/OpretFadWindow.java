package gui;

import application.controller.Controller;
import application.model.Fad;
import application.model.Lager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class OpretFadWindow extends Stage {

    private TextField txfType, txfStoerrelse, txfLeverandoer;
    private ComboBox cbLager;


    public OpretFadWindow(String title) {
        this.setTitle(title);
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        Scene scene = new Scene(pane);
        this.setScene(scene);

        Label lblName = new Label("Type");
        pane.add(lblName, 0, 0);

        txfType = new TextField();
        pane.add(txfType, 0, 1);

        Label lblSize = new Label("Literkapacitet");
        pane.add(lblSize, 0, 2);

        txfStoerrelse = new TextField();
        pane.add(txfStoerrelse, 0, 3);

        Label lblLeve = new Label("Leverandør");
        pane.add(lblLeve, 1, 0);

        txfLeverandoer = new TextField();
        pane.add(txfLeverandoer, 1, 1);

        Label lblLager = new Label("Lager");
        pane.add(lblLager, 1, 2);

        cbLager = new ComboBox<>();
        pane.add(cbLager, 1, 3);
        cbLager.getItems().setAll(Controller.getLager());

        Button btnOpret = new Button("Opret Fad");
        pane.add(btnOpret, 0, 4);
        btnOpret.setOnAction(event -> opretFad());
    }


    public void opretFad() {
        Lager lager = (Lager) cbLager.getSelectionModel().getSelectedItem();
        String type = txfType.getText().trim();
        String leverandoer = txfLeverandoer.getText().trim();
        try {
            int stoerrelse = Integer.parseInt(txfStoerrelse.getText().trim());
            if (type.isEmpty() || leverandoer.isEmpty() || stoerrelse <= 0 || lager == null) {
                alertPopUp("Fejl", "Fad ikke oprettet", "Udfyld alle felter korrekt");
            } else {
                Controller.opretFad(type, stoerrelse, lager, leverandoer);
                this.hide();
            }
        } catch (NumberFormatException e) {
            alertPopUp("Fejl", "Fad ikke oprettet", "Udfyld alle felter korrekt");
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
