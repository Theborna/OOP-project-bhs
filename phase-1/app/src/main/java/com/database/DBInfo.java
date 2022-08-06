package com.database;

import com.project.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBInfo {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/electron";
    public static final String USName = "root";
    public static final String PassWD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USName, PassWD);
    }

    public static Connection getConnection(String classname) throws SQLException {
        Log.logger.info("Connected to database successfully" + " : " + classname);
        return DriverManager.getConnection(DB_URL, USName, PassWD);
    }

    public static LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date.substring(0,
                date.length() - 2), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}