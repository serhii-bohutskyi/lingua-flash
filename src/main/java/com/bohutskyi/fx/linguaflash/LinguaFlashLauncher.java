package com.bohutskyi.fx.linguaflash;

import atlantafx.base.theme.NordLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Objects;

public class LinguaFlashLauncher extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent rootNode;

    @Override
    public void init() {
        springContext = SpringApplication.run(LinguaFlashApplication.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/main.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        try {
            rootNode = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) {
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
        stage.getIcons().add(new Image(Objects.requireNonNull(LinguaFlashLauncher.class.getResourceAsStream(
                "/static/image/favicon.png"))));

        stage.setTitle("LinguaFlash");
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }
}