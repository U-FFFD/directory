import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab 8 GUI");

        Button btn = new Button();
        btn.setText("Submit [send]");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button 1 output");
            }
        });

        Button btn2 = new Button();
        btn2.setText("Exit");
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button 2 output");
            }
        });


        StackPane root = new StackPane();
        root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        btn.setTranslateX(0);
        btn.setTranslateY(20);
        //btn2.setTranslateX(20);
        btn2.setTranslateY(100);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setTranslateY(-140);
        grid.setTranslateX(90);

        Text scenetitle = new Text("Welcome");
        scenetitle.setId("fancytext");
        grid.add(scenetitle, 0, 0, 3, 1);

        Label firstName = new Label("First Name:");
        firstName.setTextFill(Color.WHITE);
        grid.add(firstName, 0, 1);
        TextField firstNameField = new TextField();
        grid.add(firstNameField, 1, 1);

        Label lastName = new Label("Last Name:");
        lastName.setTextFill(Color.WHITE);
        grid.add(lastName, 0, 2);
        TextField lastNameField = new TextField();
        grid.add(lastNameField, 1, 2);

        Label department = new Label("Department:");
        department.setTextFill(Color.WHITE);
        grid.add(department, 0,3);
        TextField departmentField = new TextField();
        grid.add(departmentField, 1 , 3);

        Label phone = new Label("Phone Number:");
        phone.setTextFill(Color.WHITE);
        grid.add(phone, 0,4);
        TextField phoneField = new TextField();
        grid.add(phoneField, 1 , 4);

        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Male");
        rb1.setTextFill(Color.WHITE);
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        rb1.setTranslateX(grid.getTranslateX() + 220);
        rb1.setTranslateY(-140);

        RadioButton rb2 = new RadioButton("Female");
        rb2.setTextFill(Color.WHITE);
        rb2.setToggleGroup(group);
        rb2.setTranslateX(grid.getTranslateX() + 220);
        rb2.setTranslateY(-110);

        RadioButton rb3 = new RadioButton("Other");
        rb3.setTextFill(Color.WHITE);
        rb3.setToggleGroup(group);
        rb3.setTranslateX(grid.getTranslateX() + 220);
        rb3.setTranslateY(-80);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                        System.out.println("test RB");


            }
        });

        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList (
            "Mr." , "Ms." , "Mrs." , "Dr." , "Col." , "Prof.");

        list.setMaxSize(60,110);
        list.setItems(items);
        list.setTranslateX(-170);
        list.setTranslateY(-110);

        root.getChildren().add(grid);
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        root.getChildren().add(rb1);
        root.getChildren().add(rb2);
        root.getChildren().add(rb3);
        root.getChildren().add(list);

        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
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
