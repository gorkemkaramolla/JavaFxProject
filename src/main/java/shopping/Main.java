package shopping;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.IOException;
import java.nio.file.Paths;

public class Main extends Application {

    //Main and only stage of the project
    static Stage stage;
    private StackPane stackPane;
    private BorderPane group;
    private Scene root;
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage =primaryStage;
        stage.setTitle("App");
        Text welcome = new Text("WELCOME TO THE APPLICATON");
        welcome.setId("welcome");
        welcome.setFont(Font.font ("Verdana", 15));
        welcome.setFill(Color.RED);
        welcome.getStyleClass().add("welcome");
        stackPane= new StackPane(welcome);

        Button start = new Button("Let's Start");
         group= new BorderPane();


        group.setTop(stackPane);
        group.setCenter(start);

         root = new Scene(group,800,600);


        //IT CALLS REGISTER SCENE FUNCTION
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Register.scene();

            }
        });
        stage.setResizable(false);
        stage.setScene(root);
        stage.show();
        String uri = Paths.get("src\\main\\java\\shopping\\Login.css").toUri().toString();
        root.getStylesheets().add(uri);

    }


}