/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Stanley Sie
 */
public class Service {

    private JDBCConnection database;
    
    public Service() {
        database = new JDBCConnection();
    }
    
    public void addStudent(Student s) throws SQLException {
        Connection connection = database.create();
        String query = "INSERT INTO students (name, age, course) VALUE (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            statement.setString(1, s.getName());
            statement.setInt(2, s.getAge());
            statement.setString(3, s.getCourse());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        }
    }
    
    public void deleteStudent(Student s) throws SQLException {
        Connection connection = database.create();
        String query = "DELETE FROM students WHERE idstudents = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            statement.setInt(1, s.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        }
    }
    
    public void updateStudent(Student s) throws SQLException {
        Connection connection = database.create();
        String query = "UPDATE students SET name = ?, age = ?, course = ? WHERE idstudents = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            statement.setString(1, s.getName());
            statement.setInt(2, s.getAge());
            statement.setString(3, s.getCourse());
            statement.setInt(4, s.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        }
    }
    
    public ObservableList<Student> getStudent() throws SQLException {
        ObservableList<Student> student = FXCollections.observableArrayList();
        Connection connection = database.create();
        String query = "SELECT * FROM students";
        PreparedStatement statement = connection.prepareStatement(query);
        try {
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Student s = new Student();
                s.setId(result.getInt("idstudents"));
                s.setName(result.getString("name"));
                s.setAge(result.getInt("age"));
                s.setCourse(result.getString("course"));
                student.add(s);
            }  
            return student;
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        }  
        return null;
    }
}
