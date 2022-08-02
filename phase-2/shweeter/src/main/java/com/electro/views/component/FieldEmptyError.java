package com.electro.views.component;

import animatefx.animation.Shake;
import javafx.scene.control.TextField;

public class FieldEmptyError {
    public FieldEmptyError(TextField field) {
        if (field.getText().length() == 0) {
            field.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            new Shake(field).play();
            new ErrorNotification(field.getPromptText() + " cannot be empty!");
        } else
            field.setStyle(null);
    }
}
