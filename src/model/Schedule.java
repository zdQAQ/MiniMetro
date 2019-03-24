package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.Controller;
import javafx.GameView;
import javafx.Main;
import javafx.Main.JavaApp;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.WindowEvent;
import netscape.javascript.JSObject;

public class Schedule {
	public static List<Event> events = new ArrayList<Event>();
	static Group group;
	static GameView gameview;
	static Game game;

	public Schedule(int type, Group g, GameView gv, Game game1) {
		events = new ArrayList<Event>();
		group = g;
		gameview = gv;
		game = game1;
		if (type == 0) {
			// stations
			events.add(new Event(0, ShapeType.SQUARE, new Position(250, 270)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(340, 320)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(250, 350)));
			events.add(new Event(15000, ShapeType.CIRCLE, new Position(160, 270)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(460, 250)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(340, 240)));
			events.add(new Event(15000, ShapeType.CIRCLE, new Position(110, 235)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(360, 390)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(550, 430)));
			events.add(new Event(15000, ShapeType.STAR, new Position(240, 530)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(570, 300)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(550, 420)));
			events.add(new Event(15000, ShapeType.SQUARE, new Position(110, 340)));
			events.add(new Event(15000, ShapeType.CIRCLE, new Position(210, 440)));
			events.add(new Event(15000, ShapeType.CIRCLE, new Position(620, 220)));
			events.add(new Event(15000, ShapeType.CROSS, new Position(780, 140)));
			events.add(new Event(20000, ShapeType.CIRCLE, new Position(670, 170)));
			events.add(new Event(20000, ShapeType.CIRCLE, new Position(620, 450)));
			events.add(new Event(20000, ShapeType.CIRCLE, new Position(320, 100)));
			events.add(new Event(20000, ShapeType.TRIANGLE, new Position(210, 110)));
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(730, 410)));
			events.add(new Event(25000, ShapeType.DIAMOND, new Position(800, 430)));
			events.add(new Event(25000, ShapeType.TRIANGLE, new Position(410, 320)));
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(640, 350)));
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(760, 210)));
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(760, 20)));
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(720, 100)));
			events.add(new Event(30000, ShapeType.SQUARE, new Position(850, 90)));
			events.add(new Event(30000, ShapeType.CIRCLE, new Position(760, 210)));
			events.add(new Event(30000, ShapeType.SQUARE, new Position(140, 140)));
			events.add(new Event(30000, ShapeType.CIRCLE, new Position(200, 20)));
			events.add(new Event(30000, ShapeType.TRIANGLE, new Position(810, 250)));
			events.add(new Event(30000, ShapeType.CIRCLE, new Position(650, 20)));
			events.add(new Event(30000, ShapeType.TRIANGLE, new Position(810, 250)));
			events.add(new Event(30000, ShapeType.CIRCLE, new Position(650, 20)));
			events.add(new Event(30000, ShapeType.DIAMOND, new Position(620, 440)));
			events.add(new Event(35000, ShapeType.CIRCLE, new Position(690, 280)));
			events.add(new Event(35000, ShapeType.SQUARE, new Position(300, 260)));
			events.add(new Event(35000, ShapeType.TRIANGLE, new Position(370, 130)));
			events.add(new Event(35000, ShapeType.TRIANGLE, new Position(410, 440)));
			events.add(new Event(35000, ShapeType.CIRCLE, new Position(530, 30)));
			events.add(new Event(35000, ShapeType.SQUARE, new Position(390, 210)));
			events.add(new Event(35000, ShapeType.CIRCLE, new Position(260, 50)));
			events.add(new Event(35000, ShapeType.TRIANGLE, new Position(40, 350)));
			events.add(new Event(35000, ShapeType.CIRCLE, new Position(900, 420)));
			events.add(new Event(35000, ShapeType.CIRCLE, new Position(320, 440)));
			events.add(new Event(35000, ShapeType.SQUARE, new Position(160, 340)));
			events.add(new Event(35000, ShapeType.SQUARE, new Position(410, 210)));
			events.add(new Event(35000, ShapeType.CIRCLE, new Position(510, 340)));
			events.add(new Event(40000, ShapeType.TRIANGLE, new Position(40, 150)));
			events.add(new Event(40000, ShapeType.SQUARE, new Position(210, 200)));
			events.add(new Event(40000, ShapeType.CIRCLE, new Position(60, 270)));
			events.add(new Event(40000, ShapeType.SQUARE, new Position(850, 90)));
			events.add(new Event(40000, ShapeType.TRIANGLE, new Position(810, 250)));
			events.add(new Event(40000, ShapeType.CIRCLE, new Position(900, 420)));
			events.add(new Event(40000, ShapeType.CIRCLE, new Position(430, 160)));
			events.add(new Event(40000, ShapeType.TRIANGLE, new Position(40, 150)));
			events.add(new Event(40000, ShapeType.CIRCLE, new Position(260, 50)));
			events.add(new Event(40000, ShapeType.CIRCLE, new Position(760, 300)));
			events.add(new Event(40000, ShapeType.CIRCLE, new Position(480, 490)));

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

		} else if (type == 1) {
			WebView webView = new WebView();

			String url = Main.class.getResource("/web/firstRound.html").toExternalForm();

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
			vBox.prefWidthProperty().bind(Main.stage.widthProperty());

			group.getChildren().add(vBox);
		} else if (type == 2) {
			// 第二阶段第一回合
			events.add(new Event(0, ShapeType.SQUARE, new Position(690, 80)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(70, 320)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(410, 80)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(800, 210)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(120, 100)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(180, 230)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(350, 230)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(480, 230)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(230, 150)));

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

			Button btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);

		} else if (type == 3) {
			// 第二阶段第一回合
			events.add(new Event(0, ShapeType.SQUARE, new Position(650, 200)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(340, 80)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(360, 270)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(730, 300)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(90, 270)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(110, 140)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(140, 250)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(220, 110)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(300, 160)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(390, 130)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(500, 110)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(500, 230)));
			events.add(new Event(0, ShapeType.CROSS, new Position(800, 160)));

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

			Button btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);

		} else if (type == 4) {
			// 第二阶段第一回合
			events.add(new Event(0, ShapeType.SQUARE, new Position(140, 250)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(620, 210)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(270, 100)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(440, 250)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(690, 90)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(710, 250)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(780, 200)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(250, 280)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(440, 90)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(800, 90)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(810, 280)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(880, 250)));
			events.add(new Event(0, ShapeType.DIAMOND, new Position(370, 210)));
			events.add(new Event(0, ShapeType.CROSS, new Position(570, 130)));

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

			Button btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);

		} else if (type == 5) {
			// 第二阶段第一回合
			events.add(new Event(0, ShapeType.SQUARE, new Position(300, 70)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(620, 250)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(100, 170)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(360, 250)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(600, 70)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(800, 240)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(100, 390)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(170, 330)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(220, 130)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(240, 220)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(410, 170)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(510, 170)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(490, 110)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(730, 290)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(750, 90)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(750, 190)));
			events.add(new Event(0, ShapeType.DIAMOND, new Position(50, 250)));
			events.add(new Event(0, ShapeType.CROSS, new Position(650, 150)));
			events.add(new Event(0, ShapeType.SECTOR, new Position(860, 110)));

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

			Button btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);
		} else if (type == 6) {
			// 第二阶段第一回合
			events.add(new Event(0,ShapeType.SQUARE,new Position(250,270)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(340,320)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(250,350)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(160,270)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(460,250)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(340,240)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(110,235)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(360,390)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(550,430)));
			events.add(new Event(0,ShapeType.STAR,new Position(240,530)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(570,300)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(210,440)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(620,220)));
			events.add(new Event(0,ShapeType.CROSS,new Position(780,140)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(670,170)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(320,100)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(210,110)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(730,410)));
			events.add(new Event(0,ShapeType.DIAMOND,new Position(800,430)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(410,320)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(640,350)));			
			events.add(new Event(0,ShapeType.CIRCLE,new Position(720,100)));
			events.add(new Event(0,ShapeType.SQUARE,new Position(850,90)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(760,210)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(200,20)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(810,250)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(650,20)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(810,250)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(650,20)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(690,280)));
			events.add(new Event(0,ShapeType.SQUARE,new Position(300,260)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(370,130)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(410,440)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(530,30)));
			events.add(new Event(0,ShapeType.SQUARE,new Position(390,210)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(260,50)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(40,350)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(900,420)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(320,440)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(510,340)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(40,150)));
			events.add(new Event(0,ShapeType.SQUARE,new Position(210,200)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(60,270)));
			events.add(new Event(0,ShapeType.SQUARE,new Position(850,90)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(810,250)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(900,420)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(430,160)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(40,150)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(260,50)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(760,300)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(480,490)));

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

			Button btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);

		} else if (type == 7) {
			// 第二阶段第一回合
			events.add(new Event(0, ShapeType.SQUARE, new Position(480, 370)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(430, 180)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(690, 200)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(410, 270)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(530, 250)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(760, 160)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(210, 270)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(610, 390)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(810, 230)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(810, 300)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(500, 200)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(600, 200)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(270, 270)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(250, 80)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(550, 390)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(440, 390)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(740, 270)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(690, 80)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(140, 360)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(370, 39)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(780, 390)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(500, 30)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(760, 60)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(550, 150)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(320, 440)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(250, 160)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(270, 390)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(740, 360)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(320, 220)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(640, 360)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(360, 90)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(140, 40)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(140, 140)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(550, 80)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(430, 60)));
			events.add(new Event(0, ShapeType.CROSS, new Position(250, 320)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(430, 60)));
			events.add(new Event(0, ShapeType.STAR, new Position(670, 320)));
			events.add(new Event(0, ShapeType.DIAMOND, new Position(320, 140)));

			// map
			// River :
			Polyline river = new Polyline(110, 0, 110, 190, 20, 470, 360, 190, 360, 350, 540, 350, 630, 240, 630, 350,
					720, 430, 960, 430);

			Color colorRiver = new Color((double) 200 / 255, (double) 230 / 255, (double) 250 / 255, 0.5);
			river.setStroke(colorRiver);
			river.setStrokeLineJoin(StrokeLineJoin.ROUND);
			river.setStrokeWidth(14);

			Polyline borderRiver = new Polyline(110, 0, 110, 240, 360, 350, 630, 350, 720, 430, 960, 430);

			Color colorBorder = new Color((double) 100 / 255, (double) 180 / 255, (double) 220 / 255, 0.5);
			borderRiver.setStroke(colorBorder);
			borderRiver.setStrokeLineJoin(StrokeLineJoin.ROUND);
			borderRiver.setStrokeWidth(16);

			group.getChildren().add(borderRiver);
			group.getChildren().add(river);

			gameview.addRiver(borderRiver);

			Button btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);

		} else if (type == 8) {
			// 第二阶段第一回合
			events.add(new Event(0, ShapeType.SQUARE, new Position(700, 170)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(230, 220)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(140, 260)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(330, 90)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(360, 410)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(460, 90)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(580, 320)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(800, 170)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(200, 180)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(230, 340)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(370, 320)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(430, 240)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(460, 370)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(570, 430)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(580, 80)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(580, 220)));
			events.add(new Event(0, ShapeType.STAR, new Position(410, 160)));

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

			Button btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);

		} else if (type == 9) {
			// 第二阶段第一回合
			events.add(new Event(0,ShapeType.SQUARE,new Position(530,170)));
			events.add(new Event(0,ShapeType.SQUARE,new Position(750,400)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(230,170)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(480,110)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(510,360)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(650,200)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(680,400)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(810,200)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(340,130)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(350,350)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(410,210)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(550,230)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(620,100)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(690,250)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(750,50)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(750,150)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(810,270)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(460,400)));
			events.add(new Event(0,ShapeType.DIAMOND,new Position(180,280)));
			events.add(new Event(0,ShapeType.CROSS,new Position(810,100)));

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

			Button btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);

		} else if (type == 10) {
			// 第二阶段第一回合
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

			Button btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);

		} else {
			gameview.hideThings();
			Label text = new Label("游戏结束～");
			text.setLayoutX(450);
			text.setLayoutY(200);
			group.getChildren().add(text);
		}
	}

	public class JavaApp {

		public void submitAnswer(int answer) {
			System.out.println("answer is" + answer);
			Main.proceed(2);
		}
	}

	public Event findNext() {
		for (Event event : events) {
			if (!event.isFinished()) {
				event.setFinished(true);
				return event;
			}
		}
		return null;
	}
}
