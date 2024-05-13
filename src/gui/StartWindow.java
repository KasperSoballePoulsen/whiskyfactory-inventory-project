package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
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

        Tab tabFad = new Tab("Fad");
        Tab tabDestilat = new Tab("Destilat");
        Tab tabFlaske = new Tab("Flaske");

        FadPane fadPane = new FadPane();
        tabFad.setContent(fadPane);
        DestillatPane destilatPane = new DestillatPane();
        tabDestilat.setContent(destilatPane);
        FlaskePane flaskePane = new FlaskePane();
        tabFlaske.setContent(flaskePane);


        tabPane.getTabs().add(tabFad);
        tabPane.getTabs().add(tabDestilat);
        tabPane.getTabs().add(tabFlaske);

        tabFad.setOnSelectionChanged(event -> fadPane.updateControls());
        //tabDestilat.setOnSelectionChanged(event -> destilatPane.updateControls());
        //tabFlaske.setOnSelectionChanged(event -> flaskePane.updateControls());

    }


}
