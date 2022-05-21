package com.project.util;

import java.util.Scanner;

import com.project.App;
import com.project.view.general.PrimaryView;

public class StdIn {
    private static boolean returnsNull;// might be used
    private static Scanner scanner = new Scanner(System.in);

    public static String nextLine() { // TODO: check for valid entries
        String answer = scanner.nextLine().trim();
        if (answer.equals("exit") || answer.equals("end"))
            System.exit(0);
        if (answer.equals("back"))
            App.setView(new PrimaryView());
        return answer;
    }
}
