package com.database;

import com.project.models.node.user.BusinessUser;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.database.DBInfo.getConnection;

public class UserDB {

    private UserDB() {
    }

    public static void sendToDB(User us) throws SQLException {
        User temp = getUserInfo(us.getUsername());
        if (temp != null) {
            updateUser(us);
        } else {
            registerUSer(us);
        }

    }

    public static void registerUSer(User user) throws SQLException {
        if (user == null)
            return;
        Connection con = getConnection();
        String query = "insert into pmresan.users values(NULL,'" + user.getUsername() + "'" +
                ",'" + user.getPassword() + "','" + user.getSalt() + "', '" +
                new Date(user.getBirthDate().getTime()) +
                "', " + Integer.toString(user.getUserType()) + ", " + ((user.isPublic()) ? "1" : "0") + ", 0,0,0,'"
                + user.getName() + "','" + user.getLastName() +
                "', '" + user.getEmail() + "', 0)";
        // System.out.println(query);
        con.createStatement().execute(query);
        con.close();
    }

    public static void updateUser(User user) throws SQLException {
        Connection con = getConnection();
        String query = "update users set US_USName = '" + user.getUsername() + "', US_Pass_enc = '" + user.getPassword()
                + "',  " +
                "US_Salt = '" + user.getSalt() + "', US_Type = " + Integer.toString(user.getUserType()) + "" +
                ", US_Visibilty = " + ((user.isPublic()) ? "1" : "0") + ", US_Name = '" + user.getName() + "'" +
                ", US_LastName = '" + user.getLastName() + "', US_Email = '" + user.getLastName() +
                "', US_PromotionIndex = " + Double.toString(user.getPromoindex()) + ";";
        // System.out.println(query);
        con.createStatement().execute(query);
        con.close();
    }

    public static User getUserInfo(long usID) throws SQLException {
        User us;
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users where US_ID=" + Long.toString(usID) + ";");
        if (!rs.next()) {
            return null;
        }
        int userType = rs.getInt(6);
        // 0 -> normal user
        // 1 -> buisness user

        if (userType == 0) {
            System.out.println(userType);
            us = new NormalUser(rs.getString(2), rs.getString(3));
            us.setUSID(rs.getLong(1));
            us.setSalt(rs.getString(4));
            us.setBirthDate(rs.getDate(5));
            us.setUserType(userType);
            us.setPublic(rs.getBoolean(7));
            us.setFollowerCnt(rs.getInt(8));
            us.setFollowingCnt(rs.getInt(9));
            us.setPostCnt(rs.getInt(10));
            us.setName(rs.getString(11));
            us.setLastName(rs.getString(12));
            us.setEmail(rs.getString(13));
            us.setPromoindex(rs.getDouble(14));
        } else {
            System.out.println(userType);
            us = new BusinessUser(rs.getString(2), rs.getString(3));
            us.setUSID(rs.getLong(1));
            us.setSalt(rs.getString(4));
            us.setBirthDate(rs.getDate(5));
            us.setUserType(userType);
            us.setPublic(rs.getBoolean(7));
            us.setFollowerCnt(rs.getInt(8));
            us.setFollowingCnt(rs.getInt(9));
            us.setPostCnt(rs.getInt(10));
            us.setName(rs.getString(11));
            us.setLastName(rs.getString(12));
            us.setEmail(rs.getString(13));
            us.setPromoindex(rs.getDouble(14));
        }
        con.close();
        return us;
    }

    public static User getUserInfo(String usName) throws SQLException {
        User us;
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users where US_USName='" + usName + "';\n");
        if (!rs.next()) {
            return null;
        }

        int userType = rs.getInt(6);
        // 0 -> normal user
        // 1 -> buisness user

        if (userType == 0) {
            System.out.println(userType);
            us = new NormalUser(rs.getString(2), rs.getString(3));
            us.setUSID(rs.getLong(1));
            us.setSalt(rs.getString(4));
            us.setBirthDate(rs.getDate(5));
            us.setUserType(userType);
            us.setPublic(rs.getBoolean(7));
            us.setFollowerCnt(rs.getInt(8));
            us.setFollowingCnt(rs.getInt(9));
            us.setPostCnt(rs.getInt(10));
            us.setName(rs.getString(11));
            us.setLastName(rs.getString(12));
            us.setEmail(rs.getString(13));
            us.setPromoindex(rs.getDouble(14));
        } else {
            System.out.println(userType);
            us = new BusinessUser(rs.getString(2), rs.getString(3));
            us.setUSID(rs.getLong(1));
            us.setSalt(rs.getString(4));
            us.setBirthDate(rs.getDate(5));
            us.setUserType(userType);
            us.setPublic(rs.getBoolean(7));
            us.setFollowerCnt(rs.getInt(8));
            us.setFollowingCnt(rs.getInt(9));
            us.setPostCnt(rs.getInt(10));
            us.setName(rs.getString(11));
            us.setLastName(rs.getString(12));
            us.setEmail(rs.getString(13));
            us.setPromoindex(rs.getDouble(14));
        }
        con.close();
        return us;
    }

    public static boolean auth(String username, String passwd) throws Throwable {
        User us = UserDB.getUserInfo(username);
        return us != null && us.getPassword().equals(passwd);
    }

    public static void deleteUser(User user) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("delete from users where US_ID = " + user.getId());
        con.close();
    }

    public static ArrayList<User> getFollowers(long userId, int size) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from following where follower_id = " + Long.toString(userId));
        int cnt = 0;
        ArrayList<User> ret = new ArrayList<>();
        while (rs.next() && (cnt < size || size == 0)) {
            ret.add(getUserInfo(rs.getLong(2)));
            cnt++;
        }
        con.close();

        return ret;
    }

    // If the userID does not wxists it will return zero as the id :)

    public static int getPromoIndexFromFollowingsDB(long followingID, long currentUserID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * from following where follower_id = " + followingID + " AND following_id = "
                + currentUserID;
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return 0;
        }
        rs.close();
        st.close();
        con.close();
        return rs.getInt(4);
    }
}
