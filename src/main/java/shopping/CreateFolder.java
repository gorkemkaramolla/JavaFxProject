package shopping;

import java.io.File;
import java.io.IOException;

public class CreateFolder {

    CreateFolder(String path) {

        create(path);

    }
    //Inside of accounts folder create a folder by username  if it doesn't exist creates
    private void create(String path)
    {
        System.out.println(path);
        File theDir = new File("src/main/java/shopping/accounts/"+path+"/");
        if (!theDir.exists()){
            theDir.mkdirs();
            String fileName="src/main/java/shopping/accounts/"+ path+"/"+path+".txt";

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
    }

}