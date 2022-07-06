package shopping;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.file.Paths;

public class Register extends Main {
    static Scene registerScene;
    public static void scene()
    {
        //GUI PART

        stage.setTitle("Register");

        Label header = new Label("Register");
        header.setId("header");
        header.setTextFill(Color.HONEYDEW);
        Label username = new Label("Username");
        TextField tfUsername = new TextField();
        tfUsername.getStyleClass().add("tfUsername");

        Label lPassword = new Label("Password");
        PasswordField password = new PasswordField();

           Button goTologin = new Button("Go back to login ");


        Button submit = new Button("Submit");
        goTologin.setMinWidth(275);
        submit.setMinWidth(100);
        GridPane gridPane = new GridPane();


        gridPane.setMaxWidth(500);
        HBox hboxButtons = new HBox(10);
        hboxButtons.setAlignment(Pos.BOTTOM_RIGHT);

        hboxButtons.getChildren().addAll(submit, goTologin);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.add(header,0,11,5,5);
        gridPane.add(username,0,22,1,1);
        gridPane.add(tfUsername,1,22,1,1);
        gridPane.add(lPassword,0,23,1,1);
        gridPane.add(password,1,23,1,1);
        gridPane.add(hboxButtons,1,24,1,1);
        GridPane.setHalignment(header,HPos.CENTER);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        //CSS
        registerScene = new Scene(gridPane, 800, 600);
        String uri = Paths.get("src\\main\\java\\shopping\\Login.css").toUri().toString();
        registerScene.getStylesheets().add(uri);

        // THIS EVENT FOR GOING TO THE LOGIN PAGE
        goTologin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Login.loginPage();
            }
        });
        submit.getStyleClass().add("submit");
        goTologin.getStyleClass().add("goToLogin");

        // ON SUBMIT IT CALLS REGISTER CONTROLLER TO CHECK EVERYTHING
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                try {
                    RegisterController registerController = new RegisterController(tfUsername.getText(),password.getText());


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        stage.setScene(registerScene);
        stage.show();


    }

}
