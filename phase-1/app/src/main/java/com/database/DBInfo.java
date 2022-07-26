package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBInfo {
    public static final String DBURL = "jdbc:mysql://localhost:3306/pmresan";
    public static final String USName = "root";
    public static final String PassWD = "";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DBURL, USName, PassWD);
    }
}