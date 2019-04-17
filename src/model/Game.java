package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import utils.ScreenShots;
import javafx.application.Platform;
import javafx.Controller;
import javafx.GameView;
import javafx.Main;

import java.util.*;

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
	private static volatile boolean clockPause = false;
	static final Object pauseLock = new Object();
	static final Object pauseLock1 = new Object();
	private static boolean gift = true;
	private Thread threadRealTimer;
	public Thread threadStation;
	public static Thread threadTime;
	private int times;

	private boolean m50 = false;
	private boolean m120 = false;
	private boolean m240 = false;
	private boolean m400 = false;
	private boolean m600 = false;
	private boolean m800 = false;
	private boolean m1000 = false;
	private boolean m1200 = false;
	private boolean m1400 = false;
	private boolean m1600 = false;
	private boolean m1800 = false;
	private boolean m2000 = false;
	private boolean m2100 = false;
	private boolean m2300 = false;
	private boolean m2400 = false;

	private int startFullTimerTimes = 0;

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
		linesColor.add(Color.GREEN);
		linesColor.add(Color.AQUAMARINE);
		linesColor.add(Color.PURPLE);
		linesColor.add(Color.PINK);
		linesColor.add(Color.YELLOW);
		linesColor.add(Color.BROWN);
		linesColor.add(Color.TEAL);
		linesColor.add(Color.LAVENDER);
		linesColor.add(Color.LIGHTBLUE);
		linesColor.add(Color.MEDIUMSPRINGGREEN);
		linesColor.add(Color.NAVAJOWHITE);
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
		startFullTimerTimes = 0;
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
							event.setFinished(true);
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

	public void timeGo() {
		threadTime = new Thread("timeGo") {
			public void run() {
				while (!isClockPause()) {
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
									if (startFullTimerTimes == 0) {
										// if (view.round == 6) {
										// DbConnector.updateZ("z22", new Date().toString());
										// } else if (view.round == 7) {
										// DbConnector.updateZ("z23", new Date().toString());
										// }
									}
									startFullTimerTimes++;
									station.startFullTimer();
								} else if (station.getClientList().size() < station.getCapacity()
										&& station.getIsFull() == true) {
									station.decreaseFullTimer();
								}
							}

							sleep(833);
							// if ((GameView.round == 8 || GameView.round == 10)
							// 		&& Schedule.getClickTimes() == 0) {
							// 	pauseGame();
							// }
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
			if (transportedClientNb == 50 && !m50) {
				view.setGift(1);
				m50 = true;
			} else if (transportedClientNb == 120 && !m120) {
				view.setGift(0);
				m120 = true;
			} else if (transportedClientNb == 240 && !m240) {
				view.setGift(0);
				m240 = true;
			} else if (transportedClientNb == 350 && !m400) {
				view.setGift(1);
				m400 = true;
			} else if (transportedClientNb == 500 && !m600) {
				view.setGift(0);
				m600 = true;
			} else if (transportedClientNb == 650 && !m800) {
				view.setGift(3);
				m800 = true;
			} else if (transportedClientNb == 800 && !m1000) {
				view.setGift(4);
				m1000 = true;
			} else if (transportedClientNb == 950 && !m1200) {
				view.setGift(1);
				m1200 = true;
			} else if (transportedClientNb == 1000 && !m1400) {
				view.setGift(3);
				m1400 = true;
			} else if (transportedClientNb == 1100 && !m1600) {
				view.setGift(1);
				m1600 = true;
			} else if (transportedClientNb == 1200 && !m1800) {
				view.setGift(3);
				m1800 = true;
			} else if (transportedClientNb == 1300 && !m2000) {
				view.setGift(1);
				m2000 = true;
			} else if (transportedClientNb == 1400 && !m2100) {
				view.setGift(0);
				m2100 = true;
			} else if (transportedClientNb == 1500 && !m2300) {
				view.setGift(1);
				m2300 = true;
			} else if (transportedClientNb == 1600 && !m2400) {
				view.setGift(1);
				m2400 = true;
			}
		} else if (view.getRound() == 8) {
			// 第三阶段第一回合给资源
			// setGift的参数 资源 0表示路线 1车厢 2车头 3隧道 4枢纽
			pauseGame();
			view.setGift(0, 3, true);
			view.setGift(0, 4, 1);
			view.setGift(2, 1, 2);
			view.setGift(0, 1, 3);
			view.setGift(3, 1, 4);
			view.setGift(0, 1, 5);
			view.setGift(2, 1, 6);
			view.setGift(3, 0, 7);
		}
	}

	public void pauseGame() {
		pause = true;
		if (GameView.round == 0) {
			threadStation.interrupt();
		}

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
			// 第一回合车站出现是根据时间定制的，其它的回合没必要通过thread生成
			if (GameView.round == 0) {
				popStation();
			}
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
		// System.out.println(s.getType());
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
		pause = false;
		if (GameView.round == 0) {
			popStation();
		}
		if (GameView.round == 0) {
			timeGo();
		} else if (GameView.round == 8) {
			timeGo();
		} else {
			setClockPause(true);
		}
	}

	public void showTip(int round) {
		// if(round == 1) {
		// return;
		// }
		pauseGame();

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setGraphic(new ImageView(new Image(this.getClass().getResource("/img/tip.png").toString())));
		if (round + 1 == 1) {
			alert.setTitle("第1阶段~");
			alert.setHeaderText("你需要作为见习生探索如何设计高效的铁路网，获得高分");
			alert.setContentText("tips：系统会依据你的累计运送人数追加新的资源；\n如果站点因拥堵而爆炸会扣除一定的累计运送人数；");
		} else if (round + 1 == 2) {
			alert.setTitle("第2阶段第1回合~");
			alert.setHeaderText("为了检验你的见习成果，在第2阶段你需要完成几个小测验");
		} else if (round + 1 == 3) {
			alert.setTitle("第2阶段第2回合~");
			alert.setHeaderText("请使用3条线路3辆机车覆盖所有站点，构筑你认为最高效的铁路网");
			alert.setContentText("Tips：每条线路最多拥有6个站点");
		} else if (round + 1 == 4) {
			alert.setTitle("第2阶段第3回合~");
			alert.setHeaderText("使用3条线路3辆机车覆盖所有站点，构建你认为最高效的铁路网");
			alert.setContentText("注意：每条线路最多穿过6个站点；");
		} else if (round + 1 == 5) {
			alert.setTitle("第2阶段第4回合~");
			alert.setHeaderText("使用3条线路3辆机车覆盖所有站点，构建你认为最高效的铁路网");
			alert.setContentText("注意：每条线路最多穿过6个站点\r\n" + "每个站点最多经过2条线路");
		} else if (round + 1 == 6) {
			alert.setTitle("第2阶段第5回合~");
			alert.setHeaderText("请使用3条线路3辆机车覆盖所有站点，构筑你认为最高效的铁路网");
			alert.setContentText("Tips：每条线路最多拥有6个站点");
		} else if (round + 1 == 7) {
			alert.setTitle("第2阶段第6回合~");
			alert.setHeaderText("正式上岗，现实中的情况比见习复杂很多，你需要自主选择物资后设计线路，物资以二选一的方式提供，共十次选择机会\r\n"
					+ "设计完成后点击激活乘客按钮可运行铁路网，之后你仅有一次暂停修改线路并提交的机会，该回合得分以最终设计的效率高低为准");
			alert.setContentText("注意：本地图共43个站点，最多可拥有6条线路，每条线路最多拥有3辆机车、9站点，每个站点最多可穿过3条线路。");
		} else if (round + 1 == 8) {
			alert.setTitle("第2阶段第7回合~");
			alert.setHeaderText("请使用3条线路5辆机车覆盖所有站点，构筑你认为最高效的铁路网");
			alert.setContentText("注意：每条线路最多穿过9个站点");
		} else if (round + 1 == 9) {
			alert.setTitle("第3阶段~");
			alert.setHeaderText("正式上岗，现实中的情况会比见习复杂很多；\n请自主选择物资后设计线路，物资以2选1的方式提供，共有8次选择机会。");
			alert.setContentText("设计完成以后点击激活乘客的按钮，运营铁路8分钟，该回合得分以最终运营分数为准。\nTips：本地图共30个站点；最多拥有6条线路；每条线路最多穿过9个站点；\n点击记事本可以查看本回合任务，有疑问时不妨去看一看。");
		} else if (round + 1 == 10) {
			alert.setTitle("第4阶段第1回合~");
			alert.setHeaderText("作为1名有经验的铁路规划师， 请观察某位见习生的铁路设计图，指出该设计中可能爆掉的站点序号");
		} else if (round + 1 == 11) {
			alert.setTitle("第4阶段第2回合~");
			alert.setHeaderText("观察线路图指出该设计中可能爆掉的站点，并使用现有资源重新设计该地图给予新手指导。");
			alert.setContentText("Tips：\n1）重新设计线路后请点击激活乘客，并运行铁路网4分钟； \n2) 激活乘客的4min中内你将不能进行任何操作，请在此期间认真观察线路运行情况以便4min运行结束后优化你的线路设计");
		} else if (round + 1 == 12) {
			alert.setTitle("第5阶段~");
			alert.setHeaderText("观察两位见习生的铁路设计图，指出该设计中可能爆掉的车站，并使用现有资源重新设计线路给予他们指导。");
			alert.setContentText("注意！激活乘客后仅有两次暂停修改设计的机会，该回合的得分等于最终设计的效率分。");
		} else {
			alert.setTitle("没有啦~");
			alert.setHeaderText("游戏结束喽！哦耶！");
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
			// 第三回合第一阶段 直接资源
			if (round == 8) {
				// Platform.runLater(() -> setGift());
			} else if (round != 9 && round != 10) {
				resumeGame();
			} else if (round == 12) {
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

	public static boolean isClockPause() {
		return clockPause;
	}

	public static void setClockPause(boolean clockPause) {
		Game.clockPause = clockPause;
	}

}
