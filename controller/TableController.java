/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Master;
import model.Student;

/**
 * FXML Controller class
 *
 * @author Stanley Sie
 */
public class TableController {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button add, del, edit;
    @FXML
    private TableView table;
    @FXML
    private AnchorPane pane;
    
    private ObservableList<Student> student;
    private Master master;
    private Stage stage;
    private Student current;
    private TableColumn name, age, course;
    
    public TableController(Master master, Stage stage) throws IOException {
        this.master = master;
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Table.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.stage.setTitle("Student Database");
        this.stage.setScene(scene);
        setData();
        this.stage.show();
    }
    
    public void initialize() {
        add.setOnAction(event -> {
            try {
                AddStudentController addStudent = new AddStudentController(this.master);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        });
        edit.setOnAction(event -> {
            try {
                EditStudentController editStudent = new EditStudentController(this.master, current);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        del.setOnAction(event -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Delete Student");
            alert.setContentText("Are you sure you want to delete " + current.getName() + "?");

            Optional<ButtonType> option = alert.showAndWait();
            if(option.get() == ButtonType.OK) {
                master.deleteStudent(current);
            } else if(option.get() == ButtonType.CANCEL) {
                event.consume();
            }
        });
        table.setOnMouseClicked(event -> {
            current = (Student) table.getSelectionModel().getSelectedItem();
            del.setDisable(false);
            edit.setDisable(false);
            if(current == null) {
                del.setDisable(true);
                edit.setDisable(true);
            }
        });
        pane.setOnMouseClicked(event -> {
            if(!event.equals(table)) {
                del.setDisable(true);
                edit.setDisable(true);
                table.getSelectionModel().clearSelection(); 
                current = null;
            }
        });
    }
    
    public void update() {
        table.getItems().clear();
        student = master.getStudent();
        table.setItems(student);
    }
    
    public void setData() {
        student = FXCollections.observableArrayList();
        this.master.setMain(this);
        name = new TableColumn("Name");
        name.setPrefWidth(150);
        name.setCellValueFactory(new PropertyValueFactory("name"));
        course = new TableColumn("Course");
        course.setCellValueFactory(new PropertyValueFactory("course"));
        course.setPrefWidth(100);
        age = new TableColumn("Age");
        age.setCellValueFactory(new PropertyValueFactory("age"));
        age.setPrefWidth(100);
        table.getColumns().setAll(name, course, age);
        del.setDisable(true);
        edit.setDisable(true);
        update();
    }
}