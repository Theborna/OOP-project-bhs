package com.database;

import com.models.node.user.User;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

import static com.database.DBInfo.getConnection;

public class UserDB {
    public static void registerUSer(@NotNull User user) throws SQLException {
        Connection con = getConnection();
        String query = "insert into pmresan.users values(NULL,'" + user.getUsername() + "'" +
                ",'" + user.getPassword() + "','" + user.getSalt() + "', '" +
                user.getDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                "', " + Integer.toString(user.getUserType()) + ", " + ((user.isPublic()) ? "1" : "0") + ", 0,0,0,'" + user.getName() + "','" + user.getLastName() +
                "', '" + user.getEmail() + "', 0)";
        //System.out.println(query);
        con.createStatement().execute(query);
        con.close();
    }

    public static void updtaeUser(User user) throws SQLException {
        Connection con = getConnection();
        String query = "update users set US_USName = '" + user.getUsername() + "', US_Pass_enc = '" + user.getPassword() + "',  " +
                "US_Salt = '" + user.getSalt() + "', US_Type = " + Integer.toString(user.getUserType()) + "" +
                ", US_Visibilty = " + ((user.isPublic()) ? "1" : "0") + ", US_Name = '" + user.getName() + "'" +
                ", US_LastName = '" + user.getLastName() + "', US_Email = '" + user.getLastName() +
                "', US_PromotionIndex = " + Double.toString(user.getPromoindex()) + ";";
        //System.out.println(query);
        con.createStatement().execute(query);
        con.close();
    }

    public User auth(User user) throws Throwable {
        
    }

    public void deleteUser(User user) throws SQLException {

    }
}
