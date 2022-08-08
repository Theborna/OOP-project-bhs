package com.electro.controllers.components;

import org.controlsfx.control.PopOver;

import com.electro.App;
import com.electro.phase1.models.node.ImageNode;
import com.electro.phase1.models.node.Media;
import com.electro.phase1.models.node.Video;
import com.electro.phase1.models.node.node;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FileController {

    @FXML
    private ImageView ivImage;

    @FXML
    private Button btnShow;

    public void initialize(Media media) {
        Node node = null;
        if (media instanceof ImageNode) {
            ivImage.setImage(((ImageNode) media).getImage(ivImage.getFitWidth(), ivImage.getFitHeight()));
            node = new ImageView(((ImageNode) media).getImage());
        } else if (media instanceof Video) {
            ivImage.setImage(App.getImage("images/icons8_video_96px.png"));
        }
        // TODO: the rest
        PopOver popOver = new PopOver(node);
        popOver.setTitle("expanded media");
        btnShow.setOnAction(evt -> {
            if (popOver.isShowing())
                popOver.hide();
            else
                popOver.show(btnShow);
        });
        initBorder();
    }

    private void initBorder() {
        Rectangle clip = new Rectangle(
                ivImage.getFitWidth(), ivImage.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        ivImage.setClip(clip);
        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = ivImage.snapshot(parameters, null);
        // remove the rounding clip so that our effect can show through.
        ivImage.setClip(null);
        // apply a shadow effect.
        ivImage.setEffect(new DropShadow(20, Color.BLACK));
        // store the rounded image in the ivImage.
        ivImage.setImage(image);
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1.0);
        Blend blush = new Blend(
                BlendMode.MULTIPLY,
                monochrome,
                new ColorInput(
                        0,
                        0,
                        ivImage.getImage().getWidth(),
                        ivImage.getImage().getHeight(),
                        Color.PURPLE));
        ivImage.effectProperty().bind(
                Bindings
                        .when(ivImage.hoverProperty())
                        .then((Effect) blush)
                        .otherwise((Effect) null));
        ivImage.setCache(true);
        ivImage.setCacheHint(CacheHint.SPEED);

    }

}
