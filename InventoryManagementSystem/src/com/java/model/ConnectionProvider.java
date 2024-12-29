package com.java.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class provides a method to establish a connection to the MySQL database.
 * It uses JDBC to connect to the database and handles any connection errors.
 */
public class ConnectionProvider {
    
    // Database URL, username, and password for the MySQL connection
    private static final String URL = "jdbc:mysql://localhost:3306/productdb";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    /**
     * Establishes and returns a connection to the MySQL database.
     * If the connection cannot be established, it catches the SQLException and 
     * prints the error stack trace.
     * 
     * @return a Connection object for interacting with the database, or null if 
     *         the connection could not be established.
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();  // Prints the error to the console if connection fails
            return null;  // Returns null if the connection cannot be established
        }
    }
}
