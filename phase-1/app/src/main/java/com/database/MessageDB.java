package com.database;

import javax.swing.plaf.nimbus.State;

import com.project.models.node.Message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class MessageDB {
    private MessageDB() {
    }

    public static void newMessage(Message msg) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "insert into messages values(NULL, " + msg.getCh().getId() + ", " + msg.getSender().getId() + ", "
                + msg.getReplyTo().getSender().getId() + ", " + msg.getMessage().toString() + ", "
                + msg.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ", "
                + msg.getEncKey() + ", " + msg.getForwardedFrom().getId() + ")";
        st.execute(query);
        con.close();
    }

    public static void UpdatePreviouslyBuiltMSG(Message msg) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "update messages set msg_enc = " + msg.getMessage() + ", msg_date = "
                + msg.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + ", msg_sender_priv_key_enc = " + msg.getEncKey() + " where msg_id = " + msg.getId() + ";";
        st.execute(query);
        st.close();
        con.close();
    }

    public static ArrayList<Message> getMessagesByUserID(long userID) throws SQLException {
        ArrayList<Message> ret = new ArrayList<>();
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * from messages where msg_sender_id = " + Long.toString(userID) + ";";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Message msg = new Message(rs.getString(5), UserDB.getUserInfo(rs.getLong(3)));
            if (rs.getLong(8) != 0) {
                msg.setForwardedFrom(null);
            } else {
                msg.setForwardedFrom(UserDB.getUserInfo(rs.getLong(8)));
            }
            if (rs.getLong(4) != 0) {
                msg.setReplyTo(MessageDB.getMessageByID(rs.getLong(4)));
            } else {
                msg.setReplyTo(null);
            }

            msg.setCreationDate(LocalDateTime.parse(rs.getString(6), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            msg.setId(rs.getLong(1));
            msg.setCh(ChatDB.getChatByID(rs.getLong(2)));
            msg.setEncKey(rs.getString(rs.getString(7)));
            ret.add(msg);
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }

    private static Message getMessageByID(long msgID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * from messages where msg_id = " + msgID;
        ResultSet rs = st.executeQuery(query);
        Message msg = null;
        if (rs.next()) {
            msg = new Message(rs.getString(5), UserDB.getUserInfo(rs.getLong(3)));
            if (rs.getLong(8) != 0) {
                msg.setForwardedFrom(null);
            } else {
                msg.setForwardedFrom(UserDB.getUserInfo(rs.getLong(8)));
            }
            if (rs.getLong(4) != 0) {
                msg.setReplyTo(MessageDB.getMessageByID(rs.getLong(4)));
            } else {
                msg.setReplyTo(null);
            }

            msg.setCreationDate(LocalDateTime.parse(rs.getString(6), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            msg.setId(rs.getLong(1));
            msg.setCh(ChatDB.getChatByID(rs.getLong(2)));
            msg.setEncKey(rs.getString(rs.getString(7)));
        }


        return msg;
    }
}