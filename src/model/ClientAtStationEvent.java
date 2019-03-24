package model;

public class ClientAtStationEvent {
	private ClientProgress circleProgress = new ClientProgress();
	private ClientProgress triangleProgress = new ClientProgress();
	private ClientProgress squareProgress = new ClientProgress();
	private ClientProgress pentagonProgress = new ClientProgress();
	private ClientProgress starProgress = new ClientProgress();
	private ClientProgress sectorProgress = new ClientProgress();
	private ClientProgress diamondProgress = new ClientProgress();
	private ClientProgress crossProgress = new ClientProgress();

	static ShapeType station;

	public ClientAtStationEvent(ShapeType s) {
		station = s;
		circleProgress = new ClientProgress();
		triangleProgress = new ClientProgress();
		squareProgress = new ClientProgress();
		pentagonProgress = new ClientProgress();
		starProgress = new ClientProgress();
		sectorProgress = new ClientProgress();
		diamondProgress = new ClientProgress();
		crossProgress = new ClientProgress();
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
