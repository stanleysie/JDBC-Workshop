/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 *
 * @author Stanley Sie
 */
public class JDBCConnection {
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "mysql2019";
    private final static String DATABASE = "student";
    
    public JDBCConnection() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection create() {
        try {
            return (DriverManager.getConnection(URL +
                    DATABASE +
                    "?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone="
                    + TimeZone.getDefault().getID(), USERNAME, PASSWORD));
        } catch (SQLException e) {
            e.printStackTrace();
            return (null);
        }
    }
}
