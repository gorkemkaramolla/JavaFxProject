package shopping;

import javafx.scene.Scene;
import javafx.scene.layout.*;

import java.io.IOException;
import java.nio.file.Paths;

public class MakeProfile extends MProfilePage {
   static Scene profilePageScene;

    public MakeProfile() throws Exception {
    }
    //PROFILE SCREEN GUI
    public static void mainPage() throws IOException {
        MainPage.addPath();


        MToolBar toolBar = new MToolBar();
        MForm center = new MForm();
        VBox top = new VBox(toolBar);
        MStatusBar bottom = new MStatusBar();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(top);
        borderPane.setBottom(bottom);
        borderPane.setCenter(center);

        profilePageScene = new Scene(borderPane,800,600);
        //CSS CONNECTION
        String uri = Paths.get("src/main/java/shopping/test.css").toUri().toString();
        profilePageScene.getStylesheets().add(uri);
        stage.setScene(profilePageScene);


    }







}
