package com.project.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {
    public static final Logger logger = Logger.getLogger(Log.class.getName());

    private Log() {
    }

    public static void init() {
        LogManager.getLogManager().reset();
        try {
            logger.addHandler(new FileHandler("logs.log", false));
            logger.info("starting application");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
    }
}
