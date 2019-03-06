package model;

import java.util.ArrayList;
import java.util.List;

import javafx.GameView;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeLineJoin;

public class Schedule {
	static List <Event> events = new ArrayList<Event>() ;
	static Group group;
	static GameView gameview;
	static Game game;
	
	public Schedule(String type,Group g,GameView gv,Game game1) {
		group = g;
		gameview = gv;
		game = game1;
		System.out.println(type);
		if(type.equals("beijing")) {
			// stations
			events.add(new Event(0,ShapeType.SQUARE,new Position(250,270)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(340,320)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(250,350)));
			events.add(new Event(15000,ShapeType.CIRCLE,new Position(160,270)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(460,250)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(340,240)));
			events.add(new Event(15000,ShapeType.CIRCLE,new Position(110,235)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(360,390)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(550,430)));
			events.add(new Event(15000,ShapeType.PENTAGON,new Position(240,530)));
			events.add(new Event(0,ShapeType.CIRCLE,new Position(570,300)));
			events.add(new Event(0,ShapeType.TRIANGLE,new Position(550,420)));
			events.add(new Event(0,ShapeType.SQUARE,new Position(110,340)));
			events.add(new Event(15000,ShapeType.CIRCLE,new Position(210,440)));
			events.add(new Event(15000,ShapeType.CIRCLE,new Position(620,220)));
			events.add(new Event(15000,ShapeType.CIRCLE,new Position(570,140)));
			events.add(new Event(15000,ShapeType.CIRCLE,new Position(670,170)));
			events.add(new Event(15000,ShapeType.SQUARE,new Position(60,430)));
			events.add(new Event(15000,ShapeType.CIRCLE,new Position(320,100)));
			events.add(new Event(15000,ShapeType.TRIANGLE,new Position(210,110)));
			events.add(new Event(20000,ShapeType.CIRCLE,new Position(730,410)));
			events.add(new Event(20000,ShapeType.CIRCLE,new Position(640,350)));
			events.add(new Event(20000,ShapeType.TRIANGLE,new Position(410,320)));
			events.add(new Event(20000,ShapeType.SECTOR,new Position(30,210)));
			events.add(new Event(20000,ShapeType.STAR,new Position(800,430)));
			events.add(new Event(20000,ShapeType.CIRCLE,new Position(140,80)));
			events.add(new Event(20000,ShapeType.CROSS,new Position(780,140)));
			events.add(new Event(20000,ShapeType.CIRCLE,new Position(760,300)));
			events.add(new Event(20000,ShapeType.CIRCLE,new Position(760,210)));
			events.add(new Event(20000,ShapeType.SQUARE,new Position(140,140)));
			events.add(new Event(25000,ShapeType.CIRCLE,new Position(200,20)));
			events.add(new Event(25000,ShapeType.TRIANGLE,new Position(810,250)));
			events.add(new Event(25000,ShapeType.CIRCLE,new Position(650,20)));
			events.add(new Event(25000,ShapeType.TRIANGLE,new Position(810,250)));
			events.add(new Event(25000,ShapeType.CIRCLE,new Position(650,20)));
			events.add(new Event(25000,ShapeType.DIAMOND,new Position(620,440)));
			events.add(new Event(25000,ShapeType.CIRCLE,new Position(690,280)));
			events.add(new Event(25000,ShapeType.SQUARE,new Position(300,260)));
			events.add(new Event(30000,ShapeType.TRIANGLE,new Position(370,130)));
			events.add(new Event(30000,ShapeType.TRIANGLE,new Position(410,440)));
			events.add(new Event(30000,ShapeType.CIRCLE,new Position(530,30)));
			events.add(new Event(30000,ShapeType.SQUARE,new Position(390,210)));
			events.add(new Event(30000,ShapeType.CIRCLE,new Position(260,50)));
			events.add(new Event(30000,ShapeType.TRIANGLE,new Position(40,350)));
			events.add(new Event(30000,ShapeType.CIRCLE,new Position(900,420)));
			events.add(new Event(30000,ShapeType.CIRCLE,new Position(320,440)));
			events.add(new Event(35000,ShapeType.SQUARE,new Position(160,340)));
			events.add(new Event(35000,ShapeType.SQUARE,new Position(410,210)));
			events.add(new Event(35000,ShapeType.CIRCLE,new Position(510,340)));
			events.add(new Event(35000,ShapeType.TRIANGLE,new Position(40,150)));
			events.add(new Event(35000,ShapeType.SQUARE,new Position(210,200)));
			events.add(new Event(35000,ShapeType.CIRCLE,new Position(60,270)));
			events.add(new Event(35000,ShapeType.SQUARE,new Position(850,90)));
			events.add(new Event(35000,ShapeType.CIRCLE,new Position(900,210)));
			events.add(new Event(35000,ShapeType.CIRCLE,new Position(760,20)));
			events.add(new Event(35000,ShapeType.CIRCLE,new Position(720,100)));
			events.add(new Event(35000,ShapeType.CIRCLE,new Position(430,160)));
			events.add(new Event(35000,ShapeType.TRIANGLE,new Position(260,110)));
			events.add(new Event(35000,ShapeType.TRIANGLE,new Position(80,110)));
			events.add(new Event(35000,ShapeType.CIRCLE,new Position(480,490)));
			
			// map
			// River :
	        Polyline river = new Polyline(
	        		0,340,
	                20,310,
	                20,470,
	                170,470,
	                170,370,
	                290,370,
	                290,290,
	                420,290,
	                460,320,
	                500,290,
	                550,290,
	                550,370,
	                610,370,
	                640,290,
	                700,350,
	                960,350
	        );

	        Color colorRiver = new Color((double)200/255,(double)230/255,(double)250/255,0.5);
	        river.setStroke(colorRiver);
	        river.setStrokeLineJoin(StrokeLineJoin.ROUND);
	        river.setStrokeWidth(14);

	        Polyline borderRiver = new Polyline(
	                0,340,
	                20,310,
	                20,470,
	                170,470,
	                170,370,
	                290,370,
	                290,290,
	                420,290,
	                460,320,
	                500,290,
	                550,290,
	                550,370,
	                610,370,
	                640,290,
	                700,350,
	                960,350
	        );

	        Color colorBorder = new Color((double)100/255,(double)180/255,(double)220/255,0.5);
	        borderRiver.setStroke(colorBorder);
	        borderRiver.setStrokeLineJoin(StrokeLineJoin.ROUND);
	        borderRiver.setStrokeWidth(16);

	        group.getChildren().add(borderRiver);
	        group.getChildren().add(river);
	        
	        gameview.addRiver(borderRiver);
	        
	        game.start();
	        
		} else if (type.equals("london")) {
			
		} else if (type.equals("paris")) {
			
		}
	}
	
	public Event findNext() {
		for (Event event : events) {
			if(!event.isFinished()) {
				event.setFinished(true);
				return event;
			}
		}
		return null;
	}
}
