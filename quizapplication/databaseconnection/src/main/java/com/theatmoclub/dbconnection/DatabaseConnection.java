package com.theatmoclub.dbconnection;

import com.theatmoclub.exception.ConnectionFailedException;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    /**
     * Creates a connection for the database
     *
     */
    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "@Rajkumar2805");

        } catch (Exception exception) {
            throw new ConnectionFailedException("Connection failed");
        }
        return connection;
    }
}
