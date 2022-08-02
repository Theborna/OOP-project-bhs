package com.electro.views.component;

import java.io.IOException;

import org.controlsfx.control.PopOver;

import com.electro.App;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.util.Duration;

public class ProfilePopOver extends PopOver {
    public ProfilePopOver(Node preview) {// TODO: add user to constructor
        super();
        try {
            Parent root = App.loadFXML("components/profile").load();
            // root.getStylesheets().add(arg0)
            super.setContentNode(root);
            super.setAnimated(true);
            super.setFadeInDuration(Duration.seconds(0.7));
        } catch (IOException e) {
            e.printStackTrace();
        }
        preview.setOnMouseClicked(evt -> {
            if (!this.isShowing()) {
                this.show((Node) evt.getSource());
            } else
                this.hide();
        });
        // preview.setOnMouseClicked(evt -> {
        //     this.hide();
        // });
    }

    // TODO : make showing this timed
    // public class openThread extends Thread {

    // @Override
    // public void run() {
    // try {
    // Thread.sleep(1000);
    // } catch (InterruptedException e) {
    // return;
    // }

    // }

    // }
}