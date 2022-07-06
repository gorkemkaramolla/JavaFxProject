package shopping.other;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import shopping.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class GridPaneMaker extends GridPane  {
    public static ImageView profileImage;
    private int length;
    private Button btnSave;
    private Button btnProfile;
    private TextArea textArea;
    private TextField textName;
    private TextField textSurName;
    private ButtonBar btnBar;

    public GridPaneMaker() throws IOException {
        buildUI();
    }
    private void buildUI( ) throws IOException {

        textArea = new TextArea();
        textName = new TextField();
        textName.setPromptText("Name");
        textName.setPrefColumnCount(2);

        textSurName = new TextField();
        textSurName.setPromptText("SurName");
        textSurName.setPrefColumnCount(2);

        btnSave = new Button("Save");
        btnProfile = new Button("Profile");
        textArea.setPrefRowCount(5);
        textArea.setWrapText(true);
        textArea.setPromptText("Bio");

        textArea.setPrefColumnCount(3);
        btnBar = new ButtonBar();
        btnBar.getButtons().addAll(btnSave);
        Text counterText =new Text();

        counterText.setText(String.valueOf(getLength()));

        textArea.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                setLentgth();

                if(getLength()>50)
                {
                    counterText.setFill(Color.RED);
                }
                else
                {
                    counterText.setFill(Color.GRAY);
                }
                counterText.setText(String.valueOf(getLength()));

            }
        });
        HBox buttons = new HBox(120);
        buttons.getChildren().addAll(btnSave,btnProfile);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        this.setHgap(10);
        this.add(new Label("Create Your Profile"),1,0,1,1);
        this.add(textName,1,1,1,1);
        this.add(textSurName,1,2,1,1);
        Image image = new MainPage().makeImageView();
        profileImage = new ImageView(image);
        profileImage.setFitWidth(200);
        profileImage.setFitHeight(200);





        this.add(profileImage,1,3,1,1);
        this.add(counterText,1,4,1,1);
        this.add(textArea,1,5,1,1);
        this.add(buttons,1,6,1,1);



        this.setHgap(300);
        this.setVgap(8);
        this.setPadding(new Insets(0,10,10,10));


        btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                        btnSaveHandler();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        btnProfile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    btnProfileHandler();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void btnProfileHandler() throws Exception {
        MainPage.mainPage();
    }


    public void btnSaveHandler() throws IOException, ParseException {
        File file = new File("src/main/java/shopping/accounts/"+Login.enteredUser+"/"+Login.enteredUser+".txt");

        FileWriter writer = new FileWriter(file);
        if(!textName.getText().equals("") && !textSurName.getText().equals(""))
        {
            if(textArea.getText().length() >50)
            {
                Alert tooManyCharacters = new Alert(Alert.AlertType.ERROR);
                tooManyCharacters.setTitle("Error");
                tooManyCharacters.setHeaderText("You can't add more than 50 characters");
                tooManyCharacters.setContentText("Try Again");
                tooManyCharacters.showAndWait();
                return;
            }

            if(textName.getText().trim().length() >17 || textSurName.getText().trim().length() >8)
            {
                Alert tooManyCharacters = new Alert(Alert.AlertType.ERROR);
                tooManyCharacters.setTitle("Error");
                tooManyCharacters.setHeaderText("Firstname or Surname can't more than 16 Characters");
                tooManyCharacters.setContentText("Try Again");
                tooManyCharacters.showAndWait();
                return;

            }
        }
        else
        {
            Alert tooManyCharacters = new Alert(Alert.AlertType.ERROR);
            tooManyCharacters.setTitle("Error");
            tooManyCharacters.setHeaderText("Firstname or Surname can't be empty");
            tooManyCharacters.setContentText("Try to fill every fields");
            tooManyCharacters.showAndWait();
            return;
        }


        writer.write(textArea.getText().replace("\n","|").trim()+";"+textName.getText().trim()+";"+textSurName.getText().trim());
        writer.close();
        MainPage.mainPage();
        return;
    }

    public void setLentgth()
    {
        length=textArea.getLength();

    }
    public int getLength()
    {
        return this.length;
    }
}
