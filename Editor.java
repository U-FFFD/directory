/**
 * Created by Emmett on 3/8/2017.
 */
import com.google.gson.Gson;

import java.util.Scanner;
public class Editor {


    public static void main(String[] args) {
        boolean exitLoop = false;
        Scanner input = new Scanner(System.in);

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

            directoryProxy(new Employee(_fname, _lname, _phone, _dept));

        }


    }

    public String directoryProxy(Employee e) {

    }

    private class Employee {

        private String _fname;
        private String _lname;
        private String _department;
        private String _phonenum;
        
        public Employee() {}

        public Employee(String fname, String lname, String department, String phonenum) {
            _fname = fname;
            _lname = lname;
            _department = department;
            _phonenum = phonenum;
        }

        public String toString() {
            return _lname + ", " + _fname + " " + _phonenum + " " + _department;
        }

    }
}
