package shopping;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.io.*;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MProfilePage extends MainPage {
    static Scene profilePageScene;
    private String userName;
    private String lastName;
    private TextArea writePost;
    private Button post;
    private Button btnBack;

    public MProfilePage() throws Exception {
        profile();
    }
    private void profile() throws Exception {

        File userFile = new File("src/main/java/shopping/accounts/"+Login.enteredUser+"/"+Login.enteredUser+".txt");
        String nwTxtLine="";

        BufferedReader bufferedReaderUserProfile= new BufferedReader(new FileReader(userFile));

        //READS FIRST NAME AND LAST NAME FROM NAME TXT
        String line;
        Label label = null;
        while((line = bufferedReaderUserProfile.readLine()) != null) {

            if(!line.equals(""))

            {
                this.setUserName(line.split("\\;")[1]);
                this.setLastName(line.split("\\;")[2]);
                label = new Label(this.getUserName()+" " + this.getLastName());
            }


        }
        bufferedReaderUserProfile.close();
        ImageView profileIcon = new ImageView(new MainPage().makeImageView());

        //GUI
        writePost = new TextArea();
        post = new Button("Post");
        profileIcon.setFitHeight(50);
        profileIcon.setFitWidth(50);
        writePost.setPrefColumnCount(8);
        writePost.setPrefRowCount(3);
        writePost.setWrapText(true);
        HBox hBox = new HBox(profileIcon,label);
        HBox hBox1= new HBox(post);
        hBox.setMargin(label,new Insets(20,25,0,0));
        hBox1.setMargin(post,new Insets(10,0,0,150));
        VBox left = new VBox(hBox,writePost,hBox1);
        BorderPane borderPane = new BorderPane();
        btnBack = new Button("Back");
        borderPane.setLeft(left);
        VBox layout = new VBox();
        layout.setSpacing(20);
        borderPane.setCenter(layout);
        borderPane.setTop(btnBack);
        oldPosts(layout);
        ScrollPane scrollPane= new ScrollPane();
        scrollPane.setContent(borderPane);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // THIS EVENT  SENDS USER BACK TO THE MAIN PAGE
        btnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    MainPage.mainPage();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        //POST EVENT FOR PROFILE PAGE
        post.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(writePost.getText().equals(""))
                {
                    Alert empty = new Alert(Alert.AlertType.ERROR);
                    empty.setTitle("Error");
                    empty.setHeaderText("This Field Can not be Empty");
                    empty.setContentText("Fill the text");
                    empty.showAndWait();
                }
                else
                {
                    // IT READS THE CURRENT USER'S POST TXT
                    File file = new File("src/main/java/shopping/accounts/"+Login.enteredUser+"/"+Login.enteredUser+"posts.txt");
                    try {
                        Date date = Calendar.getInstance().getTime();
                        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
                        String strDate = dateFormat.format(date);



                        FileWriter fileWriter = new FileWriter(file,true);
                        fileWriter.append(strDate+"|"+Login.enteredUser+"|"+getUserName()+"|"+getLastName()+"|"+writePost.getText()+"\n");
                        fileWriter.close();

                        // MAKES A POSTMAKER OBJECT WHICH IS EXTENDS VBOX
                        PostMaker postMaker= new PostMaker(userName,lastName,new MainPage().makeImageView(),writePost.getText(),new Date());
                        postMaker.getStyleClass().add("newPost");

                        VBox.setMargin(postMaker,new Insets(5,0,25,75));
                        postMaker.setMaxHeight(300);
                        postMaker.setMaxWidth(500);
                        //ADDS THE POSTMAKER OBJECT TO THE MAIN VBOX LAYOUT
                        layout.getChildren().add(postMaker);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        profilePageScene= new Scene(scrollPane,800,600);
        stage.setScene(profilePageScene);
        String uri = Paths.get("src/main/java/shopping/Profile.css").toUri().toString();
        profilePageScene.getStylesheets().add(uri);


    }
    public void oldPosts(VBox layout) throws Exception {
        // READS CURRENT POSTS OF THE USER AND ADDS THEM TO THE LAYOUT
        File posts = new File("src/main/java/shopping/accounts/"+Login.enteredUser+"/"+Login.enteredUser+"posts.txt");
        Scanner scan = new Scanner(posts);
        File pathFile = new File("accounts.txt");

        while (scan.hasNextLine())
        {

            String nextLine = scan.nextLine();
            if (!nextLine.equals(""))
            {

                File fileAccounts = new File("accounts.txt");
                BufferedReader reader = new BufferedReader(new FileReader(fileAccounts));
                String line = "";
                File imageFile = null;

                while ((line = reader.readLine()) != null) {

                    if (line.split("\\|")[0].equals(Login.enteredUser)) {
                        imageFile = new File(line.split("\\|")[2]);
                    }
                }
                reader.close();

                Image image = new Image(imageFile.toURI().toString());

                String userName = nextLine.split("\\|")[2];
                String lastName = nextLine.split("\\|")[3];
                String postText = nextLine.split("\\|")[4];

                DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyy hh:mm:ss");
                Date date1= dateFormat.parse(nextLine.split("\\|")[0]);
                // ANOTHER POSTMAKER OBJECT FOR REFRESH LIKE SCREEN
                PostMaker postMaker= new PostMaker(userName,lastName,image,postText,date1);
                VBox.setMargin(postMaker,new Insets(5,0,25,75));
                postMaker.setMaxHeight(300);
                postMaker.setMaxWidth(500);
                postMaker.getStyleClass().add("newPost");
                layout.getChildren().add(postMaker);
            }

        }
        scan.close();
    }


    // IT CREATES POSTS TXT THIS IS FOR REGISTER PAGE TO NOT TO BE NULL
    public static void create(String path)
    {
        String fileName="src/main/java/shopping/accounts/"+ path+"/"+path+"posts.txt";

        File myObj = new File(fileName);
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());


            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void setUserName(String userName)
    {
        this.userName = userName;

    }
    public String getUserName()
    {
        return userName;
    }
    public  void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getLastName()
    {
        return lastName;
    }
}
