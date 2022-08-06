package com.company;

import com.company.telegram.TGinit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.*;

public class Log {
    public static final Logger logger = Logger.getLogger(Log.class.getName());

    public static void sendtoTG(LogRecord logRecord) {
        //System.out.println(logRecord.getMessage());
//        System.out.println("kir");
        TGinit.getInstance().sendMessage("Messaging Server:\n" + logRecord.getLevel().getName() + "\n"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n"
                + logRecord.getSourceMethodName() + "\n" + logRecord.getMessage());

    }

    public static void init() {
        LogManager.getLogManager().reset();
        try {
            FileHandler fh = new FileHandler("logs.log", true);
//            System.out.println("kir");
            fh.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    sendtoTG(record);
//                    System.out.println("kir");
                    return new SimpleFormatter().format(record);
                }
            });
            logger.addHandler(fh);
            logger.info("starting application");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
    }
}
