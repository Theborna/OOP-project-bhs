package com.company;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class Log {
    public static final Logger logger = Logger.getLogger(Log.class.getName());

    private Log() {
    }

    public static void sendtoTG(LogRecord logRecord) {

    }

    public static void init() {
        LogManager.getLogManager().reset();
        try {
            FileHandler fh = new FileHandler("logs.log", true);
            fh.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    sendtoTG(record);
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
