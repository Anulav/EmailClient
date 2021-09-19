package com.learning.controller;

import com.learning.view.ColorTheme;
import com.learning.view.EmailManager;
import com.learning.view.FontSize;
import com.learning.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsWindowController extends BaseController implements Initializable {

    @FXML
    private Slider fontSizePicker;

    @FXML
    private ChoiceBox<ColorTheme> themePicker;

    @FXML
    void applyBtnAction() {
        viewFactory.setColorTheme(themePicker.getValue());
        System.out.println("Selected : "+themePicker.getValue());
        viewFactory.setFontSize(FontSize.values()[(int)fontSizePicker.getValue()]);
        System.out.println("Selected : "+FontSize.values()[(int)fontSizePicker.getValue()]);
        viewFactory.updateCss();


    }

    @FXML
    void cancelBtnAction() {

    }
    public OptionsWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setThemePicker();
        setFontSize();

    }

    private void setFontSize() {
        fontSizePicker.setMin(0);
        fontSizePicker.setMax(FontSize.values().length-1);
        fontSizePicker.setValue(viewFactory.getFontSize().ordinal());
        fontSizePicker.setMajorTickUnit(1);
        fontSizePicker.setMinorTickCount(0);
        fontSizePicker.setBlockIncrement(1);
        fontSizePicker.setSnapToTicks(true);
        fontSizePicker.setShowTickLabels(true);
        fontSizePicker.setShowTickLabels(true);
        fontSizePicker.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                int i = object.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        });
        fontSizePicker.valueProperty().addListener(((observable, oldValue, newValue) -> {
            fontSizePicker.setValue(newValue.intValue());
        }));
    }

    private void setThemePicker() {
        themePicker.setItems(FXCollections.observableArrayList(ColorTheme.values()));
        themePicker.setValue(viewFactory.getColorTheme());
    }
}