package com.electro.phase1.util;

import java.time.LocalDateTime;

public class format {
        // we will make stuff pretty with this class
        public static String SimpleDate(LocalDateTime date) {
                return date.toString().substring(10);
        }
}
