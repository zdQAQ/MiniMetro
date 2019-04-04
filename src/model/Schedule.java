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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
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
	private int clickTimes;

	public Schedule(int type, Group g, GameView gv, Game game1) {
		events = new ArrayList<Event>();
		group = g;
		gameview = gv;
		game = game1;
		clickTimes = 0;
		if (type == 0) {
			// stations
			events.add(new Event(0, ShapeType.SQUARE, new Position(250, 250)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(340, 335)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(250, 330)));
			events.add(new Event(15000, ShapeType.CIRCLE, new Position(160, 280)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(460, 255)));
			events.add(new Event(15000, ShapeType.CIRCLE, new Position(340, 255)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(110, 235)));
			events.add(new Event(15000, ShapeType.CIRCLE, new Position(360, 390)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(550, 430)));
			events.add(new Event(15000, ShapeType.STAR, new Position(240, 480)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(580, 300)));
			events.add(new Event(15000, ShapeType.SQUARE, new Position(110, 340)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(210, 440)));
			events.add(new Event(15000, ShapeType.CIRCLE, new Position(620, 220)));
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
			events.add(new Event(25000, ShapeType.CIRCLE, new Position(200, 20)));
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
			
			Button btn = new Button("跳过该回合");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(790);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					Main.proceed(1);
				}
			});

			group.getChildren().add(btn);

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
			GameView.go2ndPart();
		} else if (type == 2) {
			// 第二阶段第一回合
			events.add(new Event(0, ShapeType.SQUARE, new Position(690, 105)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(70, 340)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(410, 100)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(800, 230)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(120, 120)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(180, 250)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(350, 255)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(480, 260)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(230, 170)));

			// map
			// River :
			// Polyline river = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290, 420, 290,
			// 		460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960, 350);

			// Color colorRiver = new Color((double) 200 / 255, (double) 230 / 255, (double) 250 / 255, 0.5);
			// river.setStroke(colorRiver);
			// river.setStrokeLineJoin(StrokeLineJoin.ROUND);
			// river.setStrokeWidth(14);

			// Polyline borderRiver = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290, 420,
			// 		290, 460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960, 350);

			// Color colorBorder = new Color((double) 100 / 255, (double) 180 / 255, (double) 220 / 255, 0.5);
			// borderRiver.setStroke(colorBorder);
			// borderRiver.setStrokeLineJoin(StrokeLineJoin.ROUND);
			// borderRiver.setStrokeWidth(16);

			// group.getChildren().add(borderRiver);
			// group.getChildren().add(river);

			 gameview.addRiver(new Polyline(0,0));

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
			
//			Button btn1 = new Button("刷新");
//
//			btn1.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");
//
//			btn1.setLayoutX(870);
//			btn1.setLayoutY(400);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					boolean res = Targets.fourCircles(gameview.getLineLinks());
					DbConnector.updateG("g2", Boolean.toString(res));
					int g5res = Targets.squareLineNums(gameview.getLineLinks());
					DbConnector.updateG("g5", Integer.toString(g5res));
					int g7res = Targets.triangleLineNums(gameview.getLineLinks());
					DbConnector.updateG("g7", Integer.toString(g7res));
					Platform.runLater(()->Main.proceed(2));
				}
			});

//			group.getChildren().add(btn1);

		} else if (type == 3) {
			// 第二阶段第一回合
			events.add(new Event(0, ShapeType.SQUARE, new Position(530, 220)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(620, 150)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(420, 90)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(480, 360)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(650, 220)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(90, 230)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(150, 150)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(200, 320)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(250, 250)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(280, 100)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(330, 350)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(380, 200)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(500, 120)));
			events.add(new Event(0, ShapeType.CROSS, new Position(750, 200)));

			// map
			// River :
			// Polyline river = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290, 420, 290,
			// 		460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960, 350);

			// Color colorRiver = new Color((double) 200 / 255, (double) 230 / 255, (double) 250 / 255, 0.5);
			// river.setStroke(colorRiver);
			// river.setStrokeLineJoin(StrokeLineJoin.ROUND);
			// river.setStrokeWidth(14);

			// Polyline borderRiver = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290, 420,
			// 		290, 460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960, 350);

			// Color colorBorder = new Color((double) 100 / 255, (double) 180 / 255, (double) 220 / 255, 0.5);
			// borderRiver.setStroke(colorBorder);
			// borderRiver.setStrokeLineJoin(StrokeLineJoin.ROUND);
			// borderRiver.setStrokeWidth(16);

			// group.getChildren().add(borderRiver);
			// group.getChildren().add(river);

			gameview.addRiver(new Polyline(0,0));

			Button btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					int g6res = Targets.squareLineNums(gameview.getLineLinks());
					DbConnector.updateG("g6", Integer.toString(g6res));
					boolean res = Targets.fourCircles(gameview.getLineLinks());
					DbConnector.updateG("g3", Boolean.toString(res));
					int g8res = Targets.triangleLineNums(gameview.getLineLinks());
					DbConnector.updateG("g8", Integer.toString(g8res));
					int g9res = Targets.crossLineNums(gameview.getLineLinks());
					DbConnector.updateG("g9", Integer.toString(g9res));
					int g10res = 0;
					for (Station station : game.getStationList()) {
			            if (station.getType().equals(ShapeType.SQUARE)) {
			                if(station.getLinks().size()>1) {
			                	g10res++;
			                }
			            }
			            if (station.getType().equals(ShapeType.CROSS)) {
			                if(station.getLinks().size()>1) {
			                	g10res++;
			                }
			            }
			        }
					DbConnector.updateG("g10", Integer.toString(g10res));
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);
			
//			Button btn1 = new Button("刷新");
//
//			btn1.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");
//
//			btn1.setLayoutX(870);
//			btn1.setLayoutY(400);
//
//			btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//				@Override
//				public void handle(MouseEvent event) {
//					Platform.runLater(()->Main.proceed(3));
//				}
//			});
//
//			group.getChildren().add(btn1);

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
			// Polyline river = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290, 420, 290,
			// 		460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960, 350);

			// Color colorRiver = new Color((double) 200 / 255, (double) 230 / 255, (double) 250 / 255, 0.5);
			// river.setStroke(colorRiver);
			// river.setStrokeLineJoin(StrokeLineJoin.ROUND);
			// river.setStrokeWidth(14);

			// Polyline borderRiver = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290, 420,
			// 		290, 460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960, 350);

			// Color colorBorder = new Color((double) 100 / 255, (double) 180 / 255, (double) 220 / 255, 0.5);
			// borderRiver.setStroke(colorBorder);
			// borderRiver.setStrokeLineJoin(StrokeLineJoin.ROUND);
			// borderRiver.setStrokeWidth(16);

			// group.getChildren().add(borderRiver);
			// group.getChildren().add(river);

			gameview.addRiver(new Polyline(0,0));

			Button btn = new Button("提交");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(870);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					boolean res = Targets.fourCircles(gameview.getLineLinks());
					DbConnector.updateG("g4", Boolean.toString(res));
					int g11res = 0;
					for (Station station : game.getStationList()) {
			            if (station.getType().equals(ShapeType.SQUARE)) {
			                if(station.getLinks().size()>1) {
			                	g11res++;
			                }
			            }
			            if (station.getType().equals(ShapeType.CROSS)) {
			                if(station.getLinks().size()>1) {
			                	g11res++;
			                }
			            }
			            if (station.getType().equals(ShapeType.DIAMOND)) {
			                if(station.getLinks().size()>1) {
			                	g11res++;
			                }
			            }
			        }
					DbConnector.updateG("g11", Integer.toString(g11res));
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);
			
//			Button btn1 = new Button("刷新");
//
//			btn1.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");
//
//			btn1.setLayoutX(870);
//			btn1.setLayoutY(400);
//
//			btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//				@Override
//				public void handle(MouseEvent event) {
//					Platform.runLater(()->Main.proceed(4));
//				}
//			});
//
//			group.getChildren().add(btn1);

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
			// Polyline river = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290, 420, 290,
			// 		460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960, 350);

			// Color colorRiver = new Color((double) 200 / 255, (double) 230 / 255, (double) 250 / 255, 0.5);
			// river.setStroke(colorRiver);
			// river.setStrokeLineJoin(StrokeLineJoin.ROUND);
			// river.setStrokeWidth(14);

			// Polyline borderRiver = new Polyline(0, 340, 20, 310, 20, 470, 170, 470, 170, 370, 290, 370, 290, 290, 420,
			// 		290, 460, 320, 500, 290, 550, 290, 550, 370, 610, 370, 640, 290, 700, 350, 960, 350);

			// Color colorBorder = new Color((double) 100 / 255, (double) 180 / 255, (double) 220 / 255, 0.5);
			// borderRiver.setStroke(colorBorder);
			// borderRiver.setStrokeLineJoin(StrokeLineJoin.ROUND);
			// borderRiver.setStrokeWidth(16);

			// group.getChildren().add(borderRiver);
			// group.getChildren().add(river);

			gameview.addRiver(new Polyline(0,0));

			Button btn = new Button("见习通过");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(800);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					GameView.go3rdPart();
				}
			});

			group.getChildren().add(btn);
			
//			Button btn1 = new Button("刷新");
//
//			btn1.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");
//
//			btn1.setLayoutX(800);
//			btn1.setLayoutY(400);
//
//			btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//				@Override
//				public void handle(MouseEvent event) {
//					Platform.runLater(()->Main.proceed(5));
//				}
//			});
//
//			group.getChildren().add(btn1);
		} else if (type == 6) {
			// 第二阶段第一回合
			events.add(new Event(0, ShapeType.SQUARE, new Position(250, 250)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(340, 330)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(250, 330)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(160, 280)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(460, 250)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(340, 250)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(110, 235)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(360, 390)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(550, 430)));
			events.add(new Event(0, ShapeType.STAR, new Position(240, 510)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(580, 300)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(110, 340)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(210, 440)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(620, 220)));
			events.add(new Event(0, ShapeType.CROSS, new Position(770, 140)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(670, 170)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(620, 450)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(320, 100)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(210, 110)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(730, 410)));
			events.add(new Event(0, ShapeType.DIAMOND, new Position(800, 435)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(410, 325)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(650, 350)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(760, 210)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(480, 120)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(720, 100)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(850, 90)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(140, 140)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(200, 20)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(810, 250)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(450, 20)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(690, 280)));
			events.add(new Event(0, ShapeType.SQUARE, new Position(350, 150)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(410, 440)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(530, 30)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(260, 50)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(50, 360)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(900, 420)));

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

			Button btn = new Button("激活乘客");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(800);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					clickTimes++;
					if (clickTimes == 1) {
						ClientSchedule.showClient = true;
						game.resumeGame();
						btn.setText("提交");
					} else {
						GameView.go3rdPart();
					}
				}
			});
			group.getChildren().add(btn);
			
//			Button btn1 = new Button("刷新");
//
//			btn1.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");
//
//			btn1.setLayoutX(800);
//			btn1.setLayoutY(420);
//
//			btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//				@Override
//				public void handle(MouseEvent event) {
//					Platform.runLater(()->Main.proceed(6));
//				}
//			});
//
//			group.getChildren().add(btn1);

			Button btn2 = new Button("选择资源");

			btn2.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn2.setLayoutX(800);
			btn2.setLayoutY(420);

			btn2.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					Platform.runLater(()->game.setGift());
					group.getChildren().remove(btn2);
				}
			});

			group.getChildren().add(btn2);

		} else if (type == 7) {
			// 第二阶段第一回合
			events.add(new Event(0, ShapeType.SQUARE, new Position(480, 370)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(430, 180)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(690, 200)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(410, 270)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(530, 250)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(760, 160)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(210, 270)));
			events.add(new Event(0, ShapeType.TRIANGLE, new Position(620, 415)));
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
			events.add(new Event(0, ShapeType.CIRCLE, new Position(360, 90)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(140, 40)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(140, 140)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(550, 80)));
			events.add(new Event(0, ShapeType.CROSS, new Position(110, 360)));
			events.add(new Event(0, ShapeType.CIRCLE, new Position(430, 60)));
			events.add(new Event(0, ShapeType.STAR, new Position(670, 320)));
			events.add(new Event(0, ShapeType.DIAMOND, new Position(320, 140)));

			// map
			// River :
//			Polyline river = new Polyline(110, 0, 110, 190, 20, 470, 360, 190, 360, 350, 540, 350, 630, 240, 630, 350,
//					720, 430, 960, 430);
//
//			Color colorRiver = new Color((double) 200 / 255, (double) 230 / 255, (double) 250 / 255, 0.5);
//			river.setStroke(colorRiver);
//			river.setStrokeLineJoin(StrokeLineJoin.ROUND);
//			river.setStrokeWidth(14);

			Polygon borderRiver = new Polygon(0,0,
					140,0,
					140,4,
					90,4,
					90,160,
					120,160,
					120,300,
					10,300,
					10,540,
					0,540);
			
			Polygon r1 = new Polygon(10,400,240,400,240,540,10,540);
			Polygon r2 = new Polygon(560,0,540,15,565,40,565,80,590,80,590,10,960,10,960,0);
			Polygon r3 = new Polygon(960,80,860,80,860,140,940,140,940,540,960,540);
			Polygon r4 = new Polygon(240,440,240,470,320,400,320,370);
			Polygon r5 = new Polygon(320,370,320,400,400,540,450,540);
			Polygon r6 = new Polygon(320,370,
					360,300,
					490,300,
					490,540,
					520,540,
					520,330,
					360,330,
					320,400);
			Polygon r7 = new Polygon(120,280,
					240,400,
					210,400,
					120,300);
			Polygon r8 = new Polygon(210,400,
					240,400,
					250,380,
					320,380,
					320,360,
					250,360,
					210,400);
			

			Color colorBorder = new Color((double) 100 / 255, (double) 180 / 255, (double) 220 / 255, 0.5);
//			borderRiver.setStroke(colorBorder);
//			borderRiver.setStrokeLineJoin(StrokeLineJoin.ROUND);
			
			borderRiver.setFill(colorBorder);
			r1.setFill(colorBorder);
			r2.setFill(colorBorder);
			r3.setFill(colorBorder);
			r4.setFill(colorBorder);
			r5.setFill(colorBorder);
			r6.setFill(colorBorder);
			r7.setFill(colorBorder);
			r8.setFill(colorBorder);
			
			borderRiver.setStrokeLineJoin(StrokeLineJoin.ROUND);
			r1.setStrokeLineJoin(StrokeLineJoin.ROUND);
			r2.setStrokeLineJoin(StrokeLineJoin.ROUND);
			r3.setStrokeLineJoin(StrokeLineJoin.ROUND);

			group.getChildren().add(borderRiver);
			group.getChildren().add(r1);
			group.getChildren().add(r2);
			group.getChildren().add(r3);
			group.getChildren().add(r4);
			group.getChildren().add(r5);
			group.getChildren().add(r6);
			group.getChildren().add(r7);
			group.getChildren().add(r8);
			
			Shape all = Shape.union(borderRiver,r1);
			all = Shape.union(all,r2);
			all = Shape.union(all,r3);
			all = Shape.union(all,r4);
			all = Shape.union(all,r5);
			all = Shape.union(all,r6);
			all = Shape.union(all,r7);
			all = Shape.union(all,r8);
			gameview.addRiver(all);

			Button btn = new Button("激活乘客");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(800);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					clickTimes++;
					if (clickTimes == 1) {
						ClientSchedule.showClient = true;
						game.resumeGame();
						btn.setText("提交");
					} else {
						GameView.go3rdPart();
					}
				}
			});

			group.getChildren().add(btn);
			
//			Button btn1 = new Button("刷新");
//
//			btn1.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");
//
//			btn1.setLayoutX(800);
//			btn1.setLayoutY(420);
//
//			btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//				@Override
//				public void handle(MouseEvent event) {
//					Platform.runLater(()->Main.proceed(7));
//				}
//			});
//
//			group.getChildren().add(btn1);

			Button btn2 = new Button("选择资源");

			btn2.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn2.setLayoutX(800);
			btn2.setLayoutY(420);

			btn2.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					Platform.runLater(()->game.setGift());
					group.getChildren().remove(btn2);
				}
			});

			group.getChildren().add(btn2);

		} else if (type == 8) {
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

			Button btn = new Button("激活乘客");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(800);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					clickTimes++;
					if (clickTimes == 1) {
						ClientSchedule.showClient = true;
						game.resumeGame();
						btn.setText("提交");
					} else {
						GameView.go3rdPart();
					}
				}
			});

			group.getChildren().add(btn);
			
//			Button btn1 = new Button("刷新");
//
//			btn1.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");
//
//			btn1.setLayoutX(800);
//			btn1.setLayoutY(400);
//
//			btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//				@Override
//				public void handle(MouseEvent event) {
//					Platform.runLater(()->Main.proceed(8));
//				}
//			});
//
//			group.getChildren().add(btn1);

		} else if (type == 9) {
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

			Button btn = new Button("激活乘客");

			btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");

			btn.setLayoutX(800);
			btn.setLayoutY(480);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					clickTimes++;
					if (clickTimes == 1) {
						ClientSchedule.showClient = true;
						game.resumeGame();
						btn.setText("提交");
					} else {
						GameView.go3rdPart();
					}
				}
			});

			group.getChildren().add(btn);
			
//			Button btn1 = new Button("刷新");
//
//			btn1.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");
//
//			btn1.setLayoutX(800);
//			btn1.setLayoutY(400);
//
//			btn1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//				@Override
//				public void handle(MouseEvent event) {
//					Platform.runLater(()->Main.proceed(9));
//				}
//			});
//
//			group.getChildren().add(btn1);

		} else {
			gameview.hideThings();
			Label text = new Label("游戏结束～");
			text.setLayoutX(450);
			text.setLayoutY(200);
			group.getChildren().add(text);
		}
		
		if (type != 1 && type != 8 && type !=9) {
			Platform.runLater(()->game.showTip(type));
		}
	}

	public class JavaApp {

		public void submitAnswer(int answer) {
			System.out.println("answer is" + answer);
			Platform.runLater(()->Main.proceed(2));
			boolean yes = answer == 23;
			DbConnector.updateG("g1", Boolean.toString(yes));
		}
	}

	public Event findNext() {
		for (Event event : events) {
			if (!event.isFinished()) {
//				event.setFinished(true);
				return event;
			}
		}
		return null;
	}
}
