package com.database;

import com.project.models.connection.Like;
import com.project.models.node.post.NormalPost;
import com.project.models.node.post.Post;
import com.project.models.node.post.PromotedPost;
import com.project.models.node.user.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PostDB {
    private PostDB() {
    }

    public static void addToDB(Post ps) throws SQLException {
        Post temp = getPostbyPostID(ps.getId());
        if (temp != null && ps.getId() != 0) {
            updatePost(ps);
        } else {
            addPost(ps);
        }
    }

    public static void addPost(Post post) throws SQLException {
        DBInfo.getConnection().createStatement().execute("insert into post values(NULL," +
                "'" + post.getText().toString() + "','" +
                "" + post.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
                "" + post.getSender().getId() + ", "
                + (post.getRepliedPost() != null ? Long.toString(post.getRepliedPost().getId()) : "0") + ", 0, 0, 0, "
                + (post instanceof PromotedPost ? "1" : "0") + ")");
        DBInfo.getConnection().close();
    }

    public static void updatePost(Post post) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("update post set post_data = '" + post.getText().toString() + "', post_likes_count = " +
                "" + Integer.toString(post.getLikes()) + ", post_view_count = " + post.getViews()
                + ", post_comments_count = "
                + post.getComments() + " where post_id = " + post.getId() + ";");
        st.close();
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
        String query = "select * from post where post_id = " + Long.toString(post_ID);
        ResultSet rs = st.executeQuery(query);
        if (!rs.next()) {
            return null;
        }
        Post ps;
        if (rs.getInt(9) == 0)
            ps = new NormalPost(rs.getString(2), null);
        else
            ps = new PromotedPost(rs.getString(2), null);

        ps.setId(post_ID);
        ps.setCreationDate(DBInfo.parseDate(rs.getString(3)));
        ps.setSender(UserDB.getUserInfo(rs.getLong(4)));
        ps.setRepliedPost(getPostbyPostID(rs.getLong(5)));
        ps.setLikes(rs.getInt(6));
        ps.setViews(rs.getInt(7));
        ps.setComments(rs.getInt(8));
        rs.close();
        st.close();
        con.close();
        return ps;
    }

    public static ArrayList<Post> getPostsByUSID(long us_ID) throws SQLException {
        ArrayList<Post> ret = new ArrayList<>();
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * from post where post_sender_id = " + us_ID;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Post ps;
            if (rs.getInt(9) == 0)
                ps = new NormalPost(rs.getString(2), null);
            else
                ps = new PromotedPost(rs.getString(2), null);

            ps.setId(rs.getLong(1));
            ps.setCreationDate(DBInfo.parseDate(rs.getString(3)));
            ps.setSender(UserDB.getUserInfo(rs.getLong(4)));
            ps.setRepliedPost(getPostbyPostID(rs.getLong(5)));
            ps.setLikes(rs.getInt(6));
            ps.setViews(rs.getInt(7));
            ps.setComments(rs.getInt(8));
            ret.add(ps);
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }

    public static Like getLikes(long postid, long userid) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from likes where lk_post_id = " + postid + " and lk_user_id = " + userid);
        if (!rs.next()) {
        }
        st.close();
        rs.close();
        con.close();
        return null;//new Like();
    }

    public static void like() {

    }

    public static ArrayList<Post> searchByText(String txt) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from post where post_data like '" + txt + "'");
        ArrayList<Post> ret = new ArrayList<>();
        while (rs.next()) {
            Post ps;
            if (rs.getInt(9) == 0)
                ps = new NormalPost(rs.getString(2), null);
            else
                ps = new PromotedPost(rs.getString(2), null);

            ps.setId(rs.getLong(1));
            ps.setCreationDate(DBInfo.parseDate(rs.getString(3)));
            ps.setSender(UserDB.getUserInfo(rs.getLong(4)));
            ps.setRepliedPost(getPostbyPostID(rs.getLong(5)));
            ps.setLikes(rs.getInt(6));
            ps.setViews(rs.getInt(7));
            ps.setComments(rs.getInt(8));
            ret.add(ps);
        }
        rs.close();
        st.close();
        con.close();
        return ret.isEmpty() ? null : ret;
    }

    public static ArrayList<Post> getFeed(long userID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = generateQueryForFeed(userID);
//        System.out.println(query + " shit ");
        ArrayList<Post> ret = new ArrayList<>();
        if (query == null)
            return new ArrayList<Post>();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Post ps;
            if (rs.getInt(9) == 0)
                ps = new NormalPost(rs.getString(2), null);
            else
                ps = new PromotedPost(rs.getString(2), null);

            ps.setId(rs.getLong(1));
            ps.setCreationDate(DBInfo.parseDate(rs.getString(3)));
            ps.setSender(UserDB.getUserInfo(rs.getLong(4)));
            ps.setRepliedPost(getPostbyPostID(rs.getLong(5)));
            ps.setLikes(rs.getInt(6));
            ps.setViews(rs.getInt(7));
            ps.setComments(rs.getInt(8));
            ret.add(ps);
        }
        return ret;
    }


    private static String generateQueryForFeed(long userID) throws SQLException {
        ArrayList<User> followings = UserDB.getFollowings(userID, 0);
        if (followings.isEmpty())
            return null;
        String temp = "";
        for (User us : followings) {
//            System.out.println(us.getId());
            temp += "post_sender_id = " + us.getId() + " or ";
        }
        temp = temp.substring(0, temp.length() - 4);
        return "select * from post where " + temp;
    }

    public static ArrayList<Post> getComments(long postID) throws SQLException {
        ArrayList<Post> ret = new ArrayList<>();
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * from post where post_replied_id = " + postID;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Post ps;
            if (rs.getInt(9) == 0)
                ps = new NormalPost(rs.getString(2), null);
            else
                ps = new PromotedPost(rs.getString(2), null);

            ps.setId(rs.getLong(1));
            ps.setCreationDate(DBInfo.parseDate(rs.getString(3)));
            ps.setSender(UserDB.getUserInfo(rs.getLong(4)));
            ps.setRepliedPost(getPostbyPostID(rs.getLong(5)));
            ps.setLikes(rs.getInt(6));
            ps.setViews(rs.getInt(7));
            ps.setComments(rs.getInt(8));
            ret.add(ps);
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }
}
