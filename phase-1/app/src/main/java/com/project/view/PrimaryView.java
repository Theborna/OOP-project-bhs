package com.project.view;

import static com.project.util.StdOut.print;

import com.project.util.StdOut;

public class PrimaryView implements View {

    @Override
    public void show() {
        StdOut.rule();
        print("inside primary");
        StdOut.rule();
    }
}
