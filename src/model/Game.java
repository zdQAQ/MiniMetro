package model;

import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.Controller;
import javafx.GameView;

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
    private static int transportedClientNb = 0;
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
    private final Object pauseLock = new Object();
    private static boolean gift = true;
    private Thread threadClient;
    public Thread threadStation;
    public Thread threadTime;

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
        clock = new Clock();
    }

    public List<Station> getStationList() {
        return stationList;
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
                            clock.incrementeTime();
                            view.updateClock(clock.getTime(), clock.getDay());
                            // 乘客出现逻辑
                            List<Client> list = clientSchedule.computeProgress(clock);
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
        // setGift的参数 资源 1表示路线 2车厢 3车头 4隧道 5枢纽
        if (view.getRound() == 0) {
            // 第一阶段给资源
            if (transportedClientNb == 50) {
                view.setGift(3, 2);
            } else if (transportedClientNb == 120) {
                view.setGift(3, 4);
            } else if (transportedClientNb == 240) {
                view.setGift(3, 1);
            } else if (transportedClientNb == 400) {
                view.setGift(3, 2);
            } else if (transportedClientNb == 600) {
                view.setGift(3, 1);
            } else if (transportedClientNb == 800) {
                view.setGift(3, 4);
            } else if (transportedClientNb == 1000) {
                view.setGift(3, 5);
            } else if (transportedClientNb == 1200) {
                view.setGift(3, 1);
            } else if (transportedClientNb == 1400) {
                view.setGift(3, 4);
            } else if (transportedClientNb == 1600) {
                view.setGift(3, 2);
            } else if (transportedClientNb == 1800) {
                view.setGift(3, 2);
            } else if (transportedClientNb == 2000) {
                view.setGift(3, 2);
            } else if (transportedClientNb == 2100) {
                view.setGift(3, 2);
            } else if (transportedClientNb == 2300) {
                view.setGift(3, 2);
            } else if (transportedClientNb == 2400) {
                view.setGift(3, 2);
            }
        } else if (view.getRound() == 6) {
            // 第三阶段第一回合给资源
            // setGift的参数 资源 1表示路线 2车厢 3车头 4隧道 5枢纽
            Controller.game.pauseGame();
            view.setGift(3, 1, 2);
            view.setGift(3, 4, 2);
            view.setGift(3, 3, 4);
            view.setGift(3, 1, 5);
            view.setGift(3, 5, 2);
            view.setGift(3, 1, 2);
            view.setGift(3, 4, 2);
            view.setGift(3, 1, 2);
            view.setGift(3, 3, 2);
            view.setGift(3, 4, 2,true);
        } else if (view.getRound() == 7) {
            // 第三阶段第二回合给资源
            // setGift的参数 资源 1表示路线 2车厢 3车头 4隧道 5枢纽
            Controller.game.pauseGame();
            view.setGift(3, 1, 2);
            view.setGift(3, 4, 2);
            view.setGift(3, 3, 4);
            view.setGift(3, 1, 5);
            view.setGift(3, 5, 2);
            view.setGift(3, 1, 2);
            view.setGift(3, 4, 2);
            view.setGift(3, 1, 2);
            view.setGift(3, 3, 2);
            view.setGift(3, 4, 2,true);
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

        }
    }

    public static boolean getPause() {
        return pause;
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

    public void start() {
        popStation();
        timeGo();
        // popRandomClient();
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
