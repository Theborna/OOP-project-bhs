package com.project.util;

import java.util.Scanner;

import com.project.App;
import com.project.util.exception.changeViewException;
import com.project.view.general.PrimaryView;

public class StdIn {
    private static boolean returnsNull;// might be used
    private static Scanner scanner = new Scanner(System.in);

    private StdIn() {
    }

    public static String nextLine() throws changeViewException { // TODO: check for valid entries
        String answer = scanner.nextLine().trim();
        if (answer.equals("exit") || answer.equals("end"))
            System.exit(0);
        if (answer.equals("logout"))
            throw new changeViewException(PrimaryView.getInstance());
        if (answer.equals("back"))
            throw new changeViewException(App.lastView());
        return answer;
    }
}
