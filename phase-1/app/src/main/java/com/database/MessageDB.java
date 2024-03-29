package com.database;


import com.project.models.node.Message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MessageDB {
    private MessageDB() {
    }

    public static void addToDB(Message msg) throws SQLException {
        Message ret = getMessageByID(msg.getId());
        //System.out.println(ret.toString());
        if (ret == null && msg.getId() == 0) {
            newMessage(msg);
        } else {
            updatePreviouslyBuiltMSG(msg);
        }
    }

    public static void newMessage(Message msg) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "insert into messages values(NULL, " + msg.getCh().getId() + ", " + msg.getSender().getId() + ", "
                + (msg.getReplyTo() == null ? "NULL" : (msg.getReplyTo().getSender() == null ? "NULL" : msg.getReplyTo().getId())) + ",'"
                + msg.getMessage().toString() + "', '"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', '"
                + (msg.getEncKey() == null ? "NULL" : msg.getEncKey()) + "', "
                + (msg.getForwardedFrom() == null ? "NULL" : msg.getForwardedFrom().getId()) + ", "
                + (msg.getMd() == null ? "0" : MediaDB.newMedia(msg.getMd())) + ")";
        st.execute(query);
        st.close();
        con.close();

    }

    public static void deleteMessage(long msg_id) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "delete from messages where msg_id = " + msg_id + ";";
        //System.out.println(query);
        st.execute(query);
        st.close();
        con.close();
    }

    public static void updatePreviouslyBuiltMSG(Message msg) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "update messages set msg_enc = '" + msg.getMessage() + "', msg_date = '"
                + msg.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "', msg_sender_priv_key_enc = '" + msg.getEncKey() + "' where msg_id = " + msg.getId() + ";";
        //System.out.println(query);
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
            Message msg = new Message(rs.getString(5), UserDB.getUserInfo(rs.getLong(3)),
                    ChatDB.getChatByID(rs.getLong(2)));
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

            msg.setCreationDate(DBInfo.parseDate(rs.getString(6)));
            msg.setId(rs.getLong(1));
            msg.setCh(ChatDB.getChatByID(rs.getLong(2)));
            msg.setEncKey((rs.getString(7)));
            if (rs.getLong(9) != 0) {
                msg.setMd(MediaDB.getMedia(rs.getLong(9)));
            }
            ret.add(msg);
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }


    public static ArrayList<Message> getLastMessage(long userID, int n) throws SQLException {
        ArrayList<Message> ret = new ArrayList<>();
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * from messages where msg_sender_id = " + Long.toString(userID) + " order by msg_id desc;";
        ResultSet rs = st.executeQuery(query);
        while (rs.next() && n > 0) {
            Message msg = new Message(rs.getString(5), UserDB.getUserInfo(rs.getLong(3)),
                    ChatDB.getChatByID(rs.getLong(2)));
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

            msg.setCreationDate(DBInfo.parseDate(rs.getString(6)));
            msg.setId(rs.getLong(1));
            msg.setCh(ChatDB.getChatByID(rs.getLong(2)));
            msg.setEncKey((rs.getString(7)));
            if (rs.getLong(9) != 0) {
                msg.setMd(MediaDB.getMedia(rs.getLong(9)));
            }
            ret.add(msg);
            n--;
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }

    public static ArrayList<Message> getMessagesByChatID(long chatID) throws SQLException {
        ArrayList<Message> ret = new ArrayList<>();
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * from messages where msg_chat_id = " + Long.toString(chatID) + ";";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Message msg = new Message(rs.getString(5), UserDB.getUserInfo(rs.getLong(3)),
                    ChatDB.getChatByID(rs.getLong(2)));
            if (rs.getLong(8) == 0) {
                msg.setForwardedFrom(null);
            } else {
                msg.setForwardedFrom(UserDB.getUserInfo(rs.getLong(8)));
            }
            if (rs.getLong(4) != 0) {
                msg.setReplyTo(MessageDB.getMessageByID(rs.getLong(4)));
            } else {
                msg.setReplyTo(null);
            }

            msg.setCreationDate(DBInfo.parseDate(rs.getString(6)));
            msg.setId(rs.getLong(1));
            msg.setCh(ChatDB.getChatByID(rs.getLong(2)));
            msg.setEncKey(rs.getString(7));
            if (rs.getLong(9) != 0) {
                msg.setMd(MediaDB.getMedia(rs.getLong(9)));
            }
            ret.add(msg);
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }

    private static Message getMessageByID(long msgID, boolean loadReply) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * from messages where msg_id = " + msgID;
        ResultSet rs = st.executeQuery(query);
        Message msg = null;
        if (rs.next()) {
            msg = new Message(rs.getString(5), UserDB.getUserInfo(rs.getLong(3)),
                    ChatDB.getChatByID(rs.getLong(2)));
            if (rs.getLong(8) != 0) {
                msg.setForwardedFrom(null);
            } else {
                msg.setForwardedFrom(UserDB.getUserInfo(rs.getLong(8)));
            }
            if (rs.getLong(4) != 0 && loadReply) {
                msg.setReplyTo(MessageDB.getMessageByID(rs.getLong(4), false));
            } else {
                msg.setReplyTo(null);
            }

            msg.setCreationDate(DBInfo.parseDate(rs.getString(6)));
            msg.setId(rs.getLong(1));
            msg.setCh(ChatDB.getChatByID(rs.getLong(2)));
            msg.setEncKey((rs.getString(7)));
            if (rs.getLong(9) != 0) {
                msg.setMd(MediaDB.getMedia(rs.getLong(9)));
            }
        }
        rs.close();
        st.close();
        con.close();
        return msg;
    }

    private static Message getMessageByID(long msgID) throws SQLException {
        return getMessageByID(msgID, true);
    }

    public static ArrayList<Message> getMessagesByChatNUserID(long chatID, long userID) throws SQLException {
        ArrayList<Message> ret = new ArrayList<>();
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * from messages where msg_chat_id = " + Long.toString(chatID)
                + "and msg_sender_id = " + userID + ";";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Message msg = new Message(rs.getString(5), UserDB.getUserInfo(rs.getLong(3)),
                    ChatDB.getChatByID(rs.getLong(2)));
            if (rs.getLong(8) == 0) {
                msg.setForwardedFrom(null);
            } else {
                msg.setForwardedFrom(UserDB.getUserInfo(rs.getLong(8)));
            }
            if (rs.getLong(4) != 0) {
                msg.setReplyTo(MessageDB.getMessageByID(rs.getLong(4)));
            } else {
                msg.setReplyTo(null);
            }

            msg.setCreationDate(DBInfo.parseDate(rs.getString(6)));
            msg.setId(rs.getLong(1));
            msg.setCh(ChatDB.getChatByID(rs.getLong(2)));
            msg.setEncKey(rs.getString(7));
            if (rs.getLong(9) != 0) {
                msg.setMd(MediaDB.getMedia(rs.getLong(9)));
            }
            ret.add(msg);
        }
        rs.close();
        st.close();
        con.close();
        return ret;
    }
}
