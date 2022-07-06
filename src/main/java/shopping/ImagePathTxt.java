package shopping;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ImagePathTxt {
    public static ArrayList<String[]> accountLines = new ArrayList<>();
    public static String imagePathWriter(String path) throws IOException {

        //Scans the accounts txt
        File file = new File("accounts.txt");
        String stringReturn="";
        Scanner scan = new Scanner(file);
        while (scan.hasNext())
        {

            String line = scan.next();
            accountLines.add(line.split("\\|"));


        }
        //Array list has String array inside of it
        //double for loop to find correct user's path
        for(int i = 0; i < accountLines.size(); i++)
        {
            for (int j=0; j < accountLines.get(i).length; j++)
            {
                if(accountLines.get(i)[0].equals(Login.enteredUser))
                {
                    accountLines.get(i)[2] = path;

                }
                if(j != accountLines.get(i).length -1)
                    stringReturn += accountLines.get(i)[j] + "|";
                else
                    stringReturn+=accountLines.get(i)[j];
            }
            stringReturn+="\n";
        }

        scan.close();
        return stringReturn;
    }
}
