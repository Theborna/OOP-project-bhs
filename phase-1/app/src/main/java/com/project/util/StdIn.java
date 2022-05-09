package com.project.util;

import java.util.Scanner;

public class StdIn {
    private static Scanner scanner = new Scanner(System.in);

    public static String nextLine() { // TODO: check for valid entries
        String answer = scanner.nextLine();
        return answer;
    }
}
