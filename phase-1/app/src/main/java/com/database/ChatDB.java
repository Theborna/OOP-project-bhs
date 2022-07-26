// package com.database;

// import com.models.node.Chat;
// import com.models.node.ChatType;

// import java.sql.Connection;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;

// public class ChatDB {
//     private ChatDB() {
//     }

//     private static ChatType chatType(int type) {
//         if (type == 0) {
//             return ChatType.PRIVATE;
//         } else if (type == 1) {
//             return ChatType.GROUP;
//         } else {
//             return ChatType.CHANNEL;
//         }

//     }

//     public static ArrayList<Chat> getChats(long USID) throws SQLException {
//         ArrayList<Chat> chs = new ArrayList<>();
//         Connection con = DBInfo.getConnection();
//         Statement st = con.createStatement();
//         String query = "select * from chat where ch_user_id = " + Long.toString(USID);
//         ResultSet rs = st.executeQuery(query);
//         while (rs.next()) {
//             Chat ch = new Chat(rs.getString(9), chatType(rs.getInt(8)));
//             ch.setCanSend((rs.getInt(3) == 1?true:false));
//             ch.setSender(UserDB.getUserInfo(rs.getLong(2)));
//             ch.setOwner(UserDB.getUserInfo(rs.getLong(7)));
//             ch.setVisible(rs.getInt(6) == 1?true:false);
//             ch.setMemberCount(rs.getInt(5));
//             ch.setCreationDate(LocalDateTime.parse(rs.getString(4), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

//         }
//         return chs;
//     }

//     public static void addChat(Chat ch) throws SQLException {
//         Connection con = DBInfo.getConnection();
//         Statement st = con.createStatement();
//         String query = "insert into chat values(" + ch.getId() + ", " + ch.getSender().getId() + "," +
//                 (ch.isCanSend() ? "1" : "0") + "," + ch.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
//                 "," + ch.getMemberCount() + "," + (ch.isVisible() ? "1" : "0") + "," + ch.getOwner().getId() + ","
//                 + ch.getType().ordinal() + ","+ch.getName()+")";
//         st.execute(query);
//     }

// }
