/*
  CS361
  Lab08
  15 March 2017
*/

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.io.File;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server{

  static String sharedResponse = "";
  static boolean gotMessageFlag = false;

  static MainDirectory theDirectory = new MainDirectory();

  static String htmltop = "<!DOCTYPE html>\n"
  + "<head>\n"
  + "<title>U+FFFD Corp's Super Private Employee Directory</title>\n"
  + "<link rel='stylesheet' type='text/css' href='style.css'>\n"
  + "</head>\n"
  + "<body>\n"
  + "<table>\n"
  + "<thead>\n"
  + "<tr>\n"
  + "<th>Title</th>\n"
  + "<th>First Name</th>\n"
  + "<th>Last Name</th>\n"
  + "<th>Gender</th>\n"
  + "<th>Phone</th>\n"
  + "<th>Department</th>\n"
  + "</tr>\n";

  static String htmlbottom = "</table>\n"
  + "</body\n"
  + "</html>\n";

  public static void main(String[] args) throws Exception{
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

    // creates contexts for displaying and posting data
    server.createContext("/displayresults", new DisplayHandler());
    server.createContext("/sendresults", new PostHandler());
    server.createContext("/style.css", new CssHandler());

    server.setExecutor(null);

    System.out.println("Server starting");
    server.start();
  }

  static class MainDirectory implements Directory{
    private ArrayList<Employee> emplDir;

    public MainDirectory(){
    	emplDir = new ArrayList<Employee>();
    }


    public void add(String input){
      Gson g = new Gson();
      ArrayList<Employee> imported = new ArrayList<>();
      imported = g.fromJson(input, new TypeToken<Collection<Employee>>(){}.getType());
      emplDir.addAll(imported);

      Collections.sort(emplDir);
    }

    @Override public void print(){
     	for(Employee x : emplDir)
    	{
    	   System.out.print(x.toString());
         System.out.println();
    	}
      System.out.println();
    }

    @Override public void clear(){

      emplDir = new ArrayList<>();

    }

    public String toString(){
      String ret = "";
      for (Employee x : emplDir){
        ret += x.toString();
        ret += "\n";
      }

      return ret;
    }

    public String toTable(){

      return "";
    }
  }

  static class PostHandler implements HttpHandler {
    public void handle(HttpExchange transmission) throws IOException {
      //  shared data that is used with other handlers
      sharedResponse = "";

      // set up a stream to read the body of the request
      InputStream inputStr = transmission.getRequestBody();

      // set up a stream to write out the body of the response
      OutputStream outputStream = transmission.getResponseBody();

      // string to hold the result of reading in the request
      StringBuilder sb = new StringBuilder();

      // read the characters from the request byte by byte and build up the sharedResponse
      int nextChar = inputStr.read();
      while (nextChar > -1) {
          sb=sb.append((char)nextChar);
          nextChar=inputStr.read();
      }

      // create our response String to use in other handler
      sharedResponse = sharedResponse+sb.toString();

      String[] splitIn = sharedResponse.split(" ", 2);

      switch(splitIn[0]){
        case "ADD":
          theDirectory.add(splitIn[1]);
          break;
        case "PRINT":
          theDirectory.print();
          break;
        case "CLEAR":
          theDirectory.clear();
          break;
        default:
          break;
      }

      // respond to the POST with ROGER
      String postResponse = "ROGER JSON RECEIVED";

      // assume that stuff works all the time
      transmission.sendResponseHeaders(300, postResponse.length());

      // write it and return it
      outputStream.write(postResponse.getBytes());

      outputStream.close();
    }
  }

  static class DisplayHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {

      String response = "";
      response += htmltop;
			Gson g = new Gson();
			// set up the header
      System.out.println(response);

      response += theDirectory.toTable();

      response += htmlbottom;
      System.out.println(response);

      // write out the response
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
  }

  static class CssHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
      String css = new Scanner(new File("style.css")).useDelimiter("\\Z").next();

      System.out.println("css: " + css);

      t.sendResponseHeaders(200, css.length());
      OutputStream os = t.getResponseBody();
      os.write(css.getBytes());
      os.close();
    }
  }
}
