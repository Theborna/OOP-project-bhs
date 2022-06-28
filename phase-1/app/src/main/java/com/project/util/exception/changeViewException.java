package com.project.util.exception;

import com.project.view.View;

public class changeViewException extends Throwable {

    View view;

    public changeViewException(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

}
