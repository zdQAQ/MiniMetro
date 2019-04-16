package model;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.Controller;
import javafx.GameView;
import javafx.Main;
import javafx.fxTimer;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.WindowEvent;
import netscape.javascript.JSObject;
import utils.ScreenShots;

public class Schedule {
	public static List<Event> events = new ArrayList<Event>();
	static Group group;
	static GameView gameview;
	static Game game;
	private static int clickTimes;
	public static Button btn;
	private fxTimer timer;

	public Schedule(int type, Group g, GameView gv, Game game1) {
		events = new ArrayList<Event>();
		group = g;
		gameview = gv;
		game = game1;
		setClickTimes(0);
		if (type == 0) {
			// stations
			events.add(new Event(0, ShapeType.SQUARE, new Position(250, 250)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(340, 335)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(250, 334)));
			events.add(new Event(15000, ShapeType.CIRCLE, new Position(160, 280)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(460, 260)));
			events.add(new Event(15000, ShapeType.CIRCLE, new Position(340, 255)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(110, 235)));
			events.add(new Event(15000, ShapeType.CIRCLE, new Position(360, 390)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(550, 430)));
			events.add(new Event(15000, ShapeType.STAR, new Position(240, 480)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(580, 300)));
			events.add(new Event(15000, ShapeType.SQUARE, new Position(110, 340)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(210, 440)));
			events.add(new Event(15000, ShapeType.CIRCLE, new Position(620, 210)));
			events.add(new Event(0, ShapeType.CROSS, new Position(770, 140)));
			events.add(new Event(20000, ShapeType.CIRCLE, new Position(670, 170)));
			events.add(new Event(20000, ShapeType.CIRCLE, new Position(620, 450)));
			events.add(new Event(20000, ShapeType.CIRCLE, new Position(320, 100)));
			events.add(new Event(20000, ShapeType.TRIANGLE, new Position(210, 110)));
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(730, 410)));
			events.add(new Event(25000, ShapeType.DIAMOND, new Position(800, 435)));
			events.add(new Event(25000, ShapeType.TRIANGLE, new Position(410, 325)));
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(650, 350)));
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(760, 210)));
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(480, 120)));
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(720, 105)));
			events.add(new Event(25000, ShapeType.SQUARE, new Position(850, 90)));
			events.add(new Event(25000, ShapeType.SQUARE, new Position(140, 140)));
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(200, 25)));
			events.add(new Event(25000, ShapeType.TRIANGLE, new Position(810, 245)));
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(450, 20)));
			events.add(new Event(30000, ShapeType.CIRCLE, new Position(690, 285)));
			events.add(new Event(30000, ShapeType.SQUARE, new Position(350, 150)));
			events.add(new Event(30000, ShapeType.TRIANGLE, new Position(410, 440)));
			events.add(new Event(30000, ShapeType.CIRCLE, new Position(530, 30)));
			events.add(new Event(30000, ShapeType.CIRCLE, new Position(260, 50)));
			events.add(new Event(30000, ShapeType.TRIANGLE, new Position(50, 360)));
			events.add(new Event(30000, ShapeType.CIRCLE, new Position(900, 420)));

			// map
			// River :
			Polyline river = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290, 420, 290,
					460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960, 350);

			Color colorRiver = new Color((double) 200 / 255, (double) 230 / 255, (double) 250 / 255, 0.5);
			river.setStroke(colorRiver);
			river.setStrokeLineJoin(StrokeLineJoin.ROUND);
			river.setStrokeWidth(14);

			Polyline borderRiver = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290, 420,
					290, 460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960, 350);

			Color colorBorder = new Color((double) 100 / 255, (double) 180 / 255, (double) 220 / 255, 0.5);
			borderRiver.setStroke(colorBorder);
			borderRiver.setStrokeLineJoin(StrokeLineJoin.ROUND);
			borderRiver.setStrokeWidth(16);

			group.getChildren().add(borderRiver);
			group.getChildren().add(river);

			gameview.addRiver(borderRiver);

			btn = new Button("跳过该回合");

			btn.setStyle(
					"-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff;-fx-border-radius: 10;");

			btn.setLayoutX(790);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					ScreenShots.make(DbConnector.username + "第一阶段点跳过");
					game.pauseGame();
					Game.setClockPause(true);
					timer.removeCallBack();
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);

			timer = new fxTimer(20);
			timer.setLayoutX(70);
			timer.setLayoutY(30);
			timer.setCallBack(() -> {
				ScreenShots.make(DbConnector.username + "第一阶段20分钟时");
				game.pauseGame();
				Game.setClockPause(true);
				GameView.go3rdPart();
				DbConnector.update("A101", gameview.getPauseTimes());
				DbConnector.update("A102", (int) gameview.pauseTimeRange);
				DbConnector.update("A103", gameview.getClickBook());
			});
			group.getChildren().add(timer);

		} else if (type == 1) {
			// 第二阶段第一回合
			gameview.hideThings();
			String uri = Main.class.getResource("/img/0201.png").toExternalForm();

			Image image = new Image(uri);
			ImageView imageView = new ImageView(image);

			VBox vBox = new VBox();
			vBox.prefWidthProperty().bind(Main.stage.widthProperty());

			TextField tf = new TextField();
			tf.setMaxWidth(120);
			tf.setMinHeight(36);
			Button b = new Button("提交");
			b.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");

			b.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (tf.getText().isEmpty() == true) {
						GameView.alertError("请输入你的答案哦");
					} else {
						DbConnector.update("A201", tf.getText());
						GameView.go3rdPart();
					}
				}
			});

			HBox hb = new HBox();
			hb.getChildren().addAll(tf, b);
			hb.setAlignment(Pos.CENTER);
			hb.setPadding(new Insets(10, 0, 0, 0));

			vBox.getChildren().addAll(imageView, hb);

			group.getChildren().add(vBox);
			// GameView.go2ndPart();
		} else if (type == 2) {
			// 第二阶段第二回合
			events.add(new Event(0, ShapeType.CIRCLE, new Position(280, 100)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(330, 350)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(380, 210)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(530, 220)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(620, 152)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(420, 90)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(650, 225)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(90, 230)));
			// events.add(new Event(0, ShapeType.CIRCLE, new Position(150, 150)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(200, 320)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(480, 360)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(250, 250)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(500, 120)));
			events.add(new Event(0, ShapeType.CROSS, new Position(750, 200)));

			gameview.addRiver(new Polyline(0, 0));

			btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					ScreenShots.make(DbConnector.username + "第二阶段第二回合");
					DbConnector.update("A202", Targets.fourCircles(gameview.getLineLinks()));
					DbConnector.update("A203", Targets.squareLineNums(gameview.getLineLinks()));
					DbConnector.update("A204", Targets.triangleLineNums(gameview.getLineLinks()));
					DbConnector.update("A205", Targets.crossLineNums(gameview.getLineLinks()));
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);
			popStaticStations(type);

		} else if (type == 3) {
			// 第二阶段第三回合
			gameview.hideThings();
			String uri = Main.class.getResource("/img/0203.png").toExternalForm();

			Image image = new Image(uri);
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(960);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);

			VBox vBox = new VBox();
			vBox.prefWidthProperty().bind(Main.stage.widthProperty());

			TextField tf = new TextField();
			tf.setMaxWidth(120);
			tf.setMinHeight(36);
			Button b = new Button("提交");
			b.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");

			b.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (tf.getText().isEmpty() == true) {
						GameView.alertError("请输入你的答案哦");
					} else {
						DbConnector.update("A206", tf.getText());
						GameView.go3rdPart();
					}
				}
			});

			HBox hb = new HBox();
			hb.getChildren().addAll(tf, b);
			hb.setAlignment(Pos.CENTER);
			hb.setPadding(new Insets(10, 0, 0, 0));

			vBox.getChildren().addAll(imageView, hb);

			group.getChildren().add(vBox);

		} else if (type == 4) {
			// 第二阶段第四回合
			gameview.hideThings();
			String uri = Main.class.getResource("/img/0204.png").toExternalForm();

			Image image = new Image(uri);
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(960);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);

			VBox vBox = new VBox();
			vBox.prefWidthProperty().bind(Main.stage.widthProperty());

			TextField tf = new TextField();
			tf.setMaxWidth(120);
			tf.setMinHeight(36);
			Button b = new Button("提交");
			b.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");

			b.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (tf.getText().isEmpty() == true) {
						GameView.alertError("请输入你的答案哦");
					} else {
						DbConnector.update("A207", tf.getText());
						GameView.go3rdPart();
					}
				}
			});

			HBox hb = new HBox();
			hb.getChildren().addAll(tf, b);
			hb.setAlignment(Pos.CENTER);
			hb.setPadding(new Insets(10, 0, 0, 0));

			vBox.getChildren().addAll(imageView, hb);

			group.getChildren().add(vBox);

		} else if (type == 5) {
			// 第二阶段第五回合
			events.add(new Event(0, ShapeType.CIRCLE, new Position(280, 100)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(330, 350)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(380, 210)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(530, 220)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(620, 152)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(420, 90)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(650, 225)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(90, 230)));
			// events.add(new Event(0, ShapeType.CIRCLE, new Position(150, 150)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(200, 320)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(480, 360)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(250, 250)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(500, 120)));
			events.add(new Event(0, ShapeType.CROSS, new Position(750, 200)));

			gameview.addRiver(new Polyline(0, 0));

			btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					ScreenShots.make(DbConnector.username + "第二阶段第五回合");
					DbConnector.update("A208", Targets.fourCircles(gameview.getLineLinks()));
					DbConnector.update("A209", Targets.squareLineNums(gameview.getLineLinks()));
					DbConnector.update("A210", Targets.triangleLineNums(gameview.getLineLinks()));
					DbConnector.update("A211", Targets.crossLineNums(gameview.getLineLinks()));
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);
			popStaticStations(type);

		} else if (type == 6) {
			// 第二阶段第六回合
			gameview.hideThings();
			String uri = Main.class.getResource("/img/0206.png").toExternalForm();

			Image image = new Image(uri);
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(960);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);

			VBox vBox = new VBox();
			vBox.prefWidthProperty().bind(Main.stage.widthProperty());

			TextField tf = new TextField();
			tf.setMaxWidth(100);

			TextField tf1 = new TextField();
			tf1.setMaxWidth(100);

			TextField tf2 = new TextField();
			tf2.setMaxWidth(100);

			TextField tf3 = new TextField();
			tf3.setMaxWidth(100);

			TextField tf4 = new TextField();
			tf4.setMaxWidth(100);

			TextField tf5 = new TextField();
			tf5.setMaxWidth(100);

			Button b = new Button("提交");
			b.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");

			b.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (tf.getText().isEmpty() == true) {
						GameView.alertError("请输入你的答案哦");
					} else {
						DbConnector.update("A212", tf.getText());
						GameView.go3rdPart();
					}
				}
			});

			b.setAlignment(Pos.CENTER);

			HBox hb = new HBox();
			hb.getChildren().addAll(tf, tf1 ,tf2,tf3,tf4,tf5);
			hb.setSpacing(10);
			hb.setAlignment(Pos.CENTER);

			VBox vb = new VBox();
			vb.getChildren().addAll(hb,b);
			vb.setSpacing(10);
			vb.setAlignment(Pos.CENTER);
			vb.setPadding(new Insets(20, 0, 0, 0));

			vBox.getChildren().addAll(imageView, vb);

			group.getChildren().add(vBox);

		} else if (type == 7) {
			// 第二阶段第七回合
			events.add(new Event(0, ShapeType.SQUARE, new Position(300, 71)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(620, 251)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(100, 171)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(360, 251)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(600, 70)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(800, 240)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(100, 390)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(170, 330)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(220, 130)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(240, 220)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(410, 172)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(510, 173)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(490, 110)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(730, 290)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(750, 90)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(750, 190)));
			events.add(new Event(0, ShapeType.DIAMOND, new Position(50, 250)));
			events.add(new Event(0, ShapeType.CROSS, new Position(650, 150)));
			events.add(new Event(0, ShapeType.STAR, new Position(860, 110)));

			gameview.addRiver(new Polyline(0, 0));

			btn = new Button("见习通过");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");

			btn.setLayoutX(800);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					ScreenShots.make(DbConnector.username + "第二阶段第七回合");
					DbConnector.update("A213", Targets.inlcudesTriangleSquare(gameview.getLineLinks()));
					DbConnector.update("A214", Targets.interchangeNums(game.getStationList()));
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);
			popStaticStations(type);

		} else if (type == 8) {
			// 第三阶段第一回合
			events.add(new Event(0, ShapeType.SQUARE, new Position(250, 251)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(340, 331)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(250, 330)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(160, 281)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(460, 252)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(340, 253)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(110, 235)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(360, 390)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(550, 430)));
			// events.add(new Event(0, ShapeType.STAR, new Position(240, 510)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(580, 300)));
			// events.add(new Event(0, ShapeType.SQUARE, new Position(110, 340)));
			events.add(new Event(0, ShapeType.STAR, new Position(210, 440)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(620, 220)));
			events.add(new Event(0, ShapeType.CROSS, new Position(770, 141)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(670, 170)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(620, 450)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(320, 100)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(210, 110)));
			events.add(new Event(0, ShapeType.DIAMOND, new Position(730, 410)));
			// events.add(new Event(0, ShapeType.DIAMOND, new Position(800, 435)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(410, 325)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(650, 350)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(760, 210)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(480, 120)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(720, 101)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(850, 90)));
			// events.add(new Event(0, ShapeType.SQUARE, new Position(140, 140)));
			// events.add(new Event(0, ShapeType.CIRCLE, new Position(200, 21)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(810, 250)));
			// events.add(new Event(0, ShapeType.CIRCLE, new Position(450, 20)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(690, 280)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(350, 150)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(410, 441)));
			// events.add(new Event(0, ShapeType.CIRCLE, new Position(530, 30)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(260, 50)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(50, 360)));
			// events.add(new Event(0, ShapeType.CIRCLE, new Position(900, 420)));

			// map
			// River :
			Polyline river = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290, 420, 290,
					460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960, 350);

			Color colorRiver = new Color((double) 200 / 255, (double) 230 / 255, (double) 250 / 255, 0.5);
			river.setStroke(colorRiver);
			river.setStrokeLineJoin(StrokeLineJoin.ROUND);
			river.setStrokeWidth(14);

			Polyline borderRiver = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290, 420,
					290, 460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960, 350);

			Color colorBorder = new Color((double) 100 / 255, (double) 180 / 255, (double) 220 / 255, 0.5);
			borderRiver.setStroke(colorBorder);
			borderRiver.setStrokeLineJoin(StrokeLineJoin.ROUND);
			borderRiver.setStrokeWidth(16);

			group.getChildren().add(borderRiver);
			group.getChildren().add(river);

			gameview.addRiver(borderRiver);

			btn = new Button("激活乘客");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");

			btn.setLayoutX(800);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					timer = new fxTimer(5);
					timer.setLayoutX(70);
					timer.setLayoutY(30);
					timer.setCallBack(() -> {
						ScreenShots.make(DbConnector.username + "第三阶段结束");
						Game.setClockPause(true);
						game.pauseGame();
						DbConnector.update("P301", gameview.getLineLinks().size() >= 6 ? 1 : 0);
						GameView.go3rdPart();
					});
					group.getChildren().add(timer);
					ScreenShots.make(DbConnector.username + "第三阶段第一回合激活时");
					ClientSchedule.showClient = true;
					game.timeGo();
					Game.setClockPause(false);
					game.resumeGame();

					group.getChildren().remove(btn);
				}
			});

			Button btn2 = new Button("选择资源");

			btn2.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");

			btn2.setLayoutX(800);
			btn2.setLayoutY(480);

			btn2.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					Platform.runLater(() -> game.setGift());
					group.getChildren().remove(btn2);
					group.getChildren().add(btn);
				}
			});

			group.getChildren().add(btn2);
			popStaticStations(type);

		} else if (type == 9) {
			// 第四阶段第一回合
			gameview.hideThings();
			String uri = Main.class.getResource("/img/round8.png").toExternalForm();

			Image image = new Image(uri);
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(960);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);

			VBox vBox = new VBox();
			vBox.prefWidthProperty().bind(Main.stage.widthProperty());

			TextField tf = new TextField();
			tf.setMaxWidth(120);
			tf.setMinHeight(36);
			Button b = new Button("提交");
			b.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");

			b.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (tf.getText().isEmpty() == true) {
						GameView.alertError("请输入你的答案哦");
					} else {
						DbConnector.update("A217", tf.getText());
						GameView.go3rdPart();
					}
				}
			});

			HBox hb = new HBox();
			hb.getChildren().addAll(tf, b);
			hb.setAlignment(Pos.CENTER);
			hb.setPadding(new Insets(10, 0, 0, 0));

			vBox.getChildren().addAll(imageView, hb);

			group.getChildren().add(vBox);

		} else if (type == 10) {
			// 第四阶段第二回合
			gameview.hideThings();
			String uri = Main.class.getResource("/img/round9.png").toExternalForm();

			Image image = new Image(uri);
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(960);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);

			VBox vBox = new VBox();
			vBox.prefWidthProperty().bind(Main.stage.widthProperty());

			TextField tf = new TextField();
			tf.setMaxWidth(120);
			tf.setMinHeight(36);
			Button b = new Button("提交");
			b.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");

			b.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (tf.getText().isEmpty() == true) {
						GameView.alertError("请输入你的答案哦");
					} else {
						DbConnector.update("A218", tf.getText());
						group.getChildren().remove(vBox);
						gameview.showThings();
						events.add(new Event(0, ShapeType.SQUARE, new Position(270, 270)));
						events.add(new Event(0, ShapeType.SQUARE, new Position(460, 250)));
						events.add(new Event(0, ShapeType.SQUARE, new Position(530, 200)));
						events.add(new Event(0, ShapeType.SQUARE, new Position(600, 130)));
						events.add(new Event(0, ShapeType.TRIANGLE, new Position(100, 150)));
						events.add(new Event(0, ShapeType.TRIANGLE, new Position(180, 250)));
						events.add(new Event(0, ShapeType.TRIANGLE, new Position(370, 430)));
						events.add(new Event(0, ShapeType.TRIANGLE, new Position(460, 170)));
						events.add(new Event(0, ShapeType.TRIANGLE, new Position(510, 360)));
						events.add(new Event(0, ShapeType.TRIANGLE, new Position(690, 250)));
						events.add(new Event(0, ShapeType.TRIANGLE, new Position(810, 200)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(210, 350)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(250, 430)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(300, 200)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(370, 350)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(390, 230)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(450, 430)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(450, 360)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(480, 70)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(550, 430)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(560, 250)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(640, 200)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(690, 430)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(720, 200)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(750, 310)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(850, 250)));
						events.add(new Event(0, ShapeType.CIRCLE, new Position(800, 430)));
						events.add(new Event(0, ShapeType.DIAMOND, new Position(180, 150)));
						events.add(new Event(0, ShapeType.CROSS, new Position(250, 160)));
						events.add(new Event(0, ShapeType.STAR, new Position(580, 340)));

						// map
						// River :
						Polyline river = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290,
								420, 290, 460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960,
								350);

						Color colorRiver = new Color((double) 200 / 255, (double) 230 / 255, (double) 250 / 255, 0.5);
						river.setStroke(colorRiver);
						river.setStrokeLineJoin(StrokeLineJoin.ROUND);
						river.setStrokeWidth(14);

						Polyline borderRiver = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290,
								290, 420, 290, 460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350,
								960, 350);

						Color colorBorder = new Color((double) 100 / 255, (double) 180 / 255, (double) 220 / 255, 0.5);
						borderRiver.setStroke(colorBorder);
						borderRiver.setStrokeLineJoin(StrokeLineJoin.ROUND);
						borderRiver.setStrokeWidth(16);

						group.getChildren().add(borderRiver);
						group.getChildren().add(river);

						gameview.addRiver(borderRiver);

						btn = new Button("激活乘客");

						btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");

						btn.setLayoutX(800);
						btn.setLayoutY(480);

						btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent event) {
								game.timeGo();
								Game.setClockPause(false);
								game.resumeGame();
								btn.setDisable(true);

								timer = new fxTimer(3);
								timer.setLayoutX(70);
								timer.setLayoutY(30);
								Controller.canDrawLine = false;
								timer.setCallBack(() -> {
									GameView.alertError("请修改线路后点击继续游戏");
									DbConnector.update("R401", Game.getTransportedClientNb());
									DbConnector.update("R402", gameview.numBoom);
									game.pauseGame();
									group.getChildren().remove(timer);
									btn.setDisable(false);
									btn.setText("继续游戏");
									ScreenShots.make(DbConnector.username + "第四阶段第二回合第一个三分钟结束时");
									btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
										@Override
										public void handle(MouseEvent event) {
											fxTimer timer1 = new fxTimer(3);
											timer1.setLayoutX(70);
											timer1.setLayoutY(30);
											group.getChildren().add(timer1);
											// Controller.canDrawLine = false;
											game.resumeGame();
											timer1.setCallBack(() -> {
												Game.setClockPause(true);
												game.pauseGame();
												DbConnector.update("R403", Game.getTransportedClientNb());
												DbConnector.update("R404", gameview.numBoom);
												ScreenShots.make(DbConnector.username + "第四阶段第二回合结束时");
												GameView.go3rdPart();
											});
											group.getChildren().remove(btn);
										}
									});
								});
								group.getChildren().add(timer);
								ScreenShots.make(DbConnector.username + "第四阶段第二回合激活时");
								ClientSchedule.showClient = true;
							}
						});

						group.getChildren().add(btn);
						Platform.runLater(() -> popStaticStations(type));
					}
				}
			});

			HBox hb = new HBox();
			hb.getChildren().addAll(tf, b);
			hb.setAlignment(Pos.CENTER);
			hb.setPadding(new Insets(10, 0, 0, 0));

			vBox.getChildren().addAll(imageView, hb);

			group.getChildren().add(vBox);

		} else if (type == 11) {
			// 第五阶段
			gameview.hideThings();
			Label l0 = new Label("1.你的年级：\n1大一 2大二 3大三 4大四 5研究生及以上");
			l0.setFont(new Font(18));
			TextField tf0 = new TextField();
			tf0.setMaxWidth(120);
			VBox hb0 = new VBox();
			hb0.getChildren().addAll(l0, tf0);
			hb0.setAlignment(Pos.CENTER);
			hb0.setPadding(new Insets(20, 0, 10, 0));

			Label l1 = new Label("2.你的性别：\n1男 2女");
			l1.setFont(new Font(18));
			TextField tf1 = new TextField();
			tf1.setMaxWidth(120);
			VBox hb1 = new VBox();
			hb1.getChildren().addAll(l1, tf1);
			hb1.setAlignment(Pos.CENTER);
			hb1.setPadding(new Insets(0, 0, 10, 0));

			Label l2 = new Label("3.你觉得这个测验有趣吗？\n1非常无趣 2有点无趣 3不确定 4比较有趣 5十分有趣");
			l2.setFont(new Font(18));
			TextField tf2 = new TextField();
			tf2.setMaxWidth(120);
			VBox hb2 = new VBox();
			hb2.getChildren().addAll(l2, tf2);
			hb2.setAlignment(Pos.CENTER);
			hb2.setPadding(new Insets(0, 0, 10, 0));

			Label l3 = new Label("4.请问你之前玩过类似的游戏吗？\n1没有玩过 2玩过1-2款 3玩过3-4款 5玩过5款及以上");
			l3.setFont(new Font(18));
			TextField tf3 = new TextField();
			tf3.setMaxWidth(120);
			VBox hb3 = new VBox();
			hb3.getChildren().addAll(l3, tf3);
			hb3.setAlignment(Pos.CENTER);
			hb3.setPadding(new Insets(0, 0, 10, 0));

			Label l4 = new Label("5.请问你的成绩年级排名是多少？\n1前10% 2前10%～20% 3前20%～50% 4前50%以上");
			l4.setFont(new Font(18));
			TextField tf4 = new TextField();
			tf4.setMaxWidth(120);
			VBox hb4 = new VBox();
			hb4.getChildren().addAll(l4, tf4);
			hb4.setAlignment(Pos.CENTER);
			hb4.setPadding(new Insets(0, 0, 10, 0));

			VBox vBox = new VBox();
			vBox.prefWidthProperty().bind(Main.stage.widthProperty());

			Button b = new Button("提交");
			b.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 1.6em;-fx-text-fill:#fff");
			b.setAlignment(Pos.CENTER);

			b.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (tf0.getText().isEmpty() == true || tf1.getText().isEmpty() == true
							|| tf2.getText().isEmpty() == true || tf3.getText().isEmpty() == true
							|| tf4.getText().isEmpty() == true) {
						GameView.alertError("请输入每一个题的答案哦");
					} else {
						String s = tf0.getText() + "," + tf1.getText() + "," + tf2.getText() + "," + tf3.getText() + ","
								+ tf4.getText();
						DbConnector.update("R501", s);
						GameView.go3rdPart();
					}
				}
			});

			vBox.getChildren().addAll(hb0, hb1, hb2, hb3, hb4, b);
			vBox.setAlignment(Pos.CENTER);

			group.getChildren().add(vBox);

		} else {
			gameview.hideThings();
			try {
				DbConnector.shutdown();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Label text = new Label("游戏结束～");
			text.setLayoutX(450);
			text.setLayoutY(200);
			group.getChildren().add(text);
		}

		if (type != 3 && type != 4 && type != 6 && type != 11) {
			Platform.runLater(() -> game.showTip(type));
		}
	}

	private void popStaticStations(int type) {
		if (type != 0 && events.size() > 0) {
			for (Event event : events) {
				Station st = new Station(event.getStationType(), event.getPosition());
				Platform.runLater(() -> game.addToView(st));
			}
		}
	}

	public Event findNext() {
		for (Event event : events) {
			if (!event.isFinished()) {
				// event.setFinished(true);
				return event;
			}
		}
		return null;
	}

	public static int getClickTimes() {
		return clickTimes;
	}

	public static void setClickTimes(int clickTimes) {
		Schedule.clickTimes = clickTimes;
	}
}
