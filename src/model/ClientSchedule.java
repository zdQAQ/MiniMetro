package model;

import java.util.ArrayList;
import java.util.List;

import javafx.GameView;

public class ClientSchedule {
	static ClientProgress circle = new ClientAtStationEvent(ShapeType.CIRCLE).getToCrossProgress();
	static ClientProgress triangle = new ClientAtStationEvent(ShapeType.TRIANGLE).getToTriangleProgress();
	static ClientProgress star = new ClientAtStationEvent(ShapeType.STAR).getToStarProgress();
	static ClientProgress cross = new ClientAtStationEvent(ShapeType.CROSS).getToCrossProgress();
	static ClientProgress square = new ClientAtStationEvent(ShapeType.SQUARE).getToSquareProgress();
	static ClientProgress pentagon = new ClientAtStationEvent(ShapeType.PENTAGON).getToPentagonProgress();
	static ClientProgress diamond = new ClientAtStationEvent(ShapeType.DIAMOND).getToDiamondProgress();
	static ClientProgress sector = new ClientAtStationEvent(ShapeType.SECTOR).getToSectorProgress();
	
	static String city;
	
	static Game game;
	static GameView gameView;
//	static Clock clock;
//	
//	public void setClock(Clock c) {
//		clock = c;
//	}
	
	public void setCity(String c) {
		city = c;
	}

	public ClientSchedule(String c,Game g,GameView gv) {
		city = c;
		game = g;
		gameView = gv;
	}
	
	public List<Client> computeProgress(Clock clock) {
		List<Client> resList = new ArrayList<>();
		if (city.equals("beijing")) {
			if(clock.getTime() == 10) {
	    		circle.addProgress(ShapeType.CROSS,0.25);
	    	} else if (clock.getTime() == 16) {
	    		circle.addProgress(ShapeType.TRIANGLE, 1);
	    		cross.addProgress(ShapeType.CIRCLE, 0.25);
	    		cross.addProgress(ShapeType.DIAMOND, 0.25);
	    		diamond.addProgress(ShapeType.CROSS, 0.25);
	    		sector.addProgress(ShapeType.CIRCLE, 0.25);
	    	} else if (clock.getTime() == 18) {
	    		triangle.addProgress(ShapeType.CIRCLE, 1);
	    	} else if (clock.getTime() == 4) {
	    		cross.addProgress(ShapeType.CIRCLE, 0.25);
	    	} else if (clock.getTime() == 8) {
	    		cross.addProgress(ShapeType.DIAMOND, 0.25);
	    		diamond.addProgress(ShapeType.CROSS, 0.25);
	    		sector.addProgress(ShapeType.CIRCLE, 0.25);
	    	}
	    	
	    	if(clock.getDay()=="星期六" || clock.getDay()=="星期日") {

				System.out.println("clock.getDay()==\"星期六\":"+(clock.getDay()=="星期六" || clock.getDay()=="星期日"));
	    		if(clock.getTime()==9) {
	    			circle.addProgress(ShapeType.STAR, 0.25);
	    			triangle.addProgress(ShapeType.PENTAGON, 0.25);
	    			pentagon.addProgress(ShapeType.TRIANGLE, 0.25);
	    		} else if (clock.getTime()==11) {
	    			circle.addProgress(ShapeType.SQUARE, 0.75);
	    		} else if (clock.getTime()==12) {
	    			circle.addProgress(ShapeType.DIAMOND, 0.25);
	    			square.addProgress(ShapeType.TRIANGLE, 0.5);
	    			star.addProgress(ShapeType.TRIANGLE, 0.5);
	    		} else if (clock.getTime()==14) {
	    			circle.addProgress(ShapeType.SECTOR, 0.5);
	    			sector.addProgress(ShapeType.SQUARE, 0.1);
	    			pentagon.addProgress(ShapeType.SQUARE, 0.25);
	    		} else if (clock.getTime()==21) {
	    			circle.addProgress(ShapeType.SQUARE, 1);
	    		} else if (clock.getTime()==15) {
	    			triangle.addProgress(ShapeType.CIRCLE, 0.75);
	    			star.addProgress(ShapeType.CIRCLE, 0.25);
	    		} else if (clock.getTime()==13) {
	    			triangle.addProgress(ShapeType.STAR, 0.25);
	    			square.addProgress(ShapeType.STAR, 0.25);
	    			cross.addProgress(ShapeType.DIAMOND, 0.25);
	    			diamond.addProgress(ShapeType.CROSS, 0.25);
	    			sector.addProgress(ShapeType.CIRCLE, 0.25);
	    		} else if (clock.getTime()==17) {
	    			square.addProgress(ShapeType.CIRCLE, 1);
	    		} else if (clock.getTime()==10) {
	    			diamond.addProgress(ShapeType.SQUARE, 0.5);
	    		} else if (clock.getTime()==20) {
	    			sector.addProgress(ShapeType.CIRCLE, 0.4);
	    		}
	    	} 
		}

		for(ShapeType shape:ShapeType.values()){
			if (circle.getProgress(shape)>=1) {
				for(Station station:game.getStationList()) {
					if(station.getType().equals(ShapeType.CIRCLE)) {
						resList.add(new Client(station,shape));
					}
				}
				circle.minusProgress(shape);
			}
			if (triangle.getProgress(shape)>=1) {
				for(Station station:game.getStationList()) {
					if(station.getType().equals(ShapeType.TRIANGLE)) {
						resList.add(new Client(station,shape));
					}
				}
				triangle.minusProgress(shape);
			}
			if (sector.getProgress(shape)>=1) {
				for(Station station:game.getStationList()) {
					if(station.getType().equals(ShapeType.SECTOR)) {
						resList.add(new Client(station,shape));
					}
				}
				sector.minusProgress(shape);
			}
			if (square.getProgress(shape)>=1) {
				for(Station station:game.getStationList()) {
					if(station.getType().equals(ShapeType.SQUARE)) {
						resList.add(new Client(station,shape));
					}
				}
				square.minusProgress(shape);
			}
			if (diamond.getProgress(shape)>=1) {
				for(Station station:game.getStationList()) {
					if(station.getType().equals(ShapeType.DIAMOND)) {
						resList.add(new Client(station,shape));
					}
				}
				diamond.minusProgress(shape);
			}
			if (pentagon.getProgress(shape)>=1) {
				for(Station station:game.getStationList()) {
					if(station.getType().equals(ShapeType.PENTAGON)) {
						resList.add(new Client(station,shape));
					}
				}
				pentagon.minusProgress(shape);
			}
			if (cross.getProgress(shape)>=1) {
				for(Station station:game.getStationList()) {
					if(station.getType().equals(ShapeType.CROSS)) {
						resList.add(new Client(station,shape));
					}
				}
				cross.minusProgress(shape);
			}
			if (star.getProgress(shape)>=1) {
				for(Station station:game.getStationList()) {
					if(station.getType().equals(ShapeType.STAR)) {
						resList.add(new Client(station,shape));
					}
				}
				star.minusProgress(shape);
			}
		}
		
		return resList;
	}
}
