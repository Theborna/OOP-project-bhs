package com.electro.util;

import com.electro.phase1.models.node.node;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.Size;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResponsiveVbox {

    private static final double THRESHOLD = 755;

    private ResponsiveVbox() {

    }

    public static void bind(VBox vBox) {
        // System.out.println(vBox.getParent());
        System.out.println(vBox.getParent());
        HBox parent = getParent(vBox);
        if (parent == null)
            return;
        parent.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                float width = arg1.floatValue();
                System.out.println(width);
                if (width < THRESHOLD)
                    mobile();
                else
                    desktop();
            }

            private void mobile() {
                vBox.setPrefWidth(vBox.getMaxWidth());
            }

            private void desktop() {
                vBox.setPrefWidth(450.0);
            }
        });
    }

    public static void bind(ScrollPane vBox) {
        // System.out.println(vBox.getParent());
        System.out.println(vBox.getParent());
        HBox parent = getParent(vBox);
        if (parent == null)
            return;
        parent.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                float width = arg1.floatValue();
                System.out.println(width);
                if (width < THRESHOLD)
                    mobile(width);
                else
                    desktop();
            }

            private void mobile(double width) {
                vBox.setPrefWidth(width);
                HBox.setMargin(parent, new Insets(0, 30, 0, 30));
            }

            private void desktop() {
                vBox.setPrefWidth(450.0);
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
