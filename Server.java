/*
  CS361
  Lab07
  8 March 2017
*/

import java.util.Collection;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Server{
  public class MainDirectory implements Directory{
    private ArrayList<Employee> emplDir;

    public MainDirectory(){
    	emplDir = new ArrayList<Employee>();
    }


    public void add(String input){
      Gson g = new Gson();
      ArrayList<Employee> imported = new ArrayList<>();
      imported = g.fromJson(input, new TypeToken<Collection<Employee>>(){}.getType());
      emplDir.addAll(imported);
    }

    @Override public void print(){
     	for(Employee x : emplDir)
    	{
    	System.out.print(x.toString());
    	}
      System.out.println();
    }

    @Override public void clear(){

      emplDir = new ArrayList<>();

    }
  }
  /*
  private class Employee{
    public String lastName;
    public String firstName;
    public String phone;
    public String dept;

    @Override public String toString(){
      return lastName + " " + firstName + " " + phone + " " + dept;
    }
  }
  */
}
