package com.electro.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.electro.phase1.models.node.user.User;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

public class FunctionalListview extends ListView<String> {

    private static Consumer<User> showPage;

    public FunctionalListview(Collection<User> users) {
        super.setItems(FXCollections.observableArrayList());
        super.getItems().addAll(users.stream().map(User::getUsername).collect(Collectors.toList()));
        List<Runnable> functions = new ArrayList<Runnable>();
        functions.addAll(users.stream().map(user -> new Runnable() {
            @Override
            public void run() {
                showPage.accept(user);
            }
        }).collect(Collectors.toList()));
        bind(functions, this);
    }

    public static void bind(List<Runnable> functions, ListView<?> list) {
        list.getSelectionModel().selectedIndexProperty().addListener((a, old, niu) -> {
            if (niu.intValue() < 0)
                return;
            functions.get(niu.intValue()).run();
        });
    }

    public static void setShowPage(Consumer<User> showPage) {
        FunctionalListview.showPage = showPage;
    }
}
