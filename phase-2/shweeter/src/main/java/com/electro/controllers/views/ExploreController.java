package com.electro.controllers.views;

import java.net.URL;
import java.util.ResourceBundle;

import com.electro.phase1.models.connection.PostUserConnection;
import com.electro.phase1.models.connection.UserFollowingConnection;
import com.electro.phase1.models.node.user.User;
import com.electro.phase1.util.Suggestion;
import com.electro.util.FunctionalListview;
import com.electro.views.PostListView;

import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ExploreController {
    private static final int ROW_HEIGHT = 32;
    private static final int THRESHOLD = 500;
    @FXML
    private AnchorPane pnExplore;

    @FXML
    private ScrollPane scrollExplore;

    private ListView<String> lstRecommendedPages;

    @FXML
    private BorderPane bpMain;

    @FXML
    private VBox vboxRecommend;

    public void initialize(User user) {
        lstRecommendedPages = new FunctionalListview(UserFollowingConnection.getExploreUsers(user));
        scrollExplore.setContent(new PostListView().withPosts(PostUserConnection.getExplore(user)));
        scrollExplore.widthProperty().addListener((o, old, niu) -> vboxRecommend.setPrefWidth(niu.floatValue() / 3));
        lstRecommendedPages.setPrefHeight(lstRecommendedPages.getItems().size() * ROW_HEIGHT + 2);
        lstRecommendedPages.getItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> arg0) {
                lstRecommendedPages.setPrefHeight(lstRecommendedPages.getItems().size() * ROW_HEIGHT + 2);
            }
        });
    }
}
