package com.electro.database;

import com.electro.phase1.models.connection.Like;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.user.User;

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
            if (like.getValue() != 0)
                update(like, like);
        } else {
            Like temp = getLike(like.getObj2().getId(), like.getObj1().getId());
            update(like, temp);
        }

    }

    public static void insertView(Like like) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("insert into views values(" + like.getObj1().getId() + ", " + like.getObj2().getId() + ",0, '"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', NULL)");
        st.close();
        con.close();
    }

    public static void update(Like like, Like prevLike) throws SQLException {
        like.setValue(Math.min(like.getValue() + prevLike.getValue(), 1));
        like.setValue(Math.max(like.getValue(), -1));
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "update views set like_date = '"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', val = "
                + like.getValue() + " where post_id=" + like.getObj1().getId() + " and user_id = "
                + like.getObj2().getId();
//        System.out.println(query);
        st.execute(query);
        st.close();
        con.close();
    }

    public static Like getLike(long userID, long postID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from views where post_id = " + postID + " and user_id = " + userID);

        if (!rs.next()) {
            st.close();
            con.close();
            rs.close();
            return null;
        } else {
            Like lk = new Like(PostDB.getPostbyPostID(postID), UserDB.getUserInfo(userID));
            lk.setValue(rs.getInt(3));
            lk.setCreationDate(DBInfo.parseDate(rs.getString(4)));
            if (rs.getString(5)!=null) lk.setLastModifiedDate(DBInfo.parseDate(rs.getString(5)));
            st.close();
            con.close();
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
            if (rs.getString(5)!=null) lk.setLastModifiedDate(DBInfo.parseDate(rs.getString(5)));
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
        if (!rs.next()) {
            st.close();
            con.close();
            rs.close();
            return false;
        } else {
            st.close();
            con.close();
            rs.close();
            return true;
        }
    }

    public static int getLikesCount(long post) throws SQLException {
        int ret = 0;
        for(Like l : getLikesByPostID(post)){
            ret += l.getValue();
        }
        return ret;
    }

    public static int getViewsCount(long post) throws SQLException {
        int ret = 0;
        for(Like l : getLikesByPostID(post)){
            if(l.getValue() == 0)
                ret++;
        }
        return ret;
    }

}