package com.electro.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.electro.phase1.enums.ChatPermission;
import com.electro.phase1.enums.ChatType;
import com.electro.phase1.models.node.Chat;
import com.electro.phase1.models.node.user.User;

public class ChatDB {
    private ChatDB() {
    }

    public static void sendToDB(Chat ch) throws SQLException {
        Chat temp = getChatByID(ch.getId());
        if (temp != null && ch.getId() != 0) {
            updateChat(ch);
        } else {
            addChat(ch);
        }

    }

    private static ChatType chatType(int type) {
        if (type == 0) {
            return ChatType.PRIVATE;
        } else if (type == 1) {
            return ChatType.GROUP;
        } else {
            return ChatType.CHANNEL;
        }
    }

    private static ChatPermission chatPermission(int type) {
        if (type == 0) {
            return ChatPermission.OWNER;
        } else if (type == 1) {
            return ChatPermission.ADMIN;
        } else if (type == 2) {
            return ChatPermission.NORMAL;
        } else {
            return ChatPermission.OBSERVER;
        }
    }

    public static void updateChat(Chat ch) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "update chat set ch_member_count =" + ch.getMemberCount() + " , ch_is_visible ="
                + (ch.isVisible() ? 1 : 0)
                + " , ch_owner_id = " + ch.getOwner().getId() + ", ch_type = " + ch.getType().ordinal() + ", ch_name = "
                + ch.getName() + ", ch_txt_id = '" + ch.getLinkID() + "' where ch_id = " + ch.getId() + ";";
        st.executeQuery(query);
        st.close();
        con.close();
    }

    public static ArrayList<Chat> getChats(long USID) throws SQLException {
        ArrayList<Chat> chs = new ArrayList<>();
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * from member where userid = " + Long.toString(USID);
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Chat ch = getChatByID(rs.getLong("chatid"));
            chs.add(ch);
        }
        rs.close();
        st.close();
        con.close();
        return chs;
    }

    public static void addChat(Chat ch) throws SQLException {
        Connection con = DBInfo.getConnection("new chat");
        Statement st = con.createStatement();
        String query = "insert into chat values(" + "NULL " + ",'" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                "'," + ch.getMemberCount() + "," + (ch.isVisible() ? "1" : "0") + ","
                + (ch.getOwner() == null ? "null" : "'" + ch.getOwner().getId() + "'") + ","
                + ch.getType().ordinal() + ",'" + ch.getName() + "', '"
                + (ch.getLinkID() == null ? "null" : (ch.getLinkID() + "'")) + ")";
        System.out.println(query);
        st.execute(query);
        st.close();
        con.close();
    }

    public static Chat getChatByID(long chatID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from chat where ch_id = " + chatID);
        Chat ch = null;
        if (rs.next()) {
            ch = new Chat(rs.getString(7), chatType(rs.getInt(6)));
            ch.setId(chatID);
            ch.setOwner(UserDB.getUserInfo(rs.getLong("ch_owner_id")));
            ch.setVisible(rs.getInt(4) == 1 ? true : false);
            ch.setMemberCount(rs.getInt(3));
            ch.setCreationDate(rs.getDate(2).toLocalDate().atStartOfDay());
            ch.setLinkID(rs.getString(8));
        }
        rs.close();
        st.close();
        con.close();
        return ch;
    }

    public static Chat getChatByLinkID(String chatLinkID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from chat where ch_txt_id = '" + chatLinkID + "'");
        Chat ch = null;
        if (rs.next()) {
            ch = new Chat(rs.getString(7), chatType(rs.getInt(6)));
            ch.setId(rs.getLong(1));
            ch.setOwner(UserDB.getUserInfo(rs.getLong("ch_owner_id")));
            ch.setVisible(rs.getInt(4) == 1 ? true : false);
            ch.setMemberCount(rs.getInt(3));
            ch.setCreationDate(rs.getDate(2).toLocalDate().atStartOfDay());
            ch.setLinkID(rs.getString(8));
        }
        rs.close();
        st.close();
        con.close();
        return ch;
    }

    public static ChatPermission getChatPermission(long usid, long chatid) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from member where userid = " + usid + " and chatid = " + chatid);
        if (rs.next()) {
            return chatPermission(rs.getInt(3));
        }
        rs.close();
        st.close();
        con.close();
        return null;
    }

    public static void addMemeber(long usid, long chatid, ChatPermission cp) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("insert into member values(" + usid + "," + chatid + "," + cp.ordinal() + ")");
        st.close();
        con.close();
    }

    public static void removeMemeber(long usid, long chatid) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.executeQuery("delete from member where userid = " + usid + " and chatid = " + chatid);
        st.close();
        con.close();
    }

    public static ArrayList<Chat> searchChat(String linkID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(
                "select * from chat where ch_name like '%" + linkID + "%' or ch_txt_id like '%" + linkID + "%'");
        ArrayList<Chat> ret = new ArrayList<>();
        while (rs.next()) {
            Chat ch = new Chat(rs.getString(7), chatType(rs.getInt(6)));
            ch.setId(rs.getLong(1));
            ch.setOwner(UserDB.getUserInfo(rs.getLong("ch_owner_id")));
            ch.setVisible(rs.getInt(4) == 1 ? true : false);
            ch.setMemberCount(rs.getInt(3));
            ch.setCreationDate(rs.getDate(2).toLocalDate().atStartOfDay());
            ch.setLinkID(rs.getString(8));
            ret.add(ch);
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }

    public static Map<User, ChatPermission> getMembersOfChat(long chatid) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from member where chatid = " + chatid);
        Map<User, ChatPermission> ret = new HashMap<>();
        while (rs.next()) {
            ret.put(UserDB.getUserInfo(rs.getLong(1)), ChatPermission.values()[rs.getInt(3)]);
        }
        return ret;
    }

}
