package gui;

import application.controller.Controller;
import application.model.Fad;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class StartWindow extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("System");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setHeight(500);
        stage.setWidth(800);
        stage.show();
    }

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabFade = new Tab("Fade");

        FadePane fadePane = new FadePane();
        tabFade.setContent(fadePane);


        tabPane.getTabs().add(tabFade);

        //tabFade.setOnSelectionChanged(event -> fadePane.updateControls());

    }


}
