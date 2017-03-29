/**
 * Created by Emmett on 3/8/2017.
 */
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class Editor {


    public static void main(String[] args) {
        boolean exitLoop = false;
        Scanner input = new Scanner(System.in);
        Gson g = new Gson();

        while (!exitLoop) {
            System.out.println("DIRECTORY EDITOR - CMNDS : [ ADD | CLR | END | PRINT | QUIT ] (enter 'QUIT' to exit) ");
            String entry = input.nextLine();
            if (entry.equals("QUIT")) { break; }

            else if (entry.equals("ADD")) {
                ArrayList<Employee> employees = new ArrayList<Employee>();
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


            }
            else if (entry.equals("CLR")) {

            }
            else if (entry.equals("PRINT"))
            {

            }
            else {}
        }
    }

  public void sendDataToServer(String json){
    try {
      // set up URL to connect
      URL site = new URL("http://localhost:8000/sendresults");
      HttpURLConnection conn = (HttpURLConnection) site.openConnection();

      // create POST request
      conn.setRequestMethod("POST");
      conn.setDoOutput(true);
      conn.setDoInput(true);
      DataOutputStream out = new DataOutputStream(conn.getOutputStream());

      out.writeBytes(json);
      out.flush();
      out.close();

      System.out.println("JSON sent to server");

      InputStreamReader inputStr = new InputStreamReader(conn.getInputStream());

      StringBuilder sb = new StringBuilder();

      int nextChar;
      while((nextChar = inputStr.read()) > -1) {
        sb = sb.append((char) nextChar);
      }

      System.out.println("Return: " + sb);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
