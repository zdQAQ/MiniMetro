package model;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
	private List <Event> events = new ArrayList<Event>() ;
	
	public Schedule(String type) {
		if(type.equals("beijing")) {
			events.add(new Event(15000,ShapeType.CIRCLE,new Position(200,200)));
		} else if (type.equals("london")) {
			
		} else if (type.equals("paris")) {
			
		}
	}
	
	public Event findNext(List<Event> events) {
		for (Event event : events) {
			if(!event.isFinished()) {
				return event;
			}
		}
		return null;
	}
}
