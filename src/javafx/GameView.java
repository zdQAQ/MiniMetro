package javafx;

import javafx.Main.JavaApp;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.*;
import model.Line;
import netscape.javascript.JSObject;
import utils.ScreenShots;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.defaultShapes.getSquare;

/**
 * Created by KadirF on 20/12/2016.
 */
public class GameView {

    HashMap<Station, fxStation> stations;
    private HashMap<Train, fxTrain> trains;
    static HashMap<Client, fxClient> clients;
    private HashMap<model.Line, ArrayList<Shape>> lineLinks;
    HashMap<model.Line, Shape[]> lineEnds;
    List<Shape> links = new ArrayList<>();
    Shape river;
    private Group group;
    private Controller controller;
    private fxClock clock;
    private fxTimer timer;
    private static fxInformations info;
    private Circle point;
    private ImageView imageClient;
    private ImageView imgBook;
    private ImageView imgBoom;
    private Text nbClient;
    private Text nbBoom;
    public static int round;
    private static int boomTimes = 0;
    private int pauseTimes = 0;
    private int numBoom = 0;
    private int clickBook = 0;
    static Stage round9;
    static Stage round10;

    public GameView(Group g, Controller c,int round1) {
        stations = new HashMap<>();
        setTrains(new HashMap<>());
        setLineLinks(new HashMap<>());
        lineEnds = new HashMap<>();
        setClients(new HashMap<>());
        group = g;
        controller = c;
        setPauseTimes(0);
        numBoom = 0;
        setClickBook(0);
        round = round1;

        info = c.getInfo();
        info.setTranslateY(-50);
        info.setVisible(true);

        group.getChildren().add(info);

        setImgBook(new ImageView(
                new Image(this.getClass().getResource("/img/book.png").toString(), 40, 40, false, false)));
        getImgBook().setX(20);
        getImgBook().setY(20);

        getImgBook().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setClickBook(getClickBook() + 1);
                showBook();
            }
        });

        imageClient = new ImageView(
                new Image(this.getClass().getResource("/img/man.png").toString(), 30, 30, false, false));
        imageClient.setX(720);
        imageClient.setY(18);
        nbClient = new Text(760, 43, "0");
        nbClient.setFill(Color.CHOCOLATE);
        nbClient.setFont(Font.font(null, FontWeight.BOLD, 25));

        imgBoom = new ImageView(
                new Image(this.getClass().getResource("/img/boom.jpg").toString(), 40, 40, false, false));
        imgBoom.setX(640);
        imgBoom.setY(13);
        nbBoom = new Text(680, 43, "0");
        nbBoom.setFill(Color.CHOCOLATE);
        nbBoom.setFont(Font.font(null, FontWeight.BOLD, 25));

        group.getChildren().add(imgBook);

        if (round == 0) {
        	timer = new fxTimer();
            timer.setLayoutX(70);
            timer.setLayoutY(30);
            group.getChildren().add(timer);
        }

        // point = new Circle(480, 520, 4);
        // point.setStroke(Color.GRAY);
        // point.setOnMouseEntered(new EventHandler<MouseEvent>() {
        // @Override
        // public void handle(MouseEvent event) {
        // seeInfo();
        // }
        // });
        // group.getChildren().add(point);

        clock = new fxClock(920, 35, 16);
        if(round == 0 || round >5) {
        	group.getChildren().add(clock);
        	group.getChildren().add(imgBoom);
            group.getChildren().add(nbBoom);
            group.getChildren().add(nbClient);
            group.getChildren().add(imageClient);
        }
        Image imagePause = new Image(this.getClass().getResource("/img/pause.png").toString(), 40, 40, false, false);
        Image imagePlay = new Image(this.getClass().getResource("/img/play.png").toString(), 40, 40, false, false);
        ImageView image = new ImageView(imagePause);
//        if (round == 6||round ==7 ||round == 8|| round ==9) {
//        	image.setImage(imagePlay);
//        }
        image.setVisible(false);
        image.setX(920);
        image.setY(65);

        image.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	if (round == 6 || round == 7) {
            		if (Schedule.getClickTimes() >= 1) {
            			if (image.getImage() == imagePlay) {
                            image.setImage(imagePause);
                            Controller.game.resumeGame();
                            if(getPauseTimes()==1){
                                Schedule.btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");
                                Schedule.btn.setDisable(false);
                            }
                        } else if (getPauseTimes() < 1) {
                            image.setImage(imagePlay);
                            Controller.game.pauseGame();
                            setPauseTimes(getPauseTimes()+1);
                        } else {
                            alertError("当前回合仅可暂停一次哦");
                        }
            		}
                } else if (round == 8 || round == 9) {
                	if (Schedule.getClickTimes() >= 1) {
            			if (image.getImage() == imagePlay) {
                            image.setImage(imagePause);
                            Controller.game.resumeGame();
                            if(getPauseTimes()==2){
                                Schedule.btn.setStyle("-fx-background-color: #4eb5f1;-fx-font-size: 2em;-fx-text-fill:#fff");
                                Schedule.btn.setDisable(false);
                            }
                        } else if (getPauseTimes() < 2) {
                            if (round == 8 && getPauseTimes() == 1){
                                ScreenShots.make(DbConnector.username + "第四阶段第一回合第二次暂停时");
                            } else if (round == 9 && getPauseTimes() == 1){
                                ScreenShots.make(DbConnector.username + "第四阶段第二回合第二次暂停时");
                            }
                            image.setImage(imagePlay);
                            Controller.game.pauseGame();
                            setPauseTimes(getPauseTimes()+1);
                        } else {
                            alertError("当前回合仅可暂停两次哦");
                        }
            		}
                } else {
                	if (image.getImage() == imagePlay) {
                        image.setImage(imagePause);
                        Controller.game.resumeGame();
                    } else {
	                    image.setImage(imagePlay);
	                    Controller.game.pauseGame();
                    }
                }
            }
        });
        
        group.getChildren().add(image);

        clock.getClockBorder().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!image.isVisible()) {
                    image.setVisible(true);
                    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), image);
                    translateTransition.setFromX(0);
                    translateTransition.setToX(-20);
                    translateTransition.setCycleCount(1);
                    translateTransition.setAutoReverse(true);
                    translateTransition.play();
                    Timer t = new Timer();
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000),
                                    image);
                            translateTransition.setFromX(-20);
                            translateTransition.setToX(0);
                            translateTransition.setCycleCount(1);
                            translateTransition.setAutoReverse(true);
                            translateTransition.play();
                            translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent e) {
                                    image.setVisible(false);
                                }
                            });

                        }
                    }, 8000);
                }
            }
        });
    }

    public void hideThings() {
        group.getChildren().remove(clock);
        group.getChildren().remove(nbClient);
        group.getChildren().remove(imageClient);
        group.getChildren().remove(point);
        group.getChildren().remove(imgBoom);
        group.getChildren().remove(nbBoom);
        group.getChildren().remove(imgBook);
        group.getChildren().remove(info);
    }

    public void showThings() {
        group.getChildren().add(clock);
        group.getChildren().add(nbClient);
        group.getChildren().add(imageClient);
        group.getChildren().add(point);
    }

    public void startIncreaseArc(Station st) {
        Arc arcTimer = stations.get(st).arcTimer;
        arcTimer.setType(ArcType.ROUND);
        arcTimer.setFill(Color.web("#a39c9c", 0.8));
        arcTimer.setStrokeWidth(0);

        Double remainingTime = (20000 * (360 - arcTimer.lengthProperty().get())) / 360;

        stations.get(st).arcTimeline.stop();
        stations.get(st).arcTimeline.getKeyFrames().clear();

        KeyValue kv = new KeyValue(arcTimer.lengthProperty(), 360);
        KeyFrame kf = new KeyFrame(Duration.millis(remainingTime), kv);// 20s

        stations.get(st).arcTimeline.getKeyFrames().add(kf);
        stations.get(st).arcTimeline.play();

        stations.get(st).arcTimeline.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                if (arcTimer.lengthProperty().get() == 360) {
                    if (round == 0 || round == 6 || round == 7 || round == 8 || round == 9) {
                        // System.out.println("before :" + Game.transportedClientNb);
                        // if (boomTimes < 5) {
                            Game.transportedClientNb = (int) (Game.transportedClientNb * 0.95);
                        // } else {
                        //     Game.transportedClientNb = (int) (Game.transportedClientNb * 0.9);
                        // }
                        // System.out.println("after :" + Game.transportedClientNb);
                        startDecreaseArc(st);
                        removeClient(st.getClientList());
                        st.getClientList().clear();
                        updateNbClient();
                        updateNbBoom();
                    }
                    // endOfGame();

                }

            }
        });
    }

    public void startDecreaseArc(Station st) {
        Arc arcTimer = stations.get(st).arcTimer;
        arcTimer.setStrokeWidth(5);
        arcTimer.setFill(null);
        arcTimer.setStroke(Color.web("#a39c9c", 0.8));
        arcTimer.setType(ArcType.OPEN);

        Duration passedTime = stations.get(st).arcTimeline.getCurrentTime();

        stations.get(st).arcTimeline.stop();
        stations.get(st).arcTimeline.getKeyFrames().clear();

        Double remainingTime = (20000 * arcTimer.lengthProperty().get()) / 360;

        KeyValue kv = new KeyValue(arcTimer.lengthProperty(), 0);
        KeyFrame kf = new KeyFrame(Duration.millis(remainingTime), kv);
        stations.get(st).arcTimeline.getKeyFrames().add(kf);

        stations.get(st).arcTimeline.playFromStart();
    }

    public void setRound(int r) {
        round = r;
        Game.transportedClientNb = 0;
        updateNbClient();
    }

    public int getRound() {
        return round;
    }

    public void pauseArc() {
        for (fxStation fxt : stations.values()) {
            fxt.arcTimeline.pause();
        }
    }

    public void resumeArc() {
        for (fxStation fxt : stations.values()) {
            fxt.arcTimeline.play();
        }
    }

    public void updateClock(int hour, String dayName) {
        clock.moveNeedle(hour);
        clock.setDay(dayName);
    }

    public void showBook() {
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    Controller.game.pauseGame();
                    Stage dialog = new Stage();
                    WebView webView = new WebView();

                    String url = Main.class.getResource("/web/book" + round + ".html").toExternalForm();

                    WebEngine webEngine = webView.getEngine();

                    // process page loading
                    // webEngine.getLoadWorker().stateProperty().addListener(new
                    // ChangeListener<State>() {
                    // @Override
                    // public void changed(ObservableValue<? extends State> ov, State oldState,
                    // State newState) {
                    // if (newState == State.SUCCEEDED) {
                    // JSObject win = (JSObject) webEngine.executeScript("window");
                    // win.setMember("app", new Main().new JavaApp());
                    // }
                    // }
                    // });

                    webEngine.load(url);

                    VBox vBox = new VBox(webView);

                    Scene scene = new Scene(vBox, 540, 270);
                    dialog.setScene(scene);
                    dialog.show();

                    dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            Controller.game.resumeGame();
                        }
                    });

                } catch (Exception e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }

            }

        });
    }

    public static void go2ndPart() {
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    // Controller.game.pauseGame();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("恭喜你完成第一阶段的见习~");
                    alert.setHeaderText("为了检验你的见习成果，在第2阶段你需要完成5个小测试");
                    alert.setContentText(
                            "注意：在该阶段每次点击提交后你便会直接进入下一题，之后将没有再次检查和修改的机会，请慎重作答；\n" + "      记事本中会显示当前回合的任务，有疑问时不妨去看一下。");
                    // alert.setContentText(Game.getTransportedClientNb() + " 名乘客在你建造的地铁里一共度过 " +
                    // clock.getNbDay() +" 天");
                    // alert.setGraphic(new ImageView(new
                    // Image(this.getClass().getResource("/img/lose.png").toString())));

                    // ButtonType buttonTypeOne = new ButtonType("Recommencer");
                    ButtonType buttonTypeTwo = new ButtonType("确定");

                    alert.getButtonTypes().setAll(buttonTypeTwo);

                    // Game.getInventory().addTrain();
                    // updateTrainNb(Game.getInventory().getTrainNb());

                    Optional<ButtonType> result = alert.showAndWait();

                    /*
                     * if (result.get() == buttonTypeOne) { Controller.game.resumeGame();
                     * Main.restart();
                     */
                    // if (result.get() == buttonTypeTwo) {
                    // Main.proceed(1);
                    // }
                } catch (Exception e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }

            }

        });
    }

    public static void go3rdPart() {
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    // Controller.game.pauseGame();

                    if (round + 1 == 8) {
                        round9 = new Stage();
                        WebView webView = new WebView();

                        String url = Main.class.getResource("/web/round8.html").toExternalForm();

                        WebEngine webEngine = webView.getEngine();

                        // process page loading
                        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
                            @Override
                            public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                                if (newState == State.SUCCEEDED) {
                                    JSObject win = (JSObject) webEngine.executeScript("window");
                                    win.setMember("app", new Main().new JavaApp());
                                }
                            }
                        });

                        webEngine.load(url);

                        VBox vBox = new VBox(webView);

                        Scene scene = new Scene(vBox, 960, 540);
                        round9.setScene(scene);
                        round9.show();

                        round9.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                event.consume();
                            }
                        });
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("进入第四阶段第一回合~");
                        alert.setHeaderText("作为一名有经验的铁路规划师，你迎来了最后的考验。");
                        alert.setContentText(
                                "请观察两位见习生的铁路设计图，指出该设计中可能会爆掉的车站（最多3个），并使用现有资源重新设计该地图给予他们指导。注意！激活乘客后仅有2次主动暂停修改设计的机会。");

                        ButtonType buttonTypeTwo = new ButtonType("确定");

                        alert.getButtonTypes().setAll(buttonTypeTwo);

                        alert.show();

//                        Controller.game.pauseGame();

                    } else if (round + 1 == 9) {
                        round10 = new Stage();
                        WebView webView = new WebView();

                        String url = Main.class.getResource("/web/round9.html").toExternalForm();

                        WebEngine webEngine = webView.getEngine();

                        // process page loading
                        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
                            @Override
                            public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                                if (newState == State.SUCCEEDED) {
                                    JSObject win = (JSObject) webEngine.executeScript("window");
                                    win.setMember("app", new Main().new JavaApp());
                                }
                            }
                        });

                        webEngine.load(url);

                        VBox vBox = new VBox(webView);

                        Scene scene = new Scene(vBox, 960, 540);
                        round10.setScene(scene);
                        round10.show();

                        round10.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                event.consume();
                            }
                        });

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("进入第四阶段第二回合~");
                        alert.setHeaderText("做的不错，这是本游戏的最后一回合，加油哦~");
                        alert.setContentText("请观察两位见习生的铁路设计图，指出该设计中可能会爆掉的车站（最多3个），并使用现有资源重新设计该地图给予他们指导。\n"
                                + "注意！激活乘客后仅有2次主动暂停修改设计的机会。");

                        ButtonType buttonTypeTwo = new ButtonType("确定");

                        alert.getButtonTypes().setAll(buttonTypeTwo);

                        alert.show();

//                        Controller.game.pauseGame();
                    } else {
                        Main.proceed(round + 1);
                    }

                } catch (Exception e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }

            }

        });
    }

    public void endOfGame() {
        Platform.runLater(new Runnable() {
            public void run() {
                try {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("游戏结束");
                    alert.setHeaderText("您的地铁已关闭");

                    // ButtonType buttonTypeOne = new ButtonType("Recommencer");
                    ButtonType buttonTypeTwo = new ButtonType("离开游戏");

                    alert.getButtonTypes().setAll(buttonTypeTwo);

                    Game.getInventory().addTrain();
                    updateTrainNb(Game.getInventory().getTrainNb());

                    Optional<ButtonType> result = alert.showAndWait();

                    /*
                     * if (result.get() == buttonTypeOne) { Controller.game.resumeGame();
                     * Main.restart();
                     */
                    if (result.get() == buttonTypeTwo) {
                        Main.end();
                    }
                } catch (Exception e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }

            }

        });
    }

    public void alertError(String text) {
        Platform.runLater(new Runnable() {
            public void run() {
                try {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("提醒");
                    alert.setHeaderText(text);

                    // ButtonType buttonTypeOne = new ButtonType("Recommencer");
                    ButtonType buttonTypeTwo = new ButtonType("确定");

                    alert.getButtonTypes().setAll(buttonTypeTwo);

                    alert.show();

                    // Game.getInventory().addTrain();
                    // updateTrainNb(Game.getInventory().getTrainNb());

                    // Optional<ButtonType> result = alert.showAndWait();

                    // /*
                    //  * if (result.get() == buttonTypeOne) { Controller.game.resumeGame();
                    //  * Main.restart();
                    //  */
                    // if (result.get() == buttonTypeTwo) {
                    //     Main.end();
                    // }
                } catch (Exception e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }

            }

        });
    }

    public void setGift(int gift2) {
        Platform.runLater(new Runnable() {
            public void run() {
                Stage stage = new Stage();
                try {
                    Controller.game.pauseGame();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("目标达成");
                    alert.setHeaderText("恭喜！ 你收到了一个新的机车。");
                    alert.setContentText("另外，你将获得如下资源");

                    alert.setGraphic(
                            new ImageView(new Image(this.getClass().getResource("/img/train.png").toString())));

                    ButtonType buttonTypeOne = new ButtonType("路线");
                    ButtonType buttonTypeTwo = new ButtonType("车厢");
                    ButtonType buttonTypeThree = new ButtonType("车头");
                    ButtonType buttonTypeFour = new ButtonType("隧道");
                    ButtonType buttonTypeFive = new ButtonType("枢纽");

                    ArrayList<ButtonType> list = new ArrayList<ButtonType>();
                    list.add(buttonTypeOne);
                    list.add(buttonTypeTwo);
                    list.add(buttonTypeThree);
                    list.add(buttonTypeFour);
                    list.add(buttonTypeFive);

                    alert.getButtonTypes().setAll(list.get(gift2));

                    Game.getInventory().addTrain();
                    updateTrainNb(Game.getInventory().getTrainNb());

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne) {
                        Controller.game.resumeGame();
                        Game.getInventory().addLineNb();
                        // Controller.game.addGiftColor();
                        updateLineNb(Game.getInventory().getLineNb());
                    } else if (result.get() == buttonTypeTwo) {
                        Controller.game.resumeGame();
                        Game.getInventory().addWagonNb();
                        updateWagonNb(Game.getInventory().getWagonNb());
                    } else if (result.get() == buttonTypeThree) {
                        Controller.game.resumeGame();
                        Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                    } else if (result.get() == buttonTypeFour) {
                        Controller.game.resumeGame();
                        Game.getInventory().addTunnelNb(2);
                        updateTunnelNb(Game.getInventory().getTunnelNb());
                    } else if (result.get() == buttonTypeFive) {
                        Controller.game.resumeGame();
                        Game.getInventory().addStationNb();
                        updateStationNb(Game.getInventory().getStationNb());
                    }

                } catch (Exception e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        });
    }

    public void setGift(int gift2, int gift3,int leftTimes) {
        Platform.runLater(new Runnable() {
            public void run() {
                Stage stage = new Stage();
                try {
                    // Controller.game.pauseGame();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("获得资源");
                    alert.setHeaderText("你收到了一个新的机车。此外你还有"+leftTimes+"次机会");
                    alert.setContentText("另外，你希望选择哪种资源 ?");

                    alert.setGraphic(
                            new ImageView(new Image(this.getClass().getResource("/img/train.png").toString())));

                    ButtonType buttonTypeOne = new ButtonType("路线");
                    ButtonType buttonTypeTwo = new ButtonType("车厢");
                    ButtonType buttonTypeThree = new ButtonType("车头");
                    ButtonType buttonTypeFour = new ButtonType("隧道");
                    ButtonType buttonTypeFive = new ButtonType("枢纽");

                    ArrayList<ButtonType> list = new ArrayList<ButtonType>();
                    list.add(buttonTypeOne);
                    list.add(buttonTypeTwo);
                    list.add(buttonTypeThree);
                    list.add(buttonTypeFour);
                    list.add(buttonTypeFive);

                    alert.getButtonTypes().setAll(list.get(gift2), list.get(gift3));

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne) {
                    	if (leftTimes == 9) {
                    		if(round == 6) {
                        		DbConnector.updateZ("z9", Integer.toString(1));
                        	} else if (round == 7) {
                        		DbConnector.updateZ("z10", Integer.toString(1));
                        	}
                    	} else if (leftTimes == 6 && round == 6) {
                    		if(round == 6) {
                    			DbConnector.updateZ("z12", Integer.toString(1));
                        	} else if (round == 7) {
                        		DbConnector.updateZ("z15", Integer.toString(1));
                        	}
                    	}
                        Game.getInventory().addLineNb();
                        // Controller.game.addGiftColor();
                        updateLineNb(Game.getInventory().getLineNb());
                        Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                    } else if (result.get() == buttonTypeTwo) {
                        Game.getInventory().addWagonNb();
                        updateWagonNb(Game.getInventory().getWagonNb());
                        Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                    } else if (result.get() == buttonTypeThree) {
                        Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                        Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                    } else if (result.get() == buttonTypeFour) {
                    	if (leftTimes == 8 && round == 6) {
                    		DbConnector.updateZ("z11", Integer.toString(1));
                    	} else if (leftTimes == 3) {
                    		if (round == 6) {
                    			DbConnector.updateZ("z13", Integer.toString(1));
                    		} else if (round == 7) {
                    			DbConnector.updateZ("z14", Integer.toString(1));
                    		}
                    	}
//                    	else if (leftTimes == 3) {
//                    		if (round == 7) {
//                    			DbConnector.updateZ("z16", Integer.toString(1));
//                    		}
//                    	}
                        Game.getInventory().addTunnelNb(2);
                        updateTunnelNb(Game.getInventory().getTunnelNb());
                        Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                    } else if (result.get() == buttonTypeFive) {
                    	Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                        Game.getInventory().addStationNb();
                        updateStationNb(Game.getInventory().getStationNb());
                    }

                    // Game.resumeGame();
                    // Controller.game.resumeGame();
                    // seeInfo();

                } catch (Exception e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        });
    }

    public void setGift(int gift2, int gift3, Boolean isLast) {
        Platform.runLater(new Runnable() {
            public void run() {
                Stage stage = new Stage();
                try {
                    // Controller.game.pauseGame();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("获得资源");
                    alert.setHeaderText("你收到了一个新的机车。");
                    alert.setContentText("另外，你希望选择哪种资源 ?");

                    alert.setGraphic(
                            new ImageView(new Image(this.getClass().getResource("/img/train.png").toString())));

                    ButtonType buttonTypeOne = new ButtonType("路线");
                    ButtonType buttonTypeTwo = new ButtonType("车厢");
                    ButtonType buttonTypeThree = new ButtonType("车头");
                    ButtonType buttonTypeFour = new ButtonType("隧道");
                    ButtonType buttonTypeFive = new ButtonType("枢纽");

                    ArrayList<ButtonType> list = new ArrayList<ButtonType>();
                    list.add(buttonTypeOne);
                    list.add(buttonTypeTwo);
                    list.add(buttonTypeThree);
                    list.add(buttonTypeFour);
                    list.add(buttonTypeFive);

                    alert.getButtonTypes().setAll(list.get(gift2), list.get(gift3));

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne) {
                        Game.getInventory().addLineNb();
                        // Controller.game.addGiftColor();
                        updateLineNb(Game.getInventory().getLineNb());
                        Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                        if (isLast) {
                            // Controller.game.resumeGame();
                        }
                    } else if (result.get() == buttonTypeTwo) {
                        Game.getInventory().addWagonNb();
                        updateWagonNb(Game.getInventory().getWagonNb());
                        Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                        if (isLast) {
                            // Controller.game.resumeGame();
                        }
                    } else if (result.get() == buttonTypeThree) {
                        Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                        Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                        if (isLast) {
                            // Controller.game.resumeGame();
                        }
                    } else if (result.get() == buttonTypeFour) {
                        Game.getInventory().addTunnelNb(2);
                        updateTunnelNb(Game.getInventory().getTunnelNb());
                        Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                        if (isLast) {
                            // Controller.game.resumeGame();
                        	DbConnector.updateZ("z17", Integer.toString(1));
                        }
                    } else if (result.get() == buttonTypeFive) {
                    	Game.getInventory().addTrain();
                        updateTrainNb(Game.getInventory().getTrainNb());
                        Game.getInventory().addStationNb();
                        updateStationNb(Game.getInventory().getStationNb());
                    }

                    // Game.resumeGame();
                    // Controller.game.resumeGame();
                    // seeInfo();

                } catch (Exception e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        });
    }

    public void updateTunnelNb(int tunnel) {
        info.setNbTunnel(tunnel);
    }

    public void updateTrainNb(int train) {
        info.setNbTrain(train);
    }

    public void updateWagonNb(int wagon) {
        info.setNbWagon(wagon);
    }

    public void updateLineNb(int rail) {
        info.setNbLine(rail);
    }

    public void updateStationNb(int station) {
        info.setNbStation(station);
    }

    public void addRiver(Shape r) {
        river = r;
    }

    public void seeInfo() {
        if (!info.isVisible()) {
            info.setVisible(true);
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), info);
            translateTransition.setFromY(0);
            translateTransition.setToY(-70);
            translateTransition.setCycleCount(1);
            translateTransition.setAutoReverse(true);
            translateTransition.play();

            TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(1000), point);
            translateTransition2.setFromY(0);
            translateTransition2.setToY(-70);
            translateTransition2.setCycleCount(1);
            translateTransition2.setAutoReverse(true);
            translateTransition2.play();

            ParallelTransition para = new ParallelTransition();
            para.getChildren().addAll(translateTransition2, translateTransition);
            para.setCycleCount(1);
            para.play();

            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    hideInfo();
                }
            }, 5000);
        }
    }

    public void hideInfo() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), info);
        translateTransition.setFromY(-70);
        translateTransition.setToY(0);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);
        translateTransition.play();

        TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(1000), point);
        translateTransition2.setFromY(-70);
        translateTransition2.setToY(0);
        translateTransition2.setCycleCount(1);
        translateTransition2.setAutoReverse(true);
        translateTransition2.play();

        ParallelTransition para = new ParallelTransition();
        para.getChildren().addAll(translateTransition2, translateTransition);
        para.setCycleCount(1);
        para.play();
        para.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                info.setVisible(false);
            }
        });
    }

    public void updateNbBoom() {
        numBoom++;
        nbBoom.setText(Integer.toString(numBoom));
    }

    public void updateNbClient() {
        int newI = Game.getTransportedClientNb();
        // if(newI>=10)
        // {
        // nbClient.setX(758);
        // }
        // if(newI>=100)
        // {
        // nbClient.setX(758);
        // }
        // if(newI>=1000)
        // {
        // nbClient.setX(924);
        // }
        nbClient.setText(Integer.toString(newI));
    }

    public Shape getTunnelShape(Shape line) {
        return Shape.intersect(river, line);
    }

    public void createLine(Line l, Shape end1, Shape end2) {
        ArrayList<Shape> list = new ArrayList<>();
        getLineLinks().put(l, list);

        Shape[] ends = new Shape[2];
        ends[0] = end2;
        ends[1] = end1;
        lineEnds.put(l, ends);
    }

    public void setLineEnd(Line l, Shape end, boolean inFirst) {
        Shape[] ends = lineEnds.get(l);
        if (inFirst)
            ends[0] = end;
        else
            ends[1] = end;

    }

    public void removeEnds(Line l) {
        Shape[] ends = lineEnds.get(l);
        System.err.println(
                "REMOVING ENDS " + group.getChildren().remove(ends[0]) + " " + group.getChildren().remove(ends[1]));
    }

    public void correctRotation(Line l) {
        Shape[] ends = lineEnds.get(l);
        fxEndLine end = (fxEndLine) ends[0];
        System.err.println(l.getStationList().get(0));
        end.correctRotation(l.getStationList().get(0), l.getPath().get(1).getX(), l.getPath().get(1).getY());
        end = (fxEndLine) ends[1];
        end.correctRotation(l.getStationList().get(l.getStationList().size() - 1),
                l.getPath().get(l.getPath().size() - 2).getX(), l.getPath().get(l.getPath().size() - 2).getY());
    }

    public Shape getLineLink(Line l, boolean inFirst) {
        if (inFirst)
            return getLineLinks().get(l).get(0);
        else
            return getLineLinks().get(l).get(getLineLinks().get(l).size() - 1);
    }

    public Shape getNextLineLink(Line l, boolean inFirst) {
        ArrayList<Shape> list = getLineLinks().get(l);
        if (inFirst)
            return list.get(1);
        else
            return list.get(list.size() - 2);
    }

    public Station getNextStation(Line l, Shape nextLink) {
        ArrayList<Shape> list = getLineLinks().get(l);
        boolean loop = l.isLoop();
        System.err.println("IS LOOP " + loop + "\nIndex of next Link : " + list.indexOf(nextLink));
        if (list.indexOf(nextLink) == 0)
            return l.getStationList().get(1);
        else
            return l.getStationList().get(l.getStationList().size() - 2);
    }

    public void addLineLink(Line l, Shape link, boolean inFirst) {
        ArrayList<Shape> list = getLineLinks().get(l);
        if (inFirst) {
            list.add(0, link);
            System.err.println("ADD IN FIRST");
        } else {
            list.add(link);
            System.err.println("ADD IN LAST");
        }
        links.add(link);
        System.err.println("Index of created LINK : " + list.indexOf(link));
    }

    public void addLineLink(Line l, Shape link, int index) {
        ArrayList<Shape> list = getLineLinks().get(l);
        list.add(index, link);
        links.add(link);
    }

    public void removeLink(Shape link) {
        links.remove(link);
    }

    public void addLink(Shape link) {
        links.add(link);
    }

    public void removeLineLink(Line l, boolean inFirst) {
        Shape s;
        if (inFirst) {
            s = getLineLinks().get(l).remove(0);
            System.err.println("REMOVING IN FIRST");
        } else {
            s = getLineLinks().get(l).remove(getLineLinks().get(l).size() - 1);
            System.err.println("REMOVING IN LAST");
        }

        group.getChildren().remove(s);
        links.remove(s);

        if (getLineLinks().get(l).size() == 0)
            getLineLinks().remove(l);
    }

    public void removeLineLink(Line l, Shape link) {

        getLineLinks().get(l).remove(link);
        group.getChildren().remove(link);
        links.remove(link);
        if (getLineLinks().get(l).size() == 0)
            getLineLinks().remove(l);
    }

    public void addNode(Node n) {
        group.getChildren().add(n);
        System.err.println("ADDED NODE");
    }

    public void putWagon(Train t, Boolean isWagon, Line line) {
        removeTrain(line.getTrainList().get(0));
        getTrains().put(t, new fxTrain(t, isWagon));
        group.getChildren().add(getTrains().get(t));
        controller.addTrainEvent(getTrains().get(t).r, t);
    }

    public void put(Station s) {
        stations.put(s, new fxStation(s));
        group.getChildren().add(stations.get(s).arcTimer);
        group.getChildren().add(stations.get(s).shape);
        controller.addStationEvent(stations.get(s).shape, s);
    }

    public void put(Train t) {
        getTrains().put(t, new fxTrain(t));
        group.getChildren().add(getTrains().get(t));
        controller.addTrainEvent(getTrains().get(t).r, t);
    }

    public void put(Train t, Position p) {
        getTrains().put(t, new fxTrain(t, p));
        group.getChildren().add(getTrains().get(t));
        controller.addTrainEvent(getTrains().get(t).r, t);
    }

    public void trainChangeLine(Train t) {
        group.getChildren().remove(get(t));
        getTrains().replace(t, new fxTrain(t));
        group.getChildren().add(getTrains().get(t));
        controller.addTrainEvent(getTrains().get(t).r, t);

    }

    public void put(Client c,Boolean orginal) {
        // clientschedule中手动加了，为了避免位置重叠
        if (orginal){
            clients.put(c,new fxClient(c));
        }

        group.getChildren().add(getClients().get(c).shape);
    }

    public void put(Client c) {
       clients.put(c,new fxClient(c));

        group.getChildren().add(getClients().get(c).shape);
    }

    public void removeClient(List<Client> list) {
        for (Client c : list) {
            group.getChildren().remove(getClients().get(c).shape);
            getClients().remove(c);
        }
    }

    public void put(List<Client> list) {
        for (Client c : list) {
            getClients().put(c, new fxClient(c));

            group.getChildren().add(getClients().get(c).shape);
        }
    }

    public fxTrain get(Train t) {
        return getTrains().get(t);
    }

    public fxStation get(Station s) {
        return stations.get(s);
    }

    public fxClient get(Client c) {
        return getClients().get(c);
    }

    public void remove(Client c) {
        group.getChildren().remove(getClients().get(c).shape);
        // System.out.println(Controller.game.getStationList().size());
        getClients().remove(c);

        Station st = c.getStation();
        for (int i = 0; i < st.getClientList().size(); ++i) {
            fxClient fxCl = get(st.getClientList().get(i));
            if (fxCl != null) {
                fxCl.updatePos(st, i + 1);
            }
        }
    }

    public void addClientToTrain(Train tr, Client client) {
        fxTrain fxTr = get(tr);
        Shape s = fxTr.addClient(client);
        fxClient fxclient = new fxClient(s);
        getClients().put(client, fxclient);
    }

    public void removeClientFromTrain(Train tr, Client client) {
        fxTrain fxTr = get(tr);
        fxClient fxclient = get(client);
        fxTr.removeClient(fxclient.shape);
    }

    public void removeTrain(Train tr) {
        fxTrain fxtr = get(tr);
        group.getChildren().remove(fxtr);
        getTrains().remove(tr);
    }

    public boolean intersects(Shape f) {
        // for (Shape l : links) {
        // Shape intersect = Shape.intersect(f, l);
        // if (intersect.getBoundsInLocal().getWidth() != -1) {
        // System.err.println("INTERSECTS !! ");
        // return true;
        // }
        // }
        return false;
    }

    public boolean intersectRiver(Shape f) {
        Shape intersect = Shape.intersect(f, river);
        if (intersect.getBoundsInLocal().getWidth() != -1) {
            System.err.println("INTERSECTS RIVER !! ");
            return true;
        }
        return false;
    }

    public boolean intersectRiver(Position p) {
        Polygon square = getSquare();
        for (int i = 0; i < square.getPoints().size(); i += 2) {
            double tempX = square.getPoints().get(i);
            double tempY = square.getPoints().get(i + 1);
            square.getPoints().set(i, p.getX() + tempX);
            square.getPoints().set(i + 1, p.getY() + tempY);
        }
        Shape intersect = Shape.intersect(square, river);
        if (intersect.getBoundsInLocal().getWidth() != -1) {
            System.err.println("INTERSECTS RIVER !! POSITION ");
            return true;
        }
        return false;
    }

    public void move(Train train) {
        fxTrain fxTrain = get(train);
        fxTrain.move(train.getLine().getPath().get(train.getNextPointIndex()), Game.getTrainSpeed());
    }

    public void pauseTrains() {
        for (fxTrain train : getTrains().values()) {
            train.pause();
        }
    }

    public void resumeTrains() {
        for (fxTrain train : getTrains().values()) {
            train.resume();
        }

    }

    public boolean isFirstEnd(Shape currentT, Line l) {
        Shape[] ends = lineEnds.get(l);
        return ends[0] == currentT;

    }

    public HashMap<Client, fxClient> getClients() {
        return clients;
    }

    public void setClients(HashMap<Client, fxClient> clients) {
        GameView.clients = clients;
    }

    public HashMap<model.Line, ArrayList<Shape>> getLineLinks() {
        return lineLinks;
    }

    public void setLineLinks(HashMap<model.Line, ArrayList<Shape>> lineLinks) {
        this.lineLinks = lineLinks;
    }

	public ImageView getImgBook() {
		return imgBook;
	}

	public void setImgBook(ImageView imgBook) {
		this.imgBook = imgBook;
	}

	public int getClickBook() {
		return clickBook;
	}

	public void setClickBook(int clickBook) {
		this.clickBook = clickBook;
	}

	public HashMap<Train, fxTrain> getTrains() {
		return trains;
	}

	public void setTrains(HashMap<Train, fxTrain> trains) {
		this.trains = trains;
	}

	public int getPauseTimes() {
		return pauseTimes;
	}

	public void setPauseTimes(int pauseTimes) {
		this.pauseTimes = pauseTimes;
	}
}
