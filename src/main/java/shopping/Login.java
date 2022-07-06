package shopping;

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

import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;

public class Login extends Main{
    static Scene loginScene;
    public static  String enteredUser;

    public  static void loginPage()
    {
        //GUI
        stage.setTitle("Login");

        Label header = new Label("Login");
        header.setId("header");
        header.setTextFill(Color.HONEYDEW);

        Label lUserName = new Label("Username");

        TextField tfUsername = new TextField();
        Label lPassword = new Label("Password");
        PasswordField tfPassword = new PasswordField();
        Button login = new Button("Login");
        Button backToRegister = new Button("Go back to register");

        backToRegister.setMinWidth(275);
        login.setMinWidth(100);
        GridPane gridPane = new GridPane();

        HBox hboxButtons = new HBox(10);


        hboxButtons.getChildren().addAll(login, backToRegister);

        gridPane.setHgap(20);
        gridPane.add(header,0,11,5,5);
        gridPane.add(lUserName,0,22,1,1);
        gridPane.add(tfUsername,1,22,1,1);
        gridPane.add(lPassword,0,23,1,1);
        gridPane.add(tfPassword,1,23,1,1);
        gridPane.add(hboxButtons,1,24,1,1);
        GridPane.setHalignment(header,HPos.CENTER);


        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));



        //CSS
        loginScene = new Scene(gridPane,800,600);
        String uri = Paths.get("src/main/java/shopping/login.css").toUri().toString();
        loginScene.getStylesheets().add(uri);

        //LOGIN CONTROL BY FILE LENGTH IF IT IS MORE THAN 0 IT SENDS USER TO THE MAIN PAGE
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if(LoginController.login(tfUsername.getText(),tfPassword.getText()))
                    {
                        File file = new File("src/main/java/shopping/accounts/"+Login.enteredUser+"/"+Login.enteredUser+".txt");
                        if (file.length()>0)
                        {
                            MainPage.mainPage();
                        }
                        else
                        {
                            MakeProfile.mainPage();
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        //GO BACK TO REGISTER BUTTON EVENT
        backToRegister.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Register.scene();
            }
        });
        stage.setScene(loginScene);
        stage.show();


    }





}
