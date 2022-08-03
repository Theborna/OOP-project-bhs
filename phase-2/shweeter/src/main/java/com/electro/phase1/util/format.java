package com.electro.phase1.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class format {
        // we will make stuff pretty with this class
        public static String SimpleDate(LocalDateTime date) {
                return date.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
        }
}
