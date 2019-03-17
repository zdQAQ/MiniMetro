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
import model.DbConnector;
import model.Game;
import model.Schedule;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    static Stage stage;
    static Scene scene;
    private DbConnector dbConnector;

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
    
    public static void proceed(int round) {
//    	stage.close();
		Platform.runLater(()->new Main().chooseCity(stage,round));
    }

    @Override
    public void start(Stage primaryStage) {
        try {
        	dbConnector = new DbConnector();
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
            Scene scene = new Scene(vBox, 960, 540);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void chooseCity(Stage primaryStage,int round) {
    	stage = primaryStage;
        Group page = new Group();
        try {
        	FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainPage.fxml"));
            page = (Group) loader.load();
            Controller controller = loader.getController();
            controller.setRound(round,page);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Color colorScene = new Color((double) 240 / 255, (double) 240 / 255, (double) 240 / 255, 0.5);
        Scene scene = new Scene(page, 960, 540, colorScene);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
                Platform.exit();
            }
        });
    }

    // JavaScript interface object
    public class JavaApp {

        public void chooseCity(int round) {
            Group page = null;
            try {
            	FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainPage.fxml"));
                page = (Group) loader.load();
                Controller controller = loader.getController();
                controller.setRound(round);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Color colorScene = new Color((double) 240 / 255, (double) 240 / 255, (double) 240 / 255, 0.5);
            Scene scene = new Scene(page, 960, 540, colorScene);
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
