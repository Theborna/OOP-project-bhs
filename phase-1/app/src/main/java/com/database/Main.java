package com.database;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws SQLException {
        // write your code here
        Statement st = DBInfo.getConnection().prepareStatement("select * from chat");
        ResultSet rs = st.executeQuery("select * from chat");
        while (rs.next()) {
            System.out.println(rs.getString(4));
        }

    }

}
