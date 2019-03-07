package javafx;

import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import model.Station;

//import static javafx.defaultShapes.getEndLine;

/**
 * Created by KadirF on 18/12/2016.
 */
public class fxEndLine extends Polyline {

    public fxEndLine() {
        super(0,0, 0,50, -15,50, 0,50, 15,50);
        setStrokeWidth(6);
    }

    public fxEndLine(Station modelSt,double middleX,double middleY) {
        super(0,0, 0,20, -12,20, 0,20, 12,20);
        setStrokeWidth(6);
        setStrokeLineCap(StrokeLineCap.ROUND);
        setStrokeLineJoin(StrokeLineJoin.ROUND);

        double x = modelSt.getPosition().getX(), y=modelSt.getPosition().getY();
        double angle = 0;

        if(middleY == y) {
            if(middleX > x) {
                angle = 90;
            }
            else {
                angle = -90;
            }
        }
        else if(middleX == x) {
            if(middleY > y) {
                angle = 180;
            }
            else {
                angle = 0;
            }
        }
        else {
            if(middleX > x && middleY > y) {
                angle =  135;
            }
            if(middleX > x && middleY < y) {
                angle = 45;
            }
            if(middleX < x && middleY > y) {
                angle = -135;
            }
            if(middleX < x && middleY < y) {
                angle = -45;
            }
        }

        for(int i  = 0;i<getPoints().size();i+=2) {
            double tempX = getPoints().get(i) ;
            double tempY = getPoints().get(i+1);
            getPoints().set(i,x+tempX);
            getPoints().set(i+1,y+tempY);
        }
        Rotate rot = new Rotate(angle,getPoints().get(0),getPoints().get(1));
        getTransforms().add(rot);
    }

    public void  correctRotation (Station modelSt, double middleX, double middleY) {
        double x = modelSt.getPosition().getX(), y=modelSt.getPosition().getY();
        double angle = 0;

        System.err.println(modelSt);
        System.err.println(" MIDDLE "+middleX+ " "+middleY);

        if(middleY == y) {
            if(middleX > x) {
                angle = 90;
            }
            else {
                angle = -90;
            }
        }
        else if(middleX == x) {
            if(middleY > y) {
                angle = 180;
            }
            else {
                angle = 0;
            }
        }
        else {
            if(middleX > x && middleY > y) {
                angle =  135;
            }
            if(middleX > x && middleY < y) {
                angle = 45;
            }
            if(middleX < x && middleY > y) {
                angle = -135;
            }
            if(middleX < x && middleY < y) {
                angle = -45;
            }
        }

        this.getTransforms().remove(this.getTransforms().get(0));

        Rotate rot = new Rotate(angle,getPoints().get(0),getPoints().get(1));
        getTransforms().add(rot);
    }
}
