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


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        String _fname;
        String _lname;
        String _department;
        String _phonenum;

        String _gender;
        String _prefix;

        primaryStage.setTitle("Lab 8 GUI");

        StackPane root = new StackPane();
        root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

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
        RadioButton maleRb = new RadioButton("Male");
        maleRb.setUserData("Male");
        maleRb.setTextFill(Color.WHITE);
        maleRb.setToggleGroup(group);
        maleRb.setSelected(true);
        maleRb.setTranslateX(grid.getTranslateX() + 220);
        maleRb.setTranslateY(-140);

        RadioButton femaleRb = new RadioButton("Female");
        femaleRb.setTextFill(Color.WHITE);
        femaleRb.setUserData("Female");
        femaleRb.setToggleGroup(group);
        femaleRb.setTranslateX(grid.getTranslateX() + 220);
        femaleRb.setTranslateY(-110);

        RadioButton otherRb = new RadioButton("Other");
        otherRb.setTextFill(Color.WHITE);
        otherRb.setUserData("Other");
        otherRb.setToggleGroup(group);
        otherRb.setTranslateX(grid.getTranslateX() + 220);
        otherRb.setTranslateY(-80);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                  Toggle old_toggle, Toggle new_toggle) { 
                  System.out.println("Setting Gender");
            }
        });

        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList (
            "Mr." , "Ms." , "Mrs." , "Dr." , "Col." , "Prof.");

        list.setMaxSize(60,110);
        list.setItems(items);
        list.setTranslateX(-170);
        list.setTranslateY(-110);
         
        Button submitBtn = new Button();
        submitBtn.setText("Submit [send]");
        submitBtn.setTranslateX(0);
        submitBtn.setTranslateY(20); 
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            String _fname = firstNameField.getText();
            String _lname = lastNameField.getText();
            String _department = departmentField.getText();
            String _phonenum = phoneField.getText();
            String _gender = group.getSelectedToggle().getUserData().toString();
       
            Employee.Prefix _prefix = Employee.Prefix.valueOf(list.getOnMouseClicked().toString().toUpperCase());
            System.out.println(_prefix.toString());
            
            	Employee e = new Employee(_fname, _lname, _department, _phonenum, _gender, _prefix);  
            }
        });
        
        Button exitBtn = new Button();
        exitBtn.setText("Exit");
        exitBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	System.out.println("Exiting");
            	primaryStage.close();
                
            }
        });
        exitBtn.setTranslateY(100);

        root.getChildren().add(grid);
        root.getChildren().add(submitBtn);
        root.getChildren().add(exitBtn);
        root.getChildren().add(maleRb);
        root.getChildren().add(femaleRb);
        root.getChildren().add(otherRb);
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
}
