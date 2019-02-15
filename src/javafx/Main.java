/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */
package javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    static Stage stage;
    static Scene scene;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void restart() {

        stage.close();
        Platform.runLater(() -> new Main().start(new Stage()));

    }

    public static void end() {

        System.exit(0);

    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            primaryStage.setTitle("地铁小游戏哦");

            WebView webView = new WebView();

            String url = Main.class.getResource("/web/index.html").toExternalForm();

            WebEngine webEngine = webView.getEngine();

            // process page loading
            webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
                @Override
                public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                    if (newState == State.SUCCEEDED) {
                        JSObject win = (JSObject) webEngine.executeScript("window");
                        win.setMember("app", new JavaApp());
                    }
                }
            });

            webEngine.load(url);

            VBox vBox = new VBox(webView);
            Scene scene = new Scene(vBox, 1200, 600);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // JavaScript interface object
    public class JavaApp {

        public void chooseCity(String city) {
            Group page = null;
            try {
                page = (Group) FXMLLoader.load(Main.class.getResource("mainPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Color colorScene = new Color((double) 240 / 255, (double) 240 / 255, (double) 240 / 255, 0.5);
            Scene scene = new Scene(page, 1200, 600, colorScene);
            stage.setScene(scene);
            stage.getIcons().add(new Image(this.getClass().getResource("/img/iconGame.jpg").toString()));
            stage.setTitle("地铁小游戏");
            stage.setResizable(false);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.exit(0);
                    Platform.exit();
                }
            });
        }
    }

}
