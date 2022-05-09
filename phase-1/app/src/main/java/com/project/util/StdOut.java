package com.project.util;

import static com.project.util.Color.*;

public class StdOut {

    public static void print(Object output) {
        System.out.println(output);
    }

    public static void rule() {
        System.out.println(BLUE);
        System.out.println();
        System.out.println(
                "############################################################################################################################################################");
        System.out.println();
        System.out.println(RESET);
    }
}
