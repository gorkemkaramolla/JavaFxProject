package shopping;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LoginController {

    public static boolean login(String username, String password) throws FileNotFoundException {

        File file = new File("accounts.txt");

        //SCANS THE account.txt Checks for the user informations are correct or not
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine())
        {
            try{

                String line = scan.next();

                if(line.split("\\|")[0].equals(username) && line.split("\\|")[1].equals(password))
                {
                    Alert empty = new Alert(Alert.AlertType.INFORMATION);
                    empty.setTitle("Success");
                    empty.setHeaderText("Login successful");
                    empty.setContentText("Welcome to the App");
                    empty.showAndWait();
                    Login.enteredUser= username;


                    return true;
                }
            }
            catch (NoSuchElementException e)
            {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username or Password is wrong");
                alert.setContentText("Try to change it");


                alert.showAndWait();
                scan.close();
            }


        }

        return false;

    }
}
