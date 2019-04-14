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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.DbConnector;
import model.Game;
import model.Schedule;
import netscape.javascript.JSObject;
import utils.ScreenShots;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    public static Stage stage;
    static Scene scene;
    private int round1;
    private DbConnector dbConnector;
    static String username;
    static Long uid;
    private static FXMLLoader loader;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void setUid(Long id) {
        uid = id;
    }

    public static void restart() {

        stage.close();
        Platform.runLater(() -> new Main().start(new Stage()));

    }

    public static void end() {

        System.exit(0);

    }

    public static void proceed(int round) {
        // stage.close();
        Platform.runLater(() -> {
            try {
                new Main().chooseCity(stage, round, username);
            } catch (ClassNotFoundException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            primaryStage.setTitle("地铁小游戏哦");

            Label nameInputText = new Label("请输入你的昵称（姓名首字母）：");
            nameInputText.setFont(new Font(21));
            TextField nameField = new TextField();
            
            Label l0 = new Label("本测试共包含5个阶段，全程大约50分钟");
            l0.setFont(new Font(21));
            l0.setAlignment(Pos.BASELINE_CENTER);
            l0.setPadding(new Insets(0,0,10,0));
            
            Label l1 = new Label("你需要作为地铁规划师通过合理的线路规划，尽快将乘客送往目的地");
            l1.setFont(new Font(21));
            l1.setAlignment(Pos.BASELINE_CENTER);
            l1.setPadding(new Insets(0,0,10,0));
            
            Label l2 = new Label("Tips：识别地图中蕴藏的规则可以帮助你赢得高分");
            l2.setFont(new Font(21));
            l2.setAlignment(Pos.BASELINE_CENTER);
            l2.setPadding(new Insets(0,0,10,0));
            
            Button b = new Button("提交");
            b.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff;-fx-border-radius: 10;");
            b.setAlignment(Pos.BASELINE_CENTER);
            
            b.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if(nameField.getText().isEmpty() == false) {
						loader = new FXMLLoader(Main.class.getResource("mainPage.fxml"));
						Group page = null;
						try {
							page = (Group) loader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            Controller controller = loader.getController();
			            controller.setRound(0);
			            Color colorScene = new Color((double) 240 / 255, (double) 240 / 255, (double) 240 / 255, 0.5);
			            Scene scene = new Scene(page, 960, 540, colorScene);
			            stage.setScene(scene);
			            stage.getIcons().add(new Image(this.getClass().getResource("/img/iconGame.jpg").toString()));
			            stage.setTitle("地铁小游戏哦");
			            stage.setResizable(false);
					} else {
						GameView.alertError("请输入昵称哦");
					}
				}
			});
            
            HBox hb = new HBox();
            hb.getChildren().addAll(nameInputText,nameField);
            hb.setAlignment(Pos.CENTER);
            hb.setPadding(new Insets(0,0,10,0));
            
            VBox vb = new VBox();
            vb.getChildren().addAll(hb,l0,l1,l2,b);
            vb.setAlignment(Pos.CENTER);

            Scene scene = new Scene(vb, 960, 540);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void chooseCity(Stage primaryStage, int round, String name) throws ClassNotFoundException, SQLException {
        System.out.println("进入 round" + round);
        round1 = round;
        stage = primaryStage;
        username = name;
        dbConnector = new DbConnector(name);
        Group page = new Group();
        try {
            loader = new FXMLLoader(Main.class.getResource("mainPage.fxml"));
            page = (Group) loader.load();
            Controller controller = loader.getController();
            controller.setRound(round, page);
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

}
