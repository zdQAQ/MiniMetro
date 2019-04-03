package model;

import java.util.ArrayList;
import java.util.List;

import javafx.GameView;
import javafx.fxClient;
import javafx.application.Platform;

public class ClientSchedule {
	static ClientProgress circle = new ClientProgress();
	static ClientProgress triangle = new ClientProgress();
	static ClientProgress star = new ClientProgress();
	static ClientProgress cross = new ClientProgress();
	static ClientProgress square = new ClientProgress();
	static ClientProgress pentagon = new ClientProgress();
	static ClientProgress diamond = new ClientProgress();
	static ClientProgress sector = new ClientProgress();

	static int round;
	
	static boolean showClient = false;

	static Game game;
	static GameView gameView;
	// static Clock clock;
	//
	// public void setClock(Clock c) {
	// clock = c;
	// }

	public void setCity(int c) {
		round = c;
	}

	public ClientSchedule(int c, Game g, GameView gv) {
		round = c;
		game = g;
		gameView = gv;
		showClient = false;
	}

	public List<Client> computeProgress(Clock clock) {
		List<Client> resList = new ArrayList<>();
		// System.out.println("computeProgress round: "+round);
		if (round == 0) {
			showClient = true;
		}
		if (round == 0 && showClient) {
			// 第一阶段
			if (clock.getTime() == 10) {
				circle.addProgress(ShapeType.TRIANGLE, 1.5);
				circle.addProgress(ShapeType.CROSS, 0.25);
			} else if (clock.getTime() == 16) {
				circle.addProgress(ShapeType.SQUARE, 0.8);
				triangle.addProgress(ShapeType.CIRCLE, 1.5);
			} else if (clock.getTime() == 18) {
				triangle.addProgress(ShapeType.SQUARE, 0.3);
			} else if (clock.getTime() == 4) {
				cross.addProgress(ShapeType.CIRCLE, 0.5);
			} else if (clock.getTime() == 8) {
				cross.addProgress(ShapeType.SQUARE, 0.2);
				cross.addProgress(ShapeType.DIAMOND, 2);
				star.addProgress(ShapeType.SQUARE, 2);
			}

			if (clock.getDay() == "星期六" || clock.getDay() == "星期天") {
				if (clock.getTime() == 9) {
					triangle.addProgress(ShapeType.CIRCLE, 0.5);
				} else if (clock.getTime() == 12) {
					circle.addProgress(ShapeType.DIAMOND, 0.25);
				} else if (clock.getTime() == 14) {
					square.addProgress(ShapeType.CIRCLE, 0.5);
					square.addProgress(ShapeType.TRIANGLE, 0.5);
					square.addProgress(ShapeType.STAR, 1);
				} else if (clock.getTime() == 15) {
					star.addProgress(ShapeType.SQUARE, 2);
				} else if (clock.getTime() == 13) {
					square.addProgress(ShapeType.STAR, 1);
					diamond.addProgress(ShapeType.SQUARE, 0.5);
				}
			}
		} else if (round == 6 && showClient) {
			// 第四阶段第一回合
			if (clock.getTime() == 10) {
				circle.addProgress(ShapeType.TRIANGLE, 1.5);
				circle.addProgress(ShapeType.CROSS, 0.25);
			} else if (clock.getTime() == 16) {
				circle.addProgress(ShapeType.SQUARE, 0.8);
				triangle.addProgress(ShapeType.CIRCLE, 1.5);
			} else if (clock.getTime() == 18) {
				triangle.addProgress(ShapeType.SQUARE, 0.3);
			} else if (clock.getTime() == 4) {
				cross.addProgress(ShapeType.CIRCLE, 0.5);
			} else if (clock.getTime() == 8) {
				cross.addProgress(ShapeType.SQUARE, 0.2);
				cross.addProgress(ShapeType.DIAMOND, 2);
				star.addProgress(ShapeType.SQUARE, 2);
			}

			if (clock.getDay() == "星期六" || clock.getDay() == "星期天") {
				if (clock.getTime() == 9) {
					triangle.addProgress(ShapeType.CIRCLE, 0.5);
				} else if (clock.getTime() == 12) {
					circle.addProgress(ShapeType.DIAMOND, 0.25);
				} else if (clock.getTime() == 14) {
					square.addProgress(ShapeType.CIRCLE, 0.5);
					square.addProgress(ShapeType.TRIANGLE, 0.5);
					square.addProgress(ShapeType.STAR, 1);
				} else if (clock.getTime() == 15) {
					star.addProgress(ShapeType.SQUARE, 2);
				} else if (clock.getTime() == 13) {
					square.addProgress(ShapeType.STAR, 1);
					diamond.addProgress(ShapeType.SQUARE, 0.5);
				}
			}
		} else if (round == 7 && showClient) {
			// 第四阶段第二回合
			if (clock.getTime() == 10) {
				circle.addProgress(ShapeType.TRIANGLE, 1.5);
				circle.addProgress(ShapeType.CROSS, 0.25);
			} else if (clock.getTime() == 16) {
				circle.addProgress(ShapeType.SQUARE, 0.8);
				triangle.addProgress(ShapeType.CIRCLE, 1.5);
			} else if (clock.getTime() == 18) {
				triangle.addProgress(ShapeType.SQUARE, 0.3);
			} else if (clock.getTime() == 4) {
				cross.addProgress(ShapeType.CIRCLE, 0.5);
			} else if (clock.getTime() == 8) {
				cross.addProgress(ShapeType.SQUARE, 0.2);
				cross.addProgress(ShapeType.DIAMOND, 2);
				star.addProgress(ShapeType.SQUARE, 2);
			}

			if (clock.getDay() == "星期六" || clock.getDay() == "星期天") {
				if (clock.getTime() == 9) {
					triangle.addProgress(ShapeType.CIRCLE, 0.5);
				} else if (clock.getTime() == 12) {
					circle.addProgress(ShapeType.DIAMOND, 0.25);
				} else if (clock.getTime() == 14) {
					square.addProgress(ShapeType.CIRCLE, 0.5);
					square.addProgress(ShapeType.TRIANGLE, 0.5);
					square.addProgress(ShapeType.STAR, 1);
				} else if (clock.getTime() == 15) {
					star.addProgress(ShapeType.SQUARE, 2);
				} else if (clock.getTime() == 13) {
					square.addProgress(ShapeType.STAR, 1);
					diamond.addProgress(ShapeType.SQUARE, 0.5);
				}
			}
		} else if (round == 8 && showClient) {
			// 第四阶段第二回合
			if (clock.getTime() == 10) {
				circle.addProgress(ShapeType.TRIANGLE, 1.5);
				circle.addProgress(ShapeType.CROSS, 0.25);
			} else if (clock.getTime() == 16) {
				circle.addProgress(ShapeType.SQUARE, 0.8);
				triangle.addProgress(ShapeType.CIRCLE, 1.5);
			} else if (clock.getTime() == 18) {
				triangle.addProgress(ShapeType.SQUARE, 0.3);
			} else if (clock.getTime() == 4) {
				cross.addProgress(ShapeType.CIRCLE, 0.5);
			} else if (clock.getTime() == 8) {
				cross.addProgress(ShapeType.SQUARE, 0.2);
				cross.addProgress(ShapeType.DIAMOND, 2);
				star.addProgress(ShapeType.SQUARE, 2);
			}

			if (clock.getDay() == "星期六" || clock.getDay() == "星期天") {
				if (clock.getTime() == 9) {
					triangle.addProgress(ShapeType.CIRCLE, 0.5);
				} else if (clock.getTime() == 12) {
					circle.addProgress(ShapeType.DIAMOND, 0.25);
				} else if (clock.getTime() == 14) {
					square.addProgress(ShapeType.CIRCLE, 0.5);
					square.addProgress(ShapeType.TRIANGLE, 0.5);
					square.addProgress(ShapeType.STAR, 1);
				} else if (clock.getTime() == 15) {
					star.addProgress(ShapeType.SQUARE, 2);
				} else if (clock.getTime() == 13) {
					square.addProgress(ShapeType.STAR, 1);
					diamond.addProgress(ShapeType.SQUARE, 0.5);
				}
			}
		} else if (round == 9 && showClient) {
			// 第四阶段第一回合
			if (clock.getTime() == 10) {
				circle.addProgress(ShapeType.TRIANGLE, 1.5);
				circle.addProgress(ShapeType.CROSS, 0.25);
			} else if (clock.getTime() == 16) {
				circle.addProgress(ShapeType.SQUARE, 0.8);
				triangle.addProgress(ShapeType.CIRCLE, 1.5);
			} else if (clock.getTime() == 18) {
				triangle.addProgress(ShapeType.SQUARE, 0.3);
			} else if (clock.getTime() == 4) {
				cross.addProgress(ShapeType.CIRCLE, 0.5);
			} else if (clock.getTime() == 8) {
				cross.addProgress(ShapeType.SQUARE, 0.2);
				cross.addProgress(ShapeType.DIAMOND, 2);
				star.addProgress(ShapeType.SQUARE, 2);
			}

			if (clock.getDay() == "星期六" || clock.getDay() == "星期天") {
				if (clock.getTime() == 9) {
					triangle.addProgress(ShapeType.CIRCLE, 0.5);
				} else if (clock.getTime() == 12) {
					circle.addProgress(ShapeType.DIAMOND, 0.25);
				} else if (clock.getTime() == 14) {
					square.addProgress(ShapeType.CIRCLE, 0.5);
					square.addProgress(ShapeType.TRIANGLE, 0.5);
					square.addProgress(ShapeType.STAR, 1);
				} else if (clock.getTime() == 15) {
					star.addProgress(ShapeType.SQUARE, 2);
				} else if (clock.getTime() == 13) {
					square.addProgress(ShapeType.STAR, 1);
					diamond.addProgress(ShapeType.SQUARE, 0.5);
				}
			}
		} else if (round == 10 && showClient) {
			// 第四阶段第二回合
			if (clock.getTime() == 10) {
				circle.addProgress(ShapeType.TRIANGLE, 1.5);
				circle.addProgress(ShapeType.CROSS, 0.25);
			} else if (clock.getTime() == 16) {
				circle.addProgress(ShapeType.SQUARE, 0.8);
				triangle.addProgress(ShapeType.CIRCLE, 1.5);
			} else if (clock.getTime() == 18) {
				triangle.addProgress(ShapeType.SQUARE, 0.3);
			} else if (clock.getTime() == 4) {
				cross.addProgress(ShapeType.CIRCLE, 0.5);
			} else if (clock.getTime() == 8) {
				cross.addProgress(ShapeType.SQUARE, 0.2);
				cross.addProgress(ShapeType.DIAMOND, 2);
				star.addProgress(ShapeType.SQUARE, 2);
			}

			if (clock.getDay() == "星期六" || clock.getDay() == "星期天") {
				if (clock.getTime() == 9) {
					triangle.addProgress(ShapeType.CIRCLE, 0.5);
				} else if (clock.getTime() == 12) {
					circle.addProgress(ShapeType.DIAMOND, 0.25);
				} else if (clock.getTime() == 14) {
					square.addProgress(ShapeType.CIRCLE, 0.5);
					square.addProgress(ShapeType.TRIANGLE, 0.5);
					square.addProgress(ShapeType.STAR, 1);
				} else if (clock.getTime() == 15) {
					star.addProgress(ShapeType.SQUARE, 2);
				} else if (clock.getTime() == 13) {
					square.addProgress(ShapeType.STAR, 1);
					diamond.addProgress(ShapeType.SQUARE, 0.5);
				}
			}
		}

		for (ShapeType shape : ShapeType.values()) {
			if (circle.getProgress(shape) >= 1) {
				for (Station station : game.getStationList()) {
					if (station.getType().equals(ShapeType.CIRCLE) && game.includesStationType(shape)) {
						Client c = new Client(station, shape);
						resList.add(c);
						gameView.getClients().put(c, new fxClient(c));
						Platform.runLater(() -> gameView.put(c));
					}
				}
				circle.minusProgress(shape);
			}
			if (triangle.getProgress(shape) >= 1) {
				for (Station station : game.getStationList()) {
					if (station.getType().equals(ShapeType.TRIANGLE) && game.includesStationType(shape)) {
						Client c = new Client(station, shape);
						resList.add(c);
						gameView.getClients().put(c, new fxClient(c));
						Platform.runLater(() -> gameView.put(c));
					}
				}
				triangle.minusProgress(shape);
			}
			if (sector.getProgress(shape) >= 1) {
				for (Station station : game.getStationList()) {
					if (station.getType().equals(ShapeType.SECTOR) && game.includesStationType(shape)) {
						Client c = new Client(station, shape);
						resList.add(c);
						gameView.getClients().put(c, new fxClient(c));
						Platform.runLater(() -> gameView.put(c));
					}
				}
				sector.minusProgress(shape);
			}
			if (square.getProgress(shape) >= 1) {
				for (Station station : game.getStationList()) {
					if (station.getType().equals(ShapeType.SQUARE) && game.includesStationType(shape)) {
						Client c = new Client(station, shape);
						resList.add(c);
						gameView.getClients().put(c, new fxClient(c));
						Platform.runLater(() -> gameView.put(c));
					}
				}
				square.minusProgress(shape);
			}
			if (diamond.getProgress(shape) >= 1) {
				for (Station station : game.getStationList()) {
					if (station.getType().equals(ShapeType.DIAMOND) && game.includesStationType(shape)) {
						Client c = new Client(station, shape);
						resList.add(c);
						gameView.getClients().put(c, new fxClient(c));
						Platform.runLater(() -> gameView.put(c));
					}
				}
				diamond.minusProgress(shape);
			}
			if (pentagon.getProgress(shape) >= 1) {
				for (Station station : game.getStationList()) {
					if (station.getType().equals(ShapeType.PENTAGON) && game.includesStationType(shape)) {
						Client c = new Client(station, shape);
						resList.add(c);
						gameView.getClients().put(c, new fxClient(c));
						Platform.runLater(() -> gameView.put(c));
					}
				}
				pentagon.minusProgress(shape);
			}
			if (cross.getProgress(shape) >= 1) {
				for (Station station : game.getStationList()) {
					if (station.getType().equals(ShapeType.CROSS) && game.includesStationType(shape)) {
						Client c = new Client(station, shape);
						resList.add(c);
						gameView.getClients().put(c, new fxClient(c));
						Platform.runLater(() -> gameView.put(c));
					}
				}
				cross.minusProgress(shape);
			}
			if (star.getProgress(shape) >= 1) {
				for (Station station : game.getStationList()) {
					if (station.getType().equals(ShapeType.STAR) && game.includesStationType(shape)) {
						Client c = new Client(station, shape);
						resList.add(c);
						gameView.getClients().put(c, new fxClient(c));
						Platform.runLater(() -> gameView.put(c));
					}
				}
				star.minusProgress(shape);
			}
		}

		return resList;
	}
}
