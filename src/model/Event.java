package model;

public class Event {
	private int timing = 0;
	private ShapeType stationType = null;
	private Position position = null;
	private boolean finished = false;
	
	public Event(int t,ShapeType shape,Position pos) {
		timing = t;
		stationType = shape;
		position = pos;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
}
