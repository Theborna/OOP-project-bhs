package com.company.DB;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pmresan";
    private static final String USName = "root";
    private static final String PassWD = "";

    private static DataBase db = null;

    Connection con = DriverManager.getConnection(DB_URL, USName, PassWD);

    private DataBase() throws SQLException {
        db = this;
    }

    public static DataBase getInstance() throws SQLException {
        if (db == null) {
            db = new DataBase();
        }
        return db;
    }

    public synchronized ArrayList<Long> getUserIDByChatID(long chatid) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select userid from member where chatid = " + chatid);
        ArrayList<Long> ret = new ArrayList<>();
        while (rs.next()) {
            ret.add(rs.getLong("userid"));
        }
        return ret.isEmpty() ? null : ret;
    }
}
