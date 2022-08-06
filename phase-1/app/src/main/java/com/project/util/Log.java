package com.project.util;

import com.project.models.node.user.User;
import com.project.util.telegram.TGinit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.*;

public class Log {
    public static final Logger logger = Logger.getLogger(Log.class.getName());
    private static long logNum;

    private Log() {
    }

    public static void sendToTG(LogRecord logRecord) {
        Thread sender = new Thread(() -> TGinit.getInstance().sendMessage(logRecord.getLevel().getName() + "\n"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n"
                + logRecord.getSourceMethodName() + "\n" + logRecord.getMessage()
                + (User.getCurrentUser() == null ? "" : "\nBy user: " + User.getCurrentUser().getUsername())),
                "Thread-" + String.valueOf(logNum++));
        sender.start();
    }

    public static void init() {
        LogManager.getLogManager().reset();
        try {
            FileHandler fh = new FileHandler("logs.log", true);
            fh.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    sendToTG(record);
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
