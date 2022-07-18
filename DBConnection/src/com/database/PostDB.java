package com.database;

import com.models.node.Post;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class PostDB {
    public void addPost(Post post) throws SQLException {
        DBInfo.getConnection().createStatement().execute("insert into post values(NULL," +
                "'" + post.getText().toString() + "','" +
                "" + post.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
                "" + post.getSender().getId() + ", 0" + ", 0, 0, 0)");
        DBInfo.getConnection().close();
    }
    public void updatePost(Post post) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("update post set post_data = '"+post.getText().toString()+"', post_likes_count = " +
                ""+Integer.toString(post.getLikes())+", post_view_count = "+post.getViews()+", post_comments_count = "
                +post.getComments()+" where post_id = "+post.getId()+";");
    }

    public void deletePost(Post post) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("delete from post where post_id = " + post.getId());
    }


}
