/**
 * Created by Emmett on 3/8/2017.
 */
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
public class Editor {


    public static void main(String[] args) {
        boolean exitLoop = false;
        Scanner input = new Scanner(System.in);
        DirectoryProxy proxy = new DirectoryProxy();
        Gson g = new Gson();

        while (!exitLoop) {
            System.out.println("DIRECTORY EDITOR - CMNDS : [ ADD | CLR | END | QUIT ] (enter 'QUIT' to exit) ");
            String entry = input.nextLine();
            if (entry.equals("QUIT")) { break; }

            else if (entry.equals("ADD")) {
                ArrayList<Employee> employees = null;
                String _fname;
                System.out.println("Enter 'END' when done adding.");

                // Start add loop
                while(true) {
                    System.out.println("First Name: ");
                    _fname = input.nextLine();
                    if(_fname.equals("END")) { break; }
                    System.out.println("Last Name: ");
                    String _lname = input.nextLine();
                    System.out.println("Phone: ");
                    String _phone = input.nextLine();
                    System.out.println("Department: ");
                    String _dept = input.nextLine();
                    if(_dept.equals("END")) { break; }

                    employees.add(new Employee(_fname, _lname, _phone, _dept));
                }

                proxy.add(employees);
            }
            else if (entry.equals("CLR")) {
                proxy.clear();
            }
            else {}
        }
    }

    private static class DirectoryProxy {
        DirectoryProxy() {

        }

        /**
         * @param e
         */
        public void add(Collection<Employee> employees) {

        }
        public void print() {

        }
        public void clear() {

        }
    }
}
