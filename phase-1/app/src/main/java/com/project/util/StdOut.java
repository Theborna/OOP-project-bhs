package com.project.util;

import static com.project.util.StdColor.*;

import java.util.Arrays;

import com.project.view.View;

public class StdOut {

    private static final int RULE_SIZE = 140;

    
    public static void print(Object output) {
        System.out.print(output);
    }

    public static void print(Object output, StdColor color) {
        if (output == null)
            return;
        System.out.print(color + output.toString() + RESET);
    }

    public static void println(Object output) {
        System.out.println(RESET.toString() + output);
    }

    public static void println(Object output, StdColor color) {
        if (output == null)
            return;
        System.out.println(color + output.toString() + RESET);
    }

    public static void prompt(String demand) {
        print(demand + " >> ", YELLOW);
        System.out.print(WHITE_BOLD_BRIGHT);
    }

    public static void printSelections(String... selections) {
        print("select from: ", CYAN);
        println(Arrays.asList(selections));
    }

    public static void printError(String message) {
        println("Error! " + message, RED_BRIGHT);
    }

    public static void rule() {
        System.out.println(BLUE);
        line(RULE_SIZE);
        System.out.println();
        System.out.println(RESET);
    }

    public static void viewBegin(View view) {
        System.out.println();
        String name = view.getClass().getSimpleName().replaceFirst("View", "");
        int halfWidth = (RULE_SIZE - name.length()) / 2;
        System.out.print(BLUE_BOLD);
        line(halfWidth);
        print(name, WHITE_BOLD_BRIGHT);
        System.out.print(BLUE_BOLD);
        line(halfWidth);
        System.out.println(RESET);
        System.out.println();
    }

    private static void line(int size) {
        for (int i = 0; i < size; i++)
            System.out.print("#");
    }

}
