package com.electro.util;

import java.util.List;

import javafx.scene.control.ListView;

public class FunctionalListview {
    public static void bind(List<Runnable> functions, ListView<?> list) {
        list.getSelectionModel().selectedIndexProperty().addListener((a, old, niu) -> {
            if (niu.intValue() < 0)
                return;
            functions.get(niu.intValue()).run();
        });
    }
}
