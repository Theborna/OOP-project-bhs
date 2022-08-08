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
import java.util.regex.Matcher;

public class MediaDB {
    private MediaDB() {
    }

    private static String saveTo(Media m) {
        String path = "F:/code/Java OOP/final project/content/";
        File g = new File(m.getAddress());
        String extension = "";
        int i = g.getName().lastIndexOf('.');
        if (i >= 0)
            extension = g.getName().substring(i);
        File f = new File(path + System.currentTimeMillis() + extension);
        try {
            Files.copy(g.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getPath().replaceAll(Matcher.quoteReplacement("\\"), Matcher.quoteReplacement("\\\\"));
    }

    public static long newMedia(Media media) throws SQLException {
        Connection con = DBInfo.getConnection();
        PreparedStatement st = con.prepareStatement("insert into media values(NULL, '" + saveTo(media) + "',"
                + media.getMt().ordinal() + ")", Statement.RETURN_GENERATED_KEYS);
        st.execute();
        ResultSet rs = st.getGeneratedKeys();
        while (rs.next()) {
            long ans = rs.getLong(1);
            rs.close();
            st.close();
            con.close();
            return ans;
        }
        rs.close();
        st.close();
        con.close();
        return 0;
    }

    public static Media getMedia(long mID) throws SQLException {
        Connection con = DBInfo.getConnection();
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
        st.close();
        rs.close();
        con.close();
        return md;
    }

}
