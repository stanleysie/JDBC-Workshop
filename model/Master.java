/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.TableController;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author Stanley Sie
 */
public class Master {
    private Service service;
    private TableController main;
    
    public Master() {
        service = new Service();
    }

    public void addStudent(Student s) {
        try {
            service.addStudent(s);
            main.update();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteStudent(Student s) {
        try {
            service.deleteStudent(s);
            main.update();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateStudent(Student s) {
        try {
            service.updateStudent(s);
            main.update();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public ObservableList<Student> getStudent() {
        try {
            return service.getStudent();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void setMain(TableController main) {
        this.main = main;
    }
}
