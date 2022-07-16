package com.util.exception;

import com.view.View;

public class changeViewException extends Throwable {

    View view;

    public changeViewException(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

}
