package com.electro.controllers.components;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.electro.phase1.models.connection.Like;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.util.StdColor;
import com.electro.phase1.util.StdOut;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class postStatController {

    @FXML
    private StackedAreaChart<Number, Number> stats;

    public void initialize(Post post) {
        List<Like> result = new ArrayList<Like>();
        result.addAll(Like.getUsers(post));
        LinkedHashMap<Integer, Integer[]> likes = new LinkedHashMap<Integer, Integer[]>();
        for (Like l : result) {
            int s = l.getCreationDate().getDayOfMonth();
            if (!likes.containsKey(s))
                likes.put(s, new Integer[] { 1, l.getValue() });
            else {
                Integer[] past = likes.get(s);
                likes.put(s, new Integer[] { past[0] + 1, past[1] + l.getValue() });
            }
        }
        // TODO: put last months data into this
        for (Integer[] i : likes.values()) {
            for (int j = 0; j < i[1]; j++)
                StdOut.print(" ", StdColor.RED_BACKGROUND);
            for (int j = 0; j < i[0] - i[1]; j++)
                StdOut.print(" ", StdColor.GREEN_BACKGROUND);
            StdOut.println("");
        }
        System.out.println("sakaladsad");

        XYChart.Series<Number, Number> views = new XYChart.Series<>();
        XYChart.Series<Number, Number> likesSeries = new XYChart.Series<>();
        views.setName("Views");
        likesSeries.setName("Likes");
        int j = 0;
        for (int k = 0; k < likes.values().size(); k++) {
            Integer[] i = likes.getOrDefault(likes.keySet().toArray(new Integer[likes.keySet().size() - 1])[k],
                    new Integer[] { 0, 0 });
            final XYChart.Data<Number, Number> dataL = new XYChart.Data<>(++j, i[1]);
            final XYChart.Data<Number, Number> dataV = new XYChart.Data<>(++j, i[1]);
            dataL.setNode(new HoveredThresholdNodes("engine1", i[1]));
            dataV.setNode(new HoveredThresholdNodes("engine2", i[0] - i[1]));
            likesSeries.getData().add(dataL);
            views.getData().add(dataV);
        }
        // // populating the series with data
        final XYChart.Data<Number, Number> data = new XYChart.Data<>(1, 23);
        data.setNode(new HoveredThresholdNodes("engine1", 23));
        // series.getData().add(data);
        // series.getData().add(new XYChart.Data<>(2, 14));
        // series.getData().add(new XYChart.Data<>(3, 15));
        // series.getData().add(new XYChart.Data<>(4, 24));
        // series.getData().add(new XYChart.Data<>(5, 34));
        // series.getData().add(new XYChart.Data<>(6, 36));
        // series.getData().add(new XYChart.Data<>(7, 22));
        // series.getData().add(new XYChart.Data<>(8, 45));
        // series.getData().add(new XYChart.Data<>(9, 43));
        // series.getData().add(new XYChart.Data<>(10, 17));
        // series.getData().add(new XYChart.Data<>(11, 29));
        // series.getData().add(new XYChart.Data<>(12, 25));
        stats.getData().clear();
        // stats.getData().add(series);
        stats.getData().add(likesSeries);
        stats.getData().add(views);
        // series = new XYChart.Series<>();
        // series.setName("Likes");

        // // populating the series with the difference data

        // final XYChart.Data<Number, Number> data2 = new XYChart.Data<>(1, 23);
        // data2.setNode(new HoveredThresholdNodes("engine2", 23));
        // series.getData().add(data2);
        // series.getData().add(new XYChart.Data<>(2, 24));
        // series.getData().add(new XYChart.Data<>(3, 24));
        // series.getData().add(new XYChart.Data<>(4, 24));
        // series.getData().add(new XYChart.Data<>(5, 24));
        // series.getData().add(new XYChart.Data<>(6, 24));
        // series.getData().add(new XYChart.Data<>(7, 24));
        // series.getData().add(new XYChart.Data<>(8, 24));
        // series.getData().add(new XYChart.Data<>(9, 24));
        // series.getData().add(new XYChart.Data<>(10, 24));
        // series.getData().add(new XYChart.Data<>(11, 24));
        // series.getData().add(new XYChart.Data<>(12, 24));
        // // stats.getData().clear();
        // stats.getData().add(series);
        stats.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
    }

    class HoveredThresholdNodes extends StackPane {

        public HoveredThresholdNodes(String string, Object object) {
            setPrefSize(10, 10);

            final Label label = createDataThresholdLabel(string, object);

            super.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getChildren().setAll(label);
                    setCursor(Cursor.NONE);
                    toFront();
                }
            });
            super.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getChildren().clear();
                }
            });
            super.setStyle("-fx-background-color: #000000;");
            // super.setStyle("-text-fill: #000000");
        }

        private Label createDataThresholdLabel(String string, Object object) {
            final Label label = new Label(object + "");
            label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
            label.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
            label.setStyle("-fx-text=fill: #ff0000;");
            // System.out.println(string);
            if (string.equals("engine1")) {
                label.setTextFill(Color.RED);
                label.setStyle("-fx-border-color: RED;");
            } else if (string.equals("engine2")) {
                label.setTextFill(Color.ORANGE);
                label.setStyle("-fx-border-color: ORANGE;");
            } else {
                label.setTextFill(Color.GREEN);
                label.setStyle("-fx-border-color: GREEN;");
            }
            label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
            return label;
        }
    }
}
