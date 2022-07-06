package shopping;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class MToolBar extends HBox {
    public  static Image image;
    private ToolBar toolBar;
    private Button btnOpen;
    private Button btnExit;
    private ContentDisplay CONTENT_DISPLAY = ContentDisplay.GRAPHIC_ONLY;

    public MToolBar() {
        buildUI();
    }

    public MToolBar(String s)
    {
        mainPageToolBar();
        toolBar = new ToolBar(btnExit);
        getChildren().add(this.toolBar);

        toolBar.getStyleClass().add("toolBar");

    }

    private void buildUI() {


        mainPageToolBar();
        openPics();

        toolBar = new ToolBar(btnOpen, btnExit);

        getChildren().add(this.toolBar);
        toolBar.getStyleClass().add("toolBar");




    }
    //CREATES VISUAL BUTTONS
    public Button createButton(String text, String file) {

        Image image = new Image(new File(file).toURI().toString());
        ImageView iView = new ImageView(image);
        iView.setFitHeight(32);
        iView.setFitWidth(32);
        Button button = new Button();
        button.setGraphic(iView);
        button.getStyleClass().add("mainPageButton");

        return button;
    }
    // EXIT BUTTON EVENT
    private void mainPageToolBar() {

        btnExit = createButton("Exit", "src/main/java/shopping/images/exit.png");
        btnExit.setContentDisplay(CONTENT_DISPLAY);
        btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });


    }

    // THIS FUNCTION USES FILE CHOOSER TO CHOOSE IMAGES FROM YOUR LOCAL PC
    private void openPics()
    {

        btnOpen = createButton("Open", "src/main/java/shopping/images/addImage.png");
        btnOpen.setContentDisplay(CONTENT_DISPLAY);
        btnOpen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                //EXTENSION NAME TO CHECK THE FILE JPG OR PNG
                String extensionName;
                final FileChooser fileChooser = new FileChooser();
                File f = fileChooser.showOpenDialog(null);



                if(f != null)
                {
                    //SPLITS THE FILENAME FROM LAST INDEX OF DOT TO TAKE JPG OR PNG
                    String extension = f.getName().substring(f.getName().lastIndexOf("."));
                    extensionName = extension;
                    System.out.println(extension);
                    //IF IT IS NOT PNG OR JPG SHOWS AN ALERT
                    if(!(extension.equals(".jpg")||extension.equals(".png")))
                    {
                        Alert empty = new Alert(Alert.AlertType.ERROR);
                        empty.setTitle("Error");
                        empty.setHeaderText("Invalid file type");
                        empty.setContentText("Your picture extension should be png or jpeg");
                        empty.showAndWait();
                    }
                    //  ELSE CALLS THE IMAGE WRITER FUNCTION
                    else
                    {
                        String fileName="src/main/java/shopping/accounts/"+Login.enteredUser+"/"+f.getName();
                        System.out.println(fileName);

                        try {
                            String content = ImagePathTxt.imagePathWriter(fileName);

                            FileWriter writer = new FileWriter("accounts.txt",false);
                            writer.write(content);
                            writer.close();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        File file = new File(fileName);
                        File defaultFile = new File("src/main/java/shopping/images/profileIcon.png");

                        //UPDATES THE CURRENT PROFILE PICTURE IN PROFILE
                        image =new Image(f.toURI().toString());
                        try {
                            MForm.changeProfilePicture(image);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //EXTERNAL JAR TO WRITE IMAGES
                        BufferedImage BI = SwingFXUtils.fromFXImage(image,null);

                        try {

                            ImageIO.write(BI,extensionName.split("\\.")[1],file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                }

            }
        });


    }


    }
