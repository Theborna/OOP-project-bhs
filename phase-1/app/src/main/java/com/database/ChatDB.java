package com.database;


import com.project.enums.ChatPermission;
import com.project.enums.ChatType;
import com.project.models.node.Chat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ChatDB {
    private ChatDB() {
    }

    public static void sendToDB(Chat ch) throws SQLException {
        Chat temp = getChatByID(ch.getId());
        if (temp != null) {
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
        String query = "update chat set ch_member_count =" + ch.getMemberCount() + " , ch_is_visible =" + (ch.isVisible() ? 1 : 0)
                + " , ch_owner_id = " + ch.getOwner().getId() + ", ch_type = " + ch.getType().ordinal() + ", ch_name = "
                + ch.getName() + " where ch_id = " + ch.getId() + ";";
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
        return chs.isEmpty() ? null : chs;
    }

    public static void addChat(Chat ch) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "insert into chat values(" + "NULL " + "," +
                (ch.isCanSend() ? "1" : "0") + "," + ch.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                "," + ch.getMemberCount() + "," + (ch.isVisible() ? "1" : "0") + "," + ch.getOwner().getId() + ","
                + ch.getType().ordinal() + "," + ch.getName() + ")";
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
            ch = new Chat(rs.getString(9), chatType(rs.getInt(8)));
            ch.setId(chatID);
            ch.setOwner(UserDB.getUserInfo(rs.getLong(7)));
            ch.setVisible(rs.getInt(6) == 1 ? true : false);
            ch.setMemberCount(rs.getInt(5));
            ch.setCreationDate(LocalDateTime.parse(rs.getString(4), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return ch;
    }

    public static ChatPermission getChatPermission(long usid, long chatid) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from member where userid = " + usid + " and chatid = " + chatid);
        if (rs.next()) {
            return chatPermission(rs.getInt(3));
        }
        return null;
    }

    public static void setChatPermission(long usid, long chatid, ChatPermission cp) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.executeQuery("insert into member values(" + usid + "," + chatid + "," + cp.ordinal() + ")");
    }

}
