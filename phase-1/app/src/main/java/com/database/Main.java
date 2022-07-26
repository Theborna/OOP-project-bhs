package com.database;

import com.models.node.Post;
import com.models.node.user.NormalUser;
import com.models.node.user.User;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws SQLException {
//	// write your code here
//        Statement st = DBInfo.getConnection().prepareStatement("select * from chat");
//        ResultSet rs = st.executeQuery("select * from chat");
//        while(rs.next()){
//            for (int i = 1; i < 9; i++) {
//                System.out.print(rs.getString(i) + ", ");
//            }
//            System.out.println();
//        }
//
//    }
//        User us = new NormalUser("Borna","SepH");
//        us.setDt(LocalDateTime.now());
//        us.setEmail("bornaaaaaaaaaa@skdjc.dos");
//        us.setLastName("khoda");
//        us.setName("hsrf");
//        us.setPublic(true);
//        us.setUserType(0);
//        us.setSalt("jhlkjf");
//        us.setPromoindex(0.1);
//        //UserDB.updtaeUser(us);
//        UserDB.getUserInfo(1L);
        User us = new NormalUser("Borna", "fe");
                us.setUSID(1);

        System.out.println(UserDB.getFollowers(us,5));
    }
}
