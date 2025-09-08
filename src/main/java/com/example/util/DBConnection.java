package com.example.util;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static Connection conn;

    public static Connection getConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
                Properties props = new Properties();
                props.load(input);

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.username");
                String pass = props.getProperty("db.password");

                conn = DriverManager.getConnection(url, user, pass);
            }
        }
        return conn;
    }
}
