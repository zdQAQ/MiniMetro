package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.scene.shape.Shape;

public class Targets {
	public static boolean fourCircles(HashMap<model.Line, ArrayList<Shape>> lines) {
		Iterator iter = lines.entrySet().iterator();
		while(iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    Object key = entry.getKey(); 
		    model.Line line =(model.Line) key;
		    int nums = 0;
		    for(Station station:line.getStationList()) {
		    	if(station.getType().equals(ShapeType.CIRCLE)) {
		    		nums++;
		    	}
		    }
		    if(nums >2) {
		    	return true;
		    }
		}
		return false;
	}
	
	public static int squareLineNums(HashMap<model.Line, ArrayList<Shape>> lines) {
		Iterator iter = lines.entrySet().iterator();
		int nums = 0;
		while(iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    Object key = entry.getKey(); 
		    model.Line line =(model.Line) key;
		    for(Station station:line.getStationList()) {
		    	if(station.getType().equals(ShapeType.SQUARE)) {
		    		nums++;
		    		break;
		    	}
		    }
		}
		return nums;
	}
	
	public static int triangleLineNums(HashMap<model.Line, ArrayList<Shape>> lines) {
		Iterator iter = lines.entrySet().iterator();
		int nums = 0;
		while(iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    Object key = entry.getKey(); 
		    model.Line line =(model.Line) key;
		    for(Station station:line.getStationList()) {
		    	if(station.getType().equals(ShapeType.TRIANGLE)) {
		    		nums++;
		    		break;
		    	}
		    }
		}
		return nums;
	}
	
	public static int crossLineNums(HashMap<model.Line, ArrayList<Shape>> lines) {
		Iterator iter = lines.entrySet().iterator();
		int nums = 0;
		while(iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    Object key = entry.getKey(); 
		    model.Line line =(model.Line) key;
		    for(Station station:line.getStationList()) {
		    	if(station.getType().equals(ShapeType.CROSS)) {
		    		nums++;
		    		break;
		    	}
		    }
		}
		return nums;
	}
	
//	public static int inlcudesThree(HashMap<model.Line, ArrayList<Shape>> lines) {
//		int res = 0;
//		Iterator iter = lines.entrySet().iterator();
//		while(iter.hasNext()) { 
//		    Map.Entry entry = (Map.Entry) iter.next(); 
//		    Object key = entry.getKey(); 
//		    model.Line line =(model.Line) key;
//		    if(line.getStationList().)
//		}
//		return nums;
//	}
}
