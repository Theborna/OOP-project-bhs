package com.database;

import com.models.node.Post;
import com.models.node.user.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PostDB {
    private PostDB() {
    }

    public static void addPost(Post post) throws SQLException {
        DBInfo.getConnection().createStatement().execute("insert into post values(NULL," +
                "'" + post.getText().toString() + "','" +
                "" + post.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
                "" + post.getSender().getId() + ", " + (post.getRepliedPost() != null ? Long.toString(post.getRepliedPost().getId()) : "0") + ", 0, 0, 0)");
        DBInfo.getConnection().close();
    }

    public static void updatePost(Post post) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("update post set post_data = '" + post.getText().toString() + "', post_likes_count = " +
                "" + Integer.toString(post.getLikes()) + ", post_view_count = " + post.getViews() + ", post_comments_count = "
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

    public static Post getPostByID(long us_ID) throws SQLException {
        ArrayList<Post> ret = new ArrayList<>();
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "";
        ResultSet rs = st.executeQuery(query);
        rs.next();
        Post ps = new Post(rs.getString(2));
        ps.setId(rs.getLong(1));
        ps.setCreationDate(LocalDateTime.parse(rs.getString(3)));
        ps.setSender(UserDB.getUserInfo(rs.getLong(4)));
        ps.setRepliedPost(getPostByID(rs.getLong(5)));
        ps.setLikes(rs.getInt(6));
        ps.setViews(rs.getInt(7));
        ps.setComments(rs.getInt(8));
        return ps;
    }

}
