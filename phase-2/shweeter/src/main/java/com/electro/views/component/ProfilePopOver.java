package com.electro.views.component;

import java.io.IOException;

import org.controlsfx.control.PopOver;

import com.electro.App;

import javafx.scene.Parent;
import javafx.util.Duration;

public class ProfilePopOver extends PopOver {
    public ProfilePopOver() {// TODO: add user to constructor
        super();
        try {
            Parent root = App.loadFXML("components/profile").load();
            super.setContentNode(root);
            super.setAnimated(true);
            super.setFadeInDuration(Duration.seconds(0.7));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}