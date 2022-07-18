package com.util;

import com.App;
import com.AppRegex;
import com.util.exception.changeViewException;
import com.view.View;
import com.view.general.PrimaryView;
import com.view.general.SecondaryView;

import java.util.Scanner;
import java.util.regex.Matcher;

public class StdIn {
    private static boolean returnsNull;// might be used
    private static Scanner scanner = new Scanner(System.in);
    private static Matcher m;

    private StdIn() {
    }

    public static String nextLine() throws changeViewException { // TODO: check for valid entries
        if (!scanner.hasNext()) {
            System.out.println(StdColor.RESET);
            System.exit(0);
        }
        String answer = scanner.nextLine().trim();
        if (answer.equals("-exit") || answer.equals("-end"))
            System.exit(0);
        if (answer.equals("-logout"))
            throw new changeViewException(PrimaryView.getInstance());
        if (answer.equals("-back"))
            throw new changeViewException(App.lastView());
        if (answer.equals("-hub"))
            throw new changeViewException(SecondaryView.getInstance());
        if ((m = AppRegex.SWITCH.getMatcher(answer)) != null) {
            throw new changeViewException(View.getView(m.group("next")));
        }
        return answer;
    }
}