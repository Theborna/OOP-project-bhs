package com.electro.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class StretchTextArea {

    private StretchTextArea() {
    }

    public static void bind(TextArea textArea) {
        // ScrollBar scrollBar = lookupVerticalScrollBar(textArea);
        // scrollBar.setOpacity(0.0);
        // scrollBar.visibleProperty().addListener(new ChangeListener<Boolean>() {

        // @Override
        // public void changed(ObservableValue<? extends Boolean> source,
        // Boolean wasVisible,
        // Boolean isVisible) {
        // if (isVisible) {
        // textArea.setPrefRowCount(textArea.getPrefRowCount() + 1);
        // textArea.requestLayout();
        // }
        // }
        // });
        // TODO : fix this
        textArea.textProperty().addListener((obs, old, niu) -> {
            Text t = new Text(old + niu);
            t.setFont(textArea.getFont());
            StackPane pane = new StackPane(t);
            pane.layout();
            double height = t.getLayoutBounds().getHeight();
            double padding = 20;
            textArea.setMinHeight(height + padding);
        });
    }

    private static ScrollBar lookupVerticalScrollBar(Node node) {
        if (node instanceof ScrollBar && ((ScrollBar) node).getOrientation() == Orientation.VERTICAL) {
            return (ScrollBar) node;
        }
        if (node instanceof Parent) {
            ObservableList<Node> children = ((Parent) node).getChildrenUnmodifiable();
            for (Node child : children) {
                ScrollBar scrollBar = lookupVerticalScrollBar(child);
                if (scrollBar != null) {
                    return scrollBar;
                }
            }
        }
        return null;
    }
}
