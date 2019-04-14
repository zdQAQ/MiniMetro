package javafx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Scale;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Math.abs;

import static model.Position.angle;

public class Controller implements Initializable {

	@FXML
	private Group group;

	@FXML
	private Pane pane;

	double x, y, middleX, middleY, x2, y2;
	int config, config2;
	Polyline drawing = new Polyline(0, 0, 0, 0, 0, 0), drawing2 = new Polyline(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	Polygon drawingTrain = new Polygon(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	Polygon drawingWagon = new Polygon(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	Circle drawingHinge = new Circle(0, 0, 0);

	boolean stationPressed = false, TPressed = false, canRemove = false, isDrawing, wagonPressed = false,
			hingePressed = false, canConstruct = true, trainPressed = false;
	Station currentStation, currentStation2;

	Shape currentT = null, currentLink, currentTrain;
	model.Line currentLine;
	Train modelTrain;

	public static Game game;
	public static GameView gameView;
	public static Schedule schedule;
	public static ClientSchedule clientSchedule;

	public static int mapType;

	private fxClock clock;
	static fxInformations info = new fxInformations();

	public static boolean canDrawLine = true;

	static Group group2;

	@Override // This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		group2 = group;
		group.getChildren().add(drawing);
		group.getChildren().add(drawingTrain);
		group.getChildren().add(drawingHinge);

		group.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (stationPressed && canConstruct) {
					if (!isDrawing) {
						x2 = event.getX();
						y2 = event.getY();
					}
					displayDrawing(x, y, x2, y2);
				} else if (trainPressed) {
					x2 = event.getX();
					y2 = event.getY();
					displayTrainDrawing();
				} else if (wagonPressed) {
					x2 = event.getX();
					y2 = event.getY();
					displayWagonDrawing();
				} else if (hingePressed) {
					x2 = event.getX();
					y2 = event.getY();
					displayHingeDrawing();
				}

			}
		});

		group.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

			}
		});

		group.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stationPressed = false;
				TPressed = false;
				trainPressed = false;
				hingePressed = false;
				group.getChildren().remove(drawing);
				group.getChildren().remove(drawingTrain);
				group.getChildren().remove(drawingWagon);
				group.getChildren().remove(drawingHinge);
				if (currentTrain != null)
					currentTrain.opacityProperty().set(1);
				currentT = null;
				currentLine = null;
				canRemove = true;
				isDrawing = false;
				canConstruct = true;
			}
		});

		// gameView = new GameView(group,this);
		// game = new Game(gameView,3,0,3,3,0);

		// Station s1 = new Station(ShapeType.DIAMOND,new Position(200,200));
		// Station s3 = new Station(ShapeType.PENTAGON,new Position(150,300));
		// Station s4 = new Station(ShapeType.SECTOR,new Position(400,400));
		//
		// game.addToView(s1);
		// game.addToView(s3);
		// game.addToView(s4);

		// gameView.addRiver(borderRiver);

		// game.start();

	}

	public void setRound(int round) {
		setInfo(3, 1, 3, 3, 1);
		gameView = new GameView(group, this, round);
		// gameView.setRound(round);
		game = new Game(gameView, 3, 1, 3, 3, 1);

		schedule = new Schedule(round, group, gameView, game);
		clientSchedule = new ClientSchedule(round, game, gameView);
		setMapType(round);
		game.setSchedule(schedule);
		game.setClientSchedule(clientSchedule);
		game.start();
	}

	public void setRound(int round, Group g) {
		if (round == 2) {
			setInfo(3, 0, 3, 0, 0);
		} else if (round == 3) {
			setInfo(3, 0, 3, 0, 0);
		} else if (round == 4) {
			setInfo(3, 0, 3, 0, 0);
		} else if (round == 5) {
			setInfo(3, 0, 3, 0, 0);
		} else if (round == 6) {
			setInfo(1, 0, 2, 0, 0);
		} else if (round == 7) {
			setInfo(5, 0, 3, 0, 0);
		} else if (round == 8) {
			setInfo(1, 0, 2, 0, 0);
		} else if (round == 9) {
			setInfo(6, 0, 5, 4, 0);
		} else if (round == 10) {
			setInfo(6, 0, 5, 4, 0);
		}
		group = g;
		gameView = new GameView(g, this, round);
		// gameView.setRound(round);
		if (round == 2) {
			game = new Game(gameView, 3, 0, 3, 0, 0);
		} else if (round == 3) {
			game = new Game(gameView, 3, 0, 3, 0, 0);
		} else if (round == 4) {
			game = new Game(gameView, 3, 0, 3, 0, 0);
		} else if (round == 5) {
			game = new Game(gameView, 5, 0, 3, 0, 0);
		} else if (round == 6) {
			game = new Game(gameView, 1, 0, 2, 0, 0);
		} else if (round == 7) {
			game = new Game(gameView, 2, 0, 2, 0, 0);
		} else if (round == 8) {
			game = new Game(gameView, 1, 0, 2, 0, 0);
		} else if (round == 9) {
			game = new Game(gameView, 6, 0, 5, 4, 0);
		} else if (round == 10) {
			game = new Game(gameView, 6, 0, 5, 4, 0);
		}

		clientSchedule = new ClientSchedule(round, game, gameView);
		schedule = new Schedule(round, g, gameView, game);

		setMapType(round);
		game.setSchedule(schedule);

		if (round != 1 && round <= 11) {
			game.setClientSchedule(clientSchedule);
			// game.resumeGame();
			game.start();
			// gameView.seeInfo();
		}

	}

	public void setMapType(int type) {
		mapType = type;
	}

	public int getMapType() {
		return mapType;
	}

	public void addTrainEvent(Shape shape, Train modelTr) {
		shape.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				shape.startFullDrag();
			}
		});

		shape.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				shape.opacityProperty().set(0.5);
				trainPressed = true;
				currentTrain = shape;
				modelTrain = modelTr;
			}
		});
	}

	public void setInfo(int train, int wagon, int line, int tunnel, int station) {
		info = new fxInformations(305, 540, Integer.toString(train), Integer.toString(wagon), Integer.toString(line),
				Integer.toString(tunnel), Integer.toString(station));
		info.getImageTrain().setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				info.getImageTrain().startFullDrag();
			}
		});

		info.getImageTrain().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (Game.getInventory().getTrainNb() != 0)
					trainPressed = true;
			}
		});

		info.getImageWagon().setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				info.getImageTrain().startFullDrag();
			}
		});

		info.getImageWagon().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (Game.getInventory().getWagonNb() != 0)
					wagonPressed = true;
			}
		});

		info.getImageStation().setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				info.getImageStation().startFullDrag();
			}
		});

		info.getImageStation().setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (Game.getInventory().getStationNb() != 0)
					hingePressed = true;
			}
		});
	}

	public fxInformations getInfo() {
		return info;
	}

	public void addLineEvent(Shape shape, Station a, Station b, model.Line line) {

		shape.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.err.println("Line draged " + line);
				System.err.println("Path " + line.getPath());
				if (canDrawLine) {
					shape.startFullDrag();
				} else {
					gameView.alertError("当前状态不可修改线路哦");
				}
			}
		});

		shape.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
			@Override
			public void handle(MouseDragEvent event) {
				if (canDrawLine) {
					if (trainPressed) {
						if (modelTrain != null) {
							modelTrain.changeLine(new Position(event.getX(), event.getY()), line);
							gameView.trainChangeLine(modelTrain);
							if (Game.getPause()) {
								modelTrain.move();
								gameView.get(modelTrain).pause();
							}
							modelTrain = null;
						} else if (modelTrain == null) {
							System.out.println("drop train");
							int index = line.getStationList().indexOf(a);
							modelTrain = new Train(index, line, true);
							line.addTrain(modelTrain);
							gameView.put(modelTrain, line.getStationList().get(index).getPosition());

							modelTrain.move();
							if (Game.getPause())
								gameView.get(modelTrain).pause();
							modelTrain = null;
							Game.getInventory().subTrainNb();
							gameView.updateTrainNb(Game.getInventory().getTrainNb());
						}
						trainPressed = false;
					} else if (wagonPressed) {
						if (line.getTrainList().size() != 0) {
							modelTrain = new Train(0, line, true);
							line.addTrain(modelTrain);
							gameView.putWagon(modelTrain, true, line);
							modelTrain.move();
							if (Game.getPause())
								gameView.get(modelTrain).pause();
							modelTrain = null;
							Game.getInventory().subWagonNb();
							gameView.updateWagonNb(Game.getInventory().getWagonNb());
							wagonPressed = false;
						}
					}
				}

			}
		});

		shape.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (canDrawLine) {
					currentLine = line;
					currentStation = a;
					currentStation2 = b;
					currentLink = shape;
				}
			}
		});

		shape.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (canDrawLine) {
					if (currentStation2 != null)
						gameView.addLink(currentLink);
					currentLine = null;
					currentStation = null;
					currentStation2 = null;
					currentLink = null;
					group.getChildren().remove(drawing2);
				}
			}
		});

		shape.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (canDrawLine) {
					x2 = event.getX();
					y2 = event.getY();
					displayDrawingFromLine(a, b, x2, y2);
				}

			}
		});
	}

	public void addTEvent(Shape shape, Station modelSt, model.Line modelLine, Shape link) {
		shape.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (canDrawLine) {
					shape.startFullDrag();
				} else {
					gameView.alertError("当前状态不可修改线路哦");
				}
			}
		});

		shape.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (canDrawLine) {

					Position pos = modelSt.getPosition();
					x = pos.getX();
					y = pos.getY();
					shape.setStroke(Color.TRANSPARENT);
					stationPressed = true;
					TPressed = true;
					currentStation = modelSt;
					currentT = shape;
					currentLine = modelLine;
					currentLink = link;

				}
			}
		});

		shape.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (canDrawLine) {
					shape.setStroke(currentLine.getColor());
				}
			}
		});
	}

	public void addStationEvent(Shape shape, Station modelSt) {
		shape.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (canDrawLine) {
					System.out.println("station setOnDragDetected");
					shape.startFullDrag();
				} else {
					gameView.alertError("当前状态不可修改线路哦");
				}
			}

		});

		shape.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (canDrawLine) {
					System.out.println("station setOnMousePressed");
					Position pos = modelSt.getPosition();
					x = pos.getX();
					y = pos.getY();
					stationPressed = true;
					currentStation = modelSt;
				}
			}
		});

		shape.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
			@Override
			public void handle(MouseDragEvent event) {
				if (canDrawLine) {
					if (stationPressed) {

						/* Removes the station from the selected line */
						if (modelSt == currentStation && TPressed && canRemove) {

							if (!currentLine.getStationList().contains(modelSt))
								return;

							canConstruct = false;

							boolean firstEnd = gameView.isFirstEnd(currentT, currentLine);

							group.getChildren().remove(currentT);

							Position middle;
							/* The station which will become a end of it's line */
							Station nextStation;

							/* Delete line */
							if (currentLine.getStationList().size() == 2) {

								if (gameView.intersectRiver(currentLink)) {
									game.getInventory().addTunnelNb(1);
									gameView.updateTunnelNb(game.getInventory().getTunnelNb());
								}

								gameView.removeEnds(currentLine);
								gameView.removeLineLink(currentLine, true);

								ArrayList<Client> toDeposit = new ArrayList<Client>();

								Station a = currentLine.getStationList().get(0);
								Station b = currentLine.getStationList().get(1);

								for (Train tr : currentLine.getTrainList()) {
									gameView.removeTrain(tr);
									toDeposit.addAll(tr.getClientList());
									tr.setLine(null);
									game.getInventory().addTrain();
								}
								game.getInventory().addLineNb();
								gameView.updateTrainNb(game.getInventory().getTrainNb());
								gameView.updateLineNb(game.getInventory().getLineNb());

								a.removeLink(b);
								currentLine.removeStation(a);
								currentLine.removeStation(b);
								game.addColor(currentLine.getColor());

								for (Client cl : toDeposit) {
									if (cl.getType() != a.getType()) {
										cl.setStation(a);
										a.addClient(cl);
										game.addToView(cl);
									}
								}
								return;
							}
							boolean inFirst = currentLine.getStationList().indexOf(modelSt) == 0;

							/* Removing the link */
							Shape nextLink = gameView.getNextLineLink(currentLine, inFirst);

							if (gameView.intersectRiver(currentLink)) {
								game.getInventory().addTunnelNb(1);
								gameView.updateTunnelNb(game.getInventory().getTunnelNb());
							}

							System.err.println(
									"NEXT LINK INDEX " + gameView.getLineLinks().get(currentLine).indexOf(nextLink));

							gameView.removeLineLink(currentLine, inFirst);
							nextStation = gameView.getNextStation(currentLine, nextLink);

							if (inFirst)
								middle = currentLine.getPath().get(3);
							else
								middle = currentLine.getPath().get(currentLine.getPath().size() - 4);

							middleX = middle.getX();
							middleY = middle.getY();

							fxEndLine movedEndLine = new fxEndLine(nextStation, middleX, middleY);
							movedEndLine.setStroke(currentLine.getColor());
							group.getChildren().add(1, movedEndLine);

							gameView.setLineEnd(currentLine, movedEndLine, firstEnd);

							addTEvent(movedEndLine, nextStation, currentLine, nextLink);

							if (!currentLine.isLoop()) {
								currentLine.removeStation(modelSt);
							} else {
								currentLine.removeLoop(modelSt, currentLine.getStationList().indexOf(nextStation) == 1);
							}
							modelSt.removeLink(nextStation);

							/* FULL DRAG */
							currentStation = nextStation;
							currentT = movedEndLine;
							currentLink = nextLink;
							return;
						}
						/* Avoids self Linking */
						else if (modelSt == currentStation) {
							return;
						}

						if (canConstruct == false) {
							return;
						}

						// 业务逻辑
						if (TPressed) {
							if ((mapType == 2 || mapType == 5) && (currentLine.getStationList().size() >= 6)) {
								gameView.alertError("该回合下每条线路只能有六个站点哦");
								group.getChildren().remove(drawing);
								if (currentT != null) {
									currentT.setStroke(currentLine.getColor());
								}
								return;
							} else if ((mapType == 7 || mapType == 8) && (currentLine.getStationList().size() >= 9)) {
								gameView.alertError("该回合下每条线路只能有九个站点哦");
								group.getChildren().remove(drawing);
								if (currentT != null) {
									currentT.setStroke(currentLine.getColor());
								}
								return;
							}
						}

						isDrawing = true;
						x2 = modelSt.getPosition().getX();
						y2 = modelSt.getPosition().getY();
						displayDrawing(x, y, x2, y2);

						/* cant link when loops */
						if (currentLine != null && currentLine.getStationList().size() != 0 && currentLine.isLoop()) {
							return;
						}
						/* Avoids that the middle point of a links be inside the shape */
						if (shape.contains(middleX, middleY)) {
							if (angle(x, y, middleX, middleY) != 0 && angle(x, y, middleX, middleY) != 90) {
								middleX = (x2 + x) / 2;
								middleY = (y2 + y) / 2;
							}
						}

						Polyline tempLink = new Polyline(x, y, middleX, middleY, x2, y2);
						tempLink.setStrokeWidth(6);

						/* If the current link isn't intersecting other we can add it */
						if (!gameView.intersects(tempLink)) {
							// check river intersection
							boolean riverCrossing = gameView.intersectRiver(tempLink);
							if (game.getInventory().getTunnelNb() > 0 || !riverCrossing) {

								Shape tunnel;
								if (riverCrossing) {
									game.getInventory().subTunnelNb(1);
									gameView.updateTunnelNb(game.getInventory().getTunnelNb());
								}
								/* Avoids linking 2 station already linked by the same line */
								if (TPressed && !currentLine.addAllowed(modelSt)
										|| (currentLine != null && currentLine.getStationList().size() == 2
												&& currentLine.getStationList().contains(modelSt)
												&& currentLine.getStationList().contains(currentStation))) {
									return;
								}

								fxStation toSubstract = new fxStation(
										new Station(ShapeType.CIRCLE, modelSt.getPosition()));
								// Shape link = Shape.subtract(tempLink,shape);
								Shape link = Shape.subtract(tempLink, toSubstract.shape);
								toSubstract = new fxStation(
										new Station(ShapeType.CIRCLE, currentStation.getPosition()));
								// link = Shape.subtract(link,gameView.get(currentStation).shape);
								link = Shape.subtract(link, toSubstract.shape);

								if (riverCrossing) { /* Making the tunnel */
									link = Shape.subtract(link, gameView.river);
									tempLink.getStrokeDashArray().addAll(12d, 15d);
									tunnel = gameView.getTunnelShape(tempLink);
									link = Shape.union(link, tunnel);
								}

								modelSt.addLink(currentStation);
								game.computeAllDistances();

								fxEndLine endLine = new fxEndLine(modelSt, middleX, middleY);

								Train train = null;

								/* this case we create a new Line */
								if (TPressed == false) {
									if (Game.getInventory().getLineNb() == 0) {
										gameView.alertError("没有可用线路了哦");
										group.getChildren().remove(drawing);
										return;
									} else if (mapType == 8 && gameView.getLineLinks().size() >= 6) {
										gameView.alertError("最多拥有6条线路哦");
										group.getChildren().remove(drawing);
										return;
									}

									fxEndLine endLine2 = new fxEndLine(currentStation, middleX, middleY);
									group.getChildren().add(1, endLine2);
									Color color = game.getColor();
									model.Line created = new model.Line(currentStation, modelSt, color, middleX,
											middleY);// TO
														// DO
														// LINE
									addTEvent(endLine2, currentStation, created, link);
									endLine.setStroke(color);
									endLine2.setStroke(color);
									link.setStroke(color);
									link.setFill(color);
									currentLine = created;
									gameView.createLine(currentLine, endLine, endLine2);

									game.getInventory().subLineNb();

									gameView.updateLineNb(game.getInventory().getLineNb());
									if (game.getInventory().getTrainNb() != 0) {
										game.getInventory().subTrainNb();
										gameView.updateTrainNb(game.getInventory().getTrainNb());

										train = new Train(0, created, true);
										currentLine.addTrain(train);

										gameView.put(train);
									}
								}
								if (!modelSt.getLines().contains(currentLine)) {
									modelSt.addLine(currentLine);
								}
								addTEvent(endLine, modelSt, currentLine, link);
								addLineEvent(link, modelSt, currentStation, currentLine);
								group.getChildren().add(1, endLine);
								group.getChildren().add(1, link);

								int currentStationIndex = 0;
								/* this case we add a station to the current line */
								if (TPressed) {
									link.setFill(currentLine.getColor());
									link.setStroke(currentLine.getColor());
									endLine.setStroke(currentLine.getColor());

									currentStationIndex = currentLine.getStationList().indexOf(currentStation);

									boolean wasLoop = currentLine.isLoop();

									if (currentStationIndex == 0) {
										currentLine.addStation(0, modelSt, middleX, middleY);
									} else {
										currentLine.addStation(modelSt, middleX, middleY);
									}

									/* line becomes a loop */
									if (!wasLoop && currentLine.isLoop()) {
										Station toRemoveLink;
										if (currentLine.getTrainList().get(0).getDirection()) {
											toRemoveLink = currentLine.getStationList()
													.get(currentLine.getStationList().size() - 2);
										} else {
											toRemoveLink = currentLine.getStationList().get(1);
										}
										modelSt.removeLink(toRemoveLink);
										game.computeAllDistances();
									}
								}

								/* To remove the T shape of a line */
								if (currentT != null) {
									group.getChildren().remove(currentT);
								}
								System.err.println(currentLine);
								gameView.addLineLink(currentLine, link, currentStationIndex == 0);

								if (TPressed) {
									boolean b = gameView.getLineLinks().get(currentLine).indexOf(link) == 0;
									gameView.setLineEnd(currentLine, endLine, b);
								}

								if (train != null) {
									train.move();
									if (Game.getPause())
										gameView.get(train).pause();
								}

								/* FULL DRAG */
								currentT = endLine;
								currentStation = modelSt;
								x = modelSt.getPosition().getX();
								y = modelSt.getPosition().getY();
								currentLink = link;
								TPressed = true;
								canRemove = false;

							}
						}
					}
				}
			}
		});

		shape.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
			@Override
			public void handle(MouseDragEvent event) {
				if (canDrawLine) {
					if (hingePressed == true) {
						gameView.stations.get(modelSt).shape.setScaleX(2);
						gameView.stations.get(modelSt).shape.setScaleY(2);
						modelSt.setCapacity(20);
						Game.getInventory().subStationNb();
						getInfo().setNbStation(Game.getInventory().getStationNb());
						hingePressed = false;
						// modelSt.startFullTimer();
					}
					// 拖拽路线加站
					if (currentStation2 != null) {
						// 业务逻辑
						if (mapType == 2 || mapType == 5) {
							if (currentLine.getStationList().size() >= 6) {
								gameView.alertError("该回合下每条线路只能有六个站点哦");
								return;
							}

						} else if (mapType == 7 || mapType == 8) {
							if (currentLine.getStationList().size() >= 9) {
								gameView.alertError("该回合下每条线路只能有九个站点哦");
								return;
							}
						}
						if (currentLine.getStationList().contains(modelSt))
							return;

						displayDrawingFromLine(currentStation, currentStation2, modelSt.getPosition().getX(),
								modelSt.getPosition().getY());
						Shape link1 = new Polyline(drawing2.getPoints().get(0), drawing2.getPoints().get(1),
								drawing2.getPoints().get(2), drawing2.getPoints().get(3), drawing2.getPoints().get(4),
								drawing2.getPoints().get(5));
						Shape link2 = new Polyline(drawing2.getPoints().get(4), drawing2.getPoints().get(5),
								drawing2.getPoints().get(6), drawing2.getPoints().get(7), drawing2.getPoints().get(8),
								drawing2.getPoints().get(9));
						boolean riverLink1 = gameView.intersectRiver(link1),
								riverLink2 = gameView.intersectRiver(link2);

						int riverIntersectCount = 0;
						if (riverLink1)
							++riverIntersectCount;
						if (riverLink2)
							++riverIntersectCount;

						if (Shape
								.subtract(Shape.intersect(link1, link2),
										new fxStation(new Station(ShapeType.SQUARE, modelSt.getPosition())).shape)
								.getBoundsInLocal().getWidth() != -1 || gameView.intersects(drawing2)
								|| (game.getInventory().getTunnelNb() < riverIntersectCount
										&& gameView.intersectRiver(drawing2))) {
							drawing2.setStroke(Color.TRANSPARENT);
							return;
						}

						if (gameView.intersectRiver(currentLink)) {
							game.getInventory().addTunnelNb(1);
							gameView.updateTunnelNb(game.getInventory().getTunnelNb());
						}

						boolean changeOrder = false;
						double middleX, middleY, middleX2, middleY2;
						middleX = drawing2.getPoints().get(2);
						middleY = drawing2.getPoints().get(3);
						middleX2 = drawing2.getPoints().get(6);
						middleY2 = drawing2.getPoints().get(7);

						displayDrawingFromLine(currentStation, currentStation2, modelSt.getPosition().getX(),
								modelSt.getPosition().getY());
						int maxIndex;
						if (currentLine.getStationList().indexOf(currentStation) > currentLine.getStationList()
								.indexOf(currentStation2)) {
							maxIndex = currentLine.getStationList().indexOf(currentStation);
						} else {
							maxIndex = currentLine.getStationList().indexOf(currentStation2);
							if (new Position(drawing2.getPoints().get(0), drawing2.getPoints().get(1))
									.equals(currentStation2.getPosition())) {
								System.err.println("ROTATION BUG TO FIX");
								changeOrder = true;
								if ((currentLine.isLoop() && (currentLine.getStationList().indexOf(currentStation) == 0
										|| currentLine.getStationList().indexOf(currentStation2) == 0))) {
									System.err.println("DEBUGGING LOOP + max index" + maxIndex);
									++maxIndex;
								} else {

									Shape temp = link1;
									link1 = link2;
									link2 = temp;

									Station stemp = currentStation;
									currentStation = currentStation2;
									currentStation2 = stemp;

									double dtemp = middleX;
									middleX = middleX2;
									middleX2 = dtemp;
									dtemp = middleY;
									middleY = middleY2;
									middleY2 = dtemp;
								}
							}
						}

						currentStation.removeLink(currentStation2);
						currentStation.addLink(modelSt);
						currentStation2.addLink(modelSt);
						game.computeAllDistances();

						currentLine.addStationFromLink(maxIndex, modelSt, middleX, middleY, middleX2, middleY2);

						/* make tunnels */
						if (riverLink1) {
							Shape tempLink1 = new Polyline(drawing2.getPoints().get(0), drawing2.getPoints().get(1),
									drawing2.getPoints().get(2), drawing2.getPoints().get(3),
									drawing2.getPoints().get(4), drawing2.getPoints().get(5));
							link1.getStrokeDashArray().addAll(12d, 15d);
							Shape toAdd = Shape.intersect(gameView.river, link1);
							tempLink1 = Shape.subtract(tempLink1, gameView.river);
							tempLink1 = Shape.union(tempLink1, toAdd);
							link1 = tempLink1;
							game.getInventory().subTunnelNb(1);
							gameView.updateTunnelNb(game.getInventory().getTunnelNb());

						}
						if (riverLink2) {
							Shape tempLink2 = new Polyline(drawing2.getPoints().get(4), drawing2.getPoints().get(5),
									drawing2.getPoints().get(6), drawing2.getPoints().get(7),
									drawing2.getPoints().get(8), drawing2.getPoints().get(9));
							link2.getStrokeDashArray().addAll(12d, 15d);
							Shape toAdd = Shape.intersect(gameView.river, link2);
							tempLink2 = Shape.subtract(tempLink2, gameView.river);
							tempLink2 = Shape.union(tempLink2, toAdd);
							link2 = tempLink2;

							game.getInventory().subTunnelNb(1);
							gameView.updateTunnelNb(game.getInventory().getTunnelNb());
						}

						fxStation toSubstract = new fxStation(new Station(ShapeType.SQUARE, modelSt.getPosition()));
						link2 = Shape.subtract(link2, toSubstract.shape);
						link1 = Shape.subtract(link1, toSubstract.shape);
						toSubstract = new fxStation(new Station(ShapeType.SQUARE, currentStation.getPosition()));
						link2 = Shape.subtract(link2, toSubstract.shape);
						toSubstract = new fxStation(new Station(ShapeType.SQUARE, currentStation2.getPosition()));
						link1 = Shape.subtract(link1, toSubstract.shape);

						link1.setStroke(currentLine.getColor());
						link1.setStrokeWidth(6);
						link2.setStroke(currentLine.getColor());
						link2.setStrokeWidth(6);

						group.getChildren().remove(drawing2);
						maxIndex = gameView.getLineLinks().get(currentLine).indexOf(currentLink);

						gameView.addLineLink(currentLine, link2, maxIndex);
						gameView.addLineLink(currentLine, link1, maxIndex);

						group.getChildren().add(1, link1);
						group.getChildren().add(1, link2);

						addLineEvent(link2, currentStation, modelSt, currentLine);
						addLineEvent(link1, modelSt, currentStation2, currentLine);

						gameView.correctRotation(currentLine);
						gameView.removeLineLink(currentLine, currentLink);

						currentStation2 = null;
					}
				}
			}
		});

		shape.setOnMouseDragExited(event -> {
			if (canDrawLine) {
				isDrawing = false;
			}
		});
	}

	// drawing train
	public void displayTrainDrawing() {
		group.getChildren().remove(drawingTrain);
		drawingTrain.setStroke(Color.LIGHTGREY);
		drawingTrain.setFill(Color.LIGHTGREY);

		drawingTrain.setStrokeWidth(6);
		drawingTrain.getPoints().setAll(x2 - 12, y2 - 25, x2, y2 - 30, x2 + 12, y2 - 25, x2 + 12, y2 + 25, x2 - 12,
				y2 + 25, x2 - 12, y2 - 25);
		group.getChildren().add(1, drawingTrain);

	}

	public void displayWagonDrawing() {
		group.getChildren().remove(drawingWagon);
		drawingWagon.setStroke(Color.LIGHTGREY);
		drawingWagon.setFill(Color.LIGHTGREY);

		drawingWagon.setStrokeWidth(6);
		drawingWagon.getPoints().setAll(x2 - 12, y2 - 25, x2 + 12, y2 - 25, x2 + 12, y2 + 25, x2 - 12, y2 + 25, x2 - 12,
				y2 - 25);
		group.getChildren().add(1, drawingWagon);

	}

	public void displayHingeDrawing() {
		group.getChildren().remove(drawingWagon);
		drawingHinge.setStroke(Color.LIGHTGREY);
		drawingHinge.setFill(Color.LIGHTGREY);

		drawingHinge.setCenterX(x2);
		drawingHinge.setCenterY(y2);
		drawingHinge.setRadius(30);
		group.getChildren().add(1, drawingWagon);
	}

	// drawing lines
	public void displayDrawing(double x, double y, double x2, double y2) {
		if (x == x2)
			config = 0;
		if (y == y2)
			config = 1;
		if (is45degree(x, y, x2, y2))
			config = 2;

		verifiateConfig(x2, y2);

		if (config == 0) {
			if (y2 > y) {
				middleX = x;
				middleY = y2 - abs(x2 - x);
			} else {
				middleX = x;
				middleY = y2 + abs(x2 - x);
			}
		} else if (config == 1) {
			middleY = y;
			if (x2 > x)
				middleX = x2 - abs(y2 - y);
			else
				middleX = x2 + abs(y2 - y);
		} else {
			if (sup45degree(x, y, x2, y2)) {
				middleY = y2;
				if (x2 > x)
					middleX = x + abs(y2 - y);
				else
					middleX = x - abs(y2 - y);
			} else {
				middleX = x2;
				if (y2 > y)
					middleY = y + abs(x2 - x);
				else
					middleY = y - abs(x2 - x);
			}
		}
		group.getChildren().remove(drawing);

		if (currentLine != null)
			drawing.setStroke(currentLine.getColor());
		else
			drawing.setStroke(game.getDrawingColor());

		drawing.setStrokeWidth(6);
		drawing.getPoints().setAll(x, y, middleX, middleY, x2, y2);
		group.getChildren().add(1, drawing);
		/*
		 * If the current link is intersecting other line or river without tunnel
		 * available, we make it transparent
		 */
		if (gameView.intersects(drawing)
				|| (game.getInventory().getTunnelNb() == 0 && gameView.intersectRiver(drawing))) {
			drawing.setStroke(Color.TRANSPARENT);
		}
	}

	public void displayDrawingFromLine(Station a, Station b, double x2, double y2) {
		double middleX, middleY, middleX2, middleY2;
		double x = a.getPosition().getX(), y = a.getPosition().getY();

		if (x == x2)
			config = 0;
		if (y == y2)
			config = 1;
		if (is45degree(x, y, x2, y2))
			config = 2;

		verifiateConfig(x2, y2);

		if (config == 0) {
			if (y2 > y) {
				middleX = x;
				middleY = y2 - abs(x2 - x);
			} else {
				middleX = x;
				middleY = y2 + abs(x2 - x);
			}
		} else if (config == 1) {
			middleY = y;
			if (x2 > x)
				middleX = x2 - abs(y2 - y);
			else
				middleX = x2 + abs(y2 - y);
		} else {
			if (sup45degree(x, y, x2, y2)) {
				middleY = y2;
				if (x2 > x)
					middleX = x + abs(y2 - y);
				else
					middleX = x - abs(y2 - y);
			} else {
				middleX = x2;
				if (y2 > y)
					middleY = y + abs(x2 - x);
				else
					middleY = y - abs(x2 - x);
			}
		}

		x = b.getPosition().getX();
		y = b.getPosition().getY();

		if (x == x2)
			config2 = 0;
		if (y == y2)
			config2 = 1;
		if (is45degree(x, y, x2, y2))
			config2 = 2;

		if (config2 == 0 && sup45degree(x, y, x2, y2) || config2 == 1 && abs(x - x2) < abs(y2 - y))
			config2 = 2;

		if (config2 == 0) {
			if (y2 > y) {
				middleX2 = x;
				middleY2 = y2 - abs(x2 - x);
			} else {
				middleX2 = x;
				middleY2 = y2 + abs(x2 - x);
			}
		} else if (config2 == 1) {
			middleY2 = y;
			if (x2 > x)
				middleX2 = x2 - abs(y2 - y);
			else
				middleX2 = x2 + abs(y2 - y);
		} else {
			if (sup45degree(x, y, x2, y2)) {
				middleY2 = y2;
				if (x2 > x)
					middleX2 = x + abs(y2 - y);
				else
					middleX2 = x - abs(y2 - y);
			} else {
				middleX2 = x2;
				if (y2 > y)
					middleY2 = y + abs(x2 - x);
				else
					middleY2 = y - abs(x2 - x);
			}
		}

		group.getChildren().remove(drawing2);

		if (currentLine != null)
			drawing2.setStroke(currentLine.getColor());
		else
			drawing2.setStroke(game.getDrawingColor());

		drawing2.setStrokeWidth(6);
		drawing2.getPoints().setAll(x, y, middleX2, middleY2, x2, y2, middleX, middleY, a.getPosition().getX(),
				a.getPosition().getY());
		group.getChildren().add(1, drawing2);
		/*
		 * If the current link is intersecting other line or river without tunnel
		 * available, we make it transparent
		 */

		gameView.removeLink(currentLink);

		Shape link1 = new Polyline(drawing2.getPoints().get(0), drawing2.getPoints().get(1),
				drawing2.getPoints().get(2), drawing2.getPoints().get(3), drawing2.getPoints().get(4),
				drawing2.getPoints().get(5));
		Shape link2 = new Polyline(drawing2.getPoints().get(4), drawing2.getPoints().get(5),
				drawing2.getPoints().get(6), drawing2.getPoints().get(7), drawing2.getPoints().get(8),
				drawing2.getPoints().get(9));

		if (Shape
				.subtract(Shape.intersect(link1, link2),
						new fxStation(new Station(ShapeType.SQUARE, new Position(x2, y2))).shape)
				.getBoundsInLocal().getWidth() != -1 || gameView.intersects(drawing2)
				|| (game.getInventory().getTunnelNb() == 0 && gameView.intersectRiver(drawing2))) {
			drawing2.setStroke(Color.PAPAYAWHIP);
		}
	}

	private boolean is45degree(double x1, double y1, double x2, double y2) {
		return abs(x1 - x2) == abs(y1 - y2);
	}

	private boolean sup45degree(double x1, double y1, double x2, double y2) {
		return abs(x1 - x2) > abs(y2 - y1);
	}

	private void verifiateConfig(double x2, double y2) {
		if (config == 0 && sup45degree(x, y, x2, y2) || config == 1 && abs(x - x2) < abs(y2 - y))
			config = 2;
	}

}