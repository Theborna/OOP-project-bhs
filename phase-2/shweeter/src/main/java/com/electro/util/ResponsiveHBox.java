package com.electro.util;

import com.electro.phase1.models.node.node;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.Size;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ResponsiveHBox {

    private static final double THRESHOLD = 600;

    private ResponsiveHBox() {
    }

    public static void bind(Node vBox) {
        HBox parent = getParent(vBox);
        if (parent == null)
            return;
        parent.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                float width = arg1.floatValue();
                if (width == 0)
                    return;
                // System.out.println(width);
                if (width > THRESHOLD) {
                    HBox.setHgrow(vBox, Priority.NEVER);
                    vBox.minWidth(450);
                } else
                    HBox.setHgrow(vBox, Priority.ALWAYS);
            }
        });
    }

    public static void bindCentering(Node node) {
        HBox parent = getParent(node);
        parent.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                float width = arg1.floatValue();
                if (width == 0)
                    return;
                if (width > THRESHOLD) {
                    parent.setAlignment(Pos.CENTER_LEFT);
                } else
                    parent.setAlignment(Pos.CENTER);
            }
        });
    }

    private static HBox getParent(Node node) {
        if (node instanceof HBox)
            return (HBox) node;
        if (node.getParent() != null)
            return getParent(node.getParent());
        return null;
    }
}
