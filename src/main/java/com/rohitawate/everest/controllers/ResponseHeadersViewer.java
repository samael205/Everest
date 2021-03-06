/*
 * Copyright 2018 Rohit Awate.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rohitawate.everest.controllers;

import com.rohitawate.everest.models.responses.EverestResponse;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

class ResponseHeadersViewer extends ScrollPane {
    private VBox container;

    ResponseHeadersViewer() {
        this.container = new VBox();
        container.setPadding(new Insets(10, 20, 10, 20));
        this.setContent(container);

        this.setFitToHeight(true);
        this.setFitToWidth(true);
    }

    void populate(EverestResponse response) {
        container.getChildren().clear();

        response.getHeaders().forEach((key, value) -> {
            Label keyLabel = new Label(key + ": ");
            keyLabel.getStyleClass().addAll("visualizerKeyLabel", "visualizerLabel");

            Label valueLabel = new Label(value.get(0));
            valueLabel.getStyleClass().addAll("visualizerValueLabel", "visualizerLabel");

            container.getChildren().add(new HBox(keyLabel, valueLabel));
        });
    }
}
