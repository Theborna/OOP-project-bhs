package com.database;

import com.project.models.node.post.Post;
import com.project.models.node.user.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class viewDB {

    public static void insertView(User user, Post post) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("insert into views values("+post.getId()+", "+user.getId()+",0, "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +", NULL)");
    }

}
