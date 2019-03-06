package model;

public class Event {
	private int timing = 0;
	private ShapeType stationType = null;
	private Position position = null;
	boolean finished = false;
	
	public Event(int t,ShapeType shape,Position pos) {
		setTiming(t);
		setStationType(shape);
		setPosition(pos);
		finished = false;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean f) {
		finished = f;
	}

	public int getTiming() {
		return timing;
	}

	public void setTiming(int timing) {
		this.timing = timing;
	}

	public ShapeType getStationType() {
		return stationType;
	}

	public void setStationType(ShapeType stationType) {
		this.stationType = stationType;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public String toString() {
        return "event position:"+position.getX()+","+position.getY();
    }
	
}
