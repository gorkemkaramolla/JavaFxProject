package shopping;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class MStatusBar extends HBox {

    private Label label;

    public MStatusBar() {
        this.label = new Label("Ready");
        this.getChildren().add(label);
        this.setId("statusbar");
        this.setPadding(new Insets(3));
        HBox.setHgrow(label, Priority.ALWAYS);
    }

    public void setText(String text) {
        this.label.setText(text);
    }
}