package shopping;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainPage  extends Main{
    public  static String eachLines="";
    public  static String[] eachlinesJoin;
    static  Scene mainPageScene;
    protected ImageView imageView;
    private static TextArea textArea;
    public static VBox parentVbox;
    public MainPage() throws IOException {

    }

    public static void mainPage() throws IOException, ParseException {



        Image profile= new Image(new File("src/main/java/shopping/images/profileIcon.png").toURI().toString());
        Image settings= new Image(new File("src/main/java/shopping/images/settings.png").toURI().toString());


        ImageView cloneProfileIcon = new ImageView(profile);
        ImageView imgSettings = new ImageView(settings);

        cloneProfileIcon.setFitHeight(48);
        cloneProfileIcon.setFitWidth(48);
        imgSettings.setFitHeight(48);
        imgSettings.setFitWidth(48);

        Button buttonProfile = new Button();
        buttonProfile.setPrefWidth(64);
        buttonProfile.setPrefHeight(64);
        Button buttonSetting = new Button();
        buttonSetting.setPrefWidth(64);
        buttonSetting.setPrefHeight(64);
        buttonSetting.setGraphic(imgSettings);

        VBox imageButtons = new VBox(buttonProfile,buttonSetting);
        imageButtons.setAlignment(Pos.TOP_RIGHT);
        buttonProfile.setGraphic(cloneProfileIcon);




        new MainPage().makeImageView();
        buttonProfile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    MProfilePage profilePage= new MProfilePage();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        buttonSetting.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {

                    MakeProfile.mainPage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        MToolBar top = new MToolBar("s");

        BorderPane borderPane = new BorderPane();


        textArea =new TextArea();

        parentVbox= new VBox();
        parentVbox.getChildren().clear();

        parentVbox.getChildren().add(readAllPosts());
        Label title = new Label("POSTS");
        GridPane titleTop = new GridPane();
        titleTop.add(title,2,1,1,1);
        titleTop.add(top,1,2,1,1);

        borderPane.setTop(titleTop);
        borderPane.setLeft(imageButtons);
        borderPane.setCenter(parentVbox);

        ScrollPane scrollPane2 = new ScrollPane(borderPane);

        mainPageScene = new Scene(scrollPane2,800,600);
        stage.setScene(mainPageScene);

        String uri = Paths.get("src/main/java/shopping/Profile.css").toUri().toString();
        mainPageScene.getStylesheets().add(uri);
        buttonProfile.getStyleClass().add("mainPageButton");
        buttonSetting.getStyleClass().add("mainPageButton");


    }
    public static void addPath() throws IOException {

        File userFile = new File("src/main/java/shopping/accounts/"+Login.enteredUser+"/"+Login.enteredUser+".txt");

        BufferedReader bufferedReaderUserTxt= new BufferedReader(new FileReader(userFile));
        String txtLine = null;


        txtLine = bufferedReaderUserTxt.readLine();



        String nwTxtLine="";
        bufferedReaderUserTxt.close();
        if(txtLine!=null)
        {
            nwTxtLine = txtLine.replace("|","\n");

        }
        Text bio = new Text(nwTxtLine);
        bio.getStyleClass().add("text");


    }
    public static String readImagePath() throws IOException {
        String path ="";

        File file = new File("accounts.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while((line = br.readLine()) != null)
        {

            if(line.split("\\|")[0].equals(Login.enteredUser))
            {

                System.out.println(Login.enteredUser);
                path =line.split("\\|")[2];
                System.out.println(path);
            }
        }
        br.close();
        return path;
    }
    public Image makeImageView() throws IOException {
        String path = readImagePath();
        Image image = new Image(new File(path).toURI().toString());

        return image;
    }

    public static VBox readAllPosts() throws IOException, ParseException {

        // TAKES THE FILE NAMES IN TO A STRING ARRAY THEN BY ARRAY LENGTH IT GOES TO INSIDE OF POSTS TXT

        File file = new File("src/main/java/shopping/accounts");
        String[] directories = file.list((current, name) -> new File(current, name).isDirectory());

        for (int v = 0; v < directories.length; v++) {

            String theFileName = "src/main/java/shopping/accounts/" + directories[v] + "/" + directories[v] + "posts.txt";
            File currentPath = new File(theFileName);
            BufferedReader br = new BufferedReader(new FileReader(currentPath));

            String line;
            while ((line = br.readLine()) != null) {
                eachLines += line + "\n";

            }
            br.close();
        }

        eachlinesJoin = eachLines.split("\n");
        ArrayList<String> arrayPosts =new ArrayList<>();

        Collections.addAll(arrayPosts, eachlinesJoin);

        Collections.sort(arrayPosts);
        Collections.reverse(arrayPosts);

        //POSTS LAYOUT VBOX
        VBox layout = new VBox();

            try {
                    // IT TAKES THE USER INFORMATIONS FROM EACH FOLDER AND FILES
                for (int i = 0; i < arrayPosts.size(); i++) {
                    if (!arrayPosts.get(i).equals("") || !arrayPosts.get(i).equals("\n")) {
                        System.out.println(arrayPosts.get(i));
                        String date = arrayPosts.get(i).split("\\|")[0];

                        String userName = arrayPosts.get(i).split("\\|")[1];
                        String firstName = arrayPosts.get(i).split("\\|")[2];
                        String lastName = arrayPosts.get(i).split("\\|")[3];
                        String postText = arrayPosts.get(i).split("\\|")[4];


                        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyy hh:mm:ss");
                        Date date1 = dateFormat.parse(date);


                        File fileAccounts = new File("accounts.txt");
                        BufferedReader reader = new BufferedReader(new FileReader(fileAccounts));
                        String line = "";
                        File imageFile = null;

                        while ((line = reader.readLine()) != null) {

                            if (line.split("\\|")[0].equals(userName)) {
                                imageFile = new File(line.split("\\|")[2]);
                            }
                        }


                        Image image = new Image(imageFile.toURI().toString());

                        // IT CREATES NEW POSTS BY MAKING POSTMAKER OBJECT
                        PostMaker postMaker = new PostMaker(firstName, lastName, image, postText, date1);
                        postMaker.getStyleClass().add("newPost");
                        VBox.setMargin(postMaker, new Insets(5, 0, 25, 100));
                        postMaker.setMaxHeight(300);
                        postMaker.setMaxWidth(500);
                        // ADDS EACH VBOXES TO THE PARENT LAYOUT VBOX
                        layout.getChildren().add(postMaker);

                    } else {
                        continue;
                    }

                    eachLines = "";
                }
            }
            catch (Exception e)
            {

            }
            return layout;


        }
}
