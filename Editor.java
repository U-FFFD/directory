/**
 * Created by Emmett on 3/8/2017.
 */
import com.google.gson.Gson;

import java.util.Scanner;
public class Editor {


    public static void main (String[] args) {
        boolean exitLoop = false;
        Scanner input = new Scanner(System.in);

        String entry = "";

        Gson g = new Gson();
        while(!exitLoop) {
            System.out.println("Enter a new directory record : (enter 'exit' to quit) ");
            entry = input.nextLine();
            if(entry.equals("exit"))
                    exitLoop = true;

        }


    }

}
