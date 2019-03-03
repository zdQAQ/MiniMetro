package model;

public class ClientAtStationEvent {
	static ClientProgress circleProgress = new ClientProgress();
	static ClientProgress triangleProgress = new ClientProgress();
	static ClientProgress squareProgress = new ClientProgress();
	static ClientProgress pentagonProgress = new ClientProgress();
	static ClientProgress starProgress = new ClientProgress();
	static ClientProgress sectorProgress = new ClientProgress();
	static ClientProgress diamondProgress = new ClientProgress();
	static ClientProgress crossProgress = new ClientProgress();
	
	static ShapeType station ;
	
	public ClientAtStationEvent(ShapeType s) {
		station = s;
	}
	
	public ClientProgress getToCircleProgress() {
		return circleProgress;
	}
	
	public ClientProgress getToTriangleProgress() {
		return triangleProgress;
	}
	
	public ClientProgress getToSquareProgress() {
		return squareProgress;
	}
	
	public ClientProgress getToPentagonProgress() {
		return pentagonProgress;
	}
	
	public ClientProgress getToStarProgress() {
		return starProgress;
	}
	
	public ClientProgress getToSectorProgress() {
		return sectorProgress;
	}
	
	public ClientProgress getToDiamondProgress() {
		return diamondProgress;
	}
	
	public ClientProgress getToCrossProgress() {
		return crossProgress;
	}
}
