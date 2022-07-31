package com.database;

import com.project.models.connection.Like;
import com.project.models.node.post.Post;
import com.project.models.node.user.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class viewDB {

    public static void sendToDB(Like like) throws SQLException {
        if(!isExists(like.getObj2().getId(), like.getObj1().getId())){
            insertView(like);
        }

    }

    public static void insertView(Like like) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("insert into views values(" + like.getObj1().getId() + ", " + like.getObj2().getId() + ",0, "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ", NULL)");
    }

    public static Like getLike(long userID, long postID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from views where post_id = " + postID + " and user_id = " + userID);
        if (!rs.next()) {
            return null;
        } else {
            Like lk = new Like(PostDB.getPostbyPostID(postID), UserDB.getUserInfo(userID));
            lk.setValue(rs.getInt(3));
            return lk;
        }
    }

    public static boolean isExists(long userID, long postID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from views where post_id = " + postID + " and user_id = " + userID);
        if (!rs.next()) {
            return false;
        } else {
            return true;
        }
    }

}
