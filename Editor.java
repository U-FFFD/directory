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

private class Employee {
	
	private String _fname;
	private String _lname;
	private String _department;
	private String _phonenum;

	public Employee(String fname, String lname, String department, String phonenum) {
		_fname = fname;
		_lname = lname;
		_department = department;
		_phonenum = phonenum;
	}

	public String toString() {
		return _lname + ", " + _fname + " " + _phonenum + " " +  _department;
	}
