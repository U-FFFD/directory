/**
 * Created by Emmett on 3/8/2017.
 */
import com.google.gson.Gson;

import java.util.Scanner;
public class Editor {


    public static void main(String[] args) {
        boolean exitLoop = false;
        Scanner input = new Scanner(System.in);
        DirectoryProxy proxy = new DirectoryProxy;
        String entry = "";

        Gson g = new Gson();
        while (!exitLoop) {
            System.out.println("Enter a new directory record (enter 'exit' to quit) ");
            System.out.println("First Name: ");

            entry = input.nextLine();
            if (entry.equals("exit")) { break; }
            String _fname = entry;

            System.out.println("Last Name: ");
            String _lname = input.nextLine();
            System.out.println("Phone: ");
            String _phone = input.nextLine();
            System.out.println("Department: ");
            String _dept = input.nextLine();

            proxy.add(new Employee(_fname, _lname, _phone, _dept));

        }


    }

    private class DirectoryProxy {
        DirectoryProxy() {

        }

        /**
         * @param e
         */
        public void add(Employee e) {


        }
    }


}
