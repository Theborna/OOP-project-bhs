package com.project.util;

import java.util.Scanner;

import com.project.App;
import com.project.view.ViewsEnum;

public class StdIn {
    private static Scanner scanner = new Scanner(System.in);

    public static String nextLine() { // TODO: check for valid entries
        String answer = scanner.nextLine().trim();
        if (answer.equals("exit"))
            App.stop();
        if (answer.equals("back"))
            App.setView(ViewsEnum.PRIMARY);
        return answer;
    }
}
