package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class FlaskePane extends GridPane {
    public FlaskePane() {
        this.setGridLinesVisible(false);
        this.setPadding(new Insets(30));
        this.setHgap(30);
        this.setVgap(30);
    }
}
