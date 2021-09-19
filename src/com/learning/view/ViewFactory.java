package com.learning.view;

import com.learning.controller.BaseController;
import com.learning.controller.LoginActionController;
import com.learning.controller.MainWindowController;
import com.learning.controller.OptionsWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewFactory {
    private EmailManager emailManager;

    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;
    private List<Stage> activeStages = new ArrayList<>();

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
    }



    //Configuring Options


    public void showLogin(){
        System.out.println("Login Window show called");
        BaseController controller = new LoginActionController(emailManager,this,"LoginAction.fxml");
        initializeStage(controller);
    }

    public void showMainWindow(){
        System.out.println("Main Window called");
        BaseController controller = new MainWindowController(emailManager,this,"MainWindow.fxml");
        initializeStage(controller);
    }

    public void showOptionsWindow(){
        System.out.println("Main Window called");
        BaseController controller = new OptionsWindowController(emailManager,this,"OptionsWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    public void closeStage(Stage stage){
        stage.close();
        activeStages.remove(stage);
    }

    public void updateCss() {
        for (Stage stage: activeStages) {
            System.out.println(activeStages);
            Scene scene = stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssFile(fontSize)).toExternalForm());
        }
    }
}
