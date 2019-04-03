package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.Controller;
import javafx.GameView;
import javafx.Main;

import java.util.*;

/**
 * Created by romainhry on 08/11/2016.
 */
public class Game {
    public GameView view;
    public Schedule schedule;
    private static int trainSpeed = 1;
    protected static int vehicleCapacity = 8;
    private static int stationCapacity;
    private static int timeSpeed;
    public static int transportedClientNb = 0;
    private int width = 1200;
    private int height = 600;
    private static int maxShapeWidth = 30;
    private static double distanceSpacing = 100;
    private static Inventory inventory;
    private List<Train> trainList;
    private List<Client> clientList;
    private List<Line> lineList;
    private List<Station> stationList;
    private List<Color> linesColor;
    private List<Color> giftColor;
    private Clock clock;
    private static volatile boolean pause = false;
    static final Object pauseLock = new Object();
    static final Object pauseLock1 = new Object();
    private static boolean gift = true;
    private Thread threadTip;
    public Thread threadStation;
    public static Thread threadTime;
    private int times;

    public ClientSchedule clientSchedule;

    private boolean clientReady, stationReady;

    public Game(GameView gameView, int train, int wagon, int line, int tunnel, int station) {
        view = gameView;
        trainList = new ArrayList<>();
        clientList = new ArrayList<>();
        lineList = new ArrayList<>();
        stationList = new ArrayList<>();
        inventory = new Inventory(train, wagon, line, tunnel, station);
        linesColor = new ArrayList<>();
        linesColor.add(Color.RED);
        linesColor.add(Color.BLUE);
        linesColor.add(Color.ORANGE);
        giftColor = new ArrayList<>();
        giftColor.add(Color.GREEN);
        giftColor.add(Color.AQUAMARINE);
        giftColor.add(Color.PURPLE);
        giftColor.add(Color.PINK);
        giftColor.add(Color.YELLOW);
        giftColor.add(Color.BROWN);
        giftColor.add(Color.TEAL);
        giftColor.add(Color.LAVENDER);
        giftColor.add(Color.LIGHTBLUE);
        giftColor.add(Color.MEDIUMSPRINGGREEN);
        giftColor.add(Color.NAVAJOWHITE);
        clock = new Clock();
        times = 0;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public boolean includesStationType(ShapeType s) {
        for (Station station : stationList) {
            if (station.getType().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void setSchedule(Schedule s) {
        schedule = s;
    }

    public Color getColor() {
        Color c = linesColor.get(0);
        linesColor.remove(0);
        return c;
    }

    public Color getDrawingColor() {
        if (linesColor.size() == 0)
            return Color.PAPAYAWHIP;
        return linesColor.get(0);
    }

    public void addColor(Color c) {
        linesColor.add(c);
    }

    public void addGiftColor() {
        addColor(giftColor.remove(0));
    }

    public static void setTrainSpeed(int speed) {
        trainSpeed = speed;
    }

    public static int getTrainSpeed() {
        return trainSpeed;
    }

    private void popStation() {
        threadStation = new Thread("popStation") {
            public void run() {
                while (!pause) {
                    try {
                        Event event = schedule.findNext();
                        sleep(event.getTiming());
                        if (event != null) {
                            System.out.println(event);
                            Station st = new Station(event.getStationType(), event.getPosition());
                            Platform.runLater(() -> addToView(st));
                        }
                    } catch (Exception e) {
                        // System.out.println("popStation:"+e);
                        // this.interrupt();
                    }
                }
            }
        };
        threadStation.start();
    }

    private void timeGo() {

        threadTime = new Thread("timeGo") {
            public void run() {
                while (true) {
                    synchronized (pauseLock) {
                        // we are in a while loop here to protect against spurious interrupts
                        if (pause) {
                            try {
                                pauseLock.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                // we should probably quit if we are interrupted?
                                return;
                            }
                        }
                        try {
                            // 时间
                            clock.incrementeTime();
                            view.updateClock(clock.getTime(), clock.getDay());
                            // 乘客出现逻辑
                            List<Client> list = clientSchedule.computeProgress(clock);
                            // for(Client c:list){
                            // System.out.println(c.getType());
                            // }
                            addToView(list);

                            // 预警
                            for (Station station : getStationList()) {
                                if (station.getClientList().size() > 3) {
                                    // System.out.println(station);
                                }
                                if (station.getClientList().size() > station.getCapacity()
                                        && station.getIsFull() == false) {
                                    station.startFullTimer();
                                } else if (station.getClientList().size() < station.getCapacity()
                                        && station.getIsFull() == true) {
                                    station.decreaseFullTimer();
                                }
                            }

                            sleep(833);
                        } catch (Exception ex) {
                            // System.out.println("timeGo:"+ex);
                        }
                    }
                }
            }
        };
        threadTime.start();
    }

    public void setGift() {
        // setGift的参数 资源 0表示路线 1车厢 2车头 3隧道 4枢纽
        if (view.getRound() == 0) {
            // 第一阶段给资源
            if (transportedClientNb == 50) {
                view.setGift(1);
            } else if (transportedClientNb == 120) {
                view.setGift(3);
            } else if (transportedClientNb == 240) {
                view.setGift(0);
            } else if (transportedClientNb == 400) {
                view.setGift(1);
            } else if (transportedClientNb == 600) {
                view.setGift(0);
            } else if (transportedClientNb == 800) {
                view.setGift(3);
            } else if (transportedClientNb == 1000) {
                view.setGift(4);
            } else if (transportedClientNb == 1200) {
                view.setGift(0);
            } else if (transportedClientNb == 1400) {
                view.setGift(3);
            } else if (transportedClientNb == 1600) {
                view.setGift(1);
            } else if (transportedClientNb == 1800) {
                view.setGift(1);
            } else if (transportedClientNb == 2000) {
                view.setGift(1);
            } else if (transportedClientNb == 2100) {
                view.setGift(1);
            } else if (transportedClientNb == 2300) {
                view.setGift(1);
            } else if (transportedClientNb == 2400) {
                view.setGift(1);
            }
        } else if (view.getRound() == 6) {
            // 第三阶段第一回合给资源
            // setGift的参数 资源 1表示路线 2车厢 3车头 4隧道 5枢纽
            pauseGame();
            view.setGift(0, 1, true);
            view.setGift(3, 1);
            view.setGift(2, 3);
            view.setGift(0, 4);
            view.setGift(4, 1);
            view.setGift(0, 1);
            view.setGift(3, 1);
            view.setGift(0, 1);
            view.setGift(2, 1);
            view.setGift(3, 1);
        } else if (view.getRound() == 7) {
            // 第三阶段第二回合给资源
            // setGift的参数 资源 1表示路线 2车厢 3车头 4隧道 5枢纽
            pauseGame();
            view.setGift(0, 1, true);
            view.setGift(3, 1);
            view.setGift(2, 3);
            view.setGift(0, 4);
            view.setGift(4, 1);
            view.setGift(0, 1);
            view.setGift(3, 1);
            view.setGift(0, 1);
            view.setGift(2, 1);
            view.setGift(3, 1);
        }
    }

    public void pauseGame() {
        pause = true;
        threadStation.interrupt();

        view.pauseTrains();
        view.pauseArc();
    }

    public void resumeGame() {
        synchronized (pauseLock) {
            pause = false;
            view.resumeTrains();
            gift = true;

            pauseLock.notifyAll(); // Unblocks thread
            view.resumeArc();
            popStation();
        }
    }

    public static boolean getPause() {
        return pause;
    }

    public static void setPause() {
        pause = true;
    }

    public static void resumeFromPause() {
        pause = false;
    }

    public void setGameSpeed(int speed) {

    }

    public static int getTransportedClientNb() {
        return transportedClientNb;
    }

    public static void addTransportedClient() {
        ++transportedClientNb;
    }

    public void addToView(Station s) {
        stationList.add(s);
        System.out.println(s.getType());
        view.put(s);
    }

    public void addToView(Train t) {
        trainList.add(t);
        view.put(t);
    }

    public void addToView(Client c) {
        clientList.add(c);
        view.put(c);
    }

    public void addToView(List<Client> list) {
        clientList.addAll(list);
        // view.put(list);
    }

    public void computeAllDistances() {
        for (Station st : stationList) {
            Arrays.fill(st.getDistances(), -1);
            resetChecked();
            // st.computeDistances(st,0);
            st.computeDistances();
        }
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public void resetChecked() {
        for (Station s : stationList)
            s.setChecked(false);
    }

    public void start() throws InterruptedException {
        pause = false;
        popStation();
        if (GameView.round == 0 || GameView.round == 9 || GameView.round == 10) {
            timeGo();
        }
    }

    public void showTip(int round) {
        // if(round == 1) {
        // return;
        // }
        pauseGame();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (round + 1 == 1) {
            alert.setTitle("进入第一阶段~");
            alert.setHeaderText("你需要作为见习生应对城市中不断涌现的站点与乘客，探索学习最高效的线路设计方法，合理规划线路，避免拥堵，追求运营分数最大化。");
            alert.setContentText("PS：新手指引藏在记事本中，认真阅读会带给你很大的帮助");
        } else if (round + 1 == 2) {
            alert.setTitle("进入第二阶段第一回合~");
            alert.setHeaderText("请看题～");
            alert.setContentText("注意：每输入数字");
        } else if (round + 1 == 3) {
            alert.setTitle("进入第二阶段第二回合~");
            alert.setHeaderText("使用3条线路3辆机车覆盖所有站点，构建你认为最高效的铁路网");
            alert.setContentText("注意：每条线路最多穿过5个站点");
        } else if (round + 1 == 4) {
            alert.setTitle("进入第二阶段第三回合~");
            alert.setHeaderText("使用3条线路3辆机车覆盖所有站点，构建你认为最高效的铁路网");
            alert.setContentText("注意：每条线路最多穿过6个站点；");
        } else if (round + 1 == 5) {
            alert.setTitle("进入第二阶段第四回合~");
            alert.setHeaderText("使用3条线路3辆机车覆盖所有站点，构建你认为最高效的铁路网");
            alert.setContentText("注意：每条线路最多穿过6个站点\r\n" + 
            		"每个站点最多经过2条线路");
        } else if (round + 1 == 6) {
            alert.setTitle("进入第二阶段第五回合~");
            alert.setHeaderText("使用3条线路5辆机车覆盖所有站点，构建你认为最高效的铁路网");
        } else if (round + 1 == 7) {
            alert.setTitle("进入第三阶段第一回合~");
            alert.setHeaderText("该来的总是要来，见习结束的你在第3阶段需要再设计两张复杂的地图。");
            alert.setContentText(
                    "见习检验合格，你将正式上岗~现实中的情况比之前更为复杂，请认真观察地图结合任务要求储备列车等物资，设计高效的铁路网");
        } else if (round + 1 == 8) {
            alert.setTitle("进入第三阶段第二回合~");
            alert.setHeaderText("本地图共43个站点，玩家需要自主选择物资后设计线路");
            alert.setContentText(
                    "物资以二选一的方式提供，共十次选择机会。");
        } else if (round + 1 == 9) {
            alert.setTitle("进入第三阶段第三回合~");
            alert.setHeaderText("仔细观察地图后点击资源选择按钮，采用二选一的方式进行十次选择，一次性选择所有你在当前地图要用的工具并设计线路。");
            alert.setContentText(
                    "注意！设计提交后仅有1次暂停修改设计的机会，该回合的得分等于最终设计的效率分。"
                    + "设计完成后点击“激活乘客”可运行铁路网，之后你仅有一次暂停修改线路并提交的机会，该回合得分以最终设计的效率高低为准"
                    + "注意：本地图最多可拥有6条线路，每条线路最多拥有3辆机车、9站点，每个站点最多可穿过3条线路。");
        } else if (round + 1 == 10) {
            alert.setTitle("进入第四阶段第一回合~");
            alert.setHeaderText("作为一名有经验的铁路规划师，你迎来了最后的考验。");
            alert.setContentText("请观察两位见习生的铁路设计图，指出该设计中可能会爆掉的车站（最多3个），并使用现有资源重新设计该地图给予他们指导。注意！设计提交后仅有2次主动暂停修改设计的机会。");
        } else if (round + 1 == 11) {
            alert.setTitle("进入第四阶段第二回合~");
            alert.setHeaderText("做的不错，这是本游戏的最后一回合，加油哦~");
            alert.setContentText("指出该设计中可能会爆掉的车站（最多3个），并使用现有资源重新设计该地图给予他们指导。\n" + "注意！设计提交后仅有2次主动暂停修改设计的机会。");
        } else {
            alert.setTitle("进入下一阶段~");
            alert.setHeaderText("游戏结束");
        }
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
        if (result.get() == buttonTypeTwo) {
            // 第三回合第一阶段和第二阶段 直接资源
            if (round == 6 || round == 7) {
                Platform.runLater(() -> setGift());
            } else if (round != 9 && round != 10) {
                resumeGame();
            } else if (round == 11) {
                view.endOfGame();
            }
        }
    }

    public boolean isSpaced(Position p) {
        for (Station st : stationList) {
            if (st.getPosition().distance(p) < distanceSpacing)
                return false;
        }
        return true;
    }

    public ClientSchedule getClientSchedule() {
        return clientSchedule;
    }

    public void setClientSchedule(ClientSchedule c) {
        clientSchedule = c;
    }

}
