package com.electro.database;

import com.electro.phase1.enums.MediaType;
import com.electro.phase1.models.node.ImageNode;
import com.electro.phase1.models.node.Media;
import com.electro.phase1.models.node.Video;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;

public class MediaDB {
    private MediaDB() {
    }

    private static String saveto(Media m) {
        String path = "D:/content/";
        File f = new File(path + System.currentTimeMillis());
        try {
            Files.copy(new File(m.getAddress()).toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getPath();
    }

    public static long newMedia(Media media) throws SQLException {
        Connection con = DBInfo.getConnection("Media inserted!");
        PreparedStatement st = con.prepareStatement("insert into media values(NULL, " + saveto(media) + ","
                + media.getMt().ordinal() + ")", Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        while (rs.next()) {
            return rs.getLong(1);
        }
        rs.close();
        st.close();
        con.close();
        return 0;
    }

    public static Media getMedia(long mID) throws SQLException {
        Connection con = DBInfo.getConnection("media selection id = " + mID);
        String query = "select * from media where id = " + mID;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        Media md = null;
        while (rs.next()) {
            MediaType mt = MediaType.values()[rs.getInt(3)];
            if (mt == MediaType.Image) {
                md = new ImageNode(rs.getString(2));
                md.setId(rs.getLong(1));
            } else if (mt == MediaType.Video) {
                md = new Video(rs.getString(2));
                md.setId(rs.getLong(1));
            }
        }
        rs.close();
        st.close();
        con.close();
        return md;
    }


}
