package model;

public class ClientProgress {
	 private double circleProgress = 0;
	 private double triangleProgress = 0;
	 private double squareProgress = 0;
	 private double pentagonProgress = 0;
	 private double starProgress = 0;
	 private double sectorProgress = 0;
	 private double diamondProgress = 0;
	 private double crossProgress = 0;
	 
	 public ClientProgress() {
		 circleProgress = 0;
		 triangleProgress = 0;
		 squareProgress = 0;
		 pentagonProgress = 0;
		 starProgress = 0;
		 sectorProgress = 0;
		 diamondProgress = 0;
		 crossProgress = 0;
	 }
	
	public double getProgress(ShapeType shape) {
		switch(shape) {
			case CIRCLE:
				return this.circleProgress;
			case TRIANGLE:
				return triangleProgress;
			case SQUARE:
				return squareProgress;
			case PENTAGON:
				return pentagonProgress;
			case STAR:
				return starProgress;
			case SECTOR:
				return sectorProgress;
			case DIAMOND:
				return diamondProgress;
			case CROSS:
				return crossProgress;
			default:
				return 0;
		}
	}
	
	public void addProgress(ShapeType shape,double d) {
		switch(shape) {
			case CIRCLE:
				circleProgress += d;
			case TRIANGLE:
				triangleProgress += d;
			case SQUARE:
				squareProgress += d;
			case PENTAGON:
				pentagonProgress += d;
			case STAR:
				starProgress += d;
			case SECTOR:
				sectorProgress += d;
			case DIAMOND:
				diamondProgress += d;
			case CROSS:
				crossProgress += d;
			default:
			
		}
	}
	
	public void minusProgress(ShapeType shape) {
		switch(shape) {
			case CIRCLE:
				circleProgress -= 1;
				if(circleProgress<0) {
					circleProgress = 0;
				}
			case TRIANGLE:
				triangleProgress -= 1;
				if(triangleProgress<0) {
					triangleProgress = 0;
				}
			case SQUARE:
				squareProgress-= 1;
				if(squareProgress<0) {
					squareProgress = 0;
				}
			case PENTAGON:
				pentagonProgress -= 1;
				if(pentagonProgress<0) {
					pentagonProgress = 0;
				}
			case STAR:
				starProgress -= 1;
				if(starProgress<0) {
					starProgress = 0;
				}
			case SECTOR:
				sectorProgress -= 1;
				if(sectorProgress<0) {
					sectorProgress = 0;
				}
			case DIAMOND:
				diamondProgress -= 1;
				if(diamondProgress<0) {
					diamondProgress = 0;
				}
			case CROSS:
				crossProgress -= 1;
				if(crossProgress<0) {
					crossProgress = 0;
				}
			default:
			
		}
	}
}
