package com.electro.phase1.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class format {
        // we will make stuff pretty with this class
        public static String SimpleDate(LocalDateTime date) {
                return Date.from(date.atZone(ZoneId.systemDefault()).toInstant()).toString();
        }
}
