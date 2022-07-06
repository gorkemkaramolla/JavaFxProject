package shopping;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.image.ImageView;

import java.util.Date;

// THIS IS POSTMAKER VBOX TO MAKE POSTS
public class PostMaker extends VBox {

    PostMaker(String firstName, String lastName, Image image, String postText, Date date)
    {
        makePost(firstName,lastName,image,postText,date);
    }
    private void makePost(String firstName,String lastName,Image image,String postText, Date date)
    {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        Label label = new Label(firstName+" " + lastName);
        Label postLabel = new Label(postText);
        Label dateLabel = new Label(date.toString());

        HBox hBox = new HBox(imageView,label,dateLabel);
        hBox.setSpacing(10);

        HBox postLabelLine = new HBox(postLabel);
        hBox.setMargin(label,new Insets(20,0,0,0));



        postLabelLine.setMargin(postLabel,new Insets(15,0,0,62));



        this.getChildren().addAll(hBox,postLabelLine);
    }
}
