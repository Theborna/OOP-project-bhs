package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.project.models.node.post.Post;

public class PostDB {
    private PostDB() {
    }

    public static void addPost(Post post) throws SQLException {
        DBInfo.getConnection().createStatement().execute("insert into post values(NULL," +
                "'" + post.getText().toString() + "','" +
                "" + post.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
                "" + post.getSender().getId() + ", "
                + (post.getRepliedPost() != null ? Long.toString(post.getRepliedPost().getId()) : "0") + ", 0, 0, 0)");
        DBInfo.getConnection().close();
    }

    public static void updatePost(Post post) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("update post set post_data = '" + post.getText().toString() + "', post_likes_count = " +
                "" + Integer.toString(post.getLikes()) + ", post_view_count = " + post.getViews()
                + ", post_comments_count = "
                + post.getComments() + " where post_id = " + post.getId() + ";");
        con.close();
    }

    public static void deletePost(Post post) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("delete from post where post_id = " + post.getId());
        st.close();
        con.close();
    }

    public static Post getPostbyPostID(long post_ID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * from post_id = " + Long.toString(post_ID);
        ResultSet rs = st.executeQuery(query);
        rs.next();
        Post ps = new Post(rs.getString(2));
        ps.setId(post_ID);
        ps.setCreationDate(LocalDateTime.parse(rs.getString(3)));
        ps.setSender(UserDB.getUserInfo(rs.getLong(4)));
        ps.setRepliedPost(getPostbyPostID(rs.getLong(5)));
        ps.setLikes(rs.getInt(6));
        ps.setViews(rs.getInt(7));
        ps.setComments(rs.getInt(8));
        return ps;
    }

    public static ArrayList<Post> getPostByID(long us_ID) throws SQLException {
        ArrayList<Post> ret = new ArrayList<>();
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * form post where us_ID = " + us_ID;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Post ps = new Post(rs.getString(2));
            ps.setId(rs.getLong(1));
            ps.setCreationDate(LocalDateTime.parse(rs.getString(3)));
            ps.setSender(UserDB.getUserInfo(rs.getLong(4)));
            ps.setRepliedPost(getPostbyPostID(rs.getLong(5)));
            ps.setLikes(rs.getInt(6));
            ps.setViews(rs.getInt(7));
            ps.setComments(rs.getInt(8));
        }
        return ret;
    }

}
