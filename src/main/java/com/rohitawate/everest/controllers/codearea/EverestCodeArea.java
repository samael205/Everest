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

package com.rohitawate.everest.controllers.codearea;

import com.rohitawate.everest.controllers.codearea.highlighters.Highlighter;
import com.rohitawate.everest.controllers.codearea.highlighters.JSONHighlighter;
import com.rohitawate.everest.controllers.codearea.highlighters.PlaintextHighlighter;
import com.rohitawate.everest.controllers.codearea.highlighters.XMLHighlighter;
import com.rohitawate.everest.settings.Settings;
import javafx.geometry.Insets;
import org.fxmisc.richtext.CodeArea;

import java.time.Duration;

public class EverestCodeArea extends CodeArea {
    public enum HighlightMode {
        JSON, XML, HTML, PLAIN
    }

    private Highlighter highlighter;

    private static JSONHighlighter jsonHighlighter;
    private static XMLHighlighter xmlHighlighter;
    private static PlaintextHighlighter plaintextHighlighter;

    public EverestCodeArea() {
        this.getStylesheets().add(getClass().getResource("/css/syntax/Moondust.css").toString());
        this.getStyleClass().add("everest-code-area");
        this.setWrapText(Settings.editorWrapText);
        this.setPadding(new Insets(5));

        jsonHighlighter = new JSONHighlighter();
        xmlHighlighter = new XMLHighlighter();
        plaintextHighlighter = new PlaintextHighlighter();

        setMode(HighlightMode.PLAIN);

        this.multiPlainChanges()
                .successionEnds(Duration.ofMillis(1))
                .subscribe(ignore -> this.setStyleSpans(0, highlighter.computeHighlighting(getText())));
    }

    public void setMode(HighlightMode mode) {
        switch (mode) {
            case JSON:
                highlighter = jsonHighlighter;
                break;
            case XML:
            case HTML:
                highlighter = xmlHighlighter;
                break;
            default:
                highlighter = plaintextHighlighter;
        }

        // Re-computes the highlighting for the new mode
        this.setStyleSpans(0, highlighter.computeHighlighting(getText()));
    }

    public void setText(String text, HighlightMode mode) {
        clear();
        appendText(text);
        setMode(mode);
    }
}
