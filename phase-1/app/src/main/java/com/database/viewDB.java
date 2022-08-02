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
import java.util.ArrayList;

public class viewDB {

    public static void sendToDB(Like like) throws SQLException {
        if (!isExists(like.getObj2().getId(), like.getObj1().getId())) {
            insertView(like);
        } else {
            Like temp = getLike(like.getObj2().getId(), like.getObj1().getId());
            update(like, temp);
        }

    }

    public static void insertView(Like like) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("insert into views values(" + like.getObj1().getId() + ", " + like.getObj2().getId() + ",0, "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ", NULL)");
    }

    public static void update(Like like, Like prlike) throws SQLException {
        like.setValue(Math.min(like.getValue() + prlike.getValue(), 1));
        like.setValue(Math.max(like.getValue(), -1));
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("update views set like_date = '"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', set value = "
                + like.getValue() + " where post_id=" + like.getObj1().getId() + " and user_id = "
                + like.getObj2().getId());
        st.close();
        con.close();
    }

    public static Like getLike(long userID, long postID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from views where post_id = " + postID + " and user_id = " + userID);
        st.close();
        con.close();
        if (!rs.next()) {
            rs.close();
            return null;
        } else {
            Like lk = new Like(PostDB.getPostbyPostID(postID), UserDB.getUserInfo(userID));
            lk.setValue(rs.getInt(3));
            lk.setCreationDate(DBInfo.parseDate(rs.getString(4)));
            if (rs.getString(5).equals("null")) lk.setLastModifiedDate(DBInfo.parseDate(rs.getString(5)));
            rs.close();
            return lk;
        }
    }

    public static ArrayList<Like> getLikesByUserID(long userID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from views where user_id = " + userID);
        ArrayList<Like> ret = new ArrayList<>();
        while (rs.next()) {
            Like lk = new Like(PostDB.getPostbyPostID(rs.getLong(1)), UserDB.getUserInfo(rs.getLong(2)));
            lk.setValue(rs.getInt(3));
            lk.setCreationDate(DBInfo.parseDate(rs.getString(4)));
            if (rs.getString(5).equals("null")) lk.setLastModifiedDate(DBInfo.parseDate(rs.getString(5)));
            ret.add(lk);
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }

    public static ArrayList<Like> getLikesByPostID(long userID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from views where post_id = " + userID);
        ArrayList<Like> ret = new ArrayList<>();
        while (rs.next()) {
            Like lk = new Like(PostDB.getPostbyPostID(rs.getLong(1)), UserDB.getUserInfo(rs.getLong(2)));
            lk.setValue(rs.getInt(3));
            lk.setCreationDate(DBInfo.parseDate(rs.getString(4)));
            if (rs.getString(5).equals("null")) lk.setLastModifiedDate(DBInfo.parseDate(rs.getString(5)));
            ret.add(lk);
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }

    public static boolean isExists(long userID, long postID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from views where post_id = " + postID + " and user_id = " + userID);
        st.close();
        con.close();
        if (!rs.next()) {
            rs.close();
            return false;
        } else {
            rs.close();
            return true;
        }
    }

}
