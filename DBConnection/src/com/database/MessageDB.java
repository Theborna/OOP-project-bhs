package com.database;

import com.models.node.Message;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MessageDB {
    private MessageDB() {
    }

    public static void newMessage(Message msg, String keyID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "insert into messages values(NULL, " + msg.getSender().getId() + ", "
                + msg.getReplyTo().getSender().getId() + ", " + msg.getMessage().toString() + ", "
                + msg.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ", "
                + keyID + ", " + msg.getForwardedFrom().getId() + ")";
        st.execute(query);
        con.close();
    }

    public static void UpdatePreviuoslyBuiltMSG(Message msg, String keyID) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = ""; // TODO: Compelete the query
        st.execute(query);
    }

    public static ArrayList<Message> getMessagesByUserID(long userID) throws SQLException {
        ArrayList<Message> ret = new ArrayList<>();
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = ""; // TODO: Compelete the query
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            // TODO: complete here and set the parameter of msg OBJ
            Message msg = new Message();

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
        String query = ""; // TODO: Compelete the query
        ResultSet rs = st.executeQuery(query);
        rs.next();
        Message msg = new Message();
        // TODO: complete here and set the parameter of msg OBJ


        return msg;
    }
}
