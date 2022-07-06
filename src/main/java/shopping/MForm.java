package shopping;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import shopping.other.GridPaneMaker;

import java.io.*;
import java.nio.file.Paths;

public class MForm extends VBox {

    private GridPane grid;
    private Label lblFirstName;
    private Label lblLastName;
    private TextArea textArea;
    private ButtonBar btnBar;

    public MForm() throws IOException {

        grid = new GridPane();

        lblFirstName = new Label("Comment");


        String uri = Paths.get("src/main/java/shopping/test.css").toUri().toString();
        this.getStylesheets().add(uri);
        grid.getStyleClass().add("profile");

        // IT MAKES A GRIDPANE MAKER OBJECT WHICH IS EXTENDS GRIDPANE
        GridPaneMaker gridPane = new GridPaneMaker();


        getChildren().add(gridPane);
        setPadding(new Insets(10));
        VBox.setVgrow(grid, Priority.ALWAYS);


    }

    //SETS THE IMAGE TO THE PROFILEMAKER PAGE
    public static void changeProfilePicture(Image image) throws IOException {
            GridPaneMaker.profileImage.setImage(image);


    }
}