/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Master;
import model.Student;

/**
 * FXML Controller class
 *
 * @author Stanley Sie
 */
public class AddStudentController {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button add, cancel;
    @FXML
    private TextField name, course, age;
    
    private Stage stage;
    private Master master;
    
    public AddStudentController(Master master) throws IOException {
        this.master = master;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddStudent.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Add Student");
        stage.setScene(scene);
        stage.show();
    }
    
    public void initialize() {
        add.setOnAction(event -> {
            Student s = new Student();
            s.setName(name.getText());
            s.setCourse(course.getText());
            s.setAge(Integer.parseInt(age.getText()));
            master.addStudent(s);
            this.stage.close();
        });
        cancel.setOnAction(event -> {
           this.stage.close();
        });
    }    
}
