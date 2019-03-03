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
    protected static int vehicleCapacity = 8 ;
    private static int stationCapacity ;
    private static int timeSpeed ;
    private static int transportedClientNb = 0;
    private int width = 1200;
    private int height = 600;
    private static int maxShapeWidth = 30;
    private static double distanceSpacing = 100;
    private static Inventory inventory;
    private List<Train> trainList;
    private List<Client> clientList;
    private List<Line> lineList;
    private List <Station> stationList ;
    private List <Color> linesColor ;
    private List <Color> giftColor ;
    private Clock clock;
    private static volatile boolean pause =false;
    private final Object pauseLock = new Object();
    private static boolean gift = true;
    private Thread threadClient;
    private Thread threadStation;
    
    public ClientSchedule clientSchedule;

    private boolean clientReady,stationReady;

    public Game(GameView gameView) {
        view = gameView;
        trainList = new ArrayList<>();
        clientList = new ArrayList<>();
        lineList = new ArrayList<>();
        stationList = new ArrayList<>();
        inventory = new Inventory(3,3,0,3,0);
        linesColor  = new ArrayList<>();
        linesColor.add(Color.RED); linesColor.add(Color.BLUE);linesColor.add(Color.ORANGE);
        giftColor = new ArrayList<>();  giftColor.add(Color.GREEN); giftColor.add(Color.AQUAMARINE); giftColor.add(Color.PURPLE);
        clock = new Clock();
    }
    
    public List <Station> getStationList(){
    	return stationList;
    }
    
    public void setSchedule(Schedule s) {
    	schedule =s;
    }

    public Color getColor() {
        Color c = linesColor.get(0);
        linesColor.remove(0);
        return c;
    }

    public Color getDrawingColor() {
        if(linesColor.size()==0)
            return Color.PAPAYAWHIP;
        return linesColor.get(0);
    }

    public void addColor(Color c) {
        linesColor.add(c);
    }

    public void addGiftColor() {
        addColor(giftColor.remove(0));
    }

    public static void setTrainSpeed(int speed){
        trainSpeed=speed;
    }

    public static int getTrainSpeed(){return trainSpeed;}


    private void popStation() {
        threadStation = new Thread() {
            public void run() {
                while (!pause) {
                    try {
                    	Event event = schedule.findNext();
                    	if (event != null) {
                    		sleep(event.getTiming());
                    		event.setFinished(true);
                    		Station st = new Station(event.getStationType(),event.getPosition());
//                    		stationList.add(st);
                            Platform.runLater(() -> addToView(st));
                    	}
                    } catch (Exception e) {
                        System.out.println("popStation:"+e);
                    }
                }
            }
        };
        threadStation.start();
    }



    private void popRandomClient() {

        ArrayList <ShapeType> types = new ArrayList<>(Arrays.asList(ShapeType.values()));

        threadClient = new Thread() {
            public void run() {
                while(!pause){
                    try {
                    	sleep(833);
//                    	System.out.println("clock:"+clock.getTime());
//                    	// 乘客出现逻辑
//                        List<Client> list = clientSchedule.computeProgress(clock);
//                        System.out.println("list: "+list);
//                        for(Client clt:list) {
//                        	clientList.add(clt);
//                        	System.out.println(clt);
//                            Platform.runLater(() -> addToView(clt));
//                        }
//                        Random random = new Random();
//                        Thread.sleep(random.nextInt(5000));  //min 0 s, max 5 s of delay between 2 new clients
//                        Station randomStation = stationList.get(random.nextInt(stationList.size()));
//
//                        ShapeType randomType;
//                        types.remove(randomStation.getType());
//
//                        boolean exist = false;
//                        do {
//                            randomType = types.get(random.nextInt(types.size()));
//                            exist = false;
//                            for (int i = 0; i < stationList.size() && !exist; ++i) {
//
//                                exist=stationList.get(i).getType()==randomType;
//                            }
//                        }while(!exist);
//
//                        Client clt = new Client(randomStation,randomType);
//                        clientList.add(clt);
//                        types.add(randomStation.getType());
//                        Platform.runLater(() -> addToView(clt));
//                        // System.out.println("new client arrived");
//                        if(clt.getStation().getClientList().size()>=clt.getStation().getCapacity()) clt.getStation().startFullTimer();
                    }
                    catch (Exception e)
                    {
                        System.out.println("popRandomClient:"+e);
                    }
                }
            }
        };
        threadClient.start();
    }



    private void timeGo() {

        Thread threadTime = new Thread() {
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
                                Platform.runLater(() -> addToView(list));
//                                for(Client clt:list) {
//                                	System.out.println(clt);
//                                    Platform.runLater(() -> addToView(clt));
//                                }
//                                if (clock.getDay()=="星期一" && !gift) {
//                                    pop2RandomUpgrade();
//                                    gift = true;
//                                } else if (clock.getDay()=="星期一") {
//                                    gift = false;
//                                }
                                
                                sleep(833);
                            } catch (Exception ex) {
                                System.out.println("timeGo:"+ex);
                            }
                        }
                    }
                }
            };
        threadTime.start();
    }
    
    
    public void pop2RandomUpgrade() {
        Random random = new Random();

        pauseGame();
        int n1,n2;
        if(giftColor.size() != 0) {
            n1 = random.nextInt(4);
            do {
                n2 = random.nextInt(4);
            } while (n2 == n1);
            view.setGift(n1, n2);
        }
        else {
            n1 = random.nextInt(3) + 1;
            do {
                n2 = random.nextInt(3) +1;
            } while (n2 == n1);
            view.setGift(n1, n2);
        }
    }

    public void pauseGame() {
    	pause=true;
//        threadClient.interrupt();
        threadStation.interrupt();

        view.pauseTrains();
        view.pauseArc();
    }
    public void resumeGame() {
        synchronized (pauseLock) {
            pause = false;
            view.resumeTrains();
            gift=true;

            pauseLock.notifyAll(); // Unblocks thread
            view.resumeArc();
            popStation();
//            popRandomClient();
            
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
    	for(Client c:list) {
    		addToView(c);
    	}
//    	clientList.addAll(list);
//    	view.put(list);
    }

    public void computeAllDistances() {
        for( Station st : stationList) {
            Arrays.fill(st.getDistances(),-1);
            resetChecked();
            //st.computeDistances(st,0);
            st.computeDistances();
        }
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public   void resetChecked() {
        for(Station s: stationList)
            s.setChecked(false);
    }

    public void start() {
        popStation();
        timeGo();
//        popRandomClient();
    }

    public boolean isSpaced(Position p ) {
        for (Station st : stationList) {
            if(st.getPosition().distance(p) < distanceSpacing)
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
