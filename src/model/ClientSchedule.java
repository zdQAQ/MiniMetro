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
	}

	public List<Client> computeProgress(Clock clock) {
		List<Client> resList = new ArrayList<>();
		if (round == 0) {
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
		} else if (round == 2) {
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
			// 第二阶段第一回合
		} else if (round == 3) {
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
			// 第二阶段第二回合
			Game.getInventory().setInventory(3, 0, 3, 0, 0);
		} else if (round == 4) {
			// 第二阶段第三回合
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
		} else if (round == 5) {
			// 第二阶段第四回合
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
		} else if (round == 6) {
			// 第二阶段第五回合
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
		} else if (round == 7) {
			// 第三阶段第一回合
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
		} else if (round == 8) {
			// 第三阶段第二回合
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
		} else if (round == 9) {
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
		} else if (round == 10) {
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
					if (station.getType().equals(ShapeType.CIRCLE)) {
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
					if (station.getType().equals(ShapeType.TRIANGLE)) {
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
					if (station.getType().equals(ShapeType.SECTOR)) {
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
					if (station.getType().equals(ShapeType.SQUARE)) {
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
					if (station.getType().equals(ShapeType.DIAMOND)) {
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
					if (station.getType().equals(ShapeType.PENTAGON)) {
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
					if (station.getType().equals(ShapeType.CROSS)) {
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
					if (station.getType().equals(ShapeType.STAR)) {
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
