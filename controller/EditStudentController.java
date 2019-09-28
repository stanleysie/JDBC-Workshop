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
 *
 * @author Stanley Sie
 */
public class EditStudentController {
    @FXML
    private Button save, cancel;
    @FXML
    private TextField name, course, age;
    
    private Stage stage;
    private Master master;
    private Student student;
    
    public EditStudentController(Master master, Student student) throws IOException {
        this.master = master;
        this.student = student;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditStudent.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Edit Student");
        stage.setScene(scene);
        setData();
        stage.show();
    }
    
    public void initialize() {
        save.setOnAction(event -> {
            student.setName(name.getText());
            student.setCourse(course.getText());
            student.setAge(Integer.parseInt(age.getText()));
            master.updateStudent(student);
            this.stage.close();
        });
        cancel.setOnAction(event -> {
            this.stage.close();
        });
    }
    
    public void setData() {
        name.setText(student.getName());
        age.setText("" + student.getAge());
        course.setText(student.getCourse());
    }
}
