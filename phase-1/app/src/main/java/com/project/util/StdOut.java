package com.project.util;

import static com.project.util.StdColor.*;

public class StdOut {

    public static void print(Object output) {
        System.out.print(output);
    }

    public static void print(Object output, StdColor color) {
        System.out.print(color + output.toString() + RESET);
    }

    public static void println(Object output) {
        System.out.println(output);
    }

    public static void println(Object output, StdColor color) {
        System.out.println(color + output.toString() + RESET);
    }

    public static void rule() {
        System.out.println(BLUE);
        System.out.println();
        System.out.println(
                "#############################################################################################################################################");
        System.out.println();
        System.out.println(RESET);
    }

}
