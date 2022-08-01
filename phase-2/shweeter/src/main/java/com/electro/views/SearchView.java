package com.electro.views;

import com.electro.controllers.views.SearchController;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SearchView extends inPane {

    private SearchController controller;

    public SearchView(StringProperty textProperty) {
        super();
        controller = getController("search-view");
        controller.initialize(textProperty);
    }

}
