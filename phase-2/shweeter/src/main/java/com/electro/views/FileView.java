package com.electro.views;

import com.electro.controllers.components.FileController;
import com.electro.phase1.models.node.Media;

public class FileView extends inPane {

    private FileController controller;

    public FileView(Media media) {
        controller = getController("pic-viewer");
        controller.initialize(media);
    }
}
