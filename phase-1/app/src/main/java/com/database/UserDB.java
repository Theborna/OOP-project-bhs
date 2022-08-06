package com.database;


import com.project.crypt;
import com.project.enums.Security;
import com.project.models.node.user.BusinessUser;
import com.project.models.node.user.NormalUser;
import com.project.models.node.user.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.database.DBInfo.getConnection;


public class UserDB {

    private UserDB() {
    }

    public static void sendToDB(User us) throws SQLException {
        User temp = getUserInfo(us.getUsername());
        if (temp == null)
            temp = getUserInfo(us.getId());
        if (temp != null && us.getId() != 0) {
            updateUser(us);
        } else {
            registerUSer(us);
        }

    }

    public static void registerUSer(User user) throws SQLException {
        if (user == null)
            return;
        Connection con = getConnection();
        String query = "insert into users values(NULL,'" + user.getUsername() + "'" +
                ",'" + user.getPassword() + "','" + user.getSalt() + "', '" +
                new Date(user.getBirthDate().getTime()) +
                "', " + Integer.toString(user.getUserType()) + ", " + ((user.isPublic()) ? "1" : "0") + ", 0,0,0,'"
                + user.getName() + "','" + user.getLastName() +
                "', '" + user.getEmail() + "', 0, " + user.getSecType().ordinal() + ", '" + user.getSecAns()
                + "', '1')";// TODO: add pfp
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
                "', US_PromotionIndex = " + Double.toString(user.getPromoindex()) + " where US_ID = " + user.getId()
                + ";";
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
            // System.out.println(userType);
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
            us.setSecType(Security.values()[rs.getInt(15)]);
            us.setSecAns(rs.getString(16));
        } else {
            // System.out.println(userType);
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
            us.setSecType(Security.values()[rs.getInt(15)]);
            us.setSecAns(rs.getString(16));
        }
        rs.close();
        st.close();
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
            // System.out.println(userType);
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
            us.setSecType(Security.values()[rs.getInt(15)]);
            us.setSecAns(rs.getString(16));
        } else {
            // System.out.println(userType);
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
            us.setSecType(Security.values()[rs.getInt(15)]);
            us.setSecAns(rs.getString(16));
        }
        rs.close();
        st.close();
        con.close();
        return us;
    }

    public static boolean auth(String username, String passwd) throws Throwable {
        User us = UserDB.getUserInfo(username);
        return us != null && us.getPassword().equals(crypt.encryptedString(passwd + us.getSalt()));
    }

    public static void deleteUser(User user) throws SQLException {
        Connection con = getConnection();
        Statement st = con.createStatement();
        st.execute("delete from users where US_ID = " + user.getId());
        st.close();
        con.close();
    }

    public static Map<User, Integer> getFollowers(long userId, int size) throws SQLException {
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from following where following_id = " + Long.toString(userId));
        int cnt = 0;
        Map<User, Integer> ret = new HashMap<>();
        while (rs.next() && (cnt < size || size == 0)) {
            ret.put(getUserInfo(rs.getLong(1)), rs.getInt(4));
            cnt++;
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }

    public static Map<User, Integer> getFollowings(long userId, int size) throws SQLException {
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from following where follower_id = " + Long.toString(userId));
        int cnt = 0;
        Map<User, Integer> ret = new HashMap<>();
        while (rs.next() && (cnt < size || size == 0)) {
            ret.put(getUserInfo(rs.getLong(1)), rs.getInt(4));
            cnt++;
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }

    // If the userID does not wxists it will return zero as the id :)

    public static int getPromoIndexFromFollowingsDB(long followingID, long currentUserID) throws SQLException {
        Connection con = getConnection();
        Statement st = con.createStatement();
        String query = "select * from following where follower_id = " + currentUserID + " AND following_id = "
                + followingID;
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return 0;
        }
        rs.close();
        st.close();
        con.close();
        return rs.getInt(4);
    }

    public static ArrayList<User> searchByUserName(String entry) throws SQLException {
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(
                "select * from users where US_USName like '%" + entry + "%' or US_Name like '%" + entry + "%';");
        ArrayList<User> ret = new ArrayList<>();
        while (rs.next()) {
            int userType = rs.getInt(6);
            // 0 -> normal user
            // 1 -> buisness user
            User us;
            if (userType == 0) {
                // System.out.println(userType);
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
                us.setSecType(Security.values()[rs.getInt(15)]);
                us.setSecAns(rs.getString(16));
            } else {
                // System.out.println(userType);
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
                us.setSecType(Security.values()[rs.getInt(15)]);
                us.setSecAns(rs.getString(16));
            }
            ret.add(us);
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }

    public static void follow(User current, User toFollow) throws SQLException {
        Connection con = getConnection();
        Statement st = con.createStatement();
        st.execute("insert into following values(" + current.getId() + ", " + toFollow.getId() + ",'"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', 100);");
        st.close();
        con.close();
    }

    public static void unFollow(User current, User toFollow) throws SQLException {
        Connection con = getConnection();
        Statement st = con.createStatement();
        st.execute("delete from following where follower_id = " + current.getId() + " and following_id = "
                + toFollow.getId());
        st.close();
        con.close();
    }

    public static boolean isFollowed(User currentUser, User user) throws SQLException {
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from following where follower_id = "
                + currentUser.getId() + " and following_id = " + user.getId());
        if (!rs.next()) {
            rs.close();
            st.close();
            con.close();
            return false;
        }
        rs.close();
        st.close();
        con.close();
        return true;
    }

    public static void updatePromoIndex(double prom, long followed, long follower) throws SQLException {
        Connection con = getConnection();
        Statement st = con.createStatement();
        st.execute("update following set promo_index = " + prom + " where following_id = "
                + followed + " and follower_id = " + follower);
        st.close();
        con.close();
    }

}
