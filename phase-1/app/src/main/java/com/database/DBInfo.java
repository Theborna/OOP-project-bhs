package com.database;

import com.project.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBInfo {
    public static final String DBURL = "jdbc:mysql://localhost:3306/pmresan";
    public static final String USName = "root";
    public static final String PassWD = "";

    public static Connection getConnection() throws SQLException {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        Log.logger.info("Connected to database succressfully");
        return DriverManager.getConnection(DBURL, USName, PassWD);
    }

    public static Connection getConnection(String classname) throws SQLException {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        Log.logger.info("Connected to database succressfully");
        Log.logger.info("Connected to database succressfully" + " : " + classname);
        return DriverManager.getConnection(DBURL, USName, PassWD);
    }

    public static LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date.substring(0,
                date.length() - 2), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}