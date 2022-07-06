package shopping;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.Scanner;

public class RegisterController extends Register {
    private final String REGEX = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";


    RegisterController(String username, String password) throws IOException {
        register(username,password);
    }
    public  void register( String username, String password) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("accounts.txt"),true));
        PrintWriter pwriter = new PrintWriter(writer);



        File file = new File("accounts.txt");

        Scanner scan = new Scanner(file);
        //IF ONE OF THE TEXTFIELDS EMPTY SHOW AN ERROR AND RETURN
        if((username==null || username.equals("")) || (password == null || password.equals(""))) {


            System.out.println("mistake");
            Alert empty = new Alert(Alert.AlertType.ERROR);
            empty.setTitle("Error");
            empty.setHeaderText("Username or password can't be empty");
            empty.setContentText("Fill the required fields");
            empty.showAndWait();
            return;

        }
        //IF USERNAME NOT MATCHES WITH REGEX SHOWS AN ERROR AND RETURN
        else if (!username.matches(this.REGEX))
        {
            System.out.println("regexMistake");

            Alert empty = new Alert(Alert.AlertType.ERROR);
            empty.setTitle("Error");
            empty.setHeaderText("Username should be atleast 8 characters up to 20");
            empty.setContentText("Your username should be numbers or characters");
            empty.showAndWait();
            return;
        }

        else {
            // IF USERNAME IS TAKEN SHOWS AN ERROR AND RETURN
            while (scan.hasNext()) {
                if (scan.nextLine().split("\\|")[0].equals(username)) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Username already taken");
                    alert.setContentText("Try to change your username");



                    alert.showAndWait();
                    return;

                }

            }
            // IF ANYTHING RETURNS THEN WRITE TO THE TXT AND SHOW SUCCESSFUL INFORMATION
            pwriter.println(username.trim() + "|" + password.toString().trim()+"|"+"src/main/java/shopping/images/profileIcon.png");

            CreateFolder folder = new CreateFolder(username.trim());

            Alert empty = new Alert(Alert.AlertType.INFORMATION);
            empty.setTitle("Success");
            empty.setHeaderText("Successfully registered");
            empty.setContentText("Thank you for registering");
            empty.showAndWait();

            writer.close();
            pwriter.close();
            MProfilePage.create(username.trim());

            Login.loginPage();
        }



    }


}
